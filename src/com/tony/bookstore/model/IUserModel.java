package com.tony.bookstore.model;

import com.tony.bookstore.entity.CodeImageInfo;
import com.tony.bookstore.entity.UserInfo;
import com.tony.bookstore.utils.ICallback;

public interface IUserModel extends IModel{
	void registerNewUser(UserInfo userInfo,String code,String cookie,ICallback<String> callback);
	void getCodeImage(ICallback<CodeImageInfo> callback);
	void login(UserInfo userInfo,ICallback<UserInfo> callback);
	void autoLogin(ICallback<UserInfo> callback);
}
