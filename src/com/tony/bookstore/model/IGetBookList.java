package com.tony.bookstore.model;

import java.util.List;

import com.tony.bookstore.entity.Book;
import com.tony.bookstore.utils.ICallback;

public interface IGetBookList extends IModel {

	void getBookList(String url,ICallback<List<Book>> callback);
	
}
