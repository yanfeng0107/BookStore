package com.tony.bookstore.presenter.impl;

import org.json.JSONException;
import org.json.JSONObject;

import com.tony.bookstore.entity.CodeImageInfo;
import com.tony.bookstore.entity.UserInfo;
import com.tony.bookstore.model.IUserModel;
import com.tony.bookstore.model.impl.UserModel;
import com.tony.bookstore.presenter.IRegisterPresenter;
import com.tony.bookstore.utils.Consts;
import com.tony.bookstore.utils.ICallback;
import com.tony.bookstore.view.IRegisterView;

public class RegisterPresenter implements IRegisterPresenter {

	private IUserModel userModel;
	private IRegisterView registerView;
	
	private String cookie;
	
	public RegisterPresenter(IRegisterView view) {
		userModel=new UserModel();
		registerView=view;
	}
	
	@Override
	public void registerNewUser(UserInfo userInfo, String code) {
		ICallback<String> callback=new ICallback<String>() {
			
			@Override
			public void onSuccess(String t) {
				try {
					JSONObject obj=new JSONObject(t);
					String code=obj.getString(Consts.HTTP_RESULT_KEY_CODE);
					if(Consts.HTTP_SUCCESS_VALUE.equals(code))
					{
						registerView.showRegisterSuccessTip();
					}else if(Consts.HTTP_FAIL_VALUE.equals(code))
					{
						String msg=obj.getString(Consts.HTTP_FAIL_ERROR_MSG);
						registerView.showRegisterFailTip(msg);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void onFail(String t) {
				
			}
		};
		userModel.registerNewUser(userInfo, code,cookie,callback);
	}

	@Override
	public void getCodeImage() {
		userModel.getCodeImage(new ICallback<CodeImageInfo>() {
			
			@Override
			public void onSuccess(CodeImageInfo t) {
				registerView.setCodeImage(t);
				RegisterPresenter.this.cookie=t.getCookie();
			}
			
			@Override
			public void onFail(CodeImageInfo t) {
				registerView.setCodeImage(null);
				RegisterPresenter.this.cookie=null;
			}
		});
	}

}
