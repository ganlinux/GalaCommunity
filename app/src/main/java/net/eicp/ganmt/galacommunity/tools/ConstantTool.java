package net.eicp.ganmt.galacommunity.tools;

import android.net.Uri;

public class ConstantTool {
	public static String net_version = "1.0";
	public static String AppKey = "msapp";

	public static Boolean ISLOGINSTATE = false;
	public static Boolean IsGetRewardAddCard = false;
	//
	public static final int REQUEST_CODE_CLICK_ADDCARD = 12345;
	//SERVERNAME
	public static final String SERVERNAME_MYACCOUNT_ADDCARDBANK = "account.addbankcard";
	public static final String SERVERNAME_MYACCOUNT_ADDCARDSMS = "account.sendaddbrandcardsms";
	public static final String SERVERNAME_MYACCOUNT_GETCOMMISION = "order.getdoyongjin";
	public static final String SERVERNAME_MYACCOUNT_GETCARDTYPE = "account.getbankcardinfobybin";
	public static final String SERVERNAME_MYACCOUNT_DELETECARD = "account.deletebankcard";
	public static final String SERVERNAME_MYACCOUNT_GETCARDLIST = "account.getbankcardlist";

	public static final String SERVERNAME_LOGIN_LOGIN = "account.login";
	public static final String SERVERNAME_CHANGEPASSWORD_CODE = "account.sendchangepwdsms";
	public static final String SERVERNAME_CHANGEPASSWORD_RESET = "account.changepassword";
	public static final String SERVERNAME_REGISTER_CODE = "account.sendregistersms";
	public static final String SERVERNAME_REGISTER_REGISTER = "account.register";
	
	//sharedpreference
	public static final String STRING_SP_USERGUIDE = "userguide";
	public static final String STRING_SP_TOKEN = "token";
	public static final String STRING_SP_USERNAME = "username";
	public static final String STRING_SP_PEOPLENAME = "peoplename";
	
	public static boolean isMyCarsNoWifiRefresh=false;
	public static boolean isNewestCarsNoWifiRefresh=false;
	public static final int TIMER_COUNT = 60;
	public static Uri myCarsuri = Uri.parse("content://com.yichemaishou.mycarsdatachange");	
	public static final String URL_MYACCOUNT_HELP_CENTER = "http://www.yichemall.com/msapp/help/index.html";
}
 