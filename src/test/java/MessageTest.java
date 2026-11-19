/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.prog5121poe.Message;

public class MessageTest {
    
    @Test
    public void testCheckRecipientCell_Correct() {
        Message msg = new Message("+27718693002", "Hi Mike", 0);
        String result = msg.checkRecipientCell();
        assertEquals("Cell phone number successfully captured.", result);
        assertTrue(msg.isValidRecipient());
    }

    @Test
    public void testCheckRecipientCell_Incorrect() {
        Message msg = new Message("08575975889", "Hi Mike", 0);
        String result = msg.checkRecipientCell();
        assertTrue(result.contains("incorrectly formatted"));
        assertFalse(msg.isValidRecipient());
    }

    @Test
    public void testMessageLength_Success() {
        Message msg = new Message("+27718693002", "Hi Mike, Can you join us for dinner tonight", 0);
        assertTrue(msg.checkMessageContent().contains("ready to send"));
    }

    @Test
    public void testMessageLength_Failure() {
        String longText = "A".repeat(260);
        Message msg = new Message("+27718693002", longText, 0);
        assertTrue(msg.checkMessageContent().contains("exceeds"));
    }

    @Test
    public void testCheckMessageID() {
        Message msg = new Message("+27718693002", "Test", 1);
        assertTrue(msg.checkMessageID());
        assertEquals(10, msg.getMessageID().length());
    }

    @Test
    public void testCreateMessageHash() {
        Message msg = new Message("+27718693002", "Hi Mike", 0);
        String hash = msg.createMessageHash();
        assertTrue(hash.contains(":0:"));
    }
    @Test
public void testDisplaySentMessages() {
    Message.clearAll();
    Message.addTestMessage("1234567890", "00:1:HITHANKS", "+27718693002", "Hi Mike, can you join us for dinner tonight", "Developer");
    Message.addTestMessage("0987654321", "01:2:DOESTHIS", "+27718693002", "Hi Keegan, can you join us for dinner tonight?", "Developer");
    String result = Message.displaySentMessages();
    assertTrue(result.contains("Hi Mike"));
    assertTrue(result.contains("+27718693002"));
}

@Test
public void testDisplayLongestMessage() {
    Message.clearAll();
    Message.addTestMessage("1", "00:1:SHORT", "+27718693002", "Hi", "Developer");
    Message.addTestMessage("2", "00:2:LONGESTMESSAGE", "+27834557896", "Where are you? You are late! I have asked you to be on time.", "Developer");
    assertTrue(Message.displayLongestMessage().contains("Where are you?"));
}

@Test
public void testSearchMessageID() {
    Message.clearAll();
    Message.addTestMessage("1234567890", "00:1:HITHANKS", "+27718693002", "Hi Mike, can you join us for dinner tonight", "Developer");
    assertTrue(Message.searchMessageID("1234567890").contains("Hi Mike"));
    assertEquals("Message ID not found.", Message.searchMessageID("999"));
}

@Test
public void testSearchRecipient() {
    Message.clearAll();
    Message.addTestMessage("1", "00:1:HITHANKS", "+27718693002", "Hi Mike, can you join us for dinner tonight", "Developer");
    Message.addTestMessage("2", "00:2:DOESTHIS", "+27718693002", "Hi Keegan, can you join us", "Developer");
    String result = Message.searchRecipient("+27718693002");
    assertTrue(result.contains("Hi Mike"));
    assertTrue(result.contains("Hi Keegan"));
}

@Test
public void testDeleteMessage() {
    Message.clearAll();
    Message.addTestMessage("1", "00:1:HITHANKS", "+27718693002", "Hi Mike, can you join us", "Developer");
    assertTrue(Message.deleteMessage("00:1:HITHANKS").contains("successfully deleted"));
    assertEquals("Message hash not found.", Message.deleteMessage("00:1:HITHANKS"));
}

@Test
public void testDisplayReport() {
    Message.clearAll();
    Message.addTestMessage("1", "00:1:HITHANKS", "+27718693002", "Hi Mike", "Developer");
    assertTrue(Message.displayReport().contains("00:1:HITHANKS"));
}
}