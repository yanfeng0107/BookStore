package com.tony.bookstore.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.tony.bookstore.R;
import com.tony.bookstore.app.BookstoreApplication;
import com.tony.bookstore.entity.Book;
import com.tony.bookstore.utils.UrlUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BookstoreAdapter extends CommonBaseAdapter<Book> {

	private ImageLoader imageLoader;
	
	
	public BookstoreAdapter(List<Book> data, Context context) {
		super(data, context);
	
		imageLoader=BookstoreApplication.app.getImageLoader();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null)
		{
			convertView=getInflater().inflate(R.layout.gridview_item, null);
			holder=new ViewHolder();
			holder.ivBook=(ImageView) convertView.findViewById(R.id.iv_book_pic);
			holder.tvBook=(TextView) convertView.findViewById(R.id.tv_book_title);
			
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		
		Book book=getData().get(position);
		holder.tvBook.setText(book.getProductName());
		ImageListener listener=ImageLoader.getImageListener
				(holder.ivBook, R.drawable.ic_launcher, R.drawable.ic_launcher);
		imageLoader.get(UrlUtils.getBookPicUrl(book.getProduct_pic()), listener);
		//x.image().bind(holder.ivBook, UrlUtils.getBookPicUrl(book.getProduct_pic()));
		return convertView;
	}
	
	class ViewHolder
	{
		ImageView ivBook;
		TextView tvBook;
	}
}
