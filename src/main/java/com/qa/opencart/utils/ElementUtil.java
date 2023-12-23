package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exception.FrameWorkException;

import io.qameta.allure.Step;

public class ElementUtil {
	
	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public By getBy(String locatorType, String locatorValue) {
		By by = null;

		switch (locatorType.toLowerCase().trim()) {
		case "id":
			by = By.id(locatorValue);
			break;
		case "name":
			by = By.name(locatorValue);
			break;
		case "class":
			by = By.className(locatorValue);
			break;
		case "xpath":
			by = By.xpath(locatorValue);
			break;
		case "css":
			by = By.cssSelector(locatorValue);
			break;
		case "linktext":
			by = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			by = By.partialLinkText(locatorValue);
			break;
		case "tag":
			by = By.tagName(locatorValue);
			break;

		default:
			System.out.println("wrong locator type is passed..." + locatorType);
			throw new FrameWorkException("WRONG LOCATOR TYPE");
		}

		return by;

	}

	// locatorType = "id", locatorValue = "input-email", value = "tom@gmail.com"
	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(locatorType, locatorValue).sendKeys(value);
	}

	@Step("Send Keys")
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClick(String locatorType, String locatorValue) {
		getElement(locatorType, locatorValue).click();
	}

	@Step("Get text")
	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}

	public String doElementGetText(String locatorType, String locatorValue) {
		return getElement(locatorType, locatorValue).getText();
	}

	public String doGetElementAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getBy(locatorType, locatorValue));
	}

	// WAF : capture the text of all the page links and return List<String>.
	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();// pc=0 {}
		for (WebElement e : eleList) {
			String text = e.getText();

			if (text.length() != 0) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}
	
	
	public List<String> getElementsTextListWithWait(By locator, int second) {
		List<WebElement> eleList = waitForVisibilityOfElements(locator, second);
		List<String> eleTextList = new ArrayList<String>();// pc=0 {}
		for (WebElement e : eleList) {
			String text = e.getText();

			if (text.length() != 0) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}

	// WAF: capture specific attribute from the list:
	public List<String> getElementsAttributeList(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);

		List<String> eleAttrList = new ArrayList<String>();// pc=0 {}

		for (WebElement e : eleList) {
			String attrValue = e.getAttribute(attrName);
			eleAttrList.add(attrValue);
		}

		return eleAttrList;

	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public boolean checkSingleElementPresent(By locator) {
		return driver.findElements(locator).size() == 1 ? true : false;
	}

	public boolean checkElementPresent(By locator) {
		return driver.findElements(locator).size() >= 1 ? true : false;
	}

	public boolean checkElementPresent(By locator, int totalElements) {
		return driver.findElements(locator).size() == totalElements ? true : false;
	}

	public void Search(By searchField, By suggestions, String searchKey, String suggName) throws InterruptedException {
		doSendKeys(searchField, searchKey);
		Thread.sleep(3000);
		List<WebElement> suggList = getElements(suggestions);

		System.out.println(suggList.size());

		for (WebElement e : suggList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(suggName)) {
				e.click();
				break;
			}
		}
	}

	public void clikcOnElement(By locator, String eleText) {
		List<WebElement> eleList = getElements(locator);
		System.out.println(eleList.size());
		for (WebElement e : eleList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(eleText)) {
				e.click();
				break;
			}
		}
	}
	
	
	
	//*********************Select drop down utils********************//
	
	private Select createSelect(By locator) {
		Select select = new Select(getElement(locator));
		return select;
	}
	
	public void doSelectDropDownByIndex(By locator, int index){
		createSelect(locator).selectByIndex(index);
	}
	
	
	public void doSelectDropDownByValue(By locator, String value){
		createSelect(locator).selectByValue(value);
	}
	
	
	public void doSelectDropDownByVisibleText(By locator, String visibleText){
		createSelect(locator).selectByVisibleText(visibleText);
	}
	
	
	
	public int getDropDownOptionCount(By locator){
		return createSelect(locator).getOptions().size();
	}
	
	
	
	public  List<String> getDropDownOption(By locator){
			
		List<WebElement> optionsList = createSelect(locator).getOptions();
		List<String> optionsTextList = new ArrayList<String>();
		
		for(WebElement ele: optionsList) {
			optionsTextList.add(ele.getText());
					}
		return optionsTextList;
	}
	
	
	
	/**
	 * This method is used to select the values from the drop down. It can select;
	 * 1. single selection
	 * 2. Multiple selection
	 * 3. All Selection: please pass "all" as a value to select all the values
	 * 
	 * **/
	
	public void selectDropDownOption(By locator, String country) {
		
		List<WebElement> optionsList = createSelect(locator).getOptions();
		
		System.out.println(optionsList.size());
		
		for(WebElement ele: optionsList) {
			System.out.println(ele.getText());
			if(ele.getText().equalsIgnoreCase(country)) {
				ele.click();
				break;
			}
		}
	}
	
	
	
	
	public boolean isDropDownMultiple(By locator) {
		
		return createSelect(locator).isMultiple() ? true: false;
	}
	
	public void selectDropDownMultiple(By locator, String... values) {
		
		
		if(isDropDownMultiple(locator)) {
			if(values[0].equalsIgnoreCase("all")) {
				List<WebElement> options = createSelect(locator).getOptions();
				for(WebElement option: options) {
					option.click();
				}
			} else {
				for(String value: values) {
					createSelect(locator).selectByVisibleText(value);
				}
			}
		}

	}
	
	
	// **********Actions********
	
public void doRightClick(By rightClickLoc, By rightClickOptionsLoc, String optiovlaue) {
		
		WebElement rightClickEle = getElement(rightClickLoc);
		
		Actions actions = new Actions(driver);
		actions.contextClick(rightClickEle).perform();
		
		List<WebElement> rightClickOptions = getElements(rightClickOptionsLoc);
//		System.out.println(rightClickOptions.size());
		
		for(WebElement ele: rightClickOptions) {
	//		System.out.println(ele.getText());
			if(ele.getText().equals(optiovlaue)) {
			//	Thread.sleep(1000);
				ele.click();
				break;
			}
		}
	}



public void parentChildMenu(By parentLoc, By childLoc) throws InterruptedException {
	Actions actions = new Actions(driver);
	actions.moveToElement(getElement(parentLoc)).build().perform();
	Thread.sleep(1000);
	doClick(childLoc);
}


public void multiMenuHandle(By paretlocator, By child1locator, By child2locator, By child3locator) throws InterruptedException {
	doclickByActions(paretlocator);
	Thread.sleep(1000);
	moveToElement(child1locator);
	Thread.sleep(1000);
	moveToElement(child2locator);
	Thread.sleep(1000);
	doclickByActions(child3locator);
}

public void moveToElement(By locator) {
	Actions actions = new Actions(driver);
	actions.moveToElement(getElement(locator)).build().perform();
}

public void doclickByActions(By locator) {
	Actions actions = new Actions(driver);
	actions.click(getElement(locator)).build().perform();
}

public void doSendKeysByActions(By locator, String value) {
	Actions actions = new Actions(driver);
	actions.sendKeys(getElement(locator), value).build().perform();
}


public void doSendKeysWithPauseByActions(By locator, char[] value) {
	Actions actions = new Actions(driver);
	for(char ch: value) {
		actions.sendKeys(driver.findElement(locator), String.valueOf(ch)).pause(500).build().perform();
	}
}	

//************  wait *******************

/**
 * An expectation for checking that an element is present on the DOM of a page.
 *  This does not necessarily mean that the element is visible.
 * @param locator
 * @param timeOut
 * @return
 */
 
@Step("wait for presence of element")
public  WebElement waitForPresenceOfElement(By locator, int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
}


public  WebElement waitForPresenceOfElement(By locator, int timeOut, int intervalTime) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
	return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
}



/**
 * An expectation for checking that an element is present on the DOM of a page and visible.
 *  Visibility means that the element is not only displayed but also has 
 *  a height and width that is greater than 0.
 * @param locator
 * @param timeOut
 * @return
 */
@Step("wait for visibility of element {0} : timeout{1} ")
public  WebElement waitForVisibilityOfElement(By locator, int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	 
}


public  WebElement waitForVisibilityOfElement(By locator, int timeOut, int intervalTime) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	 
}

/**
 * An expectation for checking that all elements present on the web page that match the locator are visible. 
 * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0. 
 * @param locator
 * @param timeOut
 * @return
 */

public  List<WebElement> waitForVisibilityOfElements(By locator, int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	 
}

/**
 * An expectation for checking that there is at least one element present on a web page.
 * @param locator
 * @param timeOut
 * @return
 */
public  List<WebElement> waitForPresesnceOfElements(By locator, int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	 
}



public void doClickWithWait(By locator, int timeOut) {
	waitForVisibilityOfElement(locator, timeOut).click();
}


public void doSendKeysWithWait(By locator, String value, int timeOut) {
	waitForVisibilityOfElement(locator, timeOut).sendKeys(value);
}


//partial Title
public String waitForTitleContains(String titleFraction, int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	try {
		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		}
	} catch (TimeoutException e) {
		System.out.println(titleFraction + "Value is not present...");
		e.printStackTrace();
	}
	return null;
}

//Title
@Step("waitng for the Page title{0}: and the timeout :{1}")
public String waitForTitle(String title, int timeOut){
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
	try {
	if(wait.until(ExpectedConditions.titleIs(title))) {
		return driver.getTitle();
	}
	}catch(TimeoutException e){
		System.out.println(title+ "Value is not present...");
		e.printStackTrace();
	}
	return null;
}


//partial url
public String waitForUrlContains(String urlFraction, int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	try {
		if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		}
	} catch (TimeoutException e) {
		System.out.println(urlFraction + "Value is not present...");
		e.printStackTrace();
	}
	return null;
}


//url
public String waitForUrl(String url, int timeOut){
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
	try {
	if(wait.until(ExpectedConditions.urlToBe(url))) {
		return driver.getCurrentUrl();
	}
	}catch(TimeoutException e){
		System.out.println(url + "Value is not present...");
		e.printStackTrace();
	}
	return null;
}

/////alert (Non-web elements  ----explicit wait(support web and non web elements),  impilicit wait only web elements)

public Alert waitForJSAlert(int timeOut){
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.alertIsPresent());
}

public void acceptJSAlert(int timeOut){
	waitForJSAlert(timeOut).accept();
}

public void dismissJSAlert(int timeOut){
	waitForJSAlert(timeOut).dismiss();
}

public String getJSAlertText(int timeOut){
	return waitForJSAlert(timeOut).getText();
}


public void enterValueOnJSAlert(int timeOut, String value){
	waitForJSAlert(timeOut).sendKeys(value);
}


////*************Frame

public void waitForFrameByLocator(By frameLocator, int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	 
}


public void waitForFrameByIdOrName(By frameIdOrName, int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIdOrName));
	 
}

public void waitForFrameByIndex(By frameIndex, int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	 
}

public void waitForFrameByElement(By frameElement, int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	 
}

/////window handle

public boolean checkNewWindowExist(int numWindows, int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	try {
	if(wait.until(ExpectedConditions.numberOfWindowsToBe(numWindows))) {
		return true;
		}
	}catch(TimeoutException e){
		System.out.println("Number of windows are not same.....");
		e.printStackTrace();
	}
	return false; 
}


/**
 * 
 * An expectation for checking an element is visible and enabled such that you can click it.
 * @param locator
 * @param timeOut
 */
public void clickElementWhenReady(By locator, int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	try { 
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		}catch(TimeoutException e) {
			System.out.println("Element is not clickable or enabled.....");
			e.printStackTrace();
		}
}



public WebElement waitElementWithFluentWait(By emailId, int timeOut, int timeInterval){
	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(timeOut))
			.pollingEvery(Duration.ofSeconds(timeInterval))
			.withMessage("....Time out is done....Element not forund...")
			.ignoring(NoSuchElementException.class)
			.ignoring(ElementNotInteractableException.class);
			
			return wait.until(ExpectedConditions.visibilityOfElementLocated(emailId));
}


public void waitForFrameWithFluentWait(String frameIdOrName, int timeOut, int timeInterval){
	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(timeOut))
			.pollingEvery(Duration.ofSeconds(timeInterval))
			.withMessage("....Time out is done....frame not forund...")
			.ignoring(NoSuchFrameException.class);
			
			 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIdOrName));
}


public Alert waitForJSAlertFluentWait(int timeOut, int timeInterval){
	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(timeOut))
			.pollingEvery(Duration.ofSeconds(timeInterval))
			.withMessage("....Time out is done.... Alert not appeared...")
			.ignoring(NoAlertPresentException.class);
			
			return  wait.until(ExpectedConditions.alertIsPresent());
}

///////**********Custom excption  **********



public WebElement retryingElement(By locater, int noOfTries) {
	WebElement element = null;

	int count = 0;

	while (count < noOfTries) {
		try {
		    element = getElement(locater);
			System.out.println("Element is found after "+count+" attempt for the locator "+ locater);
			break;
		} catch (NoSuchElementException e) {
			System.out.println("Element is not found after "+count+" attemptat for the locator " + locater);
			try {
				Thread.sleep(500);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		count++;
	}
	if(element == null) {
		System.out.println("Element is not found after "+ noOfTries + " attempts, with the time interval of 500 millisec" );
		throw new FrameWorkException("No such elment exception....");
	}
	return element;
}



public WebElement retryingElement(By locater, int noOfTries, int timeInterval) {
	
	WebElement element = null;
	int count = 0;

	while (count < noOfTries) {
		try {
		    element = getElement(locater);
			System.out.println("Element is found after "+count+" attempt for the locator "+ locater);
			break;
		} catch (NoSuchElementException e) {
			System.out.println("Element is not found after "+count+" attemptat for the locator " + locater);
			try {
				Thread.sleep(timeInterval);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		count++;
	}
	if(element == null) {
		System.out.println("Element is not found after "+ noOfTries + " attempts, with the time interval of 500 millisec" );
		throw new FrameWorkException("No such elment exception.....");
	}
	return element;
}


public boolean isPageLoaded(int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'")).toString();
	System.out.println("flag"+ flag);
	return Boolean.parseBoolean(flag);
}

//public boolean isPageLoaded(int timeOut) {
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
//	String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'")).toString();
//	return Boolean.parseBoolean(flag);
//}

	
}
