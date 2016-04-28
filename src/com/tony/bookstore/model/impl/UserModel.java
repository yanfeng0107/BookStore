package com.tony.bookstore.model.impl;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tony.bookstore.app.BookstoreApplication;
import com.tony.bookstore.entity.CodeImageInfo;
import com.tony.bookstore.entity.UserInfo;
import com.tony.bookstore.model.IUserModel;
import com.tony.bookstore.utils.Consts;
import com.tony.bookstore.utils.ExtendImageRequest;
import com.tony.bookstore.utils.ExtendStringRequest;
import com.tony.bookstore.utils.ICallback;
import com.tony.bookstore.utils.UrlUtils;

import android.graphics.Bitmap.Config;
import android.util.Log;

public class UserModel implements IUserModel {

	private RequestQueue queue;

	public UserModel() {
		queue = BookstoreApplication.app.getQueue();
	}

	@Override
	public void registerNewUser(final UserInfo userInfo, final String code, final String cookie,
			final ICallback<String> callback) {
		String url = UrlUtils.getRegisterUserUrl();
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				callback.onSuccess(response);
			}
		};
		ErrorListener errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				callback.onFail(null);
			}
		};

		ExtendStringRequest request = new ExtendStringRequest(Method.POST, url, listener, errorListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put(Consts.REGISTER_PARAM_KEY_EMAIL, userInfo.getEmail());
				map.put(Consts.REGISTER_PARAM_KEY_NAME, userInfo.getName());
				map.put(Consts.REGISTER_PARAM_KEY_PWD, userInfo.getPwd());
				map.put(Consts.REGISTER_PARAM_KEY_NUMBER, code);
				return map;
			}
		};
		request.setCookie(cookie);
		queue.add(request);
	}

	@Override
	public void getCodeImage(final ICallback<CodeImageInfo> callback) {

		Listener<CodeImageInfo> listener = new Listener<CodeImageInfo>() {

			@Override
			public void onResponse(CodeImageInfo response) {
				callback.onSuccess(response);
			}
		};
		ErrorListener errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				callback.onFail(null);
			}

		};
		String url = UrlUtils.getCodeImageUrl();
		ExtendImageRequest request = new ExtendImageRequest(url, listener, 120, 60, Config.ARGB_8888, errorListener);
		queue.add(request);
	}

	@Override
	public void login(final UserInfo userInfo,final ICallback<UserInfo> callback) {
		String url = UrlUtils.getUserLoginUrl();
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					JSONObject obj=new JSONObject(response);
					if(Consts.HTTP_SUCCESS_VALUE.equals(obj.getString(Consts.HTTP_RESULT_KEY_CODE)))
					{
						BookstoreApplication.setToken(obj.getString(Consts.USER_TOKEN_KEY));
						JSONObject userObj=obj.getJSONObject(Consts.USER_USEROBJ_KEY);
						userInfo.setName(userObj.getString(Consts.USER_NICKNAME_KEY));
						userInfo.setErrMsg(null);
						userInfo.setId(userObj.getString(Consts.USER_ID_KEY));
						BookstoreApplication.setUserInfo(userInfo);
					}else
					{
						userInfo.setErrMsg(obj.getString(Consts.HTTP_FAIL_ERROR_MSG));
					}
				} catch (JSONException e) {
					e.printStackTrace();
					userInfo.setErrMsg("获取信息失败！");
				}
				callback.onSuccess(userInfo);
			}

		};
		ErrorListener errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				callback.onFail(null);
			}

		};
		ExtendStringRequest request = new ExtendStringRequest(Method.POST,url, listener, errorListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String,String> map=new HashMap<String,String>();
				map.put(Consts.LOGIN_PARAM_KEY_EMAIL, userInfo.getEmail());
				map.put(Consts.LOGIN_PARAM_KEY_PWD, userInfo.getPwd());
				return map;
			}
		};
		
		queue.add(request);
	}

	@Override
	public void autoLogin(final ICallback<UserInfo> callback) {
		String url = UrlUtils.getAutoLoginUrl();
		Log.i("auto", url);
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				UserInfo userInfo=new UserInfo();
				Log.i("auto response", response);
				try {
					JSONObject obj=new JSONObject(response);
					if(Consts.HTTP_SUCCESS_VALUE.equals(obj.getString(Consts.HTTP_RESULT_KEY_CODE)))
					{
						String token=obj.getString(Consts.USER_TOKEN_KEY);
						BookstoreApplication.setToken(token);
						
						JSONObject userObj=obj.getJSONObject(Consts.USER_USEROBJ_KEY);
						userInfo.setName(userObj.getString(Consts.USER_NICKNAME_KEY));
						userInfo.setErrMsg(null);
						userInfo.setId(userObj.getString(Consts.USER_ID_KEY));
						BookstoreApplication.setUserInfo(userInfo);
					}else
					{
						userInfo.setErrMsg(obj.getString(Consts.HTTP_FAIL_ERROR_MSG));
					}
				} catch (JSONException e) {
					e.printStackTrace();
					userInfo.setErrMsg("获取信息失败！");
				}
				callback.onSuccess(userInfo);
			}

		};
		ErrorListener errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				callback.onFail(null);
			}

		};
		ExtendStringRequest request = new ExtendStringRequest(Method.POST,url, listener, errorListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String,String> map=new HashMap<String,String>();
				map.put(Consts.USER_TOKEN_KEY,BookstoreApplication.getToken());
				return map;
			}
		};
		
		queue.add(request);
	}

}
