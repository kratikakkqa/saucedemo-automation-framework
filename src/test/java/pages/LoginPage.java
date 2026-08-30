package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class LoginPage 
{
	private WebDriver driver; // class variable
	private By txtUsername = By.id("user-name");
	private By txtPassword = By.id("password");
	private By btnLogin = By.id("login-button");
	private By loginError = By.xpath("//div[@class='error-message-container error']");
	
	
	public LoginPage(WebDriver driver) // local variable
	{
		this.driver = driver;
	}
	
	
	public void enterUsername(String username) 
	{
		driver.findElement(txtUsername).sendKeys(username);
	}
	
	
	public void enterPassword(String password) 
	{
		driver.findElement(txtPassword).sendKeys(password);
	}
	
	
	public void clickLogin() 
	{
		driver.findElement(btnLogin).click();
	}
	
	
	public void login(String username, String password)
	{
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}
	
	
	public boolean loginErrorVisible()
	{
		return driver.findElement(loginError).isDisplayed();
	}
	
	public boolean isPasswordMasked()
	{
		String type = driver.findElement(txtPassword).getAttribute("type");
		return type.equals("password");
	}
}
