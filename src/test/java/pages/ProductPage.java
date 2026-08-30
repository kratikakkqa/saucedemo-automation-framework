package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;

public class ProductPage 
{
	private WebDriver driver;
	
	public ProductPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	/* ============================================================================
                                      ALL LOCATORS
      ============================================================================*/
	
	private By aapLogo = By.className("app_logo");
	private By products = By.xpath("//div[@class='inventory_item']");
	private By productName = By.xpath("//div[contains(@class, 'item_name')]");
	private By productPrice = By.xpath("//div[@class='inventory_item_price']");
	private By cartButton = By.xpath("//div[@class='inventory_item']//button");
	private By sortButton = By.xpath("//select[@class='product_sort_container']");
	private By productImage = By.xpath("//img[@class='inventory_item_img']");
	private By cartIcon =By.id("shopping_cart_container");
	
	
	/* ============================================================================
                                   PRODUCT PAGE VALIDATIONS
       ============================================================================*/
	
	public boolean AppLogoVisible()
	{
		return driver.findElement(aapLogo).isDisplayed();
	}
	
	
	// Get product count
	public int getProductCount()
	{
		return driver.findElements(products).size();
	}
	
	// Get all products
	public List<WebElement> getAllProduct()
	{
		return driver.findElements(products);
	}
	
	// Get all product names
	public List<String> getProductName()
	{
		List<String> productNames = new ArrayList<>();
		List<WebElement> pns = driver.findElements(productName);

		for(WebElement pn : pns)
		{
			productNames.add(pn.getText());
		}
		return productNames;
	}
	
	
	// Get all product prices
	public List<String> getProductPrice()
	{
		List<String> prdct_Price = new ArrayList<>();
		List<WebElement> prdct_price = driver.findElements(productPrice);
		for(WebElement price : prdct_price)
		{
			prdct_Price.add(price.getText());
		}
		return prdct_Price;
	}
	
	
	// Get all product prices in decimal
	public List<Double> getPriceInDouble()
	{
		List<Double> priceDouble = new ArrayList<>();
		List<String> prices = getProductPrice();
		for(String amount : prices)
		{
			String amnt = amount.replace("$", "");
			priceDouble.add(Double.parseDouble(amnt));
		}
		return priceDouble;
	}
	
	
	// Get product images
	public List<WebElement> getProductImages()
	{
		return driver.findElements(productImage); 
	}
	
	
	/* ============================================================================
                                  CART OPERATIONS
       ============================================================================*/
	
	
	// Get all product cart buttons
	public List<WebElement> getCartButton()
	{
		List<WebElement> buttons = driver.findElements(cartButton);
		return buttons;
	}
	
	// Add single product to cart
	public void addSingleProductToCart()
	{
		driver.findElements(cartButton).get(1).click();
	}
	
	// Remove single product from cart
	public void removeSingleProductToCart()
	{
		driver.findElements(cartButton).get(1).click();
	}
	
	// Add multiple products to cart
	public void addMultiProductToCart(int num)
	{
		if(num!=0)
		{
			for(int i = 0;i<num;i++)
			{
				driver.findElements(cartButton).get(i).click();
			}
		}
	}
	
	
	// Get cart item count
	public int getCartCount()
	{
		String cnt = driver.findElement(cartIcon).getText();
		if (cnt.isEmpty())
		{
			return 0;
		}
		else
		{
			int count = Integer.parseInt(cnt);
			return count;
		}
	}
	
	// Get cart button text
	public String getCartText()
	{
		return driver.findElements(cartButton).get(1).getText();
	}
	
	
	/* ============================================================================
                                   PRODUCT SORTING
       ============================================================================*/
	
	// Sort products by name: A to Z
	public void sortA_Z()
	{
		WebElement button = driver.findElement(sortButton);
		Select s = new Select(button);
		s.selectByValue("az");
	}
	
	// Get expected product names sorted A to Z
	public List<String> getProductList_AtoZ()
	{
		List<String> prod_names = new ArrayList<>(getProductName());
		Collections.sort(prod_names);
		return prod_names;
	}
	
	// Sort products by name: Z to A
	public void sortZ_A()
	{
		WebElement button = driver.findElement(sortButton);
		Select s = new Select(button);
		s.selectByValue("za");
	}
	
	// Get expected product names sorted Z to A
	public List<String> getZtoA_productList()
	{
		List<String> ZtoA_ProdList = new ArrayList<>(getProductList_AtoZ());
		Collections.reverse(ZtoA_ProdList);		
		return ZtoA_ProdList;
	}
	
	// Sort products by price: Low to High
	public void sortPrice_LowToHigh()
	{
		WebElement button = driver.findElement(sortButton);
		Select s = new Select(button);
		s.selectByValue("lohi");
	}
	
	// Get expected prices sorted Low to High
	public List<Double> getPrice_LowToHigh_ProductList()
	{
		List<Double> LowToHigh = new ArrayList<>(getPriceInDouble());
		Collections.sort(LowToHigh);
		return LowToHigh;
	}
	
	// Sort products by price: High to Low
	public void sortPrice_HighToLow()
	{
		WebElement button = driver.findElement(sortButton);
		Select s = new Select(button);
		s.selectByValue("hilo");
	}
	
	// Get expected prices sorted High to Low
	public List<Double> getPrice_HighToLow_ProductList()
	{
		List<Double> HighToLow = new ArrayList<>(getPrice_LowToHigh_ProductList());
		Collections.reverse(HighToLow);
		return HighToLow;
	}

}
