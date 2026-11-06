/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prog5121poe;

import java.util.Scanner;

public class PROG5121POE {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== Registration ===");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter South African cell phone number: ");
        String cellPhoneNumber = scanner.nextLine();

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        String registrationResult = login.registerUser(username, password, cellPhoneNumber, firstName, lastName);
        System.out.println(registrationResult);

        // Only proceed to login if registration was successful
        if (registrationResult.equals("Registration successful.")) {
            System.out.println("\n=== Login ===");

            System.out.print("Enter username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String loginPassword = scanner.nextLine();

            String loginStatus = login.returnLoginStatus(loginUsername, loginPassword);
System.out.println(loginStatus);

 if (loginStatus.contains("Welcome")) {

            System.out.println("\nWelcome to QuickChat.");

            System.out.println("(1) Send messages");
            System.out.println("(2) Show recently sent messages");
            System.out.println("(3) Quit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch(option) {
                case 1:
                    System.out.print("How many messages would you like to send? ");
                    int numMessages = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Number of messages to send: " + numMessages);
                    for(int i=0; i<numMessages; i++) {
                        System.out.println("\n--- Message " + (i+1) + " ---");

                        System.out.print("Enter recipient (with +code e.g. +27718693002): ");
                        String recipient = scanner.nextLine();

                        System.out.print("Enter message: ");
                        String text = scanner.nextLine();

                        Message msg = new Message(recipient, text, i);
                        if(!msg.isValidRecipient()) {
                            System.out.println(msg.checkRecipientCell());
                            i--;
                            continue;
                        } else {
                            System.out.println(msg.checkRecipientCell());
                        }

                        if(text.length() > 250) {
                            System.out.println(msg.checkMessageContent());
                            i--;
                            continue;
                        } else {
                            System.out.println(msg.checkMessageContent());
                        }

                        System.out.println("Message ID generated: " + msg.getMessageID());
                        System.out.println("Message Hash: " + msg.getMessageHash());
                        System.out.println("(1) Send Message\n(2) Disregard Message\n(3) Store Message");
                        System.out.print("Choose: ");
                        int sendChoice = scanner.nextInt();
                        scanner.nextLine();

                        String result = msg.SentMessage(sendChoice);
                        System.out.println(result);

                        if(sendChoice == 1) {
                            System.out.println("\n" + msg.getDetails());
                            System.out.println("Total sent: " + Message.getTotal() + "\n");
                            } else if(sendChoice == 2) {
                            System.out.print("Press 0 to delete: \n");
                            String del = scanner.nextLine();
                            if(del.equals("0")) {
                                System.out.println("Message deleted.\n");
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Coming Soon.");
                    break;
                case 3:
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
}
            }