package com.who.onecupafterwork.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.who.onecupafterwork.signin.R;

public class Tab3View extends FrameLayout {

	ImageView tab3View;

	public Tab3View(Context context) {
		super(context);
		init();
	}

	private void init() {
		tab3View = (ImageView) findViewById(R.id.imageViewTab3);
		LayoutInflater.from(getContext()).inflate(R.layout.tab3_view, this);
	}

}
