package tests;

import base.BaseClass;
import listeners.MyListner;

import org.testng.annotations.Listeners;
import pages.LoginPage;
import pages.ProductPage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(MyListner.class)
public class LoginTest extends BaseClass
{
	//Login data
	@DataProvider(name="loginData")
	public Object[][] getData()
	{
		return new Object[][]
				{
					{"standard_user", "secret_sauce",true},  // valid + valid
					{"standard_user", "Krati123",false},       // valid + invalid
					{"Kratik", "secret_sauce",false},          // invalid + valid
					{"Kratika","Krati123",false},             // invalid + invalid
					{"","",false},
					{"","secret_sauce",false},
					{"standard_user","",false},
				};
	}
	
	// Verify login 
	@Test(dataProvider = "loginData")
	public void verifyLogin(String username, String password, boolean validLogin)
	{
		LoginPage lp= new LoginPage(driver);
		SoftAssert sa = new SoftAssert();
		
		lp.login(username, password);
		
		if(validLogin)
		{
			ProductPage pp = new ProductPage(driver);
			boolean logo=pp.AppLogoVisible();
			sa.assertTrue(logo);
		}
		else
		{
			 boolean loginError = lp.loginErrorVisible();
			 sa.assertTrue(loginError);
		}
	
		sa.assertAll();
	}
	
	// Verify password field is masked
	@Test
	public void verifyPasswordFieldIsMasked()
	{
		LoginPage lp = new LoginPage(driver);
		SoftAssert sa = new SoftAssert();
		
		boolean masked = lp.isPasswordMasked();
		
		sa.assertTrue(masked, "Password field is not masked");
		sa.assertAll();
	}
}
