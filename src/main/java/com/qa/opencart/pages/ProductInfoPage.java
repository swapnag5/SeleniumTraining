package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	Map<String,String> productInfoMap;
	
	//1.by locators-object repository
	private By productHeader = By.cssSelector("div#content h1");
	private By totalImages = By.xpath("//ul[@class='thumbnails']//img");
	private By quantity = By.xpath("//input[@name='quantity']");
	private By addToCart = By.id("button-cart");
	private By addToCartSuccessMssg = By.cssSelector(".alert.alert-success.alert-dismissible");
	private By cart = By.xpath("//span[@id='cart-total']");
	private By viewCart = By.linkText("View Cart");
	private By shoppingCart = By.linkText("shopping cart");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");

	//2.page constructor
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//3.page actions
	public String getProductHeader()
	{
		return eleUtil.waitForElementVisible(productHeader, Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
	}
	
	public int getProductImageCount()
	{
		return eleUtil.waitForElementsVisibleByLocator(totalImages, Constants.DEFAULT_ELEMENT_TIMEOUT).size();
	}
	public ProductInfoPage enterQty(String qty) {
		eleUtil.doSendKeys(quantity, qty);
		return this;
	}
	public ProductInfoPage clickOnAddToCart() {
		eleUtil.doClick(addToCart);
		return this;
	}
	public String getAddToCartSuccessMssg() {
		return eleUtil.waitForElementVisible(addToCartSuccessMssg,Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
	}
	public String getCartItemText() {
		return eleUtil.doGetText(cart);
	}
	
	public ProductInfoPage clickOnCart() {
		eleUtil.waitForElementVisible(cart,Constants.DEFAULT_ELEMENT_TIMEOUT).click();;
		return this;
	}
	public ShoppingCartPage clickOnViewCart() {
		eleUtil.waitForElementVisible(viewCart,Constants.DEFAULT_ELEMENT_TIMEOUT).click();;
		return new ShoppingCartPage(driver);
	}
	

	public boolean isShoppingCartLinkExist() {
		return eleUtil.waitForElementVisible(shoppingCart, Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
	}
	public ShoppingCartPage clickOnShoppingCart()
	{
		if(isShoppingCartLinkExist()) {
		eleUtil.doClick(shoppingCart);
		}
		return new ShoppingCartPage(driver);
	}
	
	public Map<String,String> getProductInformation()
	{
		//we need store the data in hash map because the data available in key value format
		productInfoMap = new HashMap<String,String>();
		productInfoMap.put("name", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		productInfoMap.forEach((k,v)->System.out.println(k+" : "+v));
		return productInfoMap;
        

	}
	
	private void getProductMetaData() {
		
		//capture the meta data
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: In Stock
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		System.out.println("total product meta data :"+metaDataList.size());
		for(WebElement ele:metaDataList) {
			String meta[]=ele.getText().split(":");
			String metaKey = meta[0].trim();//Brand
			String metaValue = meta[1].trim();//Apple
			
			productInfoMap.put(metaKey, metaValue);//hash map does not maintain the order
		}
		
	}
	
	private void getProductPriceData() {
		//capture the pricing data
		
				List<WebElement> priceList = eleUtil.getElements(productPriceData);
				String price = priceList.get(0).getText().trim();
				String exTxPrice = priceList.get(1).getText().trim();
				productInfoMap.put("price", price);
				productInfoMap.put("exTaxPrice", exTxPrice);
				
	}
	
	public String getProductInfoPageInnerText() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String pageInnerText = js.executeScript("return document.documentElement.innerText").toString();
		System.out.println("######################\n"+pageInnerText+"\n##########################");
		return pageInnerText;
	}
	
}
