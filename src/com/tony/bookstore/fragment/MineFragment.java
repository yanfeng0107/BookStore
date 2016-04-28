package com.tony.bookstore.fragment;

import org.xutils.x;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.tony.bookstore.R;
import com.tony.bookstore.activiy.LoginActivity;
import com.tony.bookstore.app.BookstoreApplication;
import com.tony.bookstore.entity.UserInfo;
import com.tony.bookstore.presenter.IMinePresenter;
import com.tony.bookstore.presenter.impl.MinePresenter;
import com.tony.bookstore.view.IMineView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MineFragment extends Fragment implements IMineView{

	@ViewInject(value=R.id.iv_mine_pic)
	private ImageView ivHeader;
	@ViewInject(value=R.id.tv_mine_addr)
	private TextView tvAddr;

	@ViewInject(value=R.id.tv_mine_name)
	private TextView tvName;
	
	@ViewInject(value=R.id.et_mine_exit)
	private EditText etExit;
	
	private View view;
	private IMinePresenter minePresenter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.mine_fragment, null);
		x.view().inject(this,view);
		minePresenter=new MinePresenter(this);
		minePresenter.autoLogin();
		return view;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		initUserInfo();
	}
	
	@Event(value=R.id.et_mine_exit,type=OnClickListener.class)
	private void onClickExit(View view)
	{
		BookstoreApplication.setUserInfo(null);
		BookstoreApplication.setLogin(false);
		initUserInfo();
	}
	
	@Event(value=R.id.iv_mine_pic,type=OnClickListener.class)
	private void onClickImageHeader(View view)
	{
		switch(view.getId())
		{
			case R.id.iv_mine_pic:
				Intent intent=new Intent(getActivity(),LoginActivity.class);
				startActivity(intent);
				break;
		}
	}
	
	private void initUserInfo()
	{
		if(BookstoreApplication.isLogin())
		{
			UserInfo userInfo=BookstoreApplication.getUserInfo();
			setUserInfo(userInfo);
		}
	}

	@Override
	public void setUserInfo(UserInfo userInfo) {
		if(userInfo!=null)
		{
			tvName.setText(userInfo.getName());
		}else
		{
			tvName.setText("Œ“µƒÍ«≥∆");
		}
	}
}
