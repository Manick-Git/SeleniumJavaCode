package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(linkText = "My Account")
	WebElement MyAccountlink;
	
	@FindBy(linkText="Register")
	WebElement Registerlink;
	
	@FindBy(linkText="Login")
	WebElement Loginlink;
	
	public void clickMyAccount()
	{
		MyAccountlink.click();
	}
	
	
	public void clickRegisterlink()
	{
		Registerlink.click();
	}	
	
	public void clickLoginlink()
	{
		Loginlink.click();
	}	


}
