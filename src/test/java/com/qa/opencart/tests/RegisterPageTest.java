package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.util.ExcelUtil;

public class RegisterPageTest extends BaseTest{

	@BeforeClass
	public void registerSetUp() {
		registerPage = loginPage.goToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getRegisterTestData() {
		Object[][] regData =  ExcelUtil.getTestData(Constants.REGISTER_TEST_DATA);
		return regData;
	}
	public String getRandomEmail() {
		Random random = new Random();
		 
		String email = "automation"+random.nextInt(1000)+"@gmail.com";
		return email;
	}
	@Test(dataProvider = "getRegisterTestData")
	public void registerTest(String firsName,String lastName,
			                 String telephone,String password,String subscribe) {
		
		Assert.assertTrue(registerPage.registerUser(firsName,lastName,getRandomEmail(),
                                                    telephone,password,subscribe));
	}
}
