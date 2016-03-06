package com.who.onecupafterwork.adapter;

import java.util.ArrayList;
import java.util.Collections;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.who.onecupafterwork.view.SpinnerView;

public class MySpinnerAdapter extends BaseAdapter {

	Context mContext;
	ArrayList<String> mItems = new ArrayList<String>();

	// Map mapItems = new HashMap<String, String>();

	public MySpinnerAdapter(Context mContext, String[] array) {
		this.mContext = mContext;
		Collections.addAll(mItems, array);
	}

	// public MySpinnerAdapter(Context mContext, ArrayList<String> array) {
	// this.mContext = mContext;
	// mItems.addAll(array);
	// }

	public MySpinnerAdapter(Context mContext, ArrayList array) {
		this.mContext = mContext;
		for (int i = 0; i < array.size(); i++) {
			mItems.add(array.get(i).toString());
		}
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public String getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SpinnerView view;
		if (convertView == null) {
			view = new SpinnerView(mContext);
		} else {
			view = (SpinnerView) convertView;
		}
		view.setText(mItems.get(position));
		return view;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		SpinnerView view;
		if (convertView == null) {
			view = new SpinnerView(mContext);
		} else {
			view = (SpinnerView) convertView;
		}
		view.setText(mItems.get(position));
		return view;
	}

}
