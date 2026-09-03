package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage 
{
	private WebDriver driver;
	
	public CheckoutOverviewPage(WebDriver driver)
	{
		this.driver = driver;
	}

	
	/*===========================================================================
	 *                               LOCATORS
	 *===========================================================================
	 */
	private By overviewTitle = By.xpath("//span[@class='title' and contains(text(), 'Checkout: Overview')]");
	private By productName = By.xpath("//div[@class='inventory_item_name']");
	private By productPrice = By.xpath("//div[@class='inventory_item_price']");
	private By subTotal = By.xpath("//div[@class='summary_subtotal_label']");
	private By taxPrice = By.xpath("//div[@class='summary_tax_label']");
	private By totalPrice = By.xpath("//div[@class='summary_total_label']");
	private By finishButton = By.id("finish");

	
	/*===========================================================================
	 *                      VALIDATION, GETTER, ACTION METHODS
	 *===========================================================================
	 */	
	
	//Is overview page is displayed
	public boolean isOverviewPageDisplayed()
	{
		return driver.findElement(overviewTitle).isDisplayed();
	}
	
	//get product name on overview
	public String getOverviewProductName()
	{
		return driver.findElement(productName).getText();
	}
	
	//get product price
	public List<String> getPriceAllProduct()
	{
		List<String> price_list = new ArrayList<>();
		List<WebElement> prices = driver.findElements(productPrice);
		for(WebElement price : prices)
		{
			price_list.add(price.getText());
		}
		return price_list;
	}
	
	//get total calculated price
	public double getAllProductCalculatedPrice()
	{
		double totalPrice = 0;
		List<String> prices = getPriceAllProduct();
		for(String price : prices)
		{
			String amount = price.replace("$", "");
			double total =	Double.parseDouble(amount);
			totalPrice = totalPrice + total;
		}
		return totalPrice;
	}
	
	//get sub total price
	public double getItemTotal()
	{
		double sub_total;
		String price = driver.findElement(subTotal).getText();
		price = price.substring(13);
		sub_total = Double.parseDouble(price);
		return sub_total;
	}
	
	//get tax amount
	public double getTaxPrice()
	{
		double tax;
		String price = driver.findElement(taxPrice).getText();
		price = price = price.substring(6);
		tax = Double.parseDouble(price);
		return tax;
	}
	
	//get total amount
	public double getTotalPrice()
	{
		double total;
		String price = driver.findElement(totalPrice).getText();
		price = price.substring(8);
		total = Double.parseDouble(price);
		return total;
	}
	
	//Calculate item total and tax
	public double getTotalAmoutofCheckout()
	{
	    return getItemTotal() + getTaxPrice();
	}
	
	//Navigate to complete page
	public void navigateToCheckoutCompletePage()
	{
		driver.findElement(finishButton).click();
	}
	
	
}
