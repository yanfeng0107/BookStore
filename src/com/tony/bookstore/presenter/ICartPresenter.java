package com.tony.bookstore.presenter;

import com.tony.bookstore.entity.Book;

public interface ICartPresenter extends IPresenter {
	void addBookToCart(Book book);
	void loadCart();
	void updateTotalCost();
}
