package com.tony.bookstore.utils;

public interface ICallback<T> {
	void onSuccess(T t);
	void onFail(T t);
}
