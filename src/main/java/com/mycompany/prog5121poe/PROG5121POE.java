/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prog5121poe;

import java.util.Scanner;

public class PROG5121POE {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== REGISTRATION ===");
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Cell (+27...): ");
        String cell = scanner.nextLine();

        System.out.println(login.registerUser(username, password, cell, firstName, lastName));

        System.out.println("\n=== LOGIN ===");
        System.out.print("Enter Username: ");
        String loginUser = scanner.nextLine();
        System.out.print("Enter Password: ");
        String loginPass = scanner.nextLine();

        System.out.println(login.returnLoginStatus(loginUser, loginPass));
        if (!login.loginUser(loginUser, loginPass)) {
            return;
        }

        int choice = 0;
        while (choice != 3) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("(1) Send Messages");
            System.out.println("(2) Show recently sent messages");
            System.out.println("(3) Quit");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("How many messages do you want to send? ");
                    int num = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 1; i <= num; i++) {
                        System.out.println("\n--- Message " + i + " ---");
                        System.out.print("Enter recipient (+27...): ");
                        String recipient = scanner.nextLine();
                        System.out.print("Enter message (max 250 chars): ");
                        String text = scanner.nextLine();

                        Message msg = new Message(recipient, text, i);

                        if (!msg.isValidRecipient()) {
                            System.out.println(msg.checkRecipientCell());
                            i--;
                            continue;
                        }
                        System.out.println(msg.checkMessageContent());
                        if (text.length() > 250) {
                            i--;
                            continue;
                        }

                        System.out.println("Message ID: " + msg.getMessageID());
                        System.out.println("Message Hash: " + msg.getMessageHash());
                        System.out.println("(1) Send Message");
                        System.out.println("(2) Disregard Message");
                        System.out.println("(3) Store Message");
                        System.out.print("Choose: ");
                        int sendChoice = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println(msg.SentMessage(sendChoice));
                        if (sendChoice == 1) {
                            System.out.println(msg.getDetails());
                        }
                    }
                    System.out.println("Total messages sent: " + Message.getTotal());
                    break;

                case 2:
                    int subChoice = 0;
                    while (subChoice != 7) {
                        System.out.println("\n--- Sent Messages Menu ---");
                        System.out.println("(1) Display all sent messages");
                        System.out.println("(2) Display longest message");
                        System.out.println("(3) Search by Message ID");
                        System.out.println("(4) Search by Recipient");
                        System.out.println("(5) Delete message by Hash");
                        System.out.println("(6) Display Report");
                        System.out.println("(7) Back to Main Menu");
                        System.out.print("Choose: ");
                        subChoice = scanner.nextInt();
                        scanner.nextLine();
                        switch (subChoice) {
                            case 1:
                                System.out.println(Message.displaySentMessages());
                                break;
                            case 2:
                                System.out.println(Message.displayLongestMessage());
                                break;
                            case 3:
                                System.out.print("Enter Message ID: ");
                                System.out.println(Message.searchMessageID(scanner.nextLine()));
                                break;
                            case 4:
                                System.out.print("Enter Recipient (+27...): ");
                                System.out.println(Message.searchRecipient(scanner.nextLine()));
                                break;
                            case 5:
                                System.out.print("Enter Message Hash: ");
                                System.out.println(Message.deleteMessage(scanner.nextLine()));
                                break;
                            case 6:
                                System.out.println(Message.displayReport());
                                break;
                            case 7:
                                System.out.println("Returning...");
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }
}