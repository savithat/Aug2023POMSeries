  package com.qa.opencart.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic 100:open cart login page")
@Story("US 101: login page features")
@Feature("F50: Feature login page")
public class LoginPageTest extends BaseTest{
	
	@Description("login page title......")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	
	@Description("login page UrlTest......")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String url = loginPage.getLoginPageURL();
		Assert.assertTrue(url.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	
	
	@Description("forgotPwdLinkExist......")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3)
	public void forgotPwdLinkExistTest() {	
		boolean forgotPwdLink = loginPage.isforgotPwdLinkExist();
		Assert.assertTrue(forgotPwdLink);
	}
	
	
	
	@Description("login page title......")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 4)
	public void appLogoExistTest() {
		Assert.assertTrue(loginPage.logoExist());
	}
	
	
	
	
	@Description("login page title......")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 5)
	public void getRegisterPageTitleTest() {
		String actRegLinkText = loginPage.registrationLink();
		Assert.assertEquals(actRegLinkText, "Register");
	}
	

	
	@Description("login page Test.....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 6)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutExist());
		
	}
	
	
}
