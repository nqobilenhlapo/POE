/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import org.junit.Test;
import static org.junit.Assert.*;
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
}