package com.tony.bookstore.activiy;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.tony.bookstore.R;
import com.tony.bookstore.adapter.ViewPagerAdapter;
import com.tony.bookstore.fragment.BookstoreFragment;
import com.tony.bookstore.fragment.CartFragment;
import com.tony.bookstore.fragment.MineFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity {
	@ViewInject(value=R.id.vp_middle_pager)
	private ViewPager vpager;
	
	@ViewInject(value=R.id.rg_tab)
	private RadioGroup rgTab;
	
	@ViewInject(value=R.id.rb_tab_bookstore)
	private RadioButton rbBookstore;
	
	@ViewInject(value=R.id.rb_tab_cart)
	private RadioButton rbCart;
	
	@ViewInject(value=R.id.rb_tab_mine)
	private RadioButton rbMine;
	
	
	private FragmentPagerAdapter vpAdapter;
	private List<Fragment> fragments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		x.view().inject(this);
		
		init();
	}
	
	private void init()
	{
		fragments=new ArrayList<Fragment>();
		fragments.add(new BookstoreFragment());
		fragments.add(new CartFragment());
		fragments.add(new MineFragment());
		
		vpAdapter=new ViewPagerAdapter(getSupportFragmentManager(),fragments);
		
		vpager.setAdapter(vpAdapter);
		vpager.setOffscreenPageLimit(fragments.size());
	}
	
	@Event(value=R.id.rg_tab,type=OnCheckedChangeListener.class)
	private void rgTabCheckedChanged(RadioGroup group, int checkedId)
	{
		switch(checkedId)
		{
		case R.id.rb_tab_bookstore:
			vpager.setCurrentItem(0);
			break;
		case R.id.rb_tab_cart:
			vpager.setCurrentItem(1);
			break;
		case R.id.rb_tab_mine:
			vpager.setCurrentItem(2);
			break;
		}
	}
	@Event(value=R.id.vp_middle_pager,type=OnPageChangeListener.class
			,method="onPageSelected")
	private void onViewPageSelected(int position)
	{
		switch(position)
		{
		case 0:
			rbBookstore.setChecked(true);
			break;
		case 1:
			rbCart.setChecked(true);
			break;
		case 2:
			rbMine.setChecked(true);
			break;
		}
	}
	

}
