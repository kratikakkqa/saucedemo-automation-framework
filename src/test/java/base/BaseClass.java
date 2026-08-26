package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.ConfigReader;

public class BaseClass 
{
	protected WebDriver driver;
	protected ConfigReader cr;
	

	@BeforeMethod
	public void setUp()
	{ 
		cr = new ConfigReader();
		String browser=cr.getBrowser();
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			System.out.println("Driver created: " + driver);
		}
		else
		{
			throw new RuntimeException("Browser not supported: "+browser);
		}
		
		driver.manage().window().maximize();
		driver.get(cr.getUrl());
		
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
