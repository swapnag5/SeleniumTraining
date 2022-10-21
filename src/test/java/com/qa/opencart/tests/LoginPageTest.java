package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic-100: This Epic is for Login page of Opencart application")
@Story("LOGIN-101: design Login page with various features")

public class LoginPageTest extends BaseTest{ 

	@Description("Login page title test..")
    @Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest()
	{
		String actTitle = loginPage.getPageTitle();
		System.out.println("login page title is :"+actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE_CONST);
	}
	@Description("Login page url test..")
    @Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageUrlTest()
	{
		String actUrl = loginPage.getPageUrl();
		System.out.println("login page url is:"+actUrl);
		Assert.assertTrue(actUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	@Description("is forgot password link exist test..")
    @Severity(SeverityLevel.CRITICAL)
	@Test
	public void isForgottenPwdLinkExistTest()
	{
		Assert.assertTrue(loginPage.isForgottenPasswordLinkExist());
	}
	@Description("is register link exist test..")
    @Severity(SeverityLevel.CRITICAL)
	@Test
	public void isRegisterLinkExistTest()
	{
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	@Description("user able to login to opencart app test..")
    @Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginTest()
	{
		Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim())
		          .isLogoutLinkExist());
	}
}
