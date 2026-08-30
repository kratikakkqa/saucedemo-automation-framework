package listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

//import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import base.BaseClass;

public class MyListner implements ITestListener
{
	@Override
	public void onStart(org.testng.ITestContext context)
	{
	    System.out.println("🔥 LISTENER STARTED 🔥");
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		System.out.println("Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) 
	{
	    System.out.println("Test Failed");
	    System.out.println(result.getName());

	    BaseClass base = (BaseClass) result.getInstance();
	    WebDriver driver = base.getDriver();

	    TakesScreenshot ts = (TakesScreenshot) driver;

	    File source = ts.getScreenshotAs(OutputType.FILE);
	    Path destination = Paths.get("screenshots/" + result.getName() + ".png");

	    try 
	    {
			Files.createDirectories(Paths.get("screenshots"));
			Files.copy(source.toPath(), destination);
		} 
	    catch (IOException e) 
	    {
			e.printStackTrace();
		}
	    
	}

}
