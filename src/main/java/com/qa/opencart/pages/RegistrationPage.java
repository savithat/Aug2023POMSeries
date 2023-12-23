package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage{
	
	private WebDriver driver;
	ElementUtil elementUtil;
	
	private By firstNameLoc = By.id("input-firstname");
	private By lastNameLoc = By.id("input-lastname");
	private By emailLoc = By.id("input-email");
	private By telePhoneLoc = By.id("input-telephone");
	private By pwdLoc = By.id("input-password");
	private By pwdConfirmLoc = By.id("input-confirm");
	private By subscribeYesLoc = By.xpath("(//label[text()='Subscribe']/parent::div/div//label)[1]");
	private By subscribeNoLoc = By.xpath("(//label[text()='Subscribe']/parent::div/div//label)[2]");
	private By privacyPolicyLoc = By.xpath("(//div[contains(text(), 'read and agree to the' )]/input)[1]");
	private By continueBtnLoc = By.xpath("(//div[contains(text(), 'read and agree to the' )]/input)[2]");
	
	private By contBtnSuccessPageLoc = By.xpath("//div[@class='pull-right']/a");
	private By successMessageLoc = By.tagName("h1");
	private By logOutLoc = By.xpath("//div/a[text()='Logout']");
	private By regLoc = By.xpath("//div/a[text()='Register']");
	
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	
	
	public String getRegisterPageTitle() {	
		String title = elementUtil.waitForTitle(AppConstants.REGISTER_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);	
		System.out.println(title);
		return title;
	}
	
	
	public String getRegisterUrlFragment() {
		String url = elementUtil.waitForUrlContains("route=account/register", AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("url:" +url);
		return url;
	}
	
	public Boolean userRegistration(String firstName, String lastName, String email, String telephone, String pwd, String subScribe) {
		elementUtil.waitForVisibilityOfElement(firstNameLoc, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(firstName);
		elementUtil.waitForVisibilityOfElement(lastNameLoc, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(lastName);
		elementUtil.waitForVisibilityOfElement(emailLoc, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(email);
		elementUtil.doSendKeys(telePhoneLoc, pwd);
		elementUtil.waitForVisibilityOfElement(pwdConfirmLoc, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(pwd);
		elementUtil.waitForVisibilityOfElement(pwdLoc, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(pwd);
		
		if(subScribe.equalsIgnoreCase("yes")) {
			elementUtil.doClick(subscribeYesLoc);
		}else {
			elementUtil.doClick(subscribeNoLoc);
		}
		
		elementUtil.doClick(privacyPolicyLoc);
		elementUtil.doClick(continueBtnLoc);
		
		String sucsessMessage = elementUtil.doElementGetText(successMessageLoc);
		System.out.println("sucsessMessage"+ sucsessMessage);
		if(sucsessMessage.contains(AppConstants.REG_SUCCESS_MESSAGE)) {
			elementUtil.doClick(logOutLoc);
			elementUtil.doClick(regLoc);
			return true;
		}else {
			return false;
		}
	}



	
	

	 
}
