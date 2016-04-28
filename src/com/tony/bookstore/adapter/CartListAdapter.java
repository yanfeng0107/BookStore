package com.tony.bookstore.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.tony.bookstore.R;
import com.tony.bookstore.app.BookstoreApplication;
import com.tony.bookstore.entity.Book;
import com.tony.bookstore.entity.CartItem;
import com.tony.bookstore.utils.IUpdateView;
import com.tony.bookstore.utils.UrlUtils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CartListAdapter extends CommonBaseAdapter<CartItem> {

	private ImageLoader imageLoader;

	private boolean isEditStatus;

	private IUpdateView iView;
	
	public CartListAdapter(List<CartItem> data, Context context,IUpdateView iView) {
		super(data, context);
		imageLoader = BookstoreApplication.app.getImageLoader();
		isEditStatus = false;
		this.iView=iView;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = getInflater().inflate(R.layout.cart_list_item, null);
			holder = new ViewHolder();
			holder.ivAdd = (ImageView) convertView.findViewById(R.id.iv_count_add);
			holder.ivBook = (ImageView) convertView.findViewById(R.id.iv_book);
			holder.ivDel = (ImageView) convertView.findViewById(R.id.iv_del);
			holder.ivMinus = (ImageView) convertView.findViewById(R.id.iv_count_minus);
			holder.tvMiddleCount = (TextView) convertView.findViewById(R.id.tv_middle_count);
			holder.tvRightCount = (TextView) convertView.findViewById(R.id.tv_right_count);
			holder.tvSinglePrice = (TextView) convertView.findViewById(R.id.tv_book_single_price);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_book_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		CartItem item = getData().get(position);

		Book book = item.getBook();
		holder.tvMiddleCount.setText("×" + item.getCount());
		holder.tvRightCount.setText("" + item.getCount());
		holder.tvTitle.setText(book.getProductName());
		holder.tvSinglePrice.setText(book.getDangPrice() + "¥");
		ImageListener listener = ImageLoader.getImageListener(holder.ivBook, R.drawable.ic_launcher,
				R.drawable.ic_launcher);
		imageLoader.get(UrlUtils.getBookPicUrl(book.getProduct_pic()), listener);

		holder.ivMinus.setOnClickListener(
				new CountListener(item, CountListener.CLICK_TYPE_MINUS));
		holder.ivAdd.setOnClickListener(
				new CountListener(item, CountListener.CLICK_TYPE_ADD));

		if (isEditStatus) {
			holder.ivDel.setVisibility(View.VISIBLE);
			holder.ivDel.setOnClickListener(new DelListener(book.getId()));
		} else {
			holder.ivDel.setVisibility(View.INVISIBLE);
		}
		return convertView;
	}

	class CountListener implements OnClickListener {
		private int type;
		private CartItem item;
	
		public static final int CLICK_TYPE_ADD = 0x00;
		public static final int CLICK_TYPE_MINUS = 0x01;

		public CountListener(CartItem item, int type) {
			this.type = type;
			this.item = item;
		
		}

		@Override
		public void onClick(View v) {
			int currentCount = item.getCount();
			switch (this.type) 
			{
				case CLICK_TYPE_ADD:
					currentCount++;
					break;
				case CLICK_TYPE_MINUS:
					currentCount = currentCount - 1 < 1 ? 1 : currentCount - 1;
					break;
			}
			
			item.setCount(currentCount);
			CartListAdapter.this.notifyDataSetChanged();
			Log.i("count", item.getCount() + "");
			iView.updateView();
		}

	}

	class DelListener implements OnClickListener
	{
		private int bookId;
		public DelListener(int bookId) {
			this.bookId=bookId;
		}
		
		@Override
		public void onClick(View v) {
			BookstoreApplication.getCart().remove(bookId);
			CartListAdapter.this.notifyDataSetChanged();
		}
		
	}
	
	class ViewHolder {
		ImageView ivDel;
		ImageView ivBook;
		TextView tvTitle;
		TextView tvSinglePrice;
		TextView tvMiddleCount;
		ImageView ivMinus;
		ImageView ivAdd;
		TextView tvRightCount;
	}

	public boolean isEditStatus() {
		return isEditStatus;
	}

	public void setEditStatus(boolean isEditStatus) {
		this.isEditStatus = isEditStatus;
	}

}
