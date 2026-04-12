import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * JUnit Test Class for Login
 * Using the giving test data to work with like instructed
*/

public class LoginTest {

	Login login = new Login();
	
//================Testing user name (Assert true/false)================
@Test
	public void userNameCorrectlyFormatedTest() {
	//Test data: "kyl_1"
		assertTrue(login.checkUserName("kyl_1"));
	}

@Test
	public void userNameIncorrectlyFormatedTest() {
	//Test data: "kyle!!!!!!!"
		assertFalse(login.checkUserName("kyle!!!!!!!"));
}

//================Testing password (Assert true/false)================
@Test
	public void userPasswordCorrectlyFormatedTest() {
	//Test data: "Ch&&sec@ke99!"
		assertTrue(login.checkPassword("Ch&&sec@ke99!"));
	}
	
@Test
	public void userPasswordIncorrectlyFormatedTest() {
	//Test data: "password"
		assertFalse(login.checkPassword("password"));
}

//================Testing cell phone number (Assert true/false)================
@Test
	public void userCellPhoneNumberCorrectlyFormatedTest() {
	//Test data: "+27838968976"
		assertTrue(login.checkCellPhoneNum("+27838968976"));
	}
	
@Test
	public void userCellPhoneNumberIncorrectlyFormatedTest() {
	//Test data: "08966553"
		assertFalse(login.checkCellPhoneNum("08966553"));
}
	
//================Login  Tests (Assert true/false)================
@Test 
	public void loginSuccessFullTest() {
	//Register first then login
		login.registerUser("kyl_1", "Ch&&sec@ke99", "+27838968976", "Kyle", "Williams");
		
		assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
		assertFalse(login.loginUser("wrong", "wrong"));
}

/*================Assert equals Tests (Assert true/false)================
 * This tests the full registration flow
*/

@Test
	public void userNameSuccessMassageTest() {
	//Test data: "kyl_1"
		String result = login.registerUser("kyl_1", "Ch&&sec@ke99", "+27838968976", "Kyle", "Williams");
		assertEquals("You have succeessfully registerd.", result);
	}

@Test
	public void userNameFailureMassageTest() {
	//Test data: "kyle!!!!!!!"
		String result = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99", "+27838968976", "Kyle", "Williams");
		assertEquals("Your username is not correctly formated, please ensure that it conatains an underscore and no more then 5 characters long.", result);
	}

@Test
	public void passwordSuccessMassageTest() {
	//Test data: "Ch&&sec@ke99"
		String result = login.registerUser("kyl_1", "Ch&&sec@ke99", "+27838968976", "Kyle", "Williams");
		assertEquals("You have succeessfully registerd.", result);
	}

@Test
	public void passwordFailureMassageTest() {
	//Test data: "password"
		String result = login.registerUser("kyl_1", "password", "+27838968976", "Kyle", "Williams");
		assertEquals("Your password is not correctly formated, please ensure that it is at least 8 characters long, contais a capital letter, contains a number and also a special character.", result);
	}

@Test
	public void ceallPhoneNumberSuccessMassageTest() {
	//Test data: "+27838968976"
		String result = login.registerUser("kyl_1", "Ch&&sec@ke99", "+27838968976", "Kyle", "Williams");
		assertEquals("You have succeessfully registerd.", result);
	}

@Test
	public void ceallPhoneNumberFailureMassageTest() {
	//Test data: "08966553"
		String result = login.registerUser("kyl_1", "Ch&&sec@ke99", "08966553", "Kyle", "Williams");
		assertEquals("Your cell phone number is not correctly formated, please ensure that it contains the internatiol country code +27 followed by the other numbers of your phone number which shoul be no more then ten digits.", result);
	}

@Test
	public void loginSuccessMassageTest() {
		login.registerUser("kyl_1", "Ch&&sec@ke99", "+27838968976", "Kyle", "Williams");
		String status = login.returnLoginStatus(true);
		assertEquals("Welcome Kyle Williams you have successfully logged into your account.", status);
}

@Test
	public void loginFailureMassageTest() {
		login.registerUser("kyl_1", "Ch&&sec@ke99", "08966553", "Kyle", "Williams");
		String status = login.returnLoginStatus(false);
		assertEquals("Your password or username is incorrect please try again.", status);
}
}