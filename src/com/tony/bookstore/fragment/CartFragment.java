package com.tony.bookstore.fragment;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.tony.bookstore.R;
import com.tony.bookstore.adapter.CartListAdapter;
import com.tony.bookstore.entity.CartItem;
import com.tony.bookstore.presenter.ICartPresenter;
import com.tony.bookstore.presenter.impl.CartPresenter;
import com.tony.bookstore.utils.IUpdateView;
import com.tony.bookstore.view.ICartView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CartFragment extends Fragment implements ICartView,IUpdateView{

	@ViewInject(value=R.id.lv_middle_cart_list)
	private ListView lvCartItems;
	
	private List<CartItem> cartItems;
	
	private CartListAdapter adapter;
	
	@ViewInject(value=R.id.tv_bottom_sum)
	private TextView tvSum;
	
	@ViewInject(value=R.id.tv_middle_tip)
	private TextView tvTip;
	
	@ViewInject(value=R.id.btn_bottom_settle_account)
	private Button btnCommit;
	
	@ViewInject(value=R.id.btn_header_edit)
	private Button btnEdit;
	
	private View view;
	
	private ICartPresenter cartPresenter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.cart_fragment, null);
		x.view().inject(this, view);
		cartPresenter=new CartPresenter(this);
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		//Log.i("cart fragment","onResume");
		cartPresenter.loadCart();
	}
	
	@Event(value=R.id.btn_header_edit,type=OnClickListener.class)
	private void onClickEdit(View view)
	{
		switch(view.getId())
		{
		case R.id.btn_header_edit:
			if(adapter==null)
				return;
			adapter.setEditStatus(!adapter.isEditStatus());
			adapter.notifyDataSetChanged();
			break;
		}
	}
	
	@Override
	public void setCartAdapter(List<CartItem> data) {
		this.cartItems=data;
		if(this.cartItems!=null&&data.size()>0)
		{
			adapter=new CartListAdapter(data, getActivity(),this);
			lvCartItems.setAdapter(adapter);
			tvTip.setVisibility(View.INVISIBLE);
			lvCartItems.setVisibility(View.VISIBLE);
			
		}
	}

	@Override
	public void setAddCartItemCompleted() {
		
	}

	@Override
	public void setTotalCost(double cost) {
		this.tvSum.setText("¥"+cost);
	}

	@Override
	public void updateView() {
		cartPresenter.updateTotalCost();
	}
}
