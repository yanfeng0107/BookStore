package com.tony.bookstore.model;

import java.util.List;

import com.tony.bookstore.entity.Book;
import com.tony.bookstore.entity.CartItem;

public interface ICartModel {
	void addCartItem(Book book);
	List<CartItem> getCartList();
	double getCartTotalCost();
}
