package com.tony.bookstore.model.impl;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tony.bookstore.app.BookstoreApplication;
import com.tony.bookstore.entity.Address;
import com.tony.bookstore.model.IAddressModel;
import com.tony.bookstore.utils.UrlUtils;

public class AddressModel implements IAddressModel {

	private RequestQueue queue;

	public AddressModel() {
		queue = BookstoreApplication.app.getQueue();
	}

	@Override
	public void addAddress(Address addr) {
		String url = UrlUtils.getAddAddressUrl();
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub

			}
		};
		ErrorListener errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

			}
		};
		StringRequest request = new StringRequest(url, listener, errorListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String,String> params=new HashMap<String, String>();
				//params.put(key, value)
				return params;
			}
		};
	}

}
