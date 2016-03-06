package com.who.onecupafterwork.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.who.onecupafterwork.data.InvitedPerson;
import com.who.onecupafterwork.data.Login;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.group.FrameFragment;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.preferences.PropertyManager;
import com.who.onecupafterwork.signin.R;

public class InviteActivity extends ActionBarActivity {

	int position;
	int flag;

	ImageView imageViewFriend;
	ImageLoader imageLoader = ImageLoader.getInstance();
	TextView textViewSender;

	String aidA;
	String senderName;
	String senderPhoto;

	final String pId = PropertyManager.getInstance().getUserId();
	final String pPw = PropertyManager.getInstance().getUserPassword();

	// @Override
	// protected void onNewIntent(Intent intent) {
	// super.onNewIntent(intent);
	//
	// aidA = intent.getStringExtra("senderId");
	// senderName = intent.getStringExtra("senderName");
	// senderPhoto = intent.getStringExtra("senderPhoto");
	//
	// Log.i("onnewintent check Intent aidA", aidA);
	// Log.i("onnewintent check Intent senderName", senderName);
	// Log.i("onnewintent check intent senderPhoto", senderPhoto);
	// }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invite_activity);

		textViewSender = (TextView) findViewById(R.id.textViewSender);
		imageViewFriend = (ImageView) findViewById(R.id.imageViewSender);

		Intent intent = getIntent();

		if (intent.getStringExtra("senderId") != null) { // 푸쉬로 들어올 때, 즉 임의의 데이터가 하나라도 있을 때
			aidA = intent.getStringExtra("senderId"); // 푸쉬가 올 때 id 값을 받는다.
			senderName = intent.getStringExtra("senderName");
			senderPhoto = intent.getStringExtra("senderPhoto");

			textViewSender.setText(senderName);
			imageLoader.displayImage(senderPhoto, imageViewFriend); // 이미지 입히기 - 유니버셜 이미지 로더

			Log.i("check Intent aidA", aidA);
			Log.i("check Intent senderName", senderName);
			Log.i("check intent senderPhoto", senderPhoto);
		} else { // 푸쉬 지우고 앱 키고 들어올 때
			Login login = new Login(pId, pPw);
			NetworkModel.getInstance().postLogin(login, InviteActivity.this, new OnNetworkResultListener<ResultData>() {

				public void onResult(ResultData result) {
					if (result.result.equals("success")) {
						// InvitedPerson[] ip = new InvitedPerson[result.uprocessData.length];
						InvitedPerson[] ip = new InvitedPerson[1];
						// for (int i = 0; i < ip.length; i++) {
						// ip[i] = new InvitedPerson();
						// ip[i].aidA = result.uprocessData[i].aidA;
						// ip[i].uname = result.uprocessData[i].uname;
						// ip[i].uphoto = result.uprocessData[i].uphoto;
						//
						// textViewSender.setText(ip[i].uname);
						// imageLoader.displayImage(ip[i].uphoto, imageViewFriend); // 이미지 입히기 - 유니버셜 이미지 로더
						// }
						ip[0] = new InvitedPerson();
						ip[0] = result.uprocessData[0];
						aidA = ip[0].aidA;
						textViewSender.setText(ip[0].uname);
						imageLoader.displayImage(ip[0].uphoto, imageViewFriend);
					}
				}
			});
		}

		System.out.println("finish invite activity setting");

		Button btn = (Button) findViewById(R.id.btnOk);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				NetworkModel.getInstance().postApplyChofriendAcceptmeg(aidA, InviteActivity.this, new OnNetworkResultListener<ResultData>() {
					@Override
					public void onResult(ResultData result) {
						System.out.println(result.result);
						if (result.result.equals("success")) {
//							if (MainTabActivity.mainTabActivity.isTaskRoot()) {
//								MainTabActivity.mainTabActivity.finish();
//							}
							Log.i("Invite Success", "register success");
							Intent intent = new Intent(InviteActivity.this, MainTabActivity.class);
							intent.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.WAIT_RECOMMODATION_TIME); // 이성 대기 화면
							intent.putExtra(MainTabActivity.INTENT_TYPE, "acceptIntent");
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
							startActivity(intent);
							finish();
						}
					}
				});
			}
		});

		btn = (Button) findViewById(R.id.btnNo);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				NetworkModel.getInstance().postApplyChofriendRefusemeg(aidA, InviteActivity.this, new OnNetworkResultListener<ResultData>() {
					@Override
					public void onResult(ResultData result) {
						if (result.result.equals("success")) {
							Log.i("거절 푸쉬 전송 성공", "친구가 거절 푸쉬 전송했습니다.");
//							if (MainTabActivity.mainTabActivity.isTaskRoot()) {
//								MainTabActivity.mainTabActivity.finish();
//							}
							Intent intent = new Intent(InviteActivity.this, MainTabActivity.class);
							intent.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.ADD_SAME); // 이성 대기 화면
							// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				 			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
							startActivity(intent);
							finish();
						}
					}
				});
			}
		});
	}

	// @Override
	// public void onBackPressed() {
	// if (MainTabActivity.mainTabActivity.isTaskRoot()) {
	// super.onBackPressed();
	// } else {
	// Intent intent = new Intent(InviteActivity.this, MainTabActivity.class);
	// intent.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.ADD_SAME); // 이성 대기 화면
	// startActivity(intent);
	// finish();
	// }
	// }
}
