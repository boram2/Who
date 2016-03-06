package com.who.onecupafterwork.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.show.BanListView;

public class BanListAdapter extends BaseAdapter implements BanListView.OnButtonClickListener {

	Context mContext;
	ArrayList<String> banNumItems = new ArrayList<String>();

	public interface OnAdapterButtonClickListener {
		public void onAdapterItemClick(View v);
	}

	OnAdapterButtonClickListener mListener;

	public void setOnAdapterButtonClickListener(OnAdapterButtonClickListener listener) {
		mListener = listener;
	}

	public BanListAdapter(Context context) {
		this(context, null);
	}

	public BanListAdapter(Context mContext, ArrayList<String> banNum) {
		this.mContext = mContext;
		banNumItems.addAll(banNum);
	}

	public void add(String banPhoneNum) {
		banNumItems.add(banPhoneNum);
		notifyDataSetChanged();
	}

	public void addAll(List<String> items) {
		banNumItems.addAll(items);
		notifyDataSetChanged();
	}

	public void remove(int position) {
		banNumItems.remove(position);
	}

	@Override
	public int getCount() {
		return banNumItems.size();
	}

	@Override
	public Object getItem(int position) {
		return banNumItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BanListView view;
		if (convertView == null) {
			view = new BanListView(mContext);
			view.setOnButtonClickListener(this);
		} else {
			view = (BanListView) convertView;
		}
		view.setBanData(banNumItems.get(position));
		return view;
	}

	@Override
	public void onButtonClick(View v, final String getBanNum) {
		banNumItems.remove(getBanNum);
		System.out.println(getBanNum);
		NetworkModel.getInstance().postdelBlockPhoneNum(getBanNum, mContext, new OnNetworkResultListener<ResultData>() {

			@Override
			public void onResult(ResultData result) {
				if (result.result.equals("success")) {
					Log.i("success delete ban List", "No." + getBanNum);
					notifyDataSetChanged();
				} else if (result.result.equals("error")) {
					Log.i("fail delete ban List in db", "No." + getBanNum + result.message);

				}
			}
		});
		
		if (mListener != null) {
			mListener.onAdapterItemClick(v);
		}
	}

}
