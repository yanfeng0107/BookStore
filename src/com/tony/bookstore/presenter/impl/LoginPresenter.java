package com.tony.bookstore.presenter.impl;

import com.tony.bookstore.app.BookstoreApplication;
import com.tony.bookstore.entity.UserInfo;
import com.tony.bookstore.model.IUserModel;
import com.tony.bookstore.model.impl.UserModel;
import com.tony.bookstore.presenter.ILoginPresenter;
import com.tony.bookstore.utils.ICallback;
import com.tony.bookstore.view.ILoginView;

public class LoginPresenter implements ILoginPresenter {
	
	private ILoginView loginView;
	private IUserModel userModel;
	
	
	public LoginPresenter(ILoginView loginView) {
		this.loginView=loginView;
		userModel=new UserModel();
	}

	
	
	@Override
	public void userLogin(UserInfo userInfo) {
		ICallback<UserInfo> callback=new ICallback<UserInfo>() {
			
			@Override
			public void onSuccess(UserInfo t) {
				
				if(t.getErrMsg()==null)
				{
					BookstoreApplication.setLogin(true);
					BookstoreApplication.setUserInfo(t);
					loginView.showLoginSuccess();
				}else
				{
					loginView.showLoginFail(t.getErrMsg());
				}
			}
			
			@Override
			public void onFail(UserInfo t) {
				
			}
		};
		userModel.login(userInfo, callback);
		
	}


}
