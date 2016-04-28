package com.tony.bookstore.activiy;

import org.xutils.x;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.tony.bookstore.R;
import com.tony.bookstore.entity.UserInfo;
import com.tony.bookstore.presenter.ILoginPresenter;
import com.tony.bookstore.presenter.impl.LoginPresenter;
import com.tony.bookstore.view.ILoginView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements ILoginView{

	@ViewInject(value=R.id.iv_login_back)
	private ImageView ivBack;
	
	@ViewInject(value=R.id.et_login_email)
	private EditText etEmail;
	
	@ViewInject(value=R.id.et_login_pwd)
	private EditText etPwd;
	
	@ViewInject(value=R.id.tv_login_register)
	private TextView tvRegister;
	
	private ILoginPresenter loginPresenter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		x.view().inject(this);
		loginPresenter=new LoginPresenter(this);
	}

	@Event(value=R.id.btn_login_login,type=OnClickListener.class)
	private void onClickLogin(View view)
	{
		UserInfo userInfo;
		switch(view.getId())
		{
		case R.id.btn_login_login:
			userInfo=new UserInfo();
			userInfo.setEmail(etEmail.getText().toString());
			userInfo.setPwd(etPwd.getText().toString());
			loginPresenter.userLogin(userInfo);
			break;
		}
	}
	
	
	@Event(value=R.id.tv_login_register,type=OnClickListener.class)
	private void onClickRegisterUser(View view)
	{
		switch(view.getId())
		{
		case R.id.tv_login_register:
			Intent intent=new Intent(this,RegisterActivity.class);
			startActivity(intent);
			break;
		}
	}
	
	@Override
	public void showLoginSuccess() {
		Toast.makeText(this, "µÇÂ¼³É¹¦", Toast.LENGTH_SHORT).show();
		finish();
	}

	@Override
	public void showLoginFail(String msg) {
		Toast.makeText(this, "µÇÂ¼Ê§°Ü,"+msg, Toast.LENGTH_SHORT).show();
	}
	
	
}
