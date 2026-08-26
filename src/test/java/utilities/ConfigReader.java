package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader 
{
	Properties prop;
	
	public ConfigReader()
	{
		prop = new Properties();
		
		try 
		{
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/config.properties");
			prop.load(fis);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public String getBrowser()
	{
		return prop.getProperty("browser");
	}
	
	
	public String getUrl()
	{
		return prop.getProperty("url");
	}

}
