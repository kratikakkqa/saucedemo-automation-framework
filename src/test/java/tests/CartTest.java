package tests;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import base.BaseClass;
import listeners.MyListner;
import pages.CartPage;
import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;


@Listeners(MyListner.class)
public class CartTest extends BaseClass
{
	// Verify Cart page is loadede
	@Test
	public void verifyCartPageIsLoaded()
	{
		LoginPage lp = new LoginPage(driver);
		ProductPage pp = new ProductPage(driver);
		CartPage cp = new CartPage(driver);
		SoftAssert sa = new SoftAssert();
		
		lp.login("standard_user", "secret_sauce");
		pp.navigateToCartPage();
		sa.assertTrue(cp.isCartTextDisplayed(), "Cart page is not loaded, since the Your Cart text is not displayed");
		sa.assertAll();
	}
	
	//Verify added product is displayed in cart
	@Test
	public void verifyAddedProductIsDisplayedInCart()
	{
		LoginPage lp = new LoginPage(driver);
		ProductPage pp = new ProductPage(driver);
		CartPage cp = new CartPage(driver);
		SoftAssert sa = new SoftAssert();
		
		lp.login("standard_user", "secret_sauce");
		String exp_name = pp.getProductName_AddingIntoCart();
		pp.addSingleProductToCart(); 
		pp.navigateToCartPage();
		String act_name = cp.getProductName();
		sa.assertEquals(exp_name, act_name, "Added product is not displayed in cart page");
		sa.assertAll();
	}
	
	//Verify the multiple products added in cart
	@Parameters("cartData")
	@Test
	public void verifyMultipleProductsInCart(int cartData)
	{
		LoginPage lp = new LoginPage(driver);
		ProductPage pp = new ProductPage(driver);
		CartPage cp = new CartPage(driver);
		SoftAssert sa = new SoftAssert();
		
		lp.login("standard_user", "secret_sauce");
		pp.addMultiProductToCart(cartData);
		pp.navigateToCartPage();
		int exp_count = cartData;
		int act_count = cp.getCountCartProduct();
		sa.assertEquals(exp_count, act_count, "Added product into the cart and the listed product in cart is not same");
		sa.assertAll();	
	}
	
	//Verify product price is displayed in cart
	@Test
	public void verifyProductPriceIsDisplayed()
	{
		LoginPage lp = new LoginPage(driver);
		ProductPage pp = new ProductPage(driver);
		CartPage cp = new CartPage(driver);
		SoftAssert sa = new SoftAssert();
		
		lp.login("standard_user", "secret_sauce");
		pp.addSingleProductToCart();
		pp.navigateToCartPage();
		sa.assertTrue(cp.isProductPriceDisplayed(), "Price is not displayed for product in cart");
		sa.assertAll();
	}
	
	//Verify Product names on product page and cart page are same
    @Parameters("cartData")
	@Test
	public void verifyProductNamesCart(int cartData)
	{
		LoginPage lp = new LoginPage(driver);
		ProductPage pp = new ProductPage(driver);
		CartPage cp = new CartPage(driver);
		SoftAssert sa = new SoftAssert();
		
		lp.login("standard_user", "secret_sauce");
		System.out.println("Products are in product page");
		System.out.println(pp.getProductName());
		List<String> prod_page_name = pp.getMultipleProductNames_AddingIntoCart(cartData);
		System.out.println("Products are added into cart");
		System.out.println(prod_page_name);
		pp.navigateToCartPage();
		List<String> cart_prod_name = cp.getAllProductName();
		System.out.println("Products are in cart");
		System.out.println(cart_prod_name);
		sa.assertEquals(prod_page_name, cart_prod_name, "Product names in Cart not match the product names selected on the Products page.");
	    sa.assertAll();
	}
	
	//Verify the product is removed from cart
	@Test
	public void verifyProductIsRemovedFromCart()
	{
		LoginPage lp = new LoginPage(driver);
		ProductPage pp = new ProductPage(driver);
		CartPage cp = new CartPage(driver);
		SoftAssert sa = new SoftAssert();
		
		lp.login("standard_user", "secret_sauce");
		pp.addSingleProductToCart();
		pp.navigateToCartPage();
		cp.removeProductFromCart();
		sa.assertTrue(cp.isEmptyCart(), "Cart is not empty");
		sa.assertAll();
	}
	
	// Verify the single product removed from cart
	@Parameters({"cartData","removeCartData"})
	@Test
	public void verifyRemovingOneProductFromCart(int cartData, int removeCartData)
	{
		LoginPage lp = new LoginPage(driver);
		ProductPage pp = new ProductPage(driver);
		CartPage cp = new CartPage(driver);
		SoftAssert sa = new SoftAssert();
		
		lp.login("standard_user", "secret_sauce");
		pp.addMultiProductToCart(cartData);
		pp.navigateToCartPage();
		List<String> allProdnames = cp.getAllProductName();
		System.out.println("Before removal products are in the cart");
		System.out.println(allProdnames);
		int exp_count = cp.getCountCartProduct()- removeCartData;
		List<String> removed_prod_names = cp.removeProductFromMultiCart(removeCartData);
		List<String> after_remove_names = cp.getAllProductName();
		System.out.println("After removal products are in the cart");
		System.out.println(after_remove_names);
		int act_count = cp.getCountCartProduct();
		sa.assertEquals(exp_count, act_count, "The cart is not matching ");
		sa.assertFalse(after_remove_names.containsAll(removed_prod_names), "Removed name still exist");
		sa.assertTrue(allProdnames.containsAll(after_remove_names), "Products are not in cart");
		sa.assertAll();	
	}
	
}
