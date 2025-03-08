package com.qa.opencart.factory;
 
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	
	public OptionsManager(Properties prop){
		this.prop = prop;
	}
	
	
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
//		co.addArguments("--no-sandbox");
//		co.addArguments("--disable-dev-shm-usage");


//		if(Boolean.parseBoolean(System.getProperty("headless"))) co.addArguments("--headless");
//		if(Boolean.parseBoolean(System.getProperty("incognito"))) co.addArguments("--incognito");
		
		/**
		 * below code should add to run on grid
		 */
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("browserName", "chrome");
			
//			co.addArguments("start-maximized"); // open Browser in maximized mode
//			co.addArguments("disable-infobars"); // disabling infobars
//			co.addArguments("--disable-extensions"); // disabling extensions
//			co.addArguments("--disable-gpu"); // applicable to windows os only
//			co.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//			co.addArguments("--no-sandbox"); // Bypass OS security model
			
/**
 *  below code is for selenoid			
 * 
 */
//			co.setCapability("enableVNC", true);
//			co.setBrowserVersion(prop.getProperty("browserversion").trim());
//			
//			Map<String, Object> selenoidOptions = new HashMap<>();
//			selenoidOptions.put("screenResolution", "1280x1024x24");
//			selenoidOptions.put("enableVNC", true);
//			selenoidOptions.put("name", prop.getProperty("testname"));
//			co.setCapability("selenoid:options", selenoidOptions);

		}
		
		return co;
	}
	
	
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless"))) fo.addArguments("--headless--");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) fo.addArguments("--incongnito--");
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setCapability("browserName", "firefox");
			
//			fo.addArguments("start-maximized"); // open Browser in maximized mode
//			fo.addArguments("disable-infobars"); // disabling infobars
//			fo.addArguments("--disable-extensions"); // disabling extensions
//			fo.addArguments("--disable-gpu"); // applicable to windows os only
//			fo.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//			fo.addArguments("--no-sandbox"); // Bypass OS security model
			
			/**
			 *  below code is for selenoid			
			 * 
			 */
//			fo.setCapability("enableVNC", true);
//			fo.setBrowserVersion(prop.getProperty("browserversion").trim());
						
							
//			Map<String, Object> selenoidOptions = new HashMap<>();
//			selenoidOptions.put("screenResolution", "1280x1024x24");
//			selenoidOptions.put("enableVNC", true);
//			selenoidOptions.put("name", prop.getProperty("testname"));
//			fo.setCapability("selenoid:options", selenoidOptions);
		}
		
		return fo;
	}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless"))) eo.addArguments("--headless--");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) eo.addArguments("--incongnito--");
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			eo.setCapability("browserName", "MicrosoftEdge");
		
//			eo.addArguments("start-maximized"); // open Browser in maximized mode
//			eo.addArguments("disable-infobars"); // disabling infobars
//			eo.addArguments("--disable-extensions"); // disabling extensions
//			eo.addArguments("--disable-gpu"); // applicable to windows os only
//			eo.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//			eo.addArguments("--no-sandbox"); // Bypass OS security model
		}
		
		return eo;
	}
	
}
