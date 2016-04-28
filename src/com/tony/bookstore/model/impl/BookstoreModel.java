package com.tony.bookstore.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.tony.bookstore.app.BookstoreApplication;
import com.tony.bookstore.entity.Book;
import com.tony.bookstore.model.IGetBookList;
import com.tony.bookstore.utils.Consts;
import com.tony.bookstore.utils.ICallback;

import android.content.Context;
import android.util.Log;

public class BookstoreModel implements IGetBookList {

	private RequestQueue queue;
	private Context context;
	private Gson gson;
	
	public BookstoreModel(Context context) {
		this.context=context;
		queue=Volley.newRequestQueue(context);
		gson=BookstoreApplication.app.getGson();
	}
	
	@Override
	public void getBookList(String url, final ICallback<List<Book>> callback) {
		StringRequest request=new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				List<Book> books=new ArrayList<Book>();
				Log.i("books",response);
				try {
					JSONObject obj=new JSONObject(response);
					if(Consts.BOOKSTORE_LOAD_BOOK_LIST_SUCCESS_CODE.equals(
							obj.getString(Consts.BOOKSTORE_BOOK_LIST_KEY_CODE)))
					{
						JSONArray bookArray=obj.getJSONArray(Consts.BOOKSTORE_BOOK_LIST_KEY_DATA);
						for(int i=0;i<bookArray.length();i++)
						{
							JSONObject jobj=bookArray.getJSONObject(i);
							Book book=gson.fromJson(jobj.toString(),Book.class);
							books.add(book);
						}
					}
					callback.onSuccess(books);
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
				
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				callback.onFail(new ArrayList<Book>());
			}
		});
		queue.add(request);
	}

}
