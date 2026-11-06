/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.prog5121poe;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    private String messageID;
    private String recipient;
    private String messageText;
    private int messageNumber;
    private String messageHash;
    private static int totalMessages = 0;
    private static List<Message> sentMessages = new ArrayList<>();

    public Message(String recipient, String text, int number) {
        this.recipient = recipient;
        this.messageText = text;
        this.messageNumber = number;
        this.messageID = generateID();
        this.messageHash = createMessageHash();
    }

    private String generateID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if(recipient.length() <= 13 && recipient.startsWith("+")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public boolean isValidRecipient() {
        return recipient.length() <= 13 && recipient.startsWith("+");
    }

    public String checkMessageContent() {
        if(messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 by " + excess + ", please reduce the size.";
        }
    }
    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 1 ? words[words.length-1] : first;
        String firstTwo = messageID.substring(0,2);
        return (firstTwo + ":" + messageNumber + ":" + (first+last)).toUpperCase();
    }

    public String SentMessage(int choice) {
        if(choice == 1) {
            sentMessages.add(this);
            totalMessages++;
            return "Message successfully sent.";
        } else if(choice == 2) {
            return "Press 0 to delete the message.";
        } else if(choice == 3) {
            storeMessage();
            return "Message successfully stored.";
            } else {
            return "Invalid choice.";
        }
    }

    public void storeMessage() {
        String json = "{\"messageID\":\""+messageID+"\",\"hash\":\""+messageHash+"\",\"recipient\":\""+recipient+"\",\"message\":\""+messageText+"\"}";
        try {
            FileWriter fw = new FileWriter("storedMessages.json", true);
            fw.write(json + "\n");
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String printMessages() {
        String out = "";
        for(Message m : sentMessages) {
            out += m.messageID + " " + m.messageHash + " " + m.recipient + " " + m.messageText + "\n";
        }
        return out;
    }
    public String getDetails() {
        return "MessageID: " + messageID + "\nMessage Hash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + messageText;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public static int getTotal() {
        return totalMessages;
    }
}