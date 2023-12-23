package com.qa.opencart.tests;


import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoTest extends BaseTest{
	
	
	@BeforeClass
	public void ProductInfoSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
		
	//Without Data provider
//	@Test
//	public void productImageCountTest() {
//		searchResultPage = accPage.doSearch("macBook");
//		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
//		int actProductImgCount = productInfoPage.productImageCount();
//		Assert.assertEquals(actProductImgCount, 4);
//		}
	
	
	@DataProvider
	public Object[][] searchData() {
		return new Object[][] {
			{"macBook","MacBook Pro", 4},
			{"macBook","MacBook Air", 4},
			{"macBook","MacBook", 5}
			};
	}
	
	
	@Test(dataProvider = "searchData")
	public void productImageCountTest(String searchKey, String selcetedProductKey, int imgCount) {
		searchResultPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(selcetedProductKey);
		int actProductImgCount = productInfoPage.productImageCount();
		Assert.assertEquals(actProductImgCount, imgCount);
		}
	
	
	//reading data from excel
	@DataProvider
	public Object[][] productDataFromExcel() {
		Object[][] data = ExcelUtil.getExcelData("product");
		return data;
	}
	
	
	//below method is same as productImageCountTest
	//reading from excel data is all are string so here we have convert int to string That is only line differnt
	@Test(dataProvider = "productDataFromExcel")
	public void productImageCountTestExcel(String searchKey, String selcetedProductKey, String imgCount) {
		searchResultPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(selcetedProductKey);
		int actProductImgCount = productInfoPage.productImageCount();
		Assert.assertEquals(String.valueOf(actProductImgCount), imgCount);
		}
	
	
	
	@Test()
	public void productDetailListTest() {
		searchResultPage = accPage.doSearch("macBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> productMap = productInfoPage.getProductDetailList();
		
		softAssert.assertEquals(productMap.get("Brand"), "Apple");
		softAssert.assertEquals(productMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productMap.get("Reward Points"), "800");
		
		softAssert.assertEquals(productMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productMap.get("Ex Tax"), "$2,000.00");
		
		softAssert.assertAll();
	}
	
}


