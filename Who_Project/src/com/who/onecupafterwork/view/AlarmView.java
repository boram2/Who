package com.who.onecupafterwork.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.who.onecupafterwork.signin.R;

public class AlarmView extends FrameLayout {

	TextView textViewAlarm;
	TextView alarmListDate;
	ImageView imageViewDelete;

	int _id;

	public interface OnButtonClickListener {
		public void onButtonClick(View v, int _id);
	}

	OnButtonClickListener mListener;

	public void setOnButtonClickListener(OnButtonClickListener listener) {
		mListener = listener;
	}

	public AlarmView(Context context) {
		super(context);
		init();
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.alarm_view, this);
		textViewAlarm = (TextView) findViewById(R.id.textViewAlarm);
		alarmListDate = (TextView) findViewById(R.id.alarmListDate);
		imageViewDelete = (ImageView) findViewById(R.id.imageViewDelete);
		imageViewDelete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onButtonClick(AlarmView.this, _id);
				}
			}
		});
	}

	public void setData(String message, String date, int _id) {
		textViewAlarm.setText(message);
		Log.i("date check : ", date);
		alarmListDate.setText(date);
		this._id = _id;
	}
}
