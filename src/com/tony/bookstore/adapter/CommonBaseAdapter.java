package com.tony.bookstore.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

/**
 * 一般listview，gridview等的通用适配器的父类
 * @author tony
 *
 * @param <T> list中的数据类型
 */
public abstract class CommonBaseAdapter<T> extends BaseAdapter {

	private List<T> data;
	private Context context;
	private LayoutInflater inflater;
	
	public CommonBaseAdapter(List<T> data, Context context) {
		super();
		this.data = data;
		this.context = context;
		setInflater();
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public LayoutInflater getInflater() {
		return inflater;
	}

	private void setInflater() {
		if(context!=null)
		{
			inflater=LayoutInflater.from(context);
		}else{
			throw new IllegalArgumentException("context is null!!");
		}
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
