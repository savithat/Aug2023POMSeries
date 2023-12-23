package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//Encapsulation (bylocators are private members, methods or behaviour is in public)
	
	//ByLocators 
	private By userNameLoc = By.id("input-email");
	private By passWordLoc = By.id("input-password");
	private By loginBtnLoc = By.cssSelector("input[type='submit']");
	private By forgotPwdLinkLoc = By.xpath("//a[text()='Forgotten Password']//preceding-sibling::input");
	private By logoLoc = By.xpath("//img[@alt='naveenopencart']");
	private By registerLinkLoc = By.xpath("//div[@class='list-group']/a[text()='Register']");
	
	
	//Page constructor..
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	//page actions or methods
	
	
	@Step("LoginPageTitle")
	public String getLoginPageTitle() {
//		String title = driver.getTitle();
		
		String title = elementUtil.waitForTitle(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);	
		System.out.println(title);
		return title;
	}
	
	@Step("LoginPageURL")
	public String getLoginPageURL() {
	//	String url = driver.getCurrentUrl();
		
		String url = elementUtil.waitForUrlContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);	
		System.out.println(url);
		return url;
	}
	
	
	@Step("forgotPwdLink")
	public boolean isforgotPwdLinkExist() {		
		return elementUtil.waitForVisibilityOfElement(forgotPwdLinkLoc, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	//	return driver.findElement(forgotPwdLink).isDisplayed();
		
	}
	
	
	@Step("logoExist")
	public boolean logoExist() {
		return elementUtil.waitForPresenceOfElement(logoLoc, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
//		return driver.findElement(logoLoc).isDisplayed();
	}
	
	
	@Step("registrationLink")
	public String registrationLink() {
		String regLinktext = elementUtil.doElementGetText(registerLinkLoc);
		System.out.println(regLinktext);
		return regLinktext;
	}
	
	
	@Step("user name {0}: password {1}")
	public AccountsPage doLogin(String username, String password) {
//		driver.findElement(userNameLoc).sendKeys(username);
//		driver.findElement(passWordLoc).sendKeys(password);
//		driver.findElement(passWordLoc).click();
		System.out.println("userName:"+username+ ", password:"+password);
		elementUtil.doSendKeys(userNameLoc, username);
		elementUtil.doSendKeys(passWordLoc, password);
		elementUtil.doClick(loginBtnLoc);
//		System.out.println("User is logged in");
		return new AccountsPage(driver);
	}
	
	
	@Step("RegistrationPage")
	public RegistrationPage goToRegistartionPage() {
		System.out.println("LoginPage URL: "+driver.getCurrentUrl());
		elementUtil.waitForVisibilityOfElement(registerLinkLoc, AppConstants.SHORT_DEFAULT_WAIT).click();	
		System.out.println("LoginPage URL: "+driver.getCurrentUrl());
		return new RegistrationPage(driver);
	}   
	
}
