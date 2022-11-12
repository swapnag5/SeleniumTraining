package com.qa.opencart.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptUtil {

	private WebDriver driver;
	public  JavascriptUtil(WebDriver driver)
	{
		this.driver = driver;
	}
	//otherway to get title using javascript

	public  String getTitleByJS()
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		return js.executeScript("return document.title").toString();
	}

	//to generate alert using javascript

	public  void generateAlert(String message)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("alert('"+message+"')");
	}
	//to get entire text from page
	public  String getPageInnerText()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		return js.executeScript("return document.documentElement.innerText").toString();

	}
	//to refresh the page using js
	public  void refreshPageByJs() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("history.go(0)");
	}

	//drawing the border using js for highlighting somthing to enhance the framework
	public  void drawBorder(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.border='3px solid red'",element);
	}
	//to highlight the element using js--this tells where the driver is exactly
	public  void  flash(WebElement element) {
		String bgColor = element.getCssValue("backgroundColor");
		for(int i=0;i<15;i++)
		{
			changeColor("rgb(0,200,0)", element);
			changeColor(bgColor, element);
		}

	}
	public  void changeColor(String color,WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.backgroundColor='"+color+"'",element);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}
	//to click an element using JS
	public  void clickElementByJs(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",element);

	}
	//to scrool the page down using js
	public  void scrollPageDown() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	//overloaded scroll page down with specified height
	public  void scrollPageDown(String height) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo('"+height+"')");
	}
	//to scroll the page up using js
	public  void scrollPageUp() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	//it scrolls till the specified element
	public  void scrollIntoView(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)",element);

	}
	//sendkeys using js
	public  void sendKeysUsingWithId(String id,String value) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementById('"+id+"').value='"+value+"'");
	}
}




