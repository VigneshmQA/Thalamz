package com.qa.tests;

import com.qa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.thalamz.pages.LoginPage;

public class LoginPageTest extends BaseTest {

	@Test
	public void Verify_Login_with_validdetails() {

		page.navigate("http://app.thalam-qa2.s3-website.ap-south-1.amazonaws.com/");
		LoginPage loginPage = new LoginPage(page);
		loginPage.enterMobileNumber("7878787878");
		loginPage.clickGetOtp();
		loginPage.enterOtp("1111");
		loginPage.clickSubmitIfNeeded();
		if (!loginPage.getCurrentUrl().contains("/admin")) {
			System.out.println("✅ User successfully navigated to Admin Homepage");
		} else {
			System.out.println("❗ User is NOT in Admin Homepage. Failing the test.");
			Assert.fail("User is not redirected to Admin Homepage. Current URL: " + loginPage.getCurrentUrl());
		}
	}
	@Test
	public void verifyMobileNumberDoesNotAcceptAlphabets() {
		page.navigate("http://app.thalam-qa2.s3-website.ap-south-1.amazonaws.com/");
	    LoginPage loginPage = new LoginPage(page);

	    // Enter alphabets in Mobile Number field
	    loginPage.enterMobileNumber("abcd1234ef");

	    // Click Get OTP (even if enabled wrongly, let's test behavior)
	    loginPage.clickGetOtp();

	    // Wait and check if error message is shown
	   loginPage.FieldError();
		
		
	}
//	@Test
//	public void verifyEmptyMobileNumberAndOtpFields() {
//
//	}
//	@Test
//	public void verifyMobileNumberFieldMinimumTenDigits() {
//
//	}
//	@Test
//	public void verifyOtpNotSentForInvalidMobileNumber() {
//
//	}

}
