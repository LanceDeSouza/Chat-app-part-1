import java.util.Scanner;

/*================Main Class================
 * Handles the users input and output for registration as well as login
*/
public class Main {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		Login system = new Login();
		
	String firstName, lastName, userName, password, cellPhoneNum;
	boolean allInformationIsValid = false;
		
//============Registration loop============
	while (!allInformationIsValid) {
		System.out.println("============Registration============");
		
		System.out.print("Please enter your first name: ");
		firstName = userInput.nextLine();
		
		System.out.print("Please enter your last name:" );
		lastName = userInput.nextLine();
		
		System.out.print("Please enter the username you would like to use:" );
		userName = userInput.nextLine();
		
		System.out.print("Please enter your password:" );
		password = userInput.nextLine();
		
		System.out.print("Please enter your cell phone number (+27...): ");
		cellPhoneNum = userInput.nextLine();

/*
 *Checks all of the fields
 *If all the information is valid then the user will be registered, if not they will be promted with the errors
*/
		boolean userNameOk = system.checkUserName(userName);
		boolean userPawwordOk = system.checkPassword(password);
		boolean userCellPhoneNumOk = system.checkCellPhoneNum(cellPhoneNum);
		
		if (userNameOk && userPawwordOk && userCellPhoneNumOk) {
			allInformationIsValid = true;
				String registrationResult = system.registerUser(userName, password, cellPhoneNum, firstName, lastName);
				System.out.println(registrationResult); 		
		} else {
			System.out.println("\nPlease correct the following errors: ");
			if (!userNameOk) System.out.println("Your username is not correctly formated, please ensure that it conatains an underscore and no more then 5 characters long.");
			if (!userPawwordOk) System.out.println("Your password is not correctly formated, please ensure that it is at least 8 characters long, contais a capital letter, contains a number and also a special character.");
			if (!userCellPhoneNumOk) System.out.println("Your cell phone number is not correctly formated, please ensure that it contains the internatiol country code +27 followed by 9 other digits.");
		}
	}
	
//============Login loop============
	boolean loggedIn = false;

//This loop will continue until the user has successfully logged in
	while (!loggedIn) {
		System.out.println("============Login============");
		
		System.out.print("Enter your username: ");
		String loginUser = userInput.nextLine();
	
		System.out.print("Enter your password: ");
		String loginPassed = userInput.nextLine();
		
		boolean loginSuccessfull = system.loginUser(loginUser, loginPassed);
		System.out.println(system.returnLoginStatus(loginSuccessfull));
		
//If the login is successful then the user will be logged in and the loop will end
		if (loginSuccessfull) {
			loggedIn = true;
		}		
	}

//============QuickChat Menu (Only accessible after login)============
 		Message messageSystem = new Message();
        System.out.println("\nWelcome to QuickChat.");
        
        // Ask how many messages the user wants to send
        System.out.print("\nHow many messages would you like to send? ");
        int numMessages = userInput.nextInt();
        userInput.nextLine(); // Consume newline
        
        int messagesProcessed = 0;
        boolean quit = false;
        
        while (messagesProcessed < numMessages && !quit) {
            System.out.println("\n========== QuickChat Menu ==========");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages - Coming Soon.");
            System.out.println("3) Quit");
            System.out.print("\nSelect an option (1-3): ");
            
            int choice = userInput.nextInt();
            userInput.nextLine(); // Consume newline
            
            switch (choice) {
                case 1: // Send Message
                    System.out.println("\n--- Message " + (messagesProcessed + 1) + " of " + numMessages + " ---");
                    
                    // Generate Message ID
                    String msgID = messageSystem.generateMessageID();
                    System.out.println("Message ID generated: " + msgID);
                    
                    // Get recipient
                    System.out.print("Enter recipient cell number: ");
                    String recipient = userInput.nextLine();
                    
                    // Validate recipient
                    String cellCheck = messageSystem.checkRecipientCell(recipient);
                    System.out.println(cellCheck);
                    
                    if (!cellCheck.contains("successfully")) {
                        System.out.println("Please try again with a valid number.");
                        break;
                    }
                    
                    // Get message
                    System.out.print("Enter your message (max 250 chars): ");
                    String msgText = userInput.nextLine();
                    
                    // Check message length
                    String lengthCheck = messageSystem.checkMessageLength(msgText);
                    System.out.println(lengthCheck);
                    
                    if (!lengthCheck.contains("ready")) {
                        System.out.println("Please try again with a shorter message.");
                        break;
                    }
                    
                    // Create message hash
                    String msgHash = messageSystem.createMessageHash(msgID, messagesProcessed + 1, msgText);
                    System.out.println("Message Hash: " + msgHash);
                    
                    // Create message object
                    Message currentMsg = new Message(msgID, recipient, msgText, msgHash);
                    
                    // Send/Store/Disregard options
                    System.out.println("\nWhat would you like to do?");
                    System.out.println("1) Send Message");
                    System.out.println("2) Disregard Message");
                    System.out.println("3) Store Message to send later");
                    System.out.print("Select option (1-3): ");
                    
                    int action = userInput.nextInt();
                    userInput.nextLine(); // Consume newline
                    
                    String result = messageSystem.SentMessage(action);
                    System.out.println(result);
                    
                    if (action == 1) { // Send
                        messageSystem.addSentMessage(currentMsg);
                        System.out.println("\n" + currentMsg.displayMessageDetails());
                        messagesProcessed++;
                    } else if (action == 2) { // Disregard
                        System.out.println("Message deleted.");
                        messagesProcessed++;
                    } else if (action == 3) { // Store
                        messageSystem.addSentMessage(currentMsg);
                        System.out.println("Message stored for later.");
                        messagesProcessed++;
                    }
                    break;
                    
                case 2: // Show recent messages
                    System.out.println("Coming Soon.");
                    break;
                    
                case 3: // Quit
                    quit = true;
                    System.out.println("Thank you for using QuickChat. Goodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        // Display all sent messages summary
        System.out.println("\n" + messageSystem.printMessages());
        System.out.println("Total messages sent: " + messageSystem.returnTotalMessagess());
        
        // Store messages to JSON file
        messageSystem.storeMessage("messages.json");

//Close the scanner
	userInput.close();
	}
}