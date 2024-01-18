package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;


public class BaseTest{
	
	DriverFactory df;
	protected WebDriver driver;
	protected Properties prop;
	
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultPage searchResultPage;
	protected ProductInfoPage productInfoPage;
	protected RegistrationPage registrationPage;
	protected SoftAssert softAssert;
	
	//, "browserversion","testname"
	//, String browserVersion, String testName
	@Parameters({"browser", "browserversion","testname"})
	@BeforeTest
	public void setUp (String browserName, String browserVersion, String testName) {
		df = new DriverFactory(); 
		prop = df.initProperties();
		softAssert = new SoftAssert();
		
		if(browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testname", testName);
		}
		
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
