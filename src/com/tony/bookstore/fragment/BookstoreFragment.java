package com.tony.bookstore.fragment;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.tony.bookstore.R;
import com.tony.bookstore.activiy.BookDetailsActivity;
import com.tony.bookstore.adapter.BookstoreAdapter;
import com.tony.bookstore.entity.Book;
import com.tony.bookstore.presenter.ILoadBookList;
import com.tony.bookstore.presenter.impl.BookstorePresenter;
import com.tony.bookstore.utils.Consts;
import com.tony.bookstore.view.IBookstoreView;

public class BookstoreFragment extends Fragment implements IBookstoreView{
	private View view;
	
	private BookstoreAdapter recommondAdapter;
	private List<Book> recommondBooks;
	
	private BookstoreAdapter hotAdapter;
	private List<Book> hotBooks;
	
	private BookstoreAdapter newAdapter;
	private List<Book> newBooks;
	
	@ViewInject(value=R.id.gv_recommond)
	private GridView gvRecommond;
	@ViewInject(value=R.id.gv_hot)
	private GridView gvHot;
	@ViewInject(value=R.id.gv_new)
	private GridView gvNew;
	
	private ILoadBookList ILoadBooks;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.bookstore_fragment, null);
		x.view().inject(this, view);
		ILoadBooks=new BookstorePresenter(this);
		
		ILoadBooks.loadRecommondList();
		ILoadBooks.loadNewList();
		ILoadBooks.loadHotList();
		
		return view;
	}
	
	@Event(value={R.id.gv_recommond,R.id.gv_hot,R.id.gv_new}
			,type=OnItemClickListener.class)
	private void onGridViewItemClick(AdapterView<?> parent, View view,
			int position, long id)
	{
		Book book=null;
		switch(parent.getId())
		{
		case R.id.gv_recommond:
			book=recommondBooks.get(position);
			break;
		case R.id.gv_new:
			book=newBooks.get(position);
			break;
		case R.id.gv_hot:
			book=hotBooks.get(position);
			break;
		}
		Log.i("book click",book.toString());
		if(book!=null)
		{
			Intent intent=new Intent(getActivity(),BookDetailsActivity.class);
			intent.putExtra(Consts.EXTRA_KEY_BOOK, book);
			startActivity(intent);
		}
	}
	
	@Override
	public void setRecommondAdapter(List<Book> books) {
		recommondBooks=books;
		recommondAdapter=new BookstoreAdapter(recommondBooks, getActivity());
		gvRecommond.setAdapter(recommondAdapter);
	}
	@Override
	public void setHotAdapter(List<Book> books) {
		hotBooks=books;
		hotAdapter=new BookstoreAdapter(hotBooks, getActivity());
		gvHot.setAdapter(hotAdapter);
	}
	@Override
	public void setNewAdapter(List<Book> books) {
		newBooks=books;
		newAdapter=new BookstoreAdapter(newBooks, getActivity());
		gvNew.setAdapter(newAdapter);
	}

}
