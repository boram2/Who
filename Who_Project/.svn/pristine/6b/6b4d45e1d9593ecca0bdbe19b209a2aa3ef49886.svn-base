package com.who.onecupafterwork.show;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.who.onecupafterwork.signin.R;

public class NoticeListView extends FrameLayout {

	TextView noticeTitleView, noticeDateView;

	public NoticeListView(Context context) {
		super(context);
		init();
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.notice_list_layout, this);
		noticeTitleView = (TextView) findViewById(R.id.noticeListTitle);
		noticeDateView = (TextView) findViewById(R.id.noticeListDate);
	}

	public void setText(String title, String date) {
		noticeTitleView.setText(title);
		noticeDateView.setText(date);
	}
}
