package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
//	private By macBookProLoc = By.xpath("//a[text()='MacBook Pro']");
//	private By imagesLoc = By.tagName("img");

	public SearchResultPage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
//	public int productImageCount() {
//		int size = elementUtil.waitForVisibilityOfElements(imagesLoc, AppConstants.SHORT_DEFAULT_WAIT).size();
//		System.out.println(size);
//		return size;
//	}


	public ProductInfoPage selectProduct(String productName){
		elementUtil.waitForVisibilityOfElement(By.linkText(productName), AppConstants.SHORT_DEFAULT_WAIT).click();
		return new ProductInfoPage(driver);
	}
	

}
