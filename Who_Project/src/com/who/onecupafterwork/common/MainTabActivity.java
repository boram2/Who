package com.who.onecupafterwork.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

import com.who.onecupafterwork.alarm.AlarmFragment;
import com.who.onecupafterwork.group.FrameFragment;
import com.who.onecupafterwork.show.CancelDialog;
import com.who.onecupafterwork.show.InviteAcceptDialog;
import com.who.onecupafterwork.show.InviteCancelDialog;
import com.who.onecupafterwork.show.RecommendDialog;
import com.who.onecupafterwork.show.RecommendFriendDialog;
import com.who.onecupafterwork.show.ShowMoreInfoFragment;
import com.who.onecupafterwork.show.ShowMyProfileActivity;
import com.who.onecupafterwork.signin.R;
import com.who.onecupafterwork.view.Tab1View;
import com.who.onecupafterwork.view.Tab2View;
import com.who.onecupafterwork.view.Tab3View;

public class MainTabActivity extends ActionBarActivity {

	FragmentTabHost tabHost;

	FrameFragment ff;
	String fragmentType;
	// int alarmFlag;

	/* 전역 변수 */
	public static final String INTENT_TYPE = "intentType";
	public static final String INVITE_KEY = "inviteKey";

	public static final int NEGATIVE_CODE = -1;

	public static final int MESSAGE_TIMEOUT_BACK = 0;
	public static final int TIMEOUT_BACK_TIME = 2000;

	boolean isBackPressed = false; // 백키를 눌렀는지 확인하는 플레그

	ActionBar actionBar;

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

	public static MainTabActivity mainTabActivity;

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_tab, menu);
		MenuItem item = menu.findItem(R.id.menuProfile);
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent i = new Intent(MainTabActivity.this, ShowMyProfileActivity.class);
				startActivity(i);
				return false;
			}
		});
		return true;
	};

	@Override
	public void onBackPressed() { // 백키 눌렀을 때 처리하는 함수

		if (getSupportFragmentManager().getBackStackEntryCount() > 0) { // 프레그먼트의 경우 -> 백스택에 무언가가 남아있을 때 : 그냥 팝 시켜야 하므로 되돌린다.
			super.onBackPressed(); // 팝 시키는 함수, 디폴트 백프레스 함수라고 생각한다.
			return;
		}

		// if (alarmFlag == AlarmFragment.ALARM_FLAG) {
		// alarmFlag = 0;
		// tabHost.setCurrentTabByTag("tab2");
		// } else {

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
		// }
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_tab_activity);

		// NetworkModel.getInstance().postApply(MainTabActivity.this, new OnNetworkResultListener<ResultData>() {
		//
		// @Override
		// public void onResult(ResultData result) {
		// System.out.println("첫 사진 띄우기 진입");
		// if (result.result.equals("success")) {
		// System.out.println("첫 사진 띄우기 성공");
		// PropertyManager.getInstance().setProfilePath(result.uphoto); // 내 사진 가지고 오기
		// } else if (result.result.equals("error")) {
		// Toast.makeText(MainTabActivity.this, "apply 에러", Toast.LENGTH_SHORT).show();
		// }
		// }
		// });

		actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);

		mainTabActivity = MainTabActivity.this;

		Intent i = getIntent(); // 로그인 이후 상태 정보를 받아오는 인텐트
		// fragmentType = i.getIntExtra(INVITE_KEY, FrameFragment.WAIT_START_SCREEN); // 디폴트 값은 0
		//친구 거절 푸쉬 올때 받는 화면 
		Log.i("Main Intent : ",""+i.getStringExtra(INTENT_TYPE));
		if(i.getStringExtra(INTENT_TYPE)!=null){
			Log.i("어디서 온 노티인가? ",i.getStringExtra(INTENT_TYPE));
			FragmentManager fm = getSupportFragmentManager();
			switch (i.getStringExtra(INTENT_TYPE)) {
			case "refuseIntent":
				InviteCancelDialog icdialog = new InviteCancelDialog();
				icdialog.show(fm, "dialog");
				break;
			case "acceptIntent" :
				InviteAcceptDialog acDialog = new InviteAcceptDialog();
				acDialog.show(fm, "dialog");
				break;
			case "RecommendFriendIntent" :
				RecommendFriendDialog rfDialog = new RecommendFriendDialog();
				rfDialog.show(fm, "dialog");
				break;
			case "RecommendIntent" :
				RecommendDialog rDialog = new RecommendDialog();
				rDialog.show(fm, "dialog");
				break;
			case "cancelIntent" : 
				CancelDialog cDialog = new CancelDialog();
				cDialog.show(fm, "dialog");
				break;
			}
		}

		fragmentType = i.getStringExtra(INVITE_KEY);
		// if (ff != null) {
		// ff.switchFragment(fragmentType);
		// }

		tabHost = (FragmentTabHost) findViewById(R.id.tabHost);
		tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(new Tab1View(this)), FrameFragment.class, null);
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(new Tab2View(this)), AlarmFragment.class, null);
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(new Tab3View(this)), ShowMoreInfoFragment.class, null);

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabName) {
				if (tabName.equals("tab1")) {

				} else if (tabName.equals("tab2")) {

				} else if (tabName.equals("tab3")) {

				}
			}
		});
	}

	@Override
	protected void onNewIntent(Intent intent) { // 원래 앱을 사용중이다가 노티가 왔을 때 intent를 가지고 오는 함수
		super.onNewIntent(intent);
		if (ff != null) {
			ff.switchFragment(fragmentType);
		}
	}

	@Override
	public void onAttachFragment(Fragment fragment) {
		super.onAttachFragment(fragment);
		if (fragment instanceof FrameFragment) {
			ff = (FrameFragment) fragment;
			ff.switchFragment(fragmentType);
		}
	}

}
