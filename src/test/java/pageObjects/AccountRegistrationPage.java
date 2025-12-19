package pageObjects;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver)

	{
		super(driver);
	}

	@FindBy(xpath= "//input[@id='input-firstname']")
	WebElement firstnametextbox;
	
	@FindBy(xpath= "//input[@id='input-lastname']")
	WebElement lastnametextbox;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement emailtextbox;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement passwordtextbox;

	@FindBy(xpath= "//button[normalize-space()='Continue']")
	WebElement continuebutton;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement agreechkbox;
	
	@FindBy(xpath = "//input[@id='input-newsletter']")
	WebElement subscribechkbox;
	
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;	
	
	public void setFirstName(String fname)
	{
		firstnametextbox.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		lastnametextbox.sendKeys(lname);
	}

	public void setEmail(String email)
	{
		emailtextbox.sendKeys(email);
	}
	
	public void setPassword(String password)
	{
		passwordtextbox.sendKeys(password);
	}
	
	public void selectContinueButton()
	{
		continuebutton.click();
	}
	
	public void selectSubscribe()
	{
		subscribechkbox.click();
	}
	
	public void selectAgree()
	{
		agreechkbox.click();
	}	
	
	public String getConfirmationMsg()
	{
		try {
			return (msgConfirmation.getText());
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
		
	}
	

}



