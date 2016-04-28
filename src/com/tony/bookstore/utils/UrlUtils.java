package com.tony.bookstore.utils;

public class UrlUtils {
	public static String getRecommondUrl()
	{
		String url="http://"+Consts.SERVER_IP+":"+
				Consts.SERVER_PORT+"/dang/main/getrecommend.action";
		return url;
	}
	
	public static String getHotUrl()
	{
		String url="http://"+Consts.SERVER_IP+":"+
				Consts.SERVER_PORT+"/dang/main/gethot.action";
		return url;
	}
	
	public static String getNewUrl()
	{
		String url="http://"+Consts.SERVER_IP+":"+
				Consts.SERVER_PORT+"/dang/main/getnew.action";
		return url;
	}
	
	public static String getBookPicUrl(String picName)
	{
		String url="http://"+Consts.SERVER_IP+":"+Consts.SERVER_PORT
					+"/dang/productImages/"+picName;
		return url;
	}
	
	public static String getCodeImageUrl()
	{
		String url="http://"+Consts.SERVER_IP+":"+Consts.SERVER_PORT
					+"/dang/user/getImage.action";
		return url;
	}
	
	public static String getRegisterUserUrl()
	{
		String url="http://"+Consts.SERVER_IP+":"+Consts.SERVER_PORT
				+"/dang/user/register.action";
		return url;
	}
	
	public static String getUserLoginUrl()
	{
		String url="http://"+Consts.SERVER_IP+":"+Consts.SERVER_PORT
				+"/dang/user/login.action";
		return url;
	}
	
	public static String getAutoLoginUrl()
	{
		String url="http://"+Consts.SERVER_IP+":"+Consts.SERVER_PORT
				+"/dang/user/loginWithoutPwd.action";
		return url;
	}
	
	public static String getAddAddressUrl()
	{
		String url="http://"+Consts.SERVER_IP+":"+Consts.SERVER_PORT
				+"/dang/order/saveAdd.action";
		return url;
	}
}
