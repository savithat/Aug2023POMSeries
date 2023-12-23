package com.qa.opencart.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public final static String LOGIN_PAGE_TITLE = "Account Login";
	public final static String LOGIN_PAGE_URL_FRACTION = "naveenautomationlabs.com";
	
	public final static String ACCOUNTS_PAGE_TITLE = "My Account";
	public final static String ACCOUNTS_PAGE_URL_FRACTION = "?route=account/account";
	public final static List<String> ACCOUNTS_PAGE_HEADER_LIST = new ArrayList<String>(Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter"));	
	public final static int ACCOUNTS_PAGE_HEADER_COUNT = 4;
	
	public final static int SHORT_DEFAULT_WAIT = 5;
	public final static int MEDIUM_DEFAULT_WAIT = 10;
	public final static int LONG_DEFAULT_WAIT = 20;
	
	public final static int POLLING_DEFAULT_WAIT = 2;
	
	
	public final static String REGISTER_PAGE_TITLE = "Register Account";
	public final static String REG_PAGE_URL_FRACTION = "route=account/register";
	public final static String REG_SUCCESS_MESSAGE = "Your Account Has Been Created!";
}
