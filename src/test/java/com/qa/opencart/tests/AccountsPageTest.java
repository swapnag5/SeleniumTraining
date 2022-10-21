package com.qa.opencart.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.util.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic-200: This Epic is for Accounts page of Opencart application")
@Story("ACCPAGE-201: design Accounts page with various features")
public class AccountsPageTest extends BaseTest {

	

	@BeforeClass
	public void accSetUp() {
		accPage =loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
    @Description("Account Page title test..")
    @Severity(SeverityLevel.MINOR)
	@Test
	public void accPageTitleTest()
	{
		String actTitle = accPage.getAccPageTitle();
		System.out.println("title of the Account page is :"+actTitle);
		Assert.assertEquals(actTitle,Constants.ACCOUNT_PAGE_TITLE_CONST);
	}
    @Description("Accounts page url test..")
    @Severity(SeverityLevel.MINOR)
	@Test
	public void accPageURLTest()
	{
		String actUrl = accPage.getAccPageURL();
		System.out.println("title of the Account page is :"+actUrl);
		Assert.assertTrue(actUrl.contains(Constants.ACCOUNT_PAGE_URL_FRACTION));;
	}
	//	@Test
	//	public void accPageHeaderTest()
	//	{
	//		String header = accPage.getAccountPageHeader();
	//		System.out.println("title of the Account page is :"+header);
	//		Assert.assertEquals(Constants.ACCOUNT_PAGE_HEADER_CONST, Constants.DEFAULT_TIMEOUT);
	//	}
    @Description("Accounts page Header section list test..")
    @Severity(SeverityLevel.MINOR)
	@Test
	public void accPageSectionList()
	{
		List<String> sectionHeadersList = accPage.getsectionHeaders();
		System.out.println("Actual Account Section Header List :"+sectionHeadersList);
		Assert.assertEquals(sectionHeadersList, Constants.EXPECTED_ACCOUNT_SECTION_LIST);
	}
    @Description("log out link exist test")
    @Severity(SeverityLevel.CRITICAL)
	@Test
	public void logoutLinkTest()
	{
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
    @Description("search field exist test..")
    @Severity(SeverityLevel.CRITICAL)
	@Test
	public void searchExistTest()
	{
		Assert.assertTrue(accPage.isSearchFieldExist());
	}
	@DataProvider
	public Object[][] getSearchKey()
	{
		return new Object[][] {
			{"Macbook"},
			{"samsung"},
			{"Apple"}
		};
	}
	@Description("search test..")
    @Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider="getSearchKey")
	public void searchTest(String searchKey)
	{
		searchResPage = accPage.doSearch(searchKey);
		Assert.assertTrue(searchResPage.getSearchResultCount()>0);


	}
//	@DataProvider
//	public Object[][] getProductName()
//	{
//		return new Object[][] {
//			{"Macbook","MacBook Pro"},
//			{"samsung","Samsung Galaxy Tab 10.1"},
//			{"Apple","Apple Cinema 30\""}
//					
//		};
//	}
//	
	@DataProvider
	public  Object[][] getProductName(){
		Object[][] productTestData = ExcelUtil.getTestData(Constants.PRODUCT_TEST_DATA);
		return productTestData;
	}
	@Description("select the product test")
    @Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getProductName")
	public void selectProductTest(String searchKey,String productName)
	{
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		String productHeader = productInfoPage.getProductHeader();
		System.out.println("prduct header is :"+productHeader);
		Assert.assertEquals(productHeader,productName);
	}
	@Description("log out test")
    @Severity(SeverityLevel.BLOCKER)
	@Test(enabled = false)
	public void logoutTest()
	{
		Assert.assertEquals(accPage.clickOnLogout().getLogoutSuccessMessage(),Constants.LOGOUT_SUCCESS_MESSEGE);

	}
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][] {
			{"Macbook","MacBook Air",4},
			{"samsung","Samsung Galaxy Tab 10.1",7}
			
					
		};
	}
	@Description("product image count test..")
    @Severity(SeverityLevel.MINOR)
	@Test(dataProvider = "getProductData")
	public void productImageTest(String searchKey,String productName,int productImageCount)
	{
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
	    Assert.assertEquals(productInfoPage.getProductImageCount(),productImageCount);
	}
	
//	@DataProvider
//	public Object[][] getProductInfo()
//	{
//		return new Object[][] {
//			{"Macbook","MacBook Air","2"}//,
//			//{"samsung","Samsung Galaxy Tab 10.1","3"}
//		};
//	}
//	@Test(dataProvider = "getProductInfo")
//	public void addToCartTest(String searchKey,String productName,String quantity)
//	{
//		searchResPage = accPage.doSearch(searchKey);
//		productInfoPage = searchResPage.selectProduct(productName);
//		//productInfoPage.addToCart(quantity);
//		Assert.assertTrue(productInfoPage.addToCart(quantity));
//	}
	
	@DataProvider
	public Object[][] getProductInfo(){
		Object[][] productInfo = ExcelUtil.getTestData(Constants.PRODUCT_INFO_TEST_DATA);
		return productInfo;
	}
	@Description("adding the item to the cart test..")
    @Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider = "getProductInfo",enabled=false)
	public void addtoCartTest(String searchKey,String productName,String qty) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		String addToCartSuccessMssg =productInfoPage
				                         .enterQty(qty)
				                            .clickOnAddToCart()
				                              .getAddToCartSuccessMssg();
		softAssert.assertTrue(addToCartSuccessMssg.contains(productName));
		//validating the cart item updated or not
		String actCartItem = productInfoPage.getCartItemText();
		System.out.println("no of items in a cart:"+actCartItem);
		softAssert.assertTrue(actCartItem.contains(qty+" item(s)"));
	}
	@Description("view cart test..")
    @Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getProductInfo")
	public void viewCartTest(String searchKey,String productName,String qty) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		shoppingCartPage = productInfoPage
				             .enterQty(qty)
				               .clickOnAddToCart()
				                 .clickOnCart()
				                  .clickOnViewCart();
		String actShoppingHeader = shoppingCartPage.getShoppingCartHeader();
		Assert.assertEquals(actShoppingHeader, Constants.SHOPPINGCART_PAGE_HEADER);
	}
	
	
	@Description("Navigating to the shopping cart page(when click on shopping cart link) test..")
    @Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getProductInfo")
	public void shoppingCartTest(String searchKey,String productName,String quantity)
	{
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		String successMssg = productInfoPage
		   .enterQty(quantity).clickOnAddToCart().getAddToCartSuccessMssg(); 
		softAssert.assertTrue(successMssg.contains("shopping cart"));
		shoppingCartPage = productInfoPage.clickOnShoppingCart();
		
		String actShoppingHeader = shoppingCartPage.getShoppingCartHeader();
		softAssert.assertEquals(actShoppingHeader, Constants.SHOPPINGCART_PAGE_HEADER);
		softAssert.assertAll();
	}
	
	
	

	
	@DataProvider
	public Object[][] getProductInformation() {
		return new Object[][] {
			{"Macbook","MacBook Pro","name","MacBook Pro","price","$2,000.00"},
			{"Macbook","MacBook Pro","Availability","In Stock","price","$2,000.00"}
					
		};
	}

	@Test(dataProvider = "getProductInformation")
	public void productInfoTest(String searchKey,String searchVal,String metaHeader,String metaHdrVal,String price,String priceVal)
	{
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(searchVal);
		Map<String,String> actProductInfoMap = productInfoPage.getProductInformation();
		softAssert.assertEquals(actProductInfoMap.get(metaHeader), metaHdrVal);
		softAssert.assertEquals(actProductInfoMap.get(price), priceVal);
		//softAssert.assertEquals(actProductInfoMap.get("Availability"), "In Stock");
		softAssert.assertAll();
 //SoftAssert--When an assertion fails, don't throw an exception but record the failure. 
//Calling assertAll() will cause an exception to be thrown if at least one assertion failed.
	}
	
	@Test
	public void productDescriptionInfoTest() {
		searchResPage = accPage.doSearch("macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		softAssert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains("Leading-edge graphics"));
		softAssert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains("Designed for life on the road"));
		softAssert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains("Featuring 802.11n wireless technology"));
		softAssert.assertAll();
	}
}
