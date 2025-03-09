package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameWorkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static String highlight = null;
	
	public WebDriver initDriver(Properties prop) {
	
		String browserName = prop.getProperty("browser");
	//    highlight =  prop.getProperty("highlight");	
	//	String browserName = System.getProperty("browser");
		System.out.println("*********browserName: "+ browserName);
		optionsManager = new OptionsManager(prop);
		
		System.out.println("********remote: "+prop.getProperty("remote"));

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run it on grid:
				System.out.println("*************not suppose to enter******************");
				initRemoteDriver(browserName);
			}else{
	//		driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			break;
		case "firefox":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run it on grid:
				initRemoteDriver(browserName);
			}else{
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;
		case "microsoftedge":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run it on grid:
				initRemoteDriver(browserName);
			}else{
		//	driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			break;
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;
		default:
			System.out.println("Please enter the proper browser name");
			throw new FrameWorkException("No browser found...");
		}

//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		driver.get(prop.getProperty("url"));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}
	
/**
 * run Tests on grid	
 * @param browserName
 */
	
	private void initRemoteDriver(String browserName) {
		System.out.println("Running tests on GRID with browser: " + browserName);
		System.out.println("username: "+prop.getProperty("username"));
		System.out.println("url: "+prop.getProperty("huburl"));
		
		try {
		switch (browserName.toLowerCase().trim()) {
		case "chrome":	
			System.out.println("coming to chrome......");
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));	
			break;
		case "firefox":
			System.out.println("coming to firefox......");
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
			break;
		case "microsoftedge":
			System.out.println("coming to edge......");
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
			break;		
		default:
			System.out.println("wrong browser info..can not run on grid remote machine....");
			break;
		}
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		
	}


	public static WebDriver getDriver() {
		return(tlDriver.get());
	}
	

	public Properties initProperties() {
		// mvn clean install -Denv="qa"
		// mvn clean install

		prop = new Properties();

		FileInputStream ip = null;
		String envName = System.getProperty("env");

//		String envName = "qa";
		System.out.println("******env name is: " + envName);

		try {
			if (envName == null) {
				System.out.println("your env is null....So running in qa env");
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;
				default:
					System.out.println("Please enter proper environment name...");
					throw new FrameWorkException("wrong env: " + envName);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}


	public static String getScreenshot(String methodName) {
		
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()+".png";
				
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}


