package com.tony.bookstore.presenter;

import com.tony.bookstore.entity.UserInfo;

public interface IRegisterPresenter extends IPresenter{

	void registerNewUser(UserInfo userInfo,String code);
	void getCodeImage();
	
}
