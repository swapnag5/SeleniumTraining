package com.qa.opencart.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.util.ElementUtil;

public class ShoppingCartPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1.By locators
	private By shoppingCartHeader = By.cssSelector("div#content h1");
	//private By addedProduct = By.xpath("//td[@class='text-left']/a/following-sibling::small");
	//private By cartQty = By.cssSelector(".input-group.btn-block input");
	//2.page constructor
	public ShoppingCartPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public String getShoppingCartHeader()
	{
		return eleUtil.waitForElementVisible(shoppingCartHeader, Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
	}
//	public String checkCartQuantity()
//	{
//		 return eleUtil.doGetAttribute(cartQty, "value");
//	}
	
}
