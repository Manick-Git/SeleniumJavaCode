package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

public class BaseClass {
	
	public WebDriver driver;
	public Logger logger;
	public Properties p;
	@BeforeClass
	@Parameters({"os","browser"})
	public void setUp(String os, String br) throws InterruptedException, IOException
	{
		//Loading config.properties file
		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		Properties p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());	
		
		//driver = new ChromeDriver();
		
		switch(br.toLowerCase())
		{		
		case "chrome" : driver=new ChromeDriver();break;
		case "edge" : driver=new EdgeDriver();break;
		case "firefox" : driver=new FirefoxDriver();break;
		default : System.out.println("Invalid browser name..."); return;	
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		
		//driver.get("https://demo.opencart.com/");
		
		//Thread.sleep(20000);
		
		driver.get(p.getProperty("appURL1")); //reading URL from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	@SuppressWarnings("deprecation")
	public String randomString()
	{
		String genaratedString = RandomStringUtils.randomAlphabetic(5);
		return genaratedString;
	}
	
	@SuppressWarnings("deprecation")
	public String randomNumber()
	{
		String genaratedString = RandomStringUtils.randomNumeric(10);
		return genaratedString;
	}
	
	@SuppressWarnings("deprecation")
	public String randomAlphanumeric()
	{
		String randomAlphanumericString = RandomStringUtils.randomAlphanumeric(10);
		System.out.println(randomAlphanumericString);
		return randomAlphanumericString;
	}
}
