package com.tony.bookstore.view;

import com.tony.bookstore.entity.CodeImageInfo;

public interface IRegisterView extends IView{
	void showRegisterSuccessTip();
	void showRegisterFailTip(String msg);
	void setCodeImage(CodeImageInfo cinfo);
}
