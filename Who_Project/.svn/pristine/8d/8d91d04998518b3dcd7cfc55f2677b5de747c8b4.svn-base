package com.who.onecupafterwork.signin;

import java.io.IOException;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.who.onecupafterwork.common.InviteActivity;
import com.who.onecupafterwork.common.MainTabActivity;
import com.who.onecupafterwork.data.Login;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.preferences.PropertyManager;

public class LoginActivity extends ActionBarActivity {

	EditText idView, pswdView;
	TextView idLostView;

	String id, pswd;
	String pId, pPw;

	ActionBar actionBar;
	GoogleCloudMessaging gcm;
	String regid;

	public static final int MESSAGE_TIMEOUT_BACK = 0;
	public static final int TIMEOUT_BACK_TIME = 2000;
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	boolean isBackPressed = false; // 백키를 눌렀는지 확인하는 플레그

	Handler mHandler = new Handler() { // 핸들러 정의

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) { // 수신한 msg의 what 변수를 통해 어떤 메세지인지 판별한다.
			case MESSAGE_TIMEOUT_BACK: // 0의 경우 -> 눌렀는지 얼마 지나지 않았다. 이 점은 47째 줄에서 다시 확인.
				isBackPressed = false;
				break;
			}
		}
	};

	@Override
	public void onBackPressed() { // 백키 눌렀을 때 처리하는 함수

		if (getSupportFragmentManager().getBackStackEntryCount() > 0) { // 프레그먼트의 경우 -> 백스택에 무언가가 남아있을 때 : 그냥 팝 시켜야 하므로 되돌린다.
			super.onBackPressed(); // 팝 시키는 함수, 디폴트 백프레스 함수라고 생각한다.
			return;
		}

		if (!isBackPressed) { // 백키가 눌리면
			isBackPressed = true;
			Toast.makeText(this, "한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
			mHandler.sendMessageDelayed(mHandler.obtainMessage(MESSAGE_TIMEOUT_BACK), TIMEOUT_BACK_TIME); // TIMEOUT_BACK_TIME만큼 딜레이되면, 핸들러는
			// MESSAGE_TIMEOUT_BACK의 정보를 담아
			// 메인 스레드에 전송한다.
			// mHandler.obtainMessage(MESSAGE_TIMEOUT_BACK)
			// 처리한다.
		} else { // isBackPressed가 되어있을 때는 디폴트 onBackPressed함수를 실행.
			mHandler.removeMessages(MESSAGE_TIMEOUT_BACK); // 스레드에서 메세지를 보내는 작업을 하지 않도록 메세지 제거 함수 호출
			super.onBackPressed();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);

		actionBar = getSupportActionBar();
		actionBar.hide();

		idView = (EditText) findViewById(R.id.idView);
		pswdView = (EditText) findViewById(R.id.passwordView);

		Button btn = (Button) findViewById(R.id.btnLogin);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				id = idView.getText().toString();
				pswd = pswdView.getText().toString();
				if (!id.equals("") && id != null && !pswd.equals("") && pswd != null) {
					Login login = new Login(id, pswd);
					NetworkModel.getInstance().postLogin(login, LoginActivity.this, new OnNetworkResultListener<ResultData>() {

						@Override
						public void onResult(ResultData result) {
							System.out.println("#check#" + result.result);
							if (result.result.equals("success")) {
								PropertyManager.getInstance().setUserId(id);
								PropertyManager.getInstance().setUserPassword(pswd);
								String userStatus = result.uprocess;
								System.out.println("User Status1 : " + userStatus);
								PropertyManager.getInstance().setUserStatus(userStatus);
								/* 로그인 성공 시 Shared Preferences로 ID/PW 저장 */
								if (userStatus.equals("0101")) {
									Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
									intent.putExtra(MainTabActivity.INVITE_KEY, PropertyManager.getInstance().getUserStatus());
									// intent.putExtra(MainTabActivity.INVITE_KEY, userStatus);
									startActivity(intent);
									finish();
								} else if (userStatus.equals("0303")) {
									Intent intent = new Intent(LoginActivity.this, InviteActivity.class);
									startActivity(intent);
									finish();
								} else {
									Intent i = new Intent(LoginActivity.this, MainTabActivity.class);
									i.putExtra(MainTabActivity.INVITE_KEY, PropertyManager.getInstance().getUserStatus());
									// i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK&Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
									startActivity(i);
									finish();
								}

								// registerID 전송
								if (checkPlayServices()) {
									final String regId = PropertyManager.getInstance().getRegistrationId();
									Log.i("Success Send RegId", "Check " + regId);
									if (regId != null && regId.equals("")) {
										System.out.println("enter regId null ");
										registerInBackground();
									} else {
										setRegisterId(regId);
										finish();
									}
								}
							} else if (result.result.equals("error")) {
								Toast.makeText(LoginActivity.this, "id/pw가 일치하지 않습니다(Login)", Toast.LENGTH_SHORT).show(); // 서버와 매칭안되는건지 서버연결이 안되는건지
																															// 내려줘야함
							}
						}
					});
				} else {
					Toast.makeText(LoginActivity.this, "ID/PW를 입력해주세요.", Toast.LENGTH_SHORT).show();
				}
			}
		});

		btn = (Button) findViewById(R.id.btnRegister);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(LoginActivity.this, SignInActivity.class);
				startActivity(i);
				finish();
			}
		});
	}

	private void registerInBackground() {
		new AsyncTask<Void, Integer, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String msg = "";

				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging.getInstance(LoginActivity.this);
					}
					regid = gcm.register("873740300994"); // register id 등록 /
					// gcm으로 부터 나의 프로젝트
					// id를 받아오는 것
					msg = "Device registered, registration ID = " + regid;
					PropertyManager.getInstance().setRegistrationId(regid);
					// setRegisteredServer(regid); // 서버로 레지스터 아이디 올리는 로직
				} catch (IOException ex) {
					msg = "Error :" + ex.getMessage();
					// If there is an error, don't just keep trying to register.
					// Require the user to click a button again, or perform
					// exponential back-off.
				}
				return msg;
			}

			@Override
			protected void onPostExecute(String msg) {
				System.out.println("enter onpostExecute");
				setRegisterId(regid);// 로그아웃하고 로그인 하는 사람들을 위해
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
		NetworkModel.getInstance().postRegister(regid, LoginActivity.this, new OnNetworkResultListener<ResultData>() {

			@Override
			public void onResult(ResultData result) {
				if (result.result.equals("success")) {
					Log.i("Success Send RegId", "success" + regid);
					System.out.println("regId : " + regid + " <<finish");
				}
			}
		});
	}
}
