package com.tony.bookstore.presenter.impl;

import java.util.List;

import com.tony.bookstore.app.BookstoreApplication;
import com.tony.bookstore.entity.Book;
import com.tony.bookstore.model.IGetBookList;
import com.tony.bookstore.model.impl.BookstoreModel;
import com.tony.bookstore.presenter.ILoadBookList;
import com.tony.bookstore.utils.ICallback;
import com.tony.bookstore.utils.UrlUtils;
import com.tony.bookstore.view.IBookstoreView;

public class BookstorePresenter implements ILoadBookList {

	private IGetBookList bModel;
	private IBookstoreView ibView;
	public BookstorePresenter(IBookstoreView view)
	{
		bModel=new BookstoreModel(BookstoreApplication.app);
		ibView=view;
	}
	
	@Override
	public void loadRecommondList() {
		String url=UrlUtils.getRecommondUrl();
		ICallback<List<Book>> callback=new ICallback<List<Book>>() {

			@Override
			public void onSuccess(List<Book> books) {
				ibView.setRecommondAdapter(books);
			}

			@Override
			public void onFail(List<Book> t) {
				
			}
		};
		bModel.getBookList(url, callback);
	}

	@Override
	public void loadHotList() {
		String url=UrlUtils.getHotUrl();
		ICallback<List<Book>> callback=new ICallback<List<Book>>() {

			@Override
			public void onSuccess(List<Book> books) {
				ibView.setHotAdapter(books);
			}

			@Override
			public void onFail(List<Book> t) {
			}
		};
		bModel.getBookList(url, callback);
	}

	@Override
	public void loadNewList() {
		String url=UrlUtils.getNewUrl();
		ICallback<List<Book>> callback=new ICallback<List<Book>>() {

			@Override
			public void onSuccess(List<Book> t) {
				ibView.setNewAdapter(t);
			}

			@Override
			public void onFail(List<Book> t) {
				// TODO Auto-generated method stub
				
			}
		};
		bModel.getBookList(url, callback);
	}

}
