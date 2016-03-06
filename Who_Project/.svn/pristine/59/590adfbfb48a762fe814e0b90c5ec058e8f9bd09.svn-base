package com.who.onecupafterwork.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.who.onecupafterwork.data.MatchingGroup;
import com.who.onecupafterwork.signin.R;

public class MatchingHistoryView extends FrameLayout {

	public interface OnProfileImageClickListener {
		public void onProfileImageClick(View v, String resId);
	}

	OnProfileImageClickListener mListener;

	public void setOnProfileImageClickListener(OnProfileImageClickListener listener) {
		mListener = listener;
	}

	public MatchingHistoryView(Context context) {
		super(context);
		init();
	}

	ImageView matcher1View, matcher2View;
	TextView matchingDateView, matchingLocationView;
	String choId1, choId2;

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.matching_history_layout, this);
		matcher1View = (ImageView) findViewById(R.id.matcher1View);
		matcher2View = (ImageView) findViewById(R.id.matcher2View);
		matchingDateView = (TextView) findViewById(R.id.matchingDateView);
		matchingLocationView = (TextView) findViewById(R.id.matchingLocationView);

		matcher1View.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onProfileImageClick(MatchingHistoryView.this, choId1);
				}
			}
		});

		matcher2View.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onProfileImageClick(MatchingHistoryView.this, choId2);
				}
			}
		});
	}

	public void setMyMatchingHistoryData(MatchingGroup history) {
		matcher1View.setImageResource(history.matcher1Res);
		matcher2View.setImageResource(history.matcher2Res);
		matchingDateView.setText(history.matchingDate);
		matchingLocationView.setText(history.matchingLocation);
		// 매칭 히스토리 테이블에서 결과받을 때 id까지 받아서 누르면 profile볼수 있도록
		// resId1 = history.matcher1Res;
		// resId2 = history.matcher2Res;
	}
}
