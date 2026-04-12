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
/*================Registering the user================
 * Checking that all of the conditions for registration are met
*/
	public String registerUser (String userName, String password, String cellPhoneNum, String firstName, String lastName) {
		
//Checking userName
	if (!checkUserName(userName)) {
		return "Your username is not correctly formated, please ensure that it conatains an underscore and no more then 5 characters long.";
	}
	
//Checking password
	if (!checkPassword(password)) {
		return "Your password is not correctly formated, please ensure that it is at least 8 characters long, contais a capital letter, contains a number and also a special character.";
	}
	
//Checking cellPhoneNum
	if (!checkCellPhoneNum(cellPhoneNum)) {
		return "Your cell phone number is not correctly formated, please ensure that it contains the internatiol country code +27 followed by 9 other digits.";
	}
	
//Save the users details
	this.userName = userName;
	this.password = password;
	this.cellPhoneNum = cellPhoneNum;
	this.firstName = firstName;
	this.lastName = lastName;
	
	return "You have successfully registerd";
	}
	
/*================Login================
 * Checking that the login information of the user is correct 
*/
	public boolean loginUser(String userName, String password) {
		return  this.userName != null &&
				this.password != null &&
				this.userName.equals(userName) &&
				this.password.equals(password);
	}

/*================Login status================
 *Checking that the login information of the user is correct 
*/
	public String returnLoginStatus(boolean loginSuccessfull) {
		if (loginSuccessfull) {
			return "Welcome " + firstName + " " + lastName + " you have successfully logged into your account.";
		} else {
			return "Your password or username is incorrect please try again.";
		}
	}
}