package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test
	public void verify_account_registration() throws InterruptedException
	{
		
		logger.info("**************starting  TC001_AccountRegistrationTest ************");
		
		try {		
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		
		logger.info("**************clicked on MyAccount ************");
		
		//Thread.sleep(3000);
		
		hp.clickRegisterlink();
		
		logger.info("**************clicked on MyAccount ************");
		
		AccountRegistrationPage arp = new AccountRegistrationPage(driver);	
		
		logger.info("**************providing customer details ************");
		
		arp.setFirstName(randomString().toUpperCase());
		arp.setLastName(randomString().toUpperCase());
		arp.setEmail(randomString()+"@gmail.com");
		
		String password = randomAlphanumeric();
		arp.setPassword(password);
		
		Thread.sleep(3000);
		
		Actions ac = new Actions(driver);
		ac.scrollByAmount(500, 1000);
		ac.build().perform();
		
		Thread.sleep(3000);
		
		arp.selectAgree();
		//arp.selectSubscribe();
		arp.selectContinueButton();
		
		logger.info("************** validating expected message ************");

		
		String actulamsg = arp.getConfirmationMsg();
		
		Thread.sleep(3000);
		
		Assert.assertEquals(actulamsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		
		logger.info("**************finished  TC001_AccountRegistrationTest ************");

	}

}
