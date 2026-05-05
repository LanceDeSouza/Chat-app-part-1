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

/*================createMessageHash()================
 * Creates and returns the Message Hash
 * It formats the first two numbers of message ID + colon + message number + colon + first and last words in caps (00:0:HELLO WORLD)
*/
    public String createMessageHash(String messageID, int messageNum, String messageText) {
        String firstTwoNums = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        
        return firstTwoNums + ":" + messageNum + ":" + firstWord + lastWord;
    }

/*================SentMessage()================
 * Allows the user to choose if they want to send the message, store or delete the message
 * It also returns appropriate status message
*/
    public String SentMessage(int choice) {
        switch(choice) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

/*================printMessages()================
 * Returns all the messages that the user sent while the program is still running
*/
    public String printMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages sent yet.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== All Sent Messages ==========\n");
        for (Message msg : sentMessages) {
            sb.append("Message ID: ").append(msg.messageID).append("\n");
            sb.append("Message Hash: ").append(msg.messageHash).append("\n");
            sb.append("Recipient: ").append(msg.recipient).append("\n");
            sb.append("Message: ").append(msg.message).append("\n");
            sb.append("----------------------------------------\n");
        }
        return sb.toString();
    }

/*================returnTotalMessagess()================
 * Returns the total number of messages that were sent by the user
*/
    public int returnTotalMessagess() {
        return numMessagesSent;
    }





}
