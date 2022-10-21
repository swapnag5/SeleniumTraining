package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final  String LOGIN_PAGE_TITLE_CONST = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	
	public static final String ACCOUNT_PAGE_TITLE_CONST = "My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTION = "route=account/account";
	//public static final String ACCOUNT_PAGE_HEADER = "";
	public static final String SHOPPINGCART_PAGE_HEADER = "Shopping Cart  (0.00kg)";
	 
	public static final List<String> EXPECTED_ACCOUNT_SECTION_LIST = Arrays.asList("My Account",
			                                                                     "My Orders",
			                                                                     "My Affiliate Account",
			                                                                     "Newsletter");
	

	public static final int DEFAULT_ELEMENT_TIMEOUT = 10;
	public static final int DEFAULT_TIMEOUT = 5;
	public static final int DEFAULT_POLLING_TIMEOUT = 2;

	public static final String LOGOUT_SUCCESS_MESSEGE = "Account Logout";
	public static final String REGISTER_SUCCESS_MESSAGE="Your Account Has Been Created";
	
	//***************** Sheet Names ******************//
	public static final String REGISTER_TEST_DATA = "register";
	public static final String PRODUCT_TEST_DATA = "product";
	public static final String PRODUCT_INFO_TEST_DATA = "productInfo";

}
