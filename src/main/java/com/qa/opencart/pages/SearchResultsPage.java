package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.util.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1.by locators-object repository
	private By results = By.cssSelector("div.product-layout.product-grid");
	
	//2.page constructor
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//3.page actions
	public int getSearchResultCount()
	{
		return eleUtil.waitForElementsVisibleByLocator(results, Constants.DEFAULT_ELEMENT_TIMEOUT).size();
	}
	public ProductInfoPage selectProduct(String productName) {
		By productNameLink = By.linkText(productName);
		eleUtil.doClick(productNameLink);
		return new ProductInfoPage(driver);
	}
}
