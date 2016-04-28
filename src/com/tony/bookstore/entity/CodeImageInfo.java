package com.tony.bookstore.entity;

import android.graphics.Bitmap;

public class CodeImageInfo {
	private Bitmap bitmap;
	private String Cookie;
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public String getCookie() {
		return Cookie;
	}
	public void setCookie(String cookie) {
		Cookie = cookie;
	}
}
