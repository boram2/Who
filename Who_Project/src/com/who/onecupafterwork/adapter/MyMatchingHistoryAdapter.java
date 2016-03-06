package com.who.onecupafterwork.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.who.onecupafterwork.data.MatchingGroup;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.history.MatchingHistoryView;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;

public class MyMatchingHistoryAdapter extends BaseAdapter implements MatchingHistoryView.OnProfileImageClickListener {

	Context mContext;
	ArrayList<MatchingGroup> mItems = new ArrayList<MatchingGroup>();

	public MyMatchingHistoryAdapter(Context mContext, ArrayList<MatchingGroup> array) {
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		final MatchingHistoryView mhView;
		if (convertView == null) {
			mhView = new MatchingHistoryView(mContext);
		} else {
			mhView = (MatchingHistoryView) convertView;
		}

		// 네트워크에서 가져오는 걸로, 미구현 부분
		NetworkModel.getInstance().postMatchingHistory("나의 id", mContext, new OnNetworkResultListener<ResultData>() {

			@Override
			public void onResult(ResultData result) {
				if (result.result.equals("success")) {
					MatchingGroup mg = new MatchingGroup(result.mid, result.midF, Integer.parseInt(result.mphoto), Integer.parseInt(result.mphotoF),
							result.mdate, result.marea);
					mhView.setMyMatchingHistoryData(mg);
				}
			}
		});

		return mhView;
	}

	@Override
	public void onProfileImageClick(View v, String resId) {
		// 이미지를 클릭했을 때 id에 맞는 상대방의 프로필 추출해오기
		// NetworkModel.getInstance().postProfiles("상대방ID", mContext, new OnNetworkResultListener<ResultData>() {
		//
		// @Override
		// public void onResult(ResultData result) {
		// if (result.result.equals("success")) {
		// 프로필 정보 가지고 오기
		//
		// Intent i = new Intent(mContext, ShowAnotherPersonProfileActivity.class);
		// mContext.startActivity(i);// activity를 상속받지 않은경우에 다른 Activity호출하기
		// }
		// }
		// });
	}

}
