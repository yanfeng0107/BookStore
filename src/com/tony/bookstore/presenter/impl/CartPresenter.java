package com.tony.bookstore.presenter.impl;

import java.util.List;

import android.util.Log;

import com.tony.bookstore.entity.Book;
import com.tony.bookstore.entity.CartItem;
import com.tony.bookstore.model.ICartModel;
import com.tony.bookstore.model.impl.CartModel;
import com.tony.bookstore.presenter.ICartPresenter;
import com.tony.bookstore.view.ICartView;

public class CartPresenter implements ICartPresenter {

	private ICartModel cartModel;
	private ICartView cartView;
	public CartPresenter(ICartView view)
	{
		this.cartView=view;
		cartModel=new CartModel();
	}
	
	@Override
	public void addBookToCart(Book book) {
		Log.i("CartPresenter","addBookToCart");
		cartModel.addCartItem(book);
		cartView.setAddCartItemCompleted();
		
		cartView.setTotalCost(cartModel.getCartTotalCost());
	}

	@Override
	public void loadCart() {
		List<CartItem> data=cartModel.getCartList();
		cartView.setCartAdapter(data);
		cartView.setTotalCost(cartModel.getCartTotalCost());
	}

	@Override
	public void updateTotalCost() {
		cartView.setTotalCost(cartModel.getCartTotalCost());
	}

}
