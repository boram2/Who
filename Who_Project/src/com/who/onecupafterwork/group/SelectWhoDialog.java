package com.who.onecupafterwork.group;

import java.util.Calendar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.who.onecupafterwork.common.MainTabActivity;
import com.who.onecupafterwork.data.Contact;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.signin.R;

public class SelectWhoDialog extends DialogFragment {

	TextView textViewOther1;
	TextView textViewOther2;
	// TextView textViewFix1;
	// TextView textViewFix2;

	TextView textViewOther3;
	TextView textViewOther4;
	// TextView textViewFix3;
	// TextView textViewFix4;

	TextView textViewOther5;
	TextView textViewOther6;
	// TextView textViewFix5;
	// TextView textViewFix6;

	int otherCount;

	LinearLayout ll1;
	LinearLayout ll2;
	LinearLayout ll3;

	Contact[] whoList;
	String[] list;

	int dHour;
	String time;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	private void countOther() {
		if (otherCount == 1) {
			ll1.setVisibility(View.GONE);
			ll2.setVisibility(View.GONE);
		} else if (otherCount == 2) {
			ll1.setVisibility(View.GONE);
		} else if (otherCount == 3) {

		} else {
			dismiss();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Dialog dialog = getDialog();
		if (dialog != null) {
			dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
			dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}

		View v = inflater.inflate(R.layout.select_who_dialog, container, false);

		Calendar cal = java.util.Calendar.getInstance();

		dHour = cal.get(cal.HOUR_OF_DAY);

		if (dHour >= 12 && dHour < 13) {
			time = "T1";
		} else if (dHour >= 14 && dHour < 15) {
			time = "T2";
		} else if (dHour >= 16 && dHour < 17) {
			time = "T3";
		} else {
			time = "Error";
		}

		// 값 초기화
		whoList = new Contact[3];
		for (int i = 0; i < whoList.length; i++) {
			whoList[i] = new Contact();
		}

		list = new String[3];
		for (int i = 0; i < list.length; i++) {
			list[i] = new String();
		}

		textViewOther1 = (TextView) v.findViewById(R.id.textViewOther1);
		textViewOther2 = (TextView) v.findViewById(R.id.textViewOther2);
		// textViewFix1 = (TextView) v.findViewById(R.id.textViewFix1);
		// textViewFix2 = (TextView) v.findViewById(R.id.textViewFix2);

		textViewOther3 = (TextView) v.findViewById(R.id.textViewOhter3);
		textViewOther4 = (TextView) v.findViewById(R.id.textViewOther4);
		// textViewFix3 = (TextView) v.findViewById(R.id.textViewFix3);
		// textViewFix4 = (TextView) v.findViewById(R.id.textViewFix4);

		textViewOther5 = (TextView) v.findViewById(R.id.textViewOhter5);
		textViewOther6 = (TextView) v.findViewById(R.id.textViewOther6);
		// textViewFix5 = (TextView) v.findViewById(R.id.textViewFix5);
		// textViewFix6 = (TextView) v.findViewById(R.id.textViewFix6);

		ll1 = (LinearLayout) v.findViewById(R.id.ll1);
		ll2 = (LinearLayout) v.findViewById(R.id.ll2);
		ll3 = (LinearLayout) v.findViewById(R.id.ll3);

		otherCount = Fragment5_SelectWho.getChecked();
		countOther();

		Bundle b = getArguments();
		/* 자신이 고른 이성 그룹의 정보 가지고 오기 */

		if (otherCount == 3) {
			whoList[0] = (Contact) b.getSerializable("selectKey1");
			whoList[1] = (Contact) b.getSerializable("selectKey2");
			whoList[2] = (Contact) b.getSerializable("selectKey3");

			textViewOther1.setText(whoList[0].aidA);
			textViewOther2.setText(whoList[0].aidB);
			textViewOther3.setText(whoList[1].aidA);
			textViewOther4.setText(whoList[1].aidB);
			textViewOther5.setText(whoList[2].aidA);
			textViewOther6.setText(whoList[2].aidB);

			list[0] = whoList[0].acode;
			list[1] = whoList[1].acode;
			list[2] = whoList[2].acode;
		} else if (otherCount == 2) {
			whoList[0] = (Contact) b.getSerializable("selectKey1");
			whoList[1] = (Contact) b.getSerializable("selectKey2");

			textViewOther3.setText(whoList[0].aidA);
			textViewOther4.setText(whoList[0].aidB);
			textViewOther5.setText(whoList[1].aidA);
			textViewOther6.setText(whoList[1].aidB);

			list[0] = whoList[0].acode;
			list[1] = whoList[1].acode;
			list[2] = "NULL";
		} else if (otherCount == 1) {
			whoList[0] = (Contact) b.getSerializable("selectKey1");

			textViewOther5.setText(whoList[0].aidA);
			textViewOther6.setText(whoList[0].aidB);

			list[0] = whoList[0].acode;
			list[1] = "NULL";
			list[2] = "NULL";
		}

		// Toast.makeText(getActivity(), "" + otherCount, Toast.LENGTH_SHORT).show();

		Button btn = (Button) v.findViewById(R.id.btnCancel);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

		btn = (Button) v.findViewById(R.id.btnOk);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				/* 이성그룹 보내기 */
				NetworkModel.getInstance().postMatchingChoose(time, list, getActivity(), new OnNetworkResultListener<ResultData>() {

					@Override
					public void onResult(ResultData result) {
						if (result.result.equals("success")) {
							System.out.println("진입 성공 넘어가라");
							Intent intent = new Intent(getActivity(), MainTabActivity.class);
							intent.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.WAIT_WHO);
							dismiss();
							getActivity().finish();
							startActivity(intent);
						}
					}
				});
			}
		});
		return v;
	}

}
