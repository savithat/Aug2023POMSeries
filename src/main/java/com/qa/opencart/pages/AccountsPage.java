package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	ElementUtil elementUtil;
	
	//Encapsulation (bylocators are private members, methods or behaviour is in public)
	
	//ByLocators 
	private By logoLoc = By.xpath("//img[@alt='naveenopencart']");
	private By searchLoc = By.xpath("//input[@name='search']");
	private By searcButtonhLoc = By.xpath("//span//button[@type='button']");
	private By logOutLinkLoc = By.linkText("Logout");
	private By headersLoc = By.tagName("h2");
	
	
	//Page constructor..
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	
	//page actions or methods
	public String getAccountsPageTitle() {
		String title = elementUtil.waitForTitle(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);	
		System.out.println(title);
		return title;
	}
	
	
	public String getAccountsPageURL() {
		String url = elementUtil.waitForUrlContains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);	
		System.out.println(url);
		return url;
	}
	
	public boolean isLogoutExist() {
		return elementUtil.waitForPresenceOfElement(logOutLinkLoc, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	
	public boolean isSearchExist() {
		return elementUtil.waitForPresenceOfElement(searchLoc, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	
	public boolean logoExist() {
		return elementUtil.waitForPresenceOfElement(logoLoc, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	
	public int getHeadersCountList() {
		return  elementUtil.getElementsCount(headersLoc);
		
	}
	
	public List<String> getHeadersTextList() {
		List<String> headerEles = elementUtil.getElementsTextListWithWait(headersLoc, AppConstants.MEDIUM_DEFAULT_WAIT);
		return headerEles;
	}
	
	
	public SearchResultPage doSearch(String searchKey) {
		elementUtil.waitForVisibilityOfElement(searchLoc, AppConstants.SHORT_DEFAULT_WAIT).clear();
		elementUtil.waitForVisibilityOfElement(searchLoc, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(searchKey);
		elementUtil.doClick(searcButtonhLoc);
		return new SearchResultPage(driver);
		
	}
	
}
