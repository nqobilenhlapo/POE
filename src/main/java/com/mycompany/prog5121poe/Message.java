/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.prog5121poe;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class Message {
    private String messageID;
    private String recipient;
    private String messageText;
    private int messageNumber;
    private String messageHash;
    private static int totalMessages = 0;
    private ArrayList<Message> sentMessages = new ArrayList<>();

    private static ArrayList<String> messageIDs = new ArrayList<>();
    private static ArrayList<String> messageHashes = new ArrayList<>();
    private static ArrayList<String> recipientsList = new ArrayList<>();
    private static ArrayList<String> messagesList = new ArrayList<>();
    private static ArrayList<String> sendersList = new ArrayList<>();

    public Message(String recipient, String messageText, int messageNumber) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageID = generateID();
        this.messageHash = createMessageHash();
    }

    public String generateID() {
        Random rand = new Random();
        String id = "";
        for (int i = 0; i < 10; i++) {
            id += rand.nextInt(10);
        }
        return id;
    }

    public boolean checkMessageID() {
        return messageID.length() == 10;
    }

    public String checkRecipientCell() {
        if (recipient.length() <= 13 && recipient.startsWith("+")) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
    }

    public boolean isValidRecipient() {
        return recipient.length() <= 13 && recipient.startsWith("+");
    }

    public String checkMessageContent() {
        if (messageText.length() <= 250) {
            return "Message ready to send";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 by " + excess + ", please reduce the size.";
        }
    }

    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String first = words[0];
        String last = words.length > 1 ? words[words.length - 1] : first;
        String firstTwo = messageID.substring(0, 2);
        return (firstTwo + ":" + messageNumber + ":" + (first + last)).toUpperCase();
    }

    public String SentMessage(int choice) {
        if (choice == 1) {
            sentMessages.add(this);
            messageIDs.add(messageID);
            messageHashes.add(messageHash);
            recipientsList.add(recipient);
            messagesList.add(messageText);
            sendersList.add("User");
            totalMessages++;
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            storeMessage();
            return "Message successfully stored.";
        } else {
            return "Invalid choice.";
        }
    }

    public void storeMessage() {
        String json = "{\"messageID\":\"" + messageID + "\",\"hash\":\"" + messageHash + "\",\"recipient\":\"" + recipient + "\",\"message\":\"" + messageText + "\"}";
        try {
            FileWriter fw = new FileWriter("storedMessages.json", true);
            fw.write(json + "\n");
            fw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error storing message: " + e.getMessage());
        }
    }

    public String printMessages() {
        String output = "";
        for (Message m : sentMessages) {
            output += m.getDetails() + "\n";
        }
        return output;
    }

    public String getDetails() {
        return "MessageID: " + messageID + "\nMessage Hash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + messageText;
    }

    public static String displaySentMessages() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sendersList.size(); i++) {
            sb.append("Sender: ").append(sendersList.get(i))
              .append("\nRecipient: ").append(recipientsList.get(i))
              .append("\nMessage: ").append(messagesList.get(i))
              .append("\nMessage Hash: ").append(messageHashes.get(i))
              .append("\nMessage ID: ").append(messageIDs.get(i))
              .append("\n---\n");
        }
        if (sb.length() == 0) {
            return "No messages sent.";
        }
        return sb.toString();
    }

    public static String displayLongestMessage() {
        if (messagesList.isEmpty()) {
            return "No messages sent.";
        }
        int longestIndex = 0;
        for (int i = 1; i < messagesList.size(); i++) {
            if (messagesList.get(i).length() > messagesList.get(longestIndex).length()) {
                longestIndex = i;
            }
        }
        return "Longest Message:\nRecipient: " + recipientsList.get(longestIndex)
                + "\nMessage: " + messagesList.get(longestIndex)
                + "\nMessage Hash: " + messageHashes.get(longestIndex)
                + "\nMessage ID: " + messageIDs.get(longestIndex);
    }

    public static String searchMessageID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(id)) {
                return "Recipient: " + recipientsList.get(i) + "\nMessage: " + messagesList.get(i);
            }
        }
        return "Message ID not found.";
    }

    public static String searchRecipient(String rec) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < recipientsList.size(); i++) {
            if (recipientsList.get(i).equals(rec)) {
                sb.append("Message: ").append(messagesList.get(i))
                  .append("\nHash: ").append(messageHashes.get(i)).append("\n---\n");
            }
        }
        if (sb.length() == 0) {
            return "No messages found for this recipient.";
        }
        return sb.toString();
    }

    public static String deleteMessage(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equalsIgnoreCase(hash)) {
                String deleted = messagesList.get(i);
                messageIDs.remove(i);
                messageHashes.remove(i);
                recipientsList.remove(i);
                messagesList.remove(i);
                sendersList.remove(i);
                totalMessages--;
                return "Message \"" + deleted + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    public static String displayReport() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < messageHashes.size(); i++) {
            sb.append("Message Hash: ").append(messageHashes.get(i))
              .append("\nRecipient: ").append(recipientsList.get(i))
              .append("\nMessage: ").append(messagesList.get(i))
              .append("\n---\n");
        }
        if (sb.length() == 0) {
            return "No messages to display.";
        }
        return sb.toString();
    }

    public static void addTestMessage(String id, String hash, String recipient, String message, String sender) {
        messageIDs.add(id);
        messageHashes.add(hash);
        recipientsList.add(recipient);
        messagesList.add(message);
        sendersList.add(sender);
    }

    public static void clearAll() {
        messageIDs.clear();
        messageHashes.clear();
        recipientsList.clear();
        messagesList.clear();
        sendersList.clear();
        totalMessages = 0;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public static int getTotal() {
        return totalMessages;
    }
}