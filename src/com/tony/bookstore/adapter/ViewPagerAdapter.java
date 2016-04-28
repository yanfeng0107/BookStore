package com.tony.bookstore.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragments;
	
	public ViewPagerAdapter(FragmentManager fm ,List<Fragment> fragments) {
		super(fm);
		this.fragments=fragments;
	}

	@Override
	public Fragment getItem(int position) {
		
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		
		return fragments.size();
	}


	public List<Fragment> getFragments() {
		return fragments;
	}

	public void setFragments(List<Fragment> fragments) {
		this.fragments = fragments;
	}

}
