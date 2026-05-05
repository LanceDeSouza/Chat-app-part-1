import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.FileWriter;
import java.io.IOException;

/*================Message class================
 *Handles the massage sending 
 *
 * This class is responsible for:
 * - Generating unique message IDs
 * - Storing messages in a JSON file
 * -Tracking total massages sent
 * - Validating message content and recipient information
*/

public class Message {

    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;
    private int numMessagesSent;
    private List<Message> sentMessages;
    private static final int maxMessageLength = 250;


//Empty constructor
    public Message() {
        this.sentMessages = new ArrayList<>();
        this.numMessagesSent = 0;
 }

//Constructor with parameters
    public Message(String messageID, String message, String recipient, String messageHash) {
        this.messageID = messageID;
        this.message = message;
        this.recipient = recipient;
        this.messageHash = messageHash;
        this.numMessagesSent = 0;
    }

/*================checkMessageID()================
 * Ensures that the messageID has a max of ten characters
*/
    private boolean checkMessageID(String id) {
        return id != null && id.length() <= 10;
    }

/*================checkRecipientCell()================
 * Ensures that the recipient cell phone number is is a max of ten characters 
 * Checks if it starts with a code (the international code +27 for South Africa)
*/
    public String checkRecipientCell(String cellNumber) {
        // Check if it starts with + and contains international code
        boolean hasInternationalCode = cellNumber.startsWith("+") || cellNumber.startsWith("00");
        boolean isValidLength = cellNumber.length() <= 15; // Allow for +27 and 9 digits = 12 chars
        
        if (hasInternationalCode && isValidLength) {
            return "Cell phone number successfully captured.";
        } else {
            return "The cell phone number is not correctly formatted or or it may not contain an international code. Please correct the cell phone number and try again.";
        }
    }

/*================checkMessageLength()================
 * Ensures that the message does not contain more than 250 characters
*/
    public String checkMessageLength(String msg) {
        if (msg.length() <= maxMessageLength) {
            return "Message ready to send.";
        } else {
            int excess = msg.length() - maxMessageLength;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }





}
