package com.tony.bookstore.presenter.impl;

import com.tony.bookstore.app.BookstoreApplication;
import com.tony.bookstore.entity.UserInfo;
import com.tony.bookstore.model.IUserModel;
import com.tony.bookstore.model.impl.UserModel;
import com.tony.bookstore.presenter.IMinePresenter;
import com.tony.bookstore.utils.ICallback;
import com.tony.bookstore.view.IMineView;

public class MinePresenter implements IMinePresenter {

	private IMineView mineView;
	private IUserModel userModel;

	public MinePresenter(IMineView view) {
		this.mineView = view;
		userModel=new UserModel();
	}

	@Override
	public void autoLogin() {
		if (BookstoreApplication.getToken() != null) {
			ICallback<UserInfo> callback = new ICallback<UserInfo>() {

				@Override
				public void onSuccess(UserInfo t) {
					if (t.getErrMsg() == null) {
						BookstoreApplication.setLogin(true);
						BookstoreApplication.setUserInfo(t);
						mineView.setUserInfo(t);
					} else {

					}
				}

				@Override
				public void onFail(UserInfo t) {

				}
			};
			userModel.autoLogin(callback);
		}
	}

}
