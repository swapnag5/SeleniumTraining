package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.util.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1.By locators
	private By fName = By.id("input-firstname");
	private By lName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPwd = By.id("input-confirm");
	private By subscribeYes = By.xpath("//input[@name='newsletter' and @value='1']");
	private By subscribeNo = By.xpath("//input[@name='newsletter' and @value='0']");
	private By privacyPolicy = By.name("agree");
	private By continueBtn = By.cssSelector("input.btn.btn-primary");
	private By registerSuccess = By.cssSelector("div#content h1");
	private By logout = By.linkText("Logout");
	private By register = By.linkText("Register");

	//2.page class constructor
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	//3.page actions
	public boolean registerUser(String fName,String lName,String email,String telephone,
			                  String password,String subscribe) {
		
		eleUtil.waitForElementVisible(this.fName, Constants.DEFAULT_ELEMENT_TIMEOUT).sendKeys(fName);
		eleUtil.doSendKeys(this.lName, lName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPwd, password);
		
		if(subscribe.equals("yes")) {
			eleUtil.doClick(subscribeYes);
		}else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(privacyPolicy);
		eleUtil.doClick(continueBtn);
		String registerSuccessMssg = eleUtil.waitForElementVisible(registerSuccess,Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
		if(registerSuccessMssg.contains(Constants.REGISTER_SUCCESS_MESSAGE)) {
			
			eleUtil.doClick(logout);
			eleUtil.doClick(register);
			return true;
		}else {
			//eleUtil.doClick(logout);
			eleUtil.doClick(register);
		}
		return false;
		
	}
}
