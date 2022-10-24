package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	//1.private By locators:Object Repository

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.cssSelector("input.btn.btn-primary");
	private By forgetPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	private By sairam = By.linkText("sairam23");
	
	private By logoutSuccessMssg = By.cssSelector("div#common-success h1");


	//2.page constructor

	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	//3.perform page actions
	@Step("fetching the login page title of opencart application..")
	public String getPageTitle()
	{
		return eleUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE_CONST, Constants.DEFAULT_TIMEOUT);
	}
	@Step("fetching the login page url of open cart application..")
	public String getPageUrl()
	{
		return eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIMEOUT);
	}
	@Step("user able to login with username:{0} and password:{1}")
	public AccountsPage doLogin(String un,String pwd)
	{

		eleUtil.waitForElementVisible(emailId, Constants.DEFAULT_ELEMENT_TIMEOUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);		
		eleUtil.doClick(loginBtn);
		System.out.println("login creds are "+un+" : "+pwd);
		return new AccountsPage(driver);
	}
	@Step("is forgot password link exist...")
	public boolean isForgottenPasswordLinkExist()
	{
		return eleUtil.doIsDisplayed(forgetPwdLink);
	}
	@Step("is register link exist..")
	public boolean isRegisterLinkExist() {
		return eleUtil.doIsDisplayed(registerLink);
	}
    @Step("fetching the log out success message..")
	public String getLogoutSuccessMessage() {

		return eleUtil.waitForElementVisible(logoutSuccessMssg, Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
	}
    @Step("navigating to the register page after clicking on register link..")
	public RegisterPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	


}
