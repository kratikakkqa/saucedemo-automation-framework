package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class ProductPage 
{
	private WebDriver driver;
	private By aapLogo = By.className("app_logo");
	
	public ProductPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public boolean AppLogoVisible()
	{
		return driver.findElement(aapLogo).isDisplayed();
	}

}
