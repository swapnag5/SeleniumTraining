package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	//1.maintain  By locators from Accounts page
	
	private By header = By.cssSelector("div#logo a");
	private By 	sectionHeaders = By.cssSelector("div#content h2");
	private By logoutLink = By.linkText("Logout");
	private By search = By.xpath("//input[@placeholder='Search']");
	private By searchClick = By.xpath("//span[@class='input-group-btn']");
	
	//2.declare Account page constructor
	
	public AccountsPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//2.page actions
	@Step("getting  the title of the Accounts page..")
	public String getAccPageTitle()
	{
		return eleUtil.waitForTitleIs(Constants.ACCOUNT_PAGE_TITLE_CONST, Constants.DEFAULT_TIMEOUT);
	}
	@Step("getting the url of Accounts page..")
	public String getAccPageURL()
	{
		return eleUtil.waitForUrlContains(Constants.ACCOUNT_PAGE_URL_FRACTION, Constants.DEFAULT_TIMEOUT);
	}
	@Step("fetching the Accounts page header..")
	public String getAccountPageHeader()
	{
		return eleUtil.doGetText(header);
	}
	@Step("is search field exist..")
	public boolean isSearchFieldExist()
	{
		return eleUtil.waitForElementVisible(search,Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
	}
	@Step("user able to search with serchkey:{0} and navigating to Serch results page..")
	public SearchResultsPage doSearch(String searchKey)
	{
		if(isSearchFieldExist()) {
			eleUtil.doSendKeys(search,searchKey);
			eleUtil.waitForElementToBeClickableWithPolling(searchClick, Constants.DEFAULT_ELEMENT_TIMEOUT, Constants.DEFAULT_POLLING_TIMEOUT);
			return new SearchResultsPage(driver);
		}
		return null;
	}
	
	
	public List<String> getsectionHeaders()
	{
		List<WebElement> secList = eleUtil.getElements(sectionHeaders);
		List<String> secValList = new ArrayList<>();
		for(WebElement e:secList) {
			String text = e.getText();
			secValList.add(text);
		}
		return secValList;
	}
	@Step("is logout link exist..")
	public boolean isLogoutLinkExist()
	{
		return eleUtil.waitForElementVisible(logoutLink,Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
	}
	@Step("user able to logout after clicking on logout link..")
	public LoginPage clickOnLogout()
	{
		if(isLogoutLinkExist()) {
		 eleUtil.doClick(logoutLink);
		}
		return new LoginPage(driver);
	}
	

}
