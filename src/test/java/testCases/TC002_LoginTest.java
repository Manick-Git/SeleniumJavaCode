package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test
	public void verify_login()
	{
		logger.info("*************Starting TC02_Login test**********");
		
		try 
		{
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			Thread.sleep(3000);
			hp.clickLoginlink();
			
			LoginPage lp = new LoginPage(driver);
			Thread.sleep(3000);
			lp.setEmail("manick@xyz.com");
			//lp.setEmail(p.getProperty("email"));
			Thread.sleep(3000);
			lp.setPassword("test@123");
			//lp.setPassword(p.getProperty("password"));
			Thread.sleep(3000);
			lp.clickLogin();
			Thread.sleep(3000);
			
			MyAccountPage map = new MyAccountPage(driver);
			boolean targetPage = map.isMyAccountPageExists();
			
			//Assert.assertEquals(targetPage, true,"Login Failed");
			Assert.assertTrue(targetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("*************finshed TC02_Login test**********");

		
	}
}
