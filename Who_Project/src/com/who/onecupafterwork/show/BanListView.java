package com.who.onecupafterwork.show;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.who.onecupafterwork.signin.R;

public class BanListView extends FrameLayout {

	public interface OnButtonClickListener {
		public void onButtonClick(View v, String data);
	}

	OnButtonClickListener mListener;

	public void setOnButtonClickListener(OnButtonClickListener listener) {
		mListener = listener;
	}

	public BanListView(Context context) {
		super(context);
		init();
	}

	TextView banView;
	String getBanNum;

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.ban_list_custom_layout, this);
		banView = (TextView) findViewById(R.id.banNumCustomView);
		Button btn = (Button) findViewById(R.id.btnDeleteBanNum);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onButtonClick(BanListView.this, getBanNum);
				}

			}
		});
	}

	public void setBanData(String banNum) {
		getBanNum = banNum;
		banView.setText(getBanNum);
	}
}
