package com.who.onecupafterwork.common;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.who.onecupafterwork.data.Login;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.preferences.PropertyManager;
import com.who.onecupafterwork.signin.LoginActivity;
import com.who.onecupafterwork.signin.ProfileActivity;
import com.who.onecupafterwork.signin.R;

public class SplashActivity extends Activity {
	public static SplashActivity splashActivity;

	final String pId = PropertyManager.getInstance().getUserId();
	final String pPw = PropertyManager.getInstance().getUserPassword();
	boolean isFirst = true;
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	GoogleCloudMessaging gcm;
	String regid;

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0) {
				Log.i("Handler0", "Go Main");
				Intent intent = new Intent(SplashActivity.this, MainTabActivity.class);
				intent.putExtra(MainTabActivity.INVITE_KEY, PropertyManager.getInstance().getUserStatus());
				Log.i("status", PropertyManager.getInstance().getUserStatus());
				startActivity(intent);
				finish();
			} else if (msg.what == 1) {
				Log.i("Handler1", "Go Login");
				Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
				intent.putExtra(MainTabActivity.INVITE_KEY, PropertyManager.getInstance().getUserStatus());
				startActivity(intent);
				finish();
			} else if (msg.what == 2) {
				Log.i("Handler1", "Go Profile");
				Intent intent = new Intent(SplashActivity.this, ProfileActivity.class);
				// intent.putExtra(MainTabActivity.INVITE_KEY, PropertyManager.getInstance().getUserStatus());
				startActivity(intent);
				finish();
			} else if (msg.what == 3) {
				Log.i("Handler1", "Go Invite");
				Intent intent = new Intent(SplashActivity.this, InviteActivity.class);
				// intent.putExtra(MainTabActivity.INVITE_KEY, PropertyManager.getInstance().getUserStatus());
				startActivity(intent);
				finish();
			}
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_activity);

		splashActivity = this;

		if (checkPlayServices()) {
			final String regId = PropertyManager.getInstance().getRegistrationId();
			if (regId.equals("")) {
				registerInBackground();
			} else {
				// 레지스터 아이디가 있다면 자동 로그인 로직
				if (pId != null && !pId.equals("") && pPw != null && !pPw.equals("")) {
					Login login = new Login(pId, pPw);
					NetworkModel.getInstance().postLogin(login, SplashActivity.this, new OnNetworkResultListener<ResultData>() {

						@Override
						public void onResult(ResultData result) {
							if (result.result.equals("success")) {
								// Toast.makeText(SplashActivity.this, "로그인에 성공하였습니다.(splash1)", Toast.LENGTH_SHORT).show();
								Toast.makeText(SplashActivity.this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
								setRegisterId(regId); // 로그인 성공시 regId 올린다.

								String userStatus = result.uprocess;
								System.out.println("User Status1 : " + userStatus);
								PropertyManager.getInstance().setUserStatus(userStatus);
								if (userStatus.equals("0100")) {
									mHandler.sendEmptyMessageDelayed(2, 1000); // 프로필로 넘기기
								} else if (userStatus.equals("0303")) {
									mHandler.sendEmptyMessageDelayed(3, 1000);
								} else {
									mHandler.sendEmptyMessageDelayed(0, 1000);
								}
								Log.i("Success Send RegId", "before send " + regId);
							} else {
								// Toast.makeText(SplashActivity.this, "DB정보와 일치하지 않아 로그인 실패(splash1)", Toast.LENGTH_SHORT).show();
								Toast.makeText(SplashActivity.this, "ID/PW 정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
								mHandler.sendEmptyMessageDelayed(1, 1000);
							}
						}
					});
				} else { // 자동 로그인 실패
				// Toast.makeText(SplashActivity.this, "로그인에 실패하였습니다.(splash1-1 shared id err)", Toast.LENGTH_SHORT).show();
					mHandler.sendEmptyMessageDelayed(1, 1000);
				}
			}
		} else {// checkPlayService 메소드 실패 시
		// Toast.makeText(SplashActivity.this, "로그인에 실패하였습니다.(splash1-2 play service)", Toast.LENGTH_SHORT).show();
			Toast.makeText(SplashActivity.this, "에러가 발생했습니다. 다시 실행시켜 주세요.", Toast.LENGTH_SHORT).show();
			mHandler.sendEmptyMessageDelayed(0, 1000);
		}
	}

	private void registerInBackground() {
		new AsyncTask<Void, Integer, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String msg = "";

				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging.getInstance(SplashActivity.this);
					}
					regid = gcm.register("873740300994"); // gcm으로 부터 나의 프로젝트
															// id를 받아서 register
															// id 생성

					msg = "Device registered, registration ID = " + regid;

					PropertyManager.getInstance().setRegistrationId(regid);
				} catch (IOException ex) {
					msg = "Error :" + ex.getMessage();
				}
				return msg;
			}

			@Override
			protected void onPostExecute(String msg) {
				if (pId != null && !pId.equals("") && pPw != null && !pPw.equals("")) {
					Login login = new Login(pId, pPw);
					// 자동로그인 로직(regId가 없었으나 shared id, pw가 있을 때)
					NetworkModel.getInstance().postLogin(login, SplashActivity.this, new OnNetworkResultListener<ResultData>() {

						@Override
						public void onResult(ResultData result) {
							Log.i("Before send regID", regid);
							if (result.result.equals("success")) {
								// Toast.makeText(SplashActivity.this, "로그인에 성공하였습니다.(splash2)", Toast.LENGTH_SHORT).show();
								Toast.makeText(SplashActivity.this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
								setRegisterId(regid); // 로그인에 성공하면 레지 아이디 올린다.

								// regID등록
								String userStatus = result.uprocess;
								System.out.println("User Status3 : " + userStatus);
								PropertyManager.getInstance().setUserStatus(userStatus);
								if (userStatus.equals("0100")) {
									mHandler.sendEmptyMessageDelayed(2, 1000);
								} else if (userStatus.equals("0303")) {
									mHandler.sendEmptyMessageDelayed(3, 1000);
								} else {
									mHandler.sendEmptyMessageDelayed(0, 1000);
								}
							} else {
								// Toast.makeText(SplashActivity.this, "DB정보와 일치하지 않아(ID/PW) 로그인 실패.(splash2)", Toast.LENGTH_SHORT).show();
								Toast.makeText(SplashActivity.this, "ID/PW 정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
								mHandler.sendEmptyMessageDelayed(1, 1000);
							}
						}
					});
				} else {
					// Toast.makeText(SplashActivity.this, "로그인에 실패하였습니다.(splash2-1 shared id err)", Toast.LENGTH_SHORT).show();
					mHandler.sendEmptyMessageDelayed(1, 1000);
				}
			}
		}.execute(null, null, null);
	}

	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {
				finish();
			}
			return false;
		}
		return true;
	}

	public void setRegisterId(final String regid) {
		NetworkModel.getInstance().postRegister(regid, SplashActivity.this, new OnNetworkResultListener<ResultData>() {

			@Override
			public void onResult(ResultData result) {
				if (result.result.equals("success")) {
					Log.i("Success Send RegId", "success" + regid);
					System.out.println("RegId : " + regid + " <<finish");
				}
			}
		});
	}
}
