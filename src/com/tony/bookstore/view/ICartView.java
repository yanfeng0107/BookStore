package com.tony.bookstore.view;

import java.util.List;

import com.tony.bookstore.entity.CartItem;

public interface ICartView extends IView {
	void setCartAdapter(List<CartItem> data);
	void setAddCartItemCompleted();
	void setTotalCost(double cost);
}
