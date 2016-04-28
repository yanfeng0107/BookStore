
package com.tony.bookstore.app;

import org.xutils.x;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.tony.bookstore.entity.Cart;
import com.tony.bookstore.entity.UserInfo;

public class BookstoreApplication extends Application {
	public static BookstoreApplication app;
	private ImageLoader imageLoader;
	private Gson gson;
	private RequestQueue queue;
	private static Cart cart;
	private static boolean isLogin;
	private static UserInfo userInfo;
	private static String token;
	private static String cookie;
	
	
	private static final String SP_NAME="bs_sp";
	private static final String SP_KEY_TOKEN="token";
	
	@Override
	public void onCreate() {
		super.onCreate();
		app=this;
		x.Ext.init(this);
		x.Ext.setDebug(true);
		setGson(new Gson());
		queue=Volley.newRequestQueue(this);
		imageLoader=new ImageLoader(queue,new BitMapCache());
		cart=Cart.readCart();
		//cart=new Cart();
		readToken();
	}
	
	public static Cart getCart()
	{
		return BookstoreApplication.cart;
	}
	
	public RequestQueue getQueue() {
		return queue;
	}
	public void setQueue(RequestQueue queue) {
		this.queue = queue;
	}
	public Gson getGson() {
		return gson;
	}
	public void setGson(Gson gson) {
		this.gson = gson;
	}
	
	public ImageLoader getImageLoader() {
		return imageLoader;
	}
	public void setImageLoader(ImageLoader imageLoader) {
		this.imageLoader = imageLoader;
	}

	public static boolean isLogin() {
		return isLogin;
	}

	public static void setLogin(boolean isLogin) {
		BookstoreApplication.isLogin = isLogin;
	}

	public static UserInfo getUserInfo() {
		return userInfo;
	}

	public static void setUserInfo(UserInfo userInfo) {
		BookstoreApplication.userInfo = userInfo;
	}

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		BookstoreApplication.token = token;
		cacheToken();
	}
	
	public static void cacheToken()
	{
		SharedPreferences shp=BookstoreApplication.app.getSharedPreferences(BookstoreApplication.SP_NAME, MODE_PRIVATE);
		Editor editor=shp.edit();
		editor.putString(SP_KEY_TOKEN, token);
		editor.commit();
		
		readToken();
		Log.i("current token", token);
	}
	
	public static void readToken()
	{
		SharedPreferences shp=BookstoreApplication.app.getSharedPreferences(BookstoreApplication.SP_NAME, MODE_PRIVATE);
		token=shp.getString(SP_KEY_TOKEN, null);
	}
	
	public static String getCookie() {
		return cookie;
	}

	public static void setCookie(String cookie) {
		BookstoreApplication.cookie = cookie;
	}

	class BitMapCache implements ImageCache{

		private LruCache<String, Bitmap> mCache;
		private static final int maxSize=10*1024*1024;
		public BitMapCache()
		{
			mCache=new LruCache<String, Bitmap>(maxSize){
				@Override
				protected int sizeOf(String key, Bitmap value) {
					// TODO Auto-generated method stub
					return value.getRowBytes()*value.getHeight();
				}
			};
		}
		
		@Override
		public Bitmap getBitmap(String url) {
			
			return mCache.get(url);
		}

		@Override
		public void putBitmap(String url, Bitmap bitmap) {
			mCache.put(url, bitmap);
		}
		
	}
	
}
