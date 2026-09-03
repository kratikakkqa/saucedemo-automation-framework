package tests;

import base.BaseClass;
import listeners.MyListner;
import pages.LoginPage;
import pages.ProductPage;


import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

@Listeners(MyListner.class)
public class ProductTest extends BaseClass
{
	
	// Verify product page is displayed
	@Test
	public void verifyProductPageDisplayed()
	{
		LoginPage lp = new LoginPage(driver);
		ProductPage pp = new ProductPage(driver);
		SoftAssert sa = new SoftAssert();
		
		lp.login("standard_user", "secret_sauce");
		boolean appLogo = pp.AppLogoVisible();
		sa.assertTrue(appLogo);
		
		sa.assertAll();
	}
	
	
	// Verify all products are displayed
	@Test
	public void verifyAllProductsDisplayed()
	{
		LoginPage lp = new LoginPage(driver);
		ProductPage pp = new ProductPage(driver);
		SoftAssert sa = new SoftAssert();
		
		lp.login("standard_user", "secret_sauce");
		int number = 1;
		for(WebElement product : pp.getAllProduct())
		{
			sa.assertTrue(product.isDisplayed(), "Product "+number+" is not displayed");
			number++;
		}
		sa.assertAll();
	}
	
	
	// Verify product count
	@Test
	public void verifyProductCount()
	{
		LoginPage lp = new LoginPage(driver);
		lp.login("standard_user", "secret_sauce");
		ProductPage pp = new ProductPage(driver);
		SoftAssert sa = new SoftAssert();
		
		System.out.println("Count of products : "+pp.getProductCount());
		sa.assertEquals(pp.getProductCount(), 6, "Expected 6 products but actual is different");
		sa.assertAll();
	}
	
	
	// Verify each product has a name
    @Test
	public void verifyEaachProductHasName()
	{
		LoginPage lp = new LoginPage(driver);
		lp.login("standard_user", "secret_sauce");
		ProductPage pp = new ProductPage(driver);
		SoftAssert sa = new SoftAssert();
		for (String name : pp.getProductName())
		{
			sa.assertTrue(!name.isEmpty(), "Product name is empty");
		}
		sa.assertAll();
	}
	
		
    // Verify each product has a price
	@Test
	public void verifyEachProductHasPrice()
	{
		LoginPage lp = new LoginPage(driver);
		lp.login("standard_user", "secret_sauce");
		ProductPage pp = new ProductPage(driver);
		SoftAssert sa = new SoftAssert();
		int number = 1;
		for(String price : pp.getProductPrice())
		{
			sa.assertTrue(!price.isEmpty(), " "+number+" Product price is empty");
			number++;
		}
		sa.assertAll();
	}
	
	
	// Verify product images are displayed
	@Test
	public void verifyProductImagesDisplayed()
	{
		LoginPage lp = new LoginPage(driver);
		lp.login("standard_user", "secret_sauce");
		ProductPage pp = new ProductPage(driver);
		SoftAssert sa = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		int number = 1;
		for(WebElement img : pp.getProductImages())
		{
			wait.until(ExpectedConditions.visibilityOf(img));
			sa.assertTrue(img.isDisplayed(), "Product "+number+" image is not displayed");
			number ++;
		}
		sa.assertAll();
	}

	// Verify cart buttons are displayed and enabled
	@Test
	public void verifyCartButtonsDisplayedAndEnabled()
	{
		LoginPage lp = new LoginPage(driver);
		lp.login("standard_user", "secret_sauce");
		ProductPage pp = new ProductPage(driver);
		SoftAssert sa = new SoftAssert();
		int cart_num = 1;
		for(WebElement btn : pp.getCartButton())
		{
			sa.assertTrue(btn.isDisplayed(), " Product "+cart_num+" cart button displayed");
			sa.assertTrue(btn.isEnabled(), " Product "+cart_num+" cart button is enabled");
			cart_num++;
		}
		sa.assertAll();
	}
	
	// Verify single product can be added to cart
	@Test
	public void verifySingleProductCanBeAddedToCart() throws InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.login("standard_user", "secret_sauce");
		ProductPage pp = new ProductPage(driver);
		SoftAssert sa = new SoftAssert();
		
		int exp_count = 1 + pp.getCartCount();
		pp.addSingleProductToCart();
		sa.assertEquals(pp.getCartCount(), exp_count, "Product is not added in cart since the count is not added");		
		sa.assertEquals(pp.getCartText(), "Remove");
		sa.assertAll();
	}
	
	// Verify multiple products can be added to cart
	@Parameters("cartData")
	@Test
	public void verifyMultipleProductsCanBeAddedToCart(int cartData)
	{
		LoginPage lp = new LoginPage(driver);
		lp.login("standard_user", "secret_sauce");
		ProductPage pp = new ProductPage(driver);
		SoftAssert sa = new SoftAssert();
		
		int exp_count = cartData + pp.getCartCount();
		
		if(cartData > 0 && cartData <= pp.getProductCount())
		{
			pp.addMultiProductToCart(cartData);
			sa.assertEquals(pp.getCartCount(), exp_count, "Product is not added in cart since the count is not added");
		}
		else
		{
			sa.assertTrue(false, null);
			System.out.println("Invalid number of products: " + cartData);
		}
		
		sa.assertAll();
	}
	
	
	// Verify product can be removed from products page
	@Test
	public void verifyProductCanBeRemovedFromProductsPage()
	{
		LoginPage lp = new LoginPage(driver);
		lp.login("standard_user", "secret_sauce");
		ProductPage pp = new ProductPage(driver);
		SoftAssert sa = new SoftAssert();
		
		int cart_count = pp.getCartCount(); //0
		pp.addSingleProductToCart();
		int after_AddCart_Cont = pp.getCartCount();  // 1
		
		if(cart_count < after_AddCart_Cont && after_AddCart_Cont > 0)
		{
			sa.assertEquals(after_AddCart_Cont, cart_count+1, "Cart count is not increased");
			System.out.println("Button text before remove: " + pp.getCartText());
			pp.removeSingleProductToCart();
			System.out.println("Button text before remove: " + pp.getCartText());
			int after_RemoveCart_Cont = pp.getCartCount();
			sa.assertEquals(after_RemoveCart_Cont, cart_count, "Cart count is not decreased");
		}
		else
		{
			System.out.println("Product is not added");
		}
		sa.assertAll();
	}
	 
	
	// Verify product sorting A to Z
	@Test
	public void verifyProductSortingAToZ()
	{
		LoginPage lp = new LoginPage(driver);
		lp.login("standard_user", "secret_sauce");
		ProductPage pp = new ProductPage(driver);
		SoftAssert sa = new SoftAssert();
		System.out.println(pp.getProductName());
		pp.sortA_Z();
		List<String> actual_names = pp.getProductName();
		System.out.println(actual_names);
		List<String> expct_names = pp.getProductList_AtoZ();
		System.out.println(expct_names);
		sa.assertEquals(actual_names, expct_names, "Product names are not in soerted in A to Z ordered");
		sa.assertAll();
	 }
	
	
	// Verify product sorting Z to A
	@Test
	public void verifyProductSortingZToA()
	{
		LoginPage lp = new LoginPage(driver);
		lp.login("standard_user", "secret_sauce");
		ProductPage pp = new ProductPage(driver);
		SoftAssert sa = new SoftAssert();
		
		System.out.println(pp.getProductName());
		pp.sortZ_A();
		List<String> actual_names = pp.getProductName();
		System.out.println(actual_names);
		List<String> expct_names = pp.getZtoA_productList();
		System.out.println(expct_names);
		sa.assertEquals(actual_names, expct_names, "Product names are not in soerted in Z to A ordered");
		sa.assertAll();
	}
	
	
	// Verify product sorting price Low to High
	@Test
	public void verifyProductSortingLowToHigh()
	{
		LoginPage lp = new LoginPage(driver);
		lp.login("standard_user", "secret_sauce");
		ProductPage pp = new ProductPage(driver);
		SoftAssert sa = new SoftAssert();
		
		System.out.println(pp.getPriceInDouble());
		pp.sortPrice_LowToHigh();
		List<Double> actual_price = pp.getPriceInDouble();
		System.out.println(actual_price);
		List<Double> expct_price = pp.getPrice_LowToHigh_ProductList();
		System.out.println(expct_price);
		sa.assertEquals(actual_price, expct_price, "Price is not sorted in Low to high order");
		sa.assertAll();
	}
	
	
	// Verify product sorting price High to Low
	@Test
	public void verifyProductSortingHighToLow()
	{
		LoginPage lp = new LoginPage(driver);
		lp.login("standard_user", "secret_sauce");
		ProductPage pp = new ProductPage(driver);
		SoftAssert sa = new SoftAssert();
		
		System.out.println(pp.getPriceInDouble());
		pp.sortPrice_HighToLow();
		List<Double> actual_price = pp.getPriceInDouble();
		System.out.println(actual_price);
		List<Double> expct_price = pp.getPrice_HighToLow_ProductList();
		System.out.println(expct_price);
		sa.assertEquals(actual_price, expct_price, "Price is not sorted in high to low order");
		sa.assertAll();
	}
	

}
