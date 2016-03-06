package com.who.onecupafterwork.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.who.onecupafterwork.data.Message;
import com.who.onecupafterwork.show.NoticeListView;

public class NoticeAdapter extends BaseAdapter {

	Context mContext;
	ArrayList<Message> mItems = new ArrayList<Message>();

	public NoticeAdapter(Context mContext, ArrayList<Message> array) {
		this.mContext = mContext;
		mItems.addAll(array);
	}

	public void add(Message data) {
		mItems.add(data);
		notifyDataSetChanged();
	}

	public void addAll(ArrayList<Message> list) {
		mItems.addAll(list);
		notifyDataSetChanged();
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		final NoticeListView view;
		if (convertView == null) {
			view = new NoticeListView(mContext);
		} else {
			view = (NoticeListView) convertView;
		}

		view.setText(mItems.get(position).ntitle, mItems.get(position).regdate);

		return view;
	}

}
