package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage 
{
	private WebDriver driver;
	
	public CheckoutCompletePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	/*===========================================================================
	 *                               LOCATORS
	 *===========================================================================
	 */
	
	private By CompleteTitle = By.xpath("//span[@class='title' and contains(text(), 'Checkout: Complete!')]");
	private By backHomeButton = By.id("back-to-products");
	
	/*===========================================================================
	 *                      VALIDATION, GETTER, ACTION METHODS
	 *===========================================================================
	 */	
	
	public boolean isCheckoutCompletePageDisplayed()
	{
		return driver.findElement(CompleteTitle).isDisplayed();
	}
	
	public void navigateToHome()
	{
		driver.findElement(backHomeButton).click();
	}

}
