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

//Checks all of the fields
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
	
	while (!loggedIn) {
		System.out.println("============Login============");
		
		System.out.print("Enter your username: ");
		String loginUser = userInput.nextLine();
	
		System.out.print("Enter your password: ");
		String loginPassed = userInput.nextLine();
		
		boolean loginSuccessfull = system.loginUser(loginUser, loginPassed);
		System.out.println(system.returnLoginStatus(loginSuccessfull));
		
		if (loginSuccessfull) {
			loggedIn = true;
	
		}
			userInput.close();
	}
	}
}