package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class LoginPage 
{
	private WebDriver driver; // class variable
	
	public LoginPage(WebDriver driver) // local variable
	{
		this.driver = driver;
	}
	
/*===========================================================================
 *                               LOCATORS
 *===========================================================================
 */
	
	private By txtUsername = By.id("user-name");
	private By txtPassword = By.id("password");
	private By btnLogin = By.id("login-button");
	private By loginError = By.xpath("//div[@class='error-message-container error']");
	
/*===========================================================================
 *                      VALIDATION, GETTER, ACTION METHODS
 *===========================================================================
*/	
	// Enter user name
	public void enterUsername(String username) 
	{
		driver.findElement(txtUsername).sendKeys(username);
	}
	
	//Enter password
	public void enterPassword(String password) 
	{
		driver.findElement(txtPassword).sendKeys(password);
	}
	
	
	//Click on login button
	public void clickLogin() 
	{
		driver.findElement(btnLogin).click();
	}
	
	// Login method
	public void login(String username, String password)
	{
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}
	
	// Check is login error is displayed
	public boolean loginErrorVisible()
	{
		return driver.findElement(loginError).isDisplayed();
	}
	
	// Check is password masked
	public boolean isPasswordMasked()
	{
		String type = driver.findElement(txtPassword).getAttribute("type");
		return type.equals("password");
	}
}
