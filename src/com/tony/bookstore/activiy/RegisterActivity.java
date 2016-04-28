package com.tony.bookstore.activiy;

import org.xutils.x;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.tony.bookstore.R;
import com.tony.bookstore.entity.CodeImageInfo;
import com.tony.bookstore.entity.UserInfo;
import com.tony.bookstore.presenter.IRegisterPresenter;
import com.tony.bookstore.presenter.impl.RegisterPresenter;
import com.tony.bookstore.view.IRegisterView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends Activity implements IRegisterView{

	@ViewInject(value=R.id.iv_register_back)
	private ImageView ivBack;
	
	@ViewInject(value=R.id.et_register_email)
	private EditText etEmail;
	
	@ViewInject(value=R.id.et_register_pwd)
	private EditText etPwd;
	
	@ViewInject(value=R.id.et_register_real_name)
	private EditText etRealName;
	
	@ViewInject(value=R.id.et_register_code)
	private EditText etCode;
	
	@ViewInject(value=R.id.btn_register_register)
	private Button btnRegister;
	
	@ViewInject(value=R.id.iv_register_code)
	private ImageView ivCode;
	
	private IRegisterPresenter registerPresenter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);
		x.view().inject(this);
		registerPresenter=new RegisterPresenter(this);
		registerPresenter.getCodeImage();
	}

	@Event(value=R.id.btn_register_register,type=OnClickListener.class)
	private void onClickRegister(View view)
	{
		UserInfo userInfo=new UserInfo();
		userInfo.setEmail(etEmail.getText().toString());
		userInfo.setName(etRealName.getText().toString());
		userInfo.setPwd(etPwd.getText().toString());
		String code=etCode.getText().toString();
		switch(view.getId())
		{
		case R.id.btn_register_register:
			registerPresenter.registerNewUser(userInfo, code);
			break;
		}
	}
	
	@Event(value=R.id.iv_register_back,type=OnClickListener.class)
	private void onClickBack(View view)
	{
		switch(view.getId())
		{
		case R.id.iv_register_back:
			finish();
			break;
		}
		
	}
	
	@Override
	public void showRegisterSuccessTip() {
		Toast.makeText(this, "×¢²á³É¹¦", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showRegisterFailTip(String msg) {
		Toast.makeText(this, "×¢²áÊ§°Ü,"+msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void setCodeImage(CodeImageInfo cinfo) {
		if(cinfo==null)
		{
			return;
		}
		
		ivCode.setImageBitmap(cinfo.getBitmap());
	}
}
