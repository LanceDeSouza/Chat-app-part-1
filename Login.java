import java.util.regex.Pattern;

/*================Login class================
 * Registration and login feature
 * 
 * This class is responsible for handling the users data and for validating their information by checking
 * User name (contains an underscore and has a max of 5 characters)
 * Password (has a minimum of 8 chars, a capital letter, a number and a special character)
 * Cell phone number (South African format: +27 and followed by 9 other digits)
*/
public class Login {
		
//Declaring the private variables
		private String userName;
		private String password;
		private String cellPhoneNum;
		private String firstName;
		private String lastName;

//Empty constructor
		public Login() {
			}

//Constructor with parameters
	public Login (String userName, String password, String cellPhoneNum, String firstName, String lastName) {
		
		this.userName = userName;
		this.password = password;
		this.cellPhoneNum = cellPhoneNum;
		this.firstName = firstName;
		this.lastName = lastName;
	}

    /*================userName Validation================
 * Checking for the userName conditions
*/
	public boolean checkUserName(String userName) {
		return userName.contains("_") && userName.length() <= 5;
	}
}