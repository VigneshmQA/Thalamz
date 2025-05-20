package com.qa.thalamz.pages;

import org.testng.Assert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

	
	
	private Page page;

    // Locators for elements
    private String mobileInput = "#mobileNumber";
    private String getOtpButton = "Get OTP";
    private String Otplabel     = "Please enter OTP character";
    private String submitButton = "//button[@type='submit']";
    private String mobileFieldError = "#mobileNumber-helper-text";

    public LoginPage(Page page) {
        this.page = page;
    }

    // Enter Mobile Number
    public void enterMobileNumber(String mobileNumber) {
    	page.locator(mobileInput).fill(mobileNumber);
    }

    // Click Get OTP
    public void clickGetOtp() {
    	page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(getOtpButton)).click();
    }

    // Enter OTP (across 4 fields)
    public void enterOtp(String otp) {
        for (int i = 0; i < otp.length(); i++) {
            String otpLabel = Otplabel + " " + (i + 1); 
            Locator otpField = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName(otpLabel));
            otpField.fill(String.valueOf(otp.charAt(i)));
        }
    }

    // Click Login
    public void clickSubmitIfNeeded() {
        // Example 1: Using URL validation
        if (!page.url().contains("/admin")) {
        	System.out.println(page.url());
            System.out.println("Already in admin Home page , skipping Submit.");
        } else {
        	 System.out.println("Not in Dashboard, clicking Submit...");
            page.click(submitButton);
        }
    }

    // Get the current URL (used to verify successful login)
    public String getCurrentUrl() {
        return page.url();
    }
	
	
    public void FieldError() {
    	
    	if (page.locator(mobileFieldError).isVisible()) {
	        Locator errorText = page.getByText(mobileFieldError);
	        System.out.println("❗ Error Message Displayed: " + errorText.textContent());
	    } else {
	        System.out.println("❗ No error message displayed. Check app validation.");
	        Assert.fail("Mobile Number with alphabets did not show expected validation error.");
	    }
    	
    }
	
	
	
	
	
	
	
	
	
	
	
	
}
