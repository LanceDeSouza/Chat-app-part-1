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
    private static final int MAX_MESSAGE_LENGTH = 250;


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

}
