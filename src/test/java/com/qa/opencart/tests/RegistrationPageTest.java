package com.qa.opencart.tests;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest{
	
	//creted email using UUID
	public String randomEmail() {
		return "sam"+ UUID.randomUUID()+"@one.com";
		//	return "sam"+ System.currentTimeMillis() +"@one.com"; //
	}
	

	@BeforeTest
   	public void regSetUpTest() {
		registrationPage = loginPage.goToRegistartionPage();
	}
	
	@Test
   	public void getRegisterPageTitleTest() {
		String regPageTitle = registrationPage.getRegisterPageTitle();
		Assert.assertEquals(regPageTitle, "Register Account");
	}
	
//	@Test
//	public void randomEmailID() {	
//		
//		for(int i=0; i<3; i++) {
//		System.out.println("sam"+ System.currentTimeMillis() +"@one.com");
//		}
//	}
	

	@Test
	public void regPageUrlTest() {
		String url = registrationPage.getRegisterUrlFragment();
		Assert.assertTrue(url.contains(AppConstants.REG_PAGE_URL_FRACTION));
	}
	

			//Without Data provider	
//	@Test
//	public void userRegistrationTest() {
//		Boolean regSuccess = registrationPage.userRegistration("SamFirst1" ,"SamLast1", "sam1234@testmail.com", "9998887777", "SamPass123", "Yes");
//		Assert.assertTrue(regSuccess);
//		
//	}
	
	//register
	
	@DataProvider
	public Object[][] registrtionDataFromExcel() {
		Object[][] data = ExcelUtil.getExcelData("register");
		return data;
	}
	
	@DataProvider
	public Object[][] registrationData() {
		return new Object[][] {
			{"SamFirst1" ,"SamLast1", "9998887778", "SamPass123", "Yes"},
			{"SamFirst2" ,"SamLast2", "9998887779", "SamPass123", "No"},
			{"SamFirst3" ,"SamLast3",  "9998887776", "SamPass123", "Yes"}
			};
	}
	

	@Test(dataProvider="registrtionDataFromExcel")
	public void userRegistrationTest(String firstName, String lastName, String phoneNumber, String pwd, String subScribe) {
		Boolean regSuccess = registrationPage.userRegistration(firstName,lastName, randomEmail() , phoneNumber, pwd, subScribe);
		Assert.assertTrue(regSuccess);
		
	}
	
}
