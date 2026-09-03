package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage 
{
	private WebDriver  driver;
	
	public CheckoutPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	/*===========================================================================
	 *                               LOCATORS
	 *===========================================================================*/
	
	private By checkoutTitle = By.xpath("//span[@class='title' and contains(text(), 'Checkout')]");
	private By continueButton = By.id("continue");
	private By errorMessage = By.xpath("//h3[@data-test='error']");
	private By firstName = By.id("first-name");
	private By lastName = By.id("last-name");
	private By zipCode = By.id("postal-code");
	
	
	/*===========================================================================
	 *                      VALIDATION, GETTER, ACTION METHODS
	 *===========================================================================
	 */	
	
	//check out title is  displayed
	public boolean isCheckoutTitleDisplayed()
	{
		return driver.findElement(checkoutTitle).isDisplayed();
	}
	
	//check out continue
	public void continueCheckout()
	{
		driver.findElement(continueButton).click();
	}
	
	public boolean isErrorMessageDisplayed()
	{
		return driver.findElement(errorMessage).isDisplayed();
	}
	
	//enter first name
	public void enterFirstName(String fname)
	{
		driver.findElement(firstName).sendKeys(fname);
	}
	
	//enter last name
	public void enterLastName(String lname)
	{
		driver.findElement(lastName).sendKeys(lname);
	}
	
	//enter last name
	public void enterZipCode(String code)
	{
			driver.findElement(zipCode).sendKeys(code);
	}
	
	//add all details
	public void enterDetailsAndContinue(String fname, String lname, String code)
	{
		enterFirstName(fname);
		enterLastName(lname);
		enterZipCode(code);
		continueCheckout();
	}
	

}
