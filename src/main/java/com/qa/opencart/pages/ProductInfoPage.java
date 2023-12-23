package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By headerLoc = By.tagName("h1");
	private By prodImgloc = By.cssSelector("a.thumbnail>img");	
	////li[text()='Brand: ']//parent::ul/li
	private By prodDetailloc = By.xpath("(//li[text()='Brand: ']//parent::ul/parent::div/ul)[1]/li");
	private By priceloc = By.xpath("(//li[text()='Brand: ']//parent::ul/parent::div/ul)[2]/li");

	private Map <String, String> prodListHm;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	public String getProductHeaderName() {
		String productHeaderValue = elementUtil.doElementGetText(headerLoc);
		System.out.println(productHeaderValue);
		return productHeaderValue;
	}
	
	
	public int productImageCount() {
		int imgCount = elementUtil.waitForVisibilityOfElements(prodImgloc, AppConstants.SHORT_DEFAULT_WAIT).size();
		System.out.println("imgCount"+ imgCount);
		return imgCount;
	}
	

	public void getMetaDataListlList() {
		List<String> prodTextList = elementUtil.getElementsTextList(prodDetailloc);
	//	System.out.println(prodTextList);
		
		prodListHm = new HashMap<String, String>();
		
		//.replace(",", "")
		for(String str: prodTextList) {
			String name =  str.split(":")[0].trim();
			String value =  str.split(":")[1].trim();
			prodListHm.put(name, value);
		}
		System.out.println("prodTextList: "+prodTextList);
	}
	
	
	public void getProductPriceList() {
		List<String> prodPriceList = elementUtil.getElementsTextList(priceloc);
		System.out.println(prodPriceList);
		prodListHm.put("price", prodPriceList.get(0));
		String exPrice = prodPriceList.get(1).split(":")[0].trim();
		String exPriceValue = prodPriceList.get(1).split(":")[1].trim();
		prodListHm.put(exPrice, exPriceValue);
//		System.out.println(prodPriceList);
	}
	
	public Map<String, String> getProductDetailList() {
	//	prodListHm.put("productName", getProductHeaderName());
		getMetaDataListlList();
		getProductPriceList();
		System.out.println(prodListHm);
		return prodListHm;
	}
	
	
}
