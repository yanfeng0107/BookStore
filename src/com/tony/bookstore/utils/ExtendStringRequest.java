package com.tony.bookstore.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.tony.bookstore.app.BookstoreApplication;

public class ExtendStringRequest extends StringRequest {

	private String cookie;
	
	public ExtendStringRequest(int method, String url, Listener<String> listener, ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}

	
	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		Map<String,String> map=super.getHeaders();
		if(map==null||map.equals(Collections.emptyMap()))
		{
			map=new HashMap<String, String>();
		}
		if(cookie!=null)
		{
			map.put("Cookie", cookie);
		}
		return map;
	}
	
	public void setCookie(String cookie)
	{
		this.cookie=cookie;
	}
	public String getCookie()
	{
		return cookie;
	}
	
	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		cookie=response.headers.get("Set-Cookie");
		BookstoreApplication.setCookie(cookie);
		return super.parseNetworkResponse(response);
	}
}
