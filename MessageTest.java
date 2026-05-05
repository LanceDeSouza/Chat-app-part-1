import org.junit.Test;
import static org.junit.Assert.*;

/*
 * JUnit Test Class for Message
 * Using the given test data as instructed on the POE of the assignment
*/

public class MessageTest {
    

    Message message = new Message();
    
    //================Test Data================
    // Message 1: "Hi Mike, can you join us for dinner tonight?"
    // Recipient: +27718693002
    // Message 2: "Hi Keegan, did you receive the payment?"
    // Recipient: 08575975889
    
//================Testing Message Length (AssertEquals)================
    @Test
    public void messageLengthSuccessTest() {
        // Test with valid message (under 250 chars)
        String testMessage = "Hi Mike, can you join us for dinner tonight?";
        String result = message.checkMessageLength(testMessage);
        assertEquals("Message ready to send.", result);
    }
    
    @Test
    public void messageLengthFailureTest() {
        // Test with message over 250 characters
        StringBuilder longMessage = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longMessage.append("a");
        }
        String result = message.checkMessageLength(longMessage.toString());
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", result);
    }

//================Testing Recipient Cell Number (AssertEquals)================
    @Test
    public void recipientCellSuccessTest() {
        // Test data: "+27718693002"
        String result = message.checkRecipientCell("+27718693002");
        assertEquals("Cell phone number successfully captured.", result);
    }
    
    @Test
    public void recipientCellFailureTest() {
        // Test data: "08575975889" (no international code)
        String result = message.checkRecipientCell("08575975889");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }
    
//================Testing Message Hash================
    @Test
    public void messageHashCorrectTest() {
        // Test Case 1 data: "Hi Mike, can you join us for dinner tonight?"
        // Expected hash format: 00:0:HITONIGHT (first 2 of ID, message num, first+last word in caps)
        String messageID = "0012345678"; // Example 10-digit ID
        String messageText = "Hi Mike, can you join us for dinner tonight?";
        int messageNum = 0;
        
        String result = message.createMessageHash(messageID, messageNum, messageText);
        assertEquals("00:0:HITONIGHT", result);
    }
    
    @Test
    public void messageHashSecondMessageTest() {
        // Test with second message
        String messageID = "0098765432";
        String messageText = "Hi Keegan, did you receive the payment?";
        int messageNum = 1;
        
        String result = message.createMessageHash(messageID, messageNum, messageText);
        assertEquals("00:1:HIPAYMENT", result);
    }
    
//================Testing Message ID Generation================
    @Test
    public void messageIDGeneratedTest() {
        String msgID = message.generateMessageID();
        assertNotNull(msgID);
        assertEquals(10, msgID.length());
        assertTrue(message.checkMessageID(msgID));
    }
    
    @Test
    public void messageIDFormatTest() {
        // Test that generated ID is exactly 10 digits
        for (int i = 0; i < 10; i++) {
            String msgID = message.generateMessageID();
            assertTrue(msgID.matches("\\d{10}"));
        }
    }
    
//================Testing SentMessage() Method================
    @Test
    public void sendMessageOptionTest() {
        // Option 1: Send Message
        String result = message.SentMessage(1);
        assertEquals("Message successfully sent.", result);
    }
    
    @Test
    public void disregardMessageOptionTest() {
        // Option 2: Disregard Message
        String result = message.SentMessage(2);
        assertEquals("Press 0 to delete the message.", result);
    }
    
    @Test
    public void storeMessageOptionTest() {
        // Option 3: Store Message
        String result = message.SentMessage(3);
        assertEquals("Message successfully stored.", result);
    }
    
//================Testing checkMessageID()================
    @Test
    public void checkMessageIDValidTest() {
        assertTrue(message.checkMessageID("1234567890")); // Exactly 10 chars
        assertTrue(message.checkMessageID("12345")); // Less than 10 chars
    }
    
    @Test
    public void checkMessageIDInvalidTest() {
        assertFalse(message.checkMessageID("12345678901")); // More than 10 chars
        assertFalse(message.checkMessageID(null)); // Null check
    }
    
//================Integration Test - Full Message Flow================
    @Test
    public void fullMessageFlowTest() {
        // Simulate sending a message with Test Data 1
        String recipient = "+27718693002";
        String msgText = "Hi Mike, can you join us for dinner tonight?";
        
        // Check recipient
        assertEquals("Cell phone number successfully captured.", message.checkRecipientCell(recipient));
        
        // Check message length
        assertEquals("Message ready to send.", message.checkMessageLength(msgText));
        
        // Generate ID and create hash
        String msgID = message.generateMessageID();
        assertTrue(message.checkMessageID(msgID));
        
        String msgHash = message.createMessageHash(msgID, 0, msgText);
        assertNotNull(msgHash);
        
        // Create and add message
        Message msg = new Message(msgID, recipient, msgText, msgHash);
        message.addSentMessage(msg);
        
        // Verify total
        assertEquals(1, message.returnTotalMessagess());
        
        // Verify message details
        assertTrue(message.printMessages().contains("Hi Mike"));
    }
    
//================Testing Total Messages Counter================
    @Test
    public void returnTotalMessagessTest() {
        Message msgSystem = new Message();
        assertEquals(0, msgSystem.returnTotalMessagess());
        
        // Add a message
        Message msg1 = new Message("1234567890", "+27123456789", "Test message one", "12:0:TESTONE");
        msgSystem.addSentMessage(msg1);
        assertEquals(1, msgSystem.returnTotalMessagess());
        
        // Add another message
        Message msg2 = new Message("0987654321", "+27987654321", "Test message two", "09:1:TESTTWO");
        msgSystem.addSentMessage(msg2);
        assertEquals(2, msgSystem.returnTotalMessagess());
    }
    
//================Testing Print Messages================
    @Test
    public void printMessagesEmptyTest() {
        Message msgSystem = new Message();
        assertEquals("No messages sent yet.", msgSystem.printMessages());
    }
    
    @Test
    public void printMessagesWithDataTest() {
        Message msgSystem = new Message();
        Message msg = new Message("1234567890", "+27123456789", "Hello World", "12:0:HELLOWORLD");
        msgSystem.addSentMessage(msg);
        
        String result = msgSystem.printMessages();
        assertTrue(result.contains("Message ID: 1234567890"));
        assertTrue(result.contains("Recipient: +27123456789"));
        assertTrue(result.contains("Message: Hello World"));
    }
}
