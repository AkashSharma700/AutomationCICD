package TestCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.LandingPage;
import TestComponents.BaseTest;
import TestComponents.Retry;

public class ErrorValidation extends BaseTest {

	@Test(dataProvider = "loginData", retryAnalyzer = Retry.class)
	private void loginValidation(String email, String password, String expErrorMessage) {
		LandingPage lp = new LandingPage(driver);
		lp.login(email, password);
		Assert.assertTrue(lp.getToastMessage().equalsIgnoreCase(expErrorMessage));
	}

	@DataProvider
	private String[][] loginData() {

		String[][] dataSet = new String[1][3];

		dataSet[0][0] = "abc@gmail.com";
		dataSet[0][1] = "Pass@123";
		dataSet[0][2] = "Incorrect email or password.";

//		// Test Case 2: Empty email and password
//		dataSet[1][0] = "xyz@abc.com";
//		dataSet[1][1] = "ThisISok";
//		dataSet[1][2] = "Incorrect email password.";
//
//		// Test Case 3: Valid email but empty password
//		dataSet[2][0] = "abc@gmail.com";
//		dataSet[2][1] = "Dummy123";
//		dataSet[2][2] = "Incorrect email or password.";
//
//		// Test Case 4: Invalid email format
//		dataSet[3][0] = "abc@gmail.com";
//		dataSet[3][1] = "Pass@123";
//		dataSet[3][2] = "Incorrect email or password.";

		return dataSet;
	}

}
