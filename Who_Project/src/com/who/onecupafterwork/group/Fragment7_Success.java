package com.who.onecupafterwork.group;

import java.util.Calendar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.who.onecupafterwork.data.Contact;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.signin.R;

public class Fragment7_Success extends Fragment {

	TextView textViewPhoneNum, textViewName;
	ImageView imageViewWho1, imageViewWho2;
	
	ImageLoader imageLoader = ImageLoader.getInstance();

	int dHour;
	String whoPhon, time;

	/* 탭1의 마지막 화면, 결과를 기다려주세요. */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment7_success, container, false);

		textViewPhoneNum = (TextView) v.findViewById(R.id.textViewPhoneNum);
		textViewName = (TextView) v.findViewById(R.id.textViewName);

		imageViewWho1 = (ImageView) v.findViewById(R.id.imageViewWho1);
		imageViewWho2 = (ImageView) v.findViewById(R.id.imageViewWho2);
		
		Calendar cal = java.util.Calendar.getInstance();

		dHour = cal.get(cal.HOUR_OF_DAY);

		if (dHour >= 10 && dHour < 12) {
			time = "T1";
		} else if (dHour >= 12 && dHour < 14) {
			time = "T2";
		} else if (dHour >= 14 && dHour < 16) {
			time = "T3";
		} else {
			time = "T1";
		}
		
		NetworkModel.getInstance().postMatchingResult(time, getActivity(), new OnNetworkResultListener<ResultData>() {

			@Override
			public void onResult(ResultData result) {
				if (result.result.equals("success")) {
					Contact c = new Contact();
					c.acode = result.mcode;
					c.aidA = result.mid;
					String mname = result.mname;
					c.uphotoA = result.mphoto;
					whoPhon = result.mphon; // 이성 주선자의 전화번호
					c.aidB = result.midF;
					String mnameF = result.mnameF;
					c.uphotoB = result.mphotoF;

					// 아래 주석과 같이 뿌려주는 작업 필요
					textViewName.setText(mname);
					textViewPhoneNum.setText(whoPhon);
					Log.i("c.uphotoA", c.uphotoA);
					Log.i("c.uphotoB", c.uphotoB);
					imageLoader.displayImage(c.uphotoA, imageViewWho1);
					imageLoader.displayImage(c.uphotoB, imageViewWho2);
				}
			}
		});
		
		textViewPhoneNum.setText(whoPhon);
		textViewPhoneNum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String uri = "tel:" + whoPhon;
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse(uri));
				startActivity(intent);
			}
		});
		
		return v;
	}
}
