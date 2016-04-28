package com.tony.bookstore.activiy;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.tony.bookstore.R;
import com.tony.bookstore.app.BookstoreApplication;
import com.tony.bookstore.entity.Book;
import com.tony.bookstore.entity.CartItem;
import com.tony.bookstore.presenter.ICartPresenter;
import com.tony.bookstore.presenter.impl.CartPresenter;
import com.tony.bookstore.utils.Consts;
import com.tony.bookstore.utils.UrlUtils;
import com.tony.bookstore.view.ICartView;

public class BookDetailsActivity extends Activity implements ICartView{
	@ViewInject(value=R.id.iv_details_pic)
	private ImageView ivPic;
	
	@ViewInject(value=R.id.tv_details_title)
	private TextView tvTitle;
	
	@ViewInject(value=R.id.tv_details_price)
	private TextView tvPrice;
	
	@ViewInject(value=R.id.tv_details_author)
	private TextView tvAuthor;
	
	@ViewInject(value=R.id.tv_details_category)
	private TextView tvCategory;
	
	@ViewInject(value=R.id.tv_details_publish_time)
	private TextView tvPublishTime;
	
	@ViewInject(value=R.id.tv_details_publish)
	private TextView tvPublish;
	
	@ViewInject(value=R.id.btn_add_to_cart)
	private Button btnAddToCart;
	
	
	private ImageLoader imageLoader;

	private Book book;
	
	private ICartPresenter cartPresenter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_details_activity);
		x.view().inject(this);
		cartPresenter=new CartPresenter(this);
		imageLoader=BookstoreApplication.app.getImageLoader();
		initView();
	}
	private void initView()
	{
		Intent intent=getIntent();
		book=(Book) intent.getSerializableExtra(Consts.EXTRA_KEY_BOOK);
		if(book!=null)
		{
			tvAuthor.setText("作者："+book.getAuthor());
			tvCategory.setText("类别："+book.getCatalogue());
			tvPrice.setText(book.getDangPrice()+"¥");
			tvPublish.setText("出版社："+book.getPublishing());
			tvPublishTime.setText("出版时间："+book.getPublishedTime());
			tvTitle.setText(book.getProductName());
			ImageListener listener=ImageLoader.getImageListener
					(ivPic, R.drawable.ic_launcher, R.drawable.ic_launcher);
			imageLoader.get(UrlUtils.getBookPicUrl(book.getProduct_pic()),listener);
		}
	}
	@Event(value=R.id.btn_add_to_cart,type=OnClickListener.class)
	private void onClickAddToCart(View view)
	{
		cartPresenter.addBookToCart(book);
	}
	
	@Override
	public void setCartAdapter(List<CartItem> data) {
		
	}
	@Override
	public void setAddCartItemCompleted() {
		this.btnAddToCart.setText("已添加到购物车");
	}
	@Override
	public void setTotalCost(double cost) {
		
	}
	
}
