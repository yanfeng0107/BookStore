package com.tony.bookstore.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tony.bookstore.app.BookstoreApplication;

import android.util.Log;

public class Cart implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CartItem> data;
	
	private static final String fileName="BOOKCART.INFO"; 

	public Cart()
	{
		data=new ArrayList<CartItem>();
	}
	
	public double getTotalCost()
	{
		double sum=0;
		
		for(int i=0;i<data.size();i++)
		{
			CartItem item=data.get(i);
			Book book=item.getBook();
			sum+=book.getDangPrice()*item.getCount();
		}
		return sum;
	}
	
	public void addBook(Book book)
	{
		CartItem item=null;
		for(int i=0;i<data.size();i++)
		{
			item=data.get(i);
			
			if(item.getBook().equals(book))
			{
				item.setCount(item.getCount()+1);
				cacheCart();
				return;
			}
		}
		item=new CartItem();
		item.setBook(book);
		item.setCount(1);
		data.add(item);
		cacheCart();
	}
	
	public void remove(int bookId)
	{
		for(int i=0;i<data.size();i++)
		{
			if(data.get(i).getBook().getId()==bookId)
			{
				data.remove(i);
			}
		}
		cacheCart();
	}
	
	public void cacheCart()
	{
		File dir=new File(BookstoreApplication.app.getCacheDir(), fileName);
		Log.i("dir", dir.toString());
		try {
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(dir));
			oos.writeObject(this);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Cart readCart()
	{
		File dir=new File(BookstoreApplication.app.getCacheDir(), fileName);
		try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(dir));
			Cart cart= (Cart)ois.readObject();
			ois.close();
			if(cart!=null)
			{
				return cart;
			}else{
				return new Cart();
			}
		}  catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new Cart();
	}
	
	public List<CartItem> getData() {
		return data;
	}

	public void setData(List<CartItem> data) {
		this.data = data;
	}
	
}
