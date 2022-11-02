package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	//private WebDriver driver;
	private Properties prop;//it is a built in java class to get the properties file.
	private OptionsManager optionsManager;

	/** ThreadLocal is an inbuilt java class..
	 * It provides thread-local variables.
	 * it has two methods set and get.
	 */
	//ThreadLocal gives a local copy of the web driver object.So parellel execution will be done without
	//any problem and it will never interrupts other threads,never get deadlock situation.

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	//we need to initialize the driver
	/**
	 * we are initializing the driver on the basis of given browser name
	 * @param browserName
	 * @return it returns the driver
	 */

	public WebDriver init_driver(Properties prop)
	{
		//String browserName = prop.getProperty("browser").trim();
		String browserName = System.getProperty("browser");

		optionsManager = new OptionsManager(prop);
		//cross browser logic
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			//set() initializes the driver with the thread local
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

		}
		else if(browserName.equalsIgnoreCase("safari"))
		{
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());

		}else {
			System.out.println("please pass the right browser :"+browserName);
			throw new FrameworkException("NO BROWSER IS FOUND EXCEPTION");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());

		return getDriver();//returning the thread local copy of driver
	}
	/**
	 * get the thread local copy of driver
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();//we r going to use this local copy driver instead of normal driver
	}

	public Properties init_prop()
	{
		prop = new Properties();
		FileInputStream ip = null;

		//mvn clean install -Denv ="qa"
		String envName =System.getProperty("env");
		System.out.println("Running tests on environment..:"+envName);
		if(envName==null) {
			System.out.println("No environment has given..hence test run it on qa");
			try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					System.out.println("tests run on qa environment..:"+envName);
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					System.out.println("tests run on stage environment..:"+envName);
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					System.out.println("tests run on dev environment..:"+envName);
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "uat":
					System.out.println("tests run on uat environment..:"+envName);
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					System.out.println("tests run on production environment..:"+envName);
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				default:
					System.out.println("Please give correct environment.."+envName);
					throw new FrameworkException("NO ENVIRONMENT IS FOUND EXCEPTION");
					//break;
				}

			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}
			try {

				prop.load(ip);//it loads the properties from the input stream file in a key value pair format

			} catch (IOException e) {
				e.printStackTrace();
			}

		
		return prop;
	}
	/**
	 * take the screenshot
	 * @return path
	 */
	//TakesScreenshot is an interface in selenium.
	//It captures the screenshot and it is stored in the specified location

	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = "./screenshot"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
