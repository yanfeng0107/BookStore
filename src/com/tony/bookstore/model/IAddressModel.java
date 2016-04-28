package com.tony.bookstore.model;

import com.tony.bookstore.entity.Address;

public interface IAddressModel extends IModel {
	void addAddress(Address addr);
}
