package com.who.onecupafterwork.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.who.onecupafterwork.database.AlarmData;
import com.who.onecupafterwork.database.DBManager;
import com.who.onecupafterwork.view.AlarmView;
import com.who.onecupafterwork.view.AlarmView.OnButtonClickListener;

public class AlarmAdapter extends BaseAdapter {

	Context mContext;
	public ArrayList<AlarmData> mItems = new ArrayList<AlarmData>();

	// public interface OnAdapterButtonClickListener {
	// public void onAdapterItemClick(View v);
	// }
	//
	// OnAdapterButtonClickListener mListener;
	//
	// public void setOnAdapterButtonClickListener(OnAdapterButtonClickListener listener) {
	// mListener = listener;
	// }

	public AlarmAdapter(Context mContext, ArrayList<AlarmData> array) {
		this.mContext = mContext;
		mItems.addAll(array);
	}

	public void add(AlarmData data) {
		mItems.add(data);
		notifyDataSetChanged();
	}

	public void addAll(ArrayList<AlarmData> list) {
		mItems.addAll(list);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public AlarmData getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final AlarmView view;
		if (convertView == null) {
			view = new AlarmView(mContext);
			view.setOnButtonClickListener(new OnButtonClickListener() {

				@Override
				public void onButtonClick(View v, int _id) {
					mItems.remove(position);
					DBManager.getInstance().deletePerson(_id);
					notifyDataSetChanged();
				}
			});
		} else {
			view = (AlarmView) convertView;
			// view.setOnButtonClickListener(new OnButtonClickListener() {
			//
			// @Override
			// public void onButtonClick(View v, int _id) {
			// mItems.remove(position);
			// DBManager.getInstance().deletePerson(_id);
			// notifyDataSetChanged();
			// }
			// });
		}

		view.setData(mItems.get(position).message, mItems.get(position).date, (int) mItems.get(position)._id);

		return view;
	}

}
