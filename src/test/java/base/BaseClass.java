package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.ConfigReader;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseClass 
{
	protected WebDriver driver;
	protected ConfigReader cr;
	
	//@Parameters({"browser","url"})
	@BeforeMethod
	public void setUp()
	{ 
		cr = new ConfigReader();
		String browser=cr.getBrowser();
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			ChromeOptions options = new ChromeOptions();

		    options.addArguments("--disable-notifications");

		    options.setExperimentalOption("prefs", java.util.Map.of(
		        "credentials_enable_service", false,
		        "profile.password_manager_leak_detection", false
		    ));

		    driver = new ChromeDriver(options);
			System.out.println("Driver created: " + driver);
		}
		else
		{
			throw new RuntimeException("Browser not supported: "+browser);
		}
		
		driver.manage().window().maximize();
		driver.get(cr.getUrl());
		//driver.get(url);
	}
	
	public WebDriver getDriver()
	{
	    return driver;
	}
	
	@AfterMethod
	public void tearDown()
	{
		if(driver !=null)
		{
			driver.quit();
		}
	}

}
