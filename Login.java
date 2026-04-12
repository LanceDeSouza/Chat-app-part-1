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

/*================password Validation================
 * Checking for the password conditions
 * return true if valid, return false if otherwise
*/
	public boolean checkPassword(String password) {
		if (password.length() < 8) return false;
	
	boolean hasCapital = false;
	boolean hasNumber = false;
	boolean hasSpecailCharacter = false;
	
	for (char i : password.toCharArray()) {
		if (Character.isUpperCase(i)) hasCapital = true;
		if (Character.isDigit(i)) hasNumber = true;
		if (!Character.isLetterOrDigit(i)) hasSpecailCharacter = true;
	}
	
	return hasCapital && hasNumber && hasSpecailCharacter;
	}

/*================cellPhoneNum Validation================
 * Checking for the cellPhoneNum conditions
 * SA cell phone number format is +27 followed by 9 digits adding up to a total of 12 chars including the + 
*/
	public boolean checkCellPhoneNum(String number) {
		String regex = "^\\+27\\d{9}$";
		return Pattern.matches(regex, number);
	}
}