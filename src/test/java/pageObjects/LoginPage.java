package pageObjects;

import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(id="input-email")
	WebElement emailtextbox;
	
	@FindBy(id="input-password")
	WebElement passwordtextbox;
	
	@FindBy(xpath="//input[value='Login']")
	WebElement loginbutton;
	
	public void setEmail(String email)
	{
		emailtextbox.sendKeys(email);
	}
	
	public void setPassword(String password)
	{
		passwordtextbox.sendKeys(password);
	}

	public void clickLogin()
	{
		loginbutton.click();

		//login button clicked
	}

}
