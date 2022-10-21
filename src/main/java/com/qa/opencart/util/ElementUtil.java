package com.qa.opencart.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;

	//constructor by passing parameter driver
	public ElementUtil(WebDriver driver)
	{
		this.driver = driver;
	}

	//return the locator by passing string parameters
	public By getBy(String locatorType ,String locatorValue)
	{
		By locator = null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "classname":
			locator = By.name(locatorValue);
			break;
		case "xpath":
			locator = By.name(locatorValue);
			break;
		case "cssselector":
			locator = By.name(locatorValue);
			break;
		case "linktext":
			locator = By.name(locatorValue);
			break;
		case "partiallinktext":
			locator = By.name(locatorValue);
			break;
		case "tagname":
			locator = By.name(locatorValue);
			break;
		default:
			break;
		}
		return locator;
	}

	//perform the sending text action by string values
	public  void doSendKeys(String value,String locatorType,String locatorValue)
	{
		getElement(getBy(locatorType,locatorValue)).sendKeys(value);
	}
	//perform send text action by By locator
	public void doSendKeys(By locator,String value)
	{
		WebElement ele= getElement(locator);
		ele.clear();
		getElement(locator).sendKeys(value);

	}
	//get the element using By locator
	public  WebElement getElement(By locator)
	{
		return driver.findElement(locator);
	}

	//perform click a button action

	public void doClick(By locator)
	{
		getElement(locator).click();
	}

	//to capture the text
	public String doGetText(By locator)
	{
		return getElement(locator).getText();

	}
	//capture the attribute
	public String doGetAttribute(By locator ,String attName)
	{
		return getElement(locator).getAttribute(attName);

	}

	//whether the element is displayed or not using isDisplayed() method

	public boolean doIsDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
	}

	/**double conditions whether the element really displayed or not .
	   Sometimes isdisplayed() alone wont give accurate**/

	public  boolean isElementDisplayed(By locator)
	{
		WebElement ele = getElement(locator);
		List<WebElement> elementsList= getElements(locator);

		if(elementsList.size()>0) {
			if(ele.isDisplayed())
				return true;
		}
		return false;
	}

	//if we are not allowed to use isDisplayed() mthd then we check the list of elements size > 0

	public  boolean isElementDisplayedWithSize(By locator)
	{
		List<WebElement> elementsList= getElements(locator);

		if(elementsList.size()>0) {
			return true;
		}
		return false;
	}

	//whether the element is enabled or not
	public boolean doIsEnabled(By locator)
	{
		return getElement(locator).isEnabled();
	}

	//findElements

	public  List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}

	//total elements size in a list
	public  int getElementsCount(By locator)
	{
		return getElements(locator).size();
	}

	//capturing the attribute from a list of elements
	public  List<String> getElementsAttributeList(By locator , String attName)
	{
		List<WebElement> list = getElements(locator);
		List<String> attList = new ArrayList<String>();
		for(WebElement e:list)
		{
			String attValue = e.getAttribute(attName);
			//System.out.println(attValue);
			attList.add(attValue);
		}
		return attList;
	}
	//capturing the text of elements from a list

	public  List<String> getElementsTextList(By locator)
	{
		List<WebElement> list = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e: list)
		{
			String eleText = e.getText();
			eleTextList.add(eleText);
		}
		return eleTextList;
	}

	//get the suggestion list from the google search
	public  void getGoogleSuggestionList(By locator,String searchKey) throws InterruptedException
	{
		driver.findElement(By.name("q")).sendKeys(searchKey);

		Thread.sleep(5);
		List<WebElement> suggList = driver.findElements(locator);
		for(WebElement e : suggList)
		{
			String text = e.getText();
			System.out.println(text);
		}
	}
	//selecting particular elmnt from the suggestion list
	public  void selectElementFromSuggestionList(By locator,String searchKey,String selValue) throws InterruptedException
	{
		driver.findElement(By.name("q")).sendKeys(searchKey);

		Thread.sleep(5);
		List<WebElement> suggList = driver.findElements(locator);
		for(WebElement e : suggList)
		{
			String text = e.getText();
			Thread.sleep(2);
			System.out.println(text);
			if(text.contains(selValue)) {
				e.click();
				break;
			}
		}
	}

	//----------------DropDown Using Select class------------------// 

	public  void selectDropdownByIndex(By locator , int i)
	{
		Select select = new Select(getElement(locator));
		select.selectByIndex(i);
	}

	public  void selectDropdownByVisibleText(By locator , String visibleText)
	{
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
	}

	public  void selectDropdownByValue(By locator , String valAttribute)
	{
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(valAttribute);
	}

	//select a value from drop down using getOptions method
	public  void dropdownSelectValueWithGetOptions(By locator,String value)
	{
		Select select = new Select(getElement(locator));
		List<WebElement> dropdownList = select.getOptions();
		for(WebElement e : dropdownList)
		{
			String text = e.getText();
			System.out.println(text);
			if(text.contains(value))
			{
				e.click();
				break;
			}
		}	
	}

	//capture the size of drop down list

	public int getDropDownValuesCount(By locator)
	{
		Select select = new Select(getElement(locator));
		List<WebElement> dropdownList = select.getOptions();
		return dropdownList.size();

	}

	//------------------Drop down Without Using Select class--------------------//

	//generic method for drop down without select class

	public  void dropdownSelectValueUsingLocator(By locator,String value)
	{
		List<WebElement> optionsList =getElements(locator);
		for(WebElement e : optionsList)
		{
			String text = e.getText();
			System.out.println(text);
			if(text.contains(value))
			{
				e.click();
				break;
			}
		}
	}
	//using String locator
	public  List<String> dropdownSelectValue(By locator,String value)
	{
		List<WebElement> optionsList =getElements(locator);
		List<String> selectOptionsList = new ArrayList<>();
		for(WebElement e : optionsList)
		{
			String text = e.getText();
			System.out.println(text);
			selectOptionsList.add(value);
		}
		return selectOptionsList;


	}
	//multi selection from drop down list
	public  void selectMultiChoice(By locator,String... value)//3 . parameter is another way of array representation
	{
		List<WebElement> choiceList=getElements(locator);

		for(WebElement e : choiceList)
		{
			String text = e.getText();
			System.out.println(text);
			for(int i=0;i<value.length;i++)
			{
				if(text.equals(value[i]))
				{
					e.click();
					break;
				}
			}


		}
	}
	//it will work for single select and multi select and all selection from drop down list
	public  void selectChoice(By locator,String... value)//3 . parameter is another way of array representation
	{
		List<WebElement> choiceList=getElements(locator);
		boolean flag = false;//what if user pass wrong choice

		if(value[0].equalsIgnoreCase("all"))
		{
			for(WebElement e: choiceList)
			{
				e.click();
				flag = true;
			}
		}
		else
		{
			for(WebElement e : choiceList)
			{
				String text = e.getText();
				//System.out.println(text);
				for(int i=0;i<value.length;i++)
				{
					if(text.equals(value[i]))
					{
						flag = true;
						e.click();
						break;
					}
				}


			}
		}
		if(flag==false)
		{
			System.out.println("choice is not available "+value[0]);
		}
	}


	/******************* Actions Util********************/

	//drag and drop action
	public  void dragAndDropAction(By source,By target)
	{
		Actions act = new Actions(driver);
		act.clickAndHold(getElement(source)).moveToElement(getElement(target)).release().build().perform();

	}


	/*** moveToElement() ****///Two level menu handle--parent to child menu
	public  void levelTwoMenuHandling(By parentMenu,By childMenu) throws InterruptedException
	{
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();

		Thread.sleep(2000);

		getElement(childMenu).click();

	}

	//multi level menu handling

	public  void multiLevelMenuHandling(By parentMenu,By l1,By l2,By l3,By l4) throws InterruptedException {

		Actions act = new Actions(driver);

		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(1000);

		act.moveToElement(getElement(l1)).perform();
		Thread.sleep(1000);

		act.moveToElement(getElement(l2)).perform();
		Thread.sleep(1000);

		act.moveToElement(getElement(l3)).perform();
		Thread.sleep(1000);

		getElement(l4).click();


	}

	/********* contextClick(): right click*******/

	//select the item form right click menu directly(direct item locator)
	public  void selectRightClickMenuItem(By rightClick,By rightMenuItem)
	{
		Actions act = new Actions(driver);
		act.contextClick(getElement(rightClick)).perform();
		getElement(rightMenuItem).click();

	}
	//get the list of right click menu items using the context click
	public  List<String> getRightClickMenuList(By rightClick,By rightMenuItemList)
	{
		Actions act = new Actions(driver);
		act.contextClick(getElement(rightClick)).perform();


		List<WebElement> right_click_list = getElements(rightMenuItemList);
		List<String> rightMenuList = new ArrayList<String>();

		for(WebElement e: right_click_list)
		{
			String text = e.getText();
			rightMenuList.add(text);
		}
		return rightMenuList;

	}
	/************************** Actions sendKeys and click ****************************/

	public  void  doActionsSendKeys(By locator,String value)
	{
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	public  void  doActionsClick(By locator)
	{
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	/************************ Wait Utils *************************/

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * @param locator
	 * @param timeOut
	 * @return
	 */

	public  WebElement waitForElementPresence(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible.Visibility means that the element is not only displayed but 
	 * also has a height and width that is greater than 0.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public  WebElement waitForElementVisible(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public  void clickElementWhenReady(By locator,int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

	}
	public  void waitForElementToBeClickableWithPolling(By locator,int timeOut,int pollingTime)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10),Duration.ofMillis(1000));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	/**
	 * An expectation for checking that the title contains a case-sensitive substring
	 * @param titleFraction
	 * @param timeOut
	 * @return
	 */
	public  String waitForTitleContains(String titleFraction,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.titleContains(titleFraction)))
		{
			return driver.getTitle();
		}
		return null;
	}
	/**
	 * An expectation for checking the title of a page.
	 * @param title
	 * @param timeOut
	 * @return
	 */
	public  String waitForTitleIs(String title,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.titleIs(title)))
		{
			return driver.getTitle();
		}
		return null;
	}
	/**
	 * An expectation for the URL of the current page to contain specific text.
	 * @param fractionUrl
	 * @param timeOut
	 * @return
	 */

	public  String waitForUrlContains(String fractionUrl, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.urlContains(fractionUrl))) {
			return driver.getCurrentUrl();
		}
		return null;
	}
	/**
	 * An expectation for the URL of the current page to be a specific url.
	 * @param url
	 * @param timeOut
	 * @return
	 */
	public  String waitForUrlIs(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.urlToBe(url))) {
			return driver.getCurrentUrl();
		}
		return null;
	}
	/***************************Alert using Explicit wait*************************/

	public  Alert waitForAlert(int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait for alert and then switch to alert
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public  String doGetAlertText(int timeOut)
	{
		return waitForAlert(timeOut).getText();
	}
	public  void acceptAlert(int timeOut)
	{
		waitForAlert(timeOut).accept();
	}
	public  void dismissAlert(int timeOut)
	{
		waitForAlert(timeOut).dismiss();
	}
	public  void alertSendKeys(int timeOut,String value)
	{
		waitForAlert(timeOut).sendKeys(value);
	}
	/******************* Frame using Explicit wait *********/
	public  void waitForFrameByLocator(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		//it checks frame is available and if it is switch the driver into frame
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));

	}
	public  void waitForFrameByIndex(int frameIndex,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		//it checks frame is available and if it is switch the driver into frame
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));

	}
	public  void waitForFrameByElement(WebElement frameElement,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		//it checks frame is available and if it is switch the driver into frame
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));

	}

	/*************** Wait for Elements  ****************/
	/**
	 * An expectation for checking that all elements present on the web page that match 
	 * the locator are visible. 
	 * Visibility means that the elements are not only displayed but also 
	 * have a height and width that is greater than 0.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public  List<WebElement> waitForElementsVisibleByLocator(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}
	/**
	 * An expectation for checking that there is at least one element present on a web page
	 * Default polling time = 500 ms;
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public  List<WebElement> waitForElementsPresenceByLocator(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}
	/**
	 * An expectation for checking that there is at least one element present on a web page.
	 * Custemized polling time
	 * @param locator
	 * @param timeOut
	 * @param pollingTime
	 * @return
	 */
	public  List<WebElement> waitForElementsPresenceByLocator(By locator,int timeOut,int pollingTime)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut),Duration.ofMillis(pollingTime));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

	/***************** using FluentWait class*****************/

	public  WebElement fluentWaitForPresenceOfElement(By locator,int timeOut,
			int pollTime,String message) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(pollTime))
				.ignoring(NoSuchElementException.class,StaleElementReferenceException.class)
				.withMessage(message);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
	/**************** without using Selenium waits *****************/

	public  WebElement retryingForElement(By locator,int timeOut,int pollTime) {

		WebElement element = null;
		int attempts = 0;
		while(attempts < timeOut)
		{
			try {
				element = driver.findElement(locator);
				break;//throws NoSuchEle Exception if it is not found so surround by try catch

			} catch (NoSuchElementException e) {
				System.out.println("element is not found in attempts of :"+timeOut+"for"+locator);

				try {
					Thread.sleep(pollTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
		}
		if(element==null)
		{
			try {
				throw new Exception("ELEMENTISNOTFOUND");
			}
			catch(Exception e)
			{
				System.out.println("element is not found in attempts of :"+attempts+"sec : with interval of :"+pollTime);
			}

		}
		return element;
	}

	/********************* wait for page loading using JavaScriptExecutor **************/
	
	public  void waitForPageLoading(int timeOut) {

		long endTime = System.currentTimeMillis()+timeOut;

		while(System.currentTimeMillis() < endTime) {
			//indicates that driver can execute the javascript
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String state = js.executeScript("return document.readyState").toString();
			System.out.println("page is :"+state);
			if(state.equals("complete"))
			{
				System.out.println("the page is fully loaded");
				break;
			}
		}
	}
}
