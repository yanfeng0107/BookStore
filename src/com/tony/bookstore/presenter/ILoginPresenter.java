package com.tony.bookstore.presenter;

import com.tony.bookstore.entity.UserInfo;

public interface ILoginPresenter extends IPresenter {
	void userLogin(UserInfo userInfo);
}
