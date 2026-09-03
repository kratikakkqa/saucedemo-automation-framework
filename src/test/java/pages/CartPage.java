package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage 
{
	private WebDriver driver;
	public CartPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
  /*===========================================================================
   *                               ALL LOCATORS	
   *===========================================================================
   */
	private By cartText = By.xpath("//span[contains(text(), 'Your Cart')]");
	private By productName = By.xpath("//div[@class='inventory_item_name']");
	private By products = By.xpath("//div[@class='cart_item']");
	private By productPrice = By.xpath("//div[@class='inventory_item_price']");
	private By removeButton = By.xpath("//button[text() = 'Remove']");
	private By checkoutButton = By.id("checkout");
	
  /*===========================================================================
   *                      VALIDATION, GETTER, ACTION METHODS
   *===========================================================================
   */
	
	//Verify Your Cart text is displayed
	public boolean isCartTextDisplayed()
	{
		return driver.findElement(cartText).isDisplayed();
	}
	
	//Get product name 
	public String getProductName()
	{
		return driver.findElement(productName).getText();
	}
	
	//Get count of products in cart
	public int getCountCartProduct()
	{
		return driver.findElements(products).size();
	}
	
	//Verify price is displayed 
	public boolean isProductPriceDisplayed()
	{
		return driver.findElement(productPrice).isDisplayed();
	}
	
	//Get All product names
	public List<String> getAllProductName()
	{
		List<String> product_names = new ArrayList<>();
		List<WebElement> names = driver.findElements(productName);
		for(WebElement name : names)
		{
			product_names.add(name.getText());
		}
		return product_names;
	}
	
	//Remove product from cart
	public void removeProductFromCart()
	{
		driver.findElement(removeButton).click();
	}
	
	//Get empty cart
	public boolean isEmptyCart()
	{
		return driver.findElements(products).isEmpty();
	}
	
	//Remove product from cart when we have multiple product in cart
	public List<String> removeProductFromMultiCart(int num)
	{
		List<String> removed_names = new ArrayList<>();
		for(int i=0;i<num;i++)
		{
			removed_names.add(driver.findElements(products).get(i).getText());
			driver.findElements(removeButton).get(0).click();
		}
		return removed_names;
		
	}
	
	//Navigate to checkout page
	public void continueToCheckOut()
	{
		driver.findElement(checkoutButton).click();
	}

}
