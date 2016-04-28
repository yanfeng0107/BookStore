package com.tony.bookstore.view;

import java.util.List;

import com.tony.bookstore.entity.Book;

public interface IBookstoreView extends IView {
	void setRecommondAdapter(List<Book> books);
	void setHotAdapter(List<Book> books);
	void setNewAdapter(List<Book> books);
}
