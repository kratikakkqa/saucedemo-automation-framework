package tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseClass;
import listeners.MyListner;
import pages.CartPage;
import pages.CheckoutCompletePage;
import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;

@Listeners(MyListner.class)
public class CheckoutTest extends BaseClass
{
	   //verify checkout can be initiated
		@Test
		public void verifyCheckoutInitiated()
		{
			LoginPage lp = new LoginPage(driver);
			ProductPage pp = new ProductPage(driver);
			CartPage cp = new CartPage(driver);
			CheckoutPage cop = new CheckoutPage(driver);
			SoftAssert sa = new SoftAssert();
			
			lp.login("standard_user", "secret_sauce");
			pp.addSingleProductToCart();
			pp.navigateToCartPage();
			cp.continueToCheckOut();
			sa.assertTrue(cop.isCheckoutTitleDisplayed(), "Chekout Page is not displayed");
			sa.assertAll();
		}
		
		
		//Verify checkout information validation
		@Test
		public void verifyCheckoutInfoValidation()
		{
			LoginPage lp = new LoginPage(driver);
			ProductPage pp = new ProductPage(driver);
			CartPage cp = new CartPage(driver);
			CheckoutPage cop = new CheckoutPage(driver);
			SoftAssert sa = new SoftAssert();
			
			lp.login("standard_user", "secret_sauce");
			pp.addSingleProductToCart();
			pp.navigateToCartPage();
			cp.continueToCheckOut();
			cop.continueCheckout();
			sa.assertTrue(cop.isErrorMessageDisplayed(), "Error message is not displayed");
			sa.assertAll();
		}
		
		//verify overview page is displayed
		@Test
		public void verifyOverviewPageDisplayed()
		{
			LoginPage lp = new LoginPage(driver);
			ProductPage pp = new ProductPage(driver);
			CartPage cp = new CartPage(driver);
			CheckoutPage cop = new CheckoutPage(driver);
			CheckoutOverviewPage coop = new CheckoutOverviewPage(driver);
			SoftAssert sa = new SoftAssert();
			
			lp.login("standard_user", "secret_sauce");
			pp.addSingleProductToCart();
			pp.navigateToCartPage();
			cp.continueToCheckOut();
			cop.enterDetailsAndContinue("Kratika", "Kochrekar", "581301");
			sa.assertTrue(coop.isOverviewPageDisplayed(), "Overiew page is not displayed");
			sa.assertAll();
		}
		
		//Verify selected product details on Checkout Overview
		@Test
		public void verifySelectedProductDetailsOnCheckoutOverview()
		{
			LoginPage lp = new LoginPage(driver);
			ProductPage pp = new ProductPage(driver);
			CartPage cp = new CartPage(driver);
			CheckoutPage cop = new CheckoutPage(driver);
			CheckoutOverviewPage coop = new CheckoutOverviewPage(driver);
			SoftAssert sa = new SoftAssert();
			
			lp.login("standard_user", "secret_sauce");
			pp.addSingleProductToCart();
			pp.navigateToCartPage();
			String cart_prod_name = cp.getProductName();
			cp.continueToCheckOut();
			cop.enterDetailsAndContinue("Kratika", "Kochrekar", "581301");
			String overview_prod_name = coop.getOverviewProductName();
			sa.assertEquals(cart_prod_name, overview_prod_name, "Product name on Overview not matches the product selected in the Cart.");
			sa.assertAll();
		}
		
		//Verify total price on Checkout Overview
		@Parameters("cartData")
		@Test
		public void verifyTotalPriceOnCheckoutOverview(int cartData)
		{
			LoginPage lp = new LoginPage(driver);
			ProductPage pp = new ProductPage(driver);
			CartPage cp = new CartPage(driver);
			CheckoutPage cop = new CheckoutPage(driver);
			CheckoutOverviewPage coop = new CheckoutOverviewPage(driver);
			SoftAssert sa = new SoftAssert();
			
			lp.login("standard_user", "secret_sauce");
			pp.addMultiProductToCart(cartData);
			pp.navigateToCartPage();
			cp.continueToCheckOut();
			cop.enterDetailsAndContinue("Kratika", "Kochrekar", "581301");
			System.out.println("Price of all products");
			System.out.println(coop.getPriceAllProduct());
			
			System.out.println("Before calculation");

			double total_price = coop.getAllProductCalculatedPrice();

			System.out.println("After calculation");
			System.out.println("Expected Total Price: " + total_price);

			double sub_total = coop.getItemTotal();

			System.out.println("After subtotal");
			System.out.println("Actual Sub Total: " + sub_total);

			sa.assertEquals(total_price, sub_total, "Both price are not matching");
			sa.assertAll();
		}
		
		//Verify Tax Calculation on Checkout Overview
		@Parameters({"cartData", "firstName", "lastName", "zipCode"})
		@Test
		public void verifyTaxCalculationonCheckoutOverview(int cartData, String firstName, String lastName, String zipCode)
		{
			LoginPage lp = new LoginPage(driver);
			ProductPage pp = new ProductPage(driver);
			CartPage cp = new CartPage(driver);
			CheckoutPage cop = new CheckoutPage(driver);
			CheckoutOverviewPage coop = new CheckoutOverviewPage(driver);
			SoftAssert sa = new SoftAssert();
			

			lp.login("standard_user", "secret_sauce");
			pp.addMultiProductToCart(cartData);
			pp.navigateToCartPage();
			cp.continueToCheckOut();
			cop.enterDetailsAndContinue(firstName, lastName, zipCode);
			double expectedTotal = coop.getTotalAmoutofCheckout();
			double actualTotal = coop.getTotalPrice();
			sa.assertEquals(expectedTotal, actualTotal, "Calculated total does not match UI total");
			sa.assertAll();
		}
		
		//Verify Checkout Complete Successfully
		@Parameters({"cartData", "firstName", "lastName", "zipCode"})
		@Test
		public void verifyCheckoutCompleteSuccessfully(int cartData, String firstName, String lastName, String zipCode)
		{
			LoginPage lp = new LoginPage(driver);
			ProductPage pp = new ProductPage(driver);
			CartPage cp = new CartPage(driver);
			CheckoutPage cop = new CheckoutPage(driver);
			CheckoutOverviewPage coop = new CheckoutOverviewPage(driver);
			CheckoutCompletePage ccp = new CheckoutCompletePage(driver);
			SoftAssert sa = new SoftAssert();
			

			lp.login("standard_user", "secret_sauce");
			pp.addMultiProductToCart(cartData);
			pp.navigateToCartPage();
			cp.continueToCheckOut();
			cop.enterDetailsAndContinue(firstName, lastName, zipCode);
			coop.navigateToCheckoutCompletePage();
			sa.assertTrue(ccp.isCheckoutCompletePageDisplayed(), "Checkout Complete page is not displayed");
			sa.assertAll();
		}
		
		//public Verify user can navigate back to Products page after successful checkout
		@Parameters({"firstName", "lastName", "zipCode"})
		@Test
		public void verifyNavigateToProductsPageAfterSuccessfulCheckout(String firstName, String lastName, String zipCode)
		{
			LoginPage lp = new LoginPage(driver);
			ProductPage pp = new ProductPage(driver);
			CartPage cp = new CartPage(driver);
			CheckoutPage cop = new CheckoutPage(driver);
			CheckoutOverviewPage coop = new CheckoutOverviewPage(driver);
			CheckoutCompletePage ccp = new CheckoutCompletePage(driver);
			SoftAssert sa = new SoftAssert();
			

			lp.login("standard_user", "secret_sauce");
			pp.addSingleProductToCart();
			pp.navigateToCartPage();
			cp.continueToCheckOut();
			cop.enterDetailsAndContinue(firstName, lastName, zipCode);
			coop.navigateToCheckoutCompletePage();
			sa.assertTrue(ccp.isCheckoutCompletePageDisplayed(), "Checkout Complete page displayed");
			ccp.navigateToHome();
			sa.assertTrue(pp.AppLogoVisible(), "Product page is not displayed");
			sa.assertAll();
		}
		
}
