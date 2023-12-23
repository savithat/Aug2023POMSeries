package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accountPageTitleTest() {
		String title = accPage.getAccountsPageTitle();
		Assert.assertEquals(title, AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void accountsPageUrlTest() {
		String url = accPage.getAccountsPageURL();
		Assert.assertTrue(url.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION));
	}
	
	@Test
	public void isLogoExistTest() {
		Assert.assertTrue(accPage.logoExist());
	}
	
	
//	@Test
//	public void isLogOutExistTest() {
//		Assert.assertTrue(accPage.isLogoutExist());
//	}
	
	
	@Test
	public void isSearchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}
	
	
	public void checkHeadersCountTest() {	
//		List<String> expectList = new ArrayList<String>(Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter"));
		Assert.assertEquals(accPage.getHeadersCountList(), AppConstants.ACCOUNTS_PAGE_HEADER_COUNT);
	}
	
	@Test
	public void checkHeadersListTest() {	
//		List<String> expectList = new ArrayList<String>(Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter"));
		Assert.assertEquals(accPage.getHeadersTextList(), AppConstants.ACCOUNTS_PAGE_HEADER_LIST);
	}
	

	@Test
	public void searchTest() {	
		searchResultPage = accPage.doSearch("macbook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		String actualProductHeaderValue = productInfoPage.getProductHeaderName();
		Assert.assertEquals(actualProductHeaderValue, "MacBook Pro");
	}

		
	
	
	


}
