package com.tony.bookstore.model.impl;

import java.util.List;

import android.util.Log;

import com.tony.bookstore.app.BookstoreApplication;
import com.tony.bookstore.entity.Book;
import com.tony.bookstore.entity.Cart;
import com.tony.bookstore.entity.CartItem;
import com.tony.bookstore.model.ICartModel;

public class CartModel implements ICartModel{

	private Cart cart=BookstoreApplication.getCart();
	
	@Override
	public void addCartItem(Book book) {
		Log.i("CartModel","addCartItem");
		cart.addBook(book);
	}

	@Override
	public List<CartItem> getCartList() {
		return cart.getData();
	}

	@Override
	public double getCartTotalCost() {
		return cart.getTotalCost();
	}
	

}
