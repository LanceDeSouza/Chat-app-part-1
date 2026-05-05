import org.junit.Test;
import static org.junit.Assert.*;

/*
 * JUnit Test Class for Message
 * Using the given test data as instructed
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

}
