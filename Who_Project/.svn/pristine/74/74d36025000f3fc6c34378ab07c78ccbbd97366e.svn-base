package com.who.onecupafterwork.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.who.onecupafterwork.signin.R;

public class SpinnerView extends FrameLayout {

	TextView tv;

	public SpinnerView(Context context) {
		super(context);
		init();
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.spinner_view, this);
		tv = (TextView) findViewById(android.R.id.text1);
	}

	public void setText(String data) {
		tv.setText(data);
	}
}
