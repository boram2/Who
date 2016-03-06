package com.who.onecupafterwork.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.who.onecupafterwork.data.Company;

public class MyCompanyAdapter extends BaseAdapter {

	Context mContext;
	ArrayList<Company> mItems = new ArrayList<Company>();

	public MyCompanyAdapter(Context mContext, ArrayList<Company> array) {
		this.mContext = mContext;
		mItems.addAll(array);
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view;
		if (convertView == null) {
			view = new TextView(mContext);
		} else {
			view = (TextView) convertView;
		}
		view.setText(mItems.get(position).companyName);
		return view;
	}

}
