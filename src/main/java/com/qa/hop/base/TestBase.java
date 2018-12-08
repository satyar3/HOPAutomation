package com.qa.hop.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBase
{
	protected WebDriver driver = null;
	protected Properties prop1;
	protected FileInputStream fs1;
	protected Properties prop2;
	protected FileInputStream fs2;
	protected Properties prop;
	protected FileInputStream fs;
	protected String browser;
	
	private TestBase()
	{
		try
		{
			prop1 = new Properties();		
			fs1 = new FileInputStream("\\Resources\\configs\\config.properties");
			prop.load(fs1);
			
			prop2 = new Properties();		
			fs2 = new FileInputStream("\\Resources\\configs\\OR.properties");
			prop.load(fs2);
			
			prop = new Properties();
			prop.putAll(prop1);
			prop.putAll(prop2);
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	protected void initialize()
	{
		if(driver == null)
		{
			browser = prop.getProperty("browserName");
			if (browser.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "\\Resources\\Drivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("useAutomationExtension", false);
				driver = new ChromeDriver(options);
			}
			else
			{
				System.setProperty("webdriver.ie.driver", "Resources\\Drivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("pageloadtime")), TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicitwait")), TimeUnit.SECONDS);
		}
	}
	
	@BeforeTest
	public void setUp()
	{
			initialize();
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
		driver = null;
	}
}
