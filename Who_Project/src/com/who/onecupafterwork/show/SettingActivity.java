package com.who.onecupafterwork.show;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.who.onecupafterwork.signin.R;

public class SettingActivity extends ActionBarActivity {

	ArrayAdapter<String> mAdapter;
	ListView settingList;
	ArrayList<String> data = new ArrayList<String>();
	ToggleButton pushToggle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_activity);

		ActionBar ab = getSupportActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowHomeEnabled(false);

		settingList = (ListView) findViewById(R.id.settingListView);
		// pushToggle = (ToggleButton) findViewById(R.id.togglePushAlram);
//		pushToggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//				if (isChecked) {
//					NetworkModel.getInstance().postSetToggle(1, SettingActivity.this, new OnNetworkResultListener<ResultData>() {
//
//						@Override
//						public void onResult(ResultData result) {
//							if (result.result.equals("success")) {
////								Toast.makeText(SettingActivity.this, "Setting on toggle!", Toast.LENGTH_SHORT).show();
//							}
//						}
//
//					});
//				} else { // off
//					NetworkModel.getInstance().postSetToggle(0, SettingActivity.this, new OnNetworkResultListener<ResultData>() {
//
//						@Override
//						public void onResult(ResultData result) {
//							if (result.result.equals("success")) {
////								Toast.makeText(SettingActivity.this, "Setting off toggle!", Toast.LENGTH_SHORT).show();
//							}
//						}
//
//					});
//				}
//			}
//		});

		setupSettingList();
		mAdapter = new ArrayAdapter<>(SettingActivity.this, android.R.layout.simple_list_item_1, data);
		settingList.setAdapter(mAdapter);

		settingList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
				FragmentManager fm = getSupportFragmentManager();
				Intent i;
				switch (position) {
//				case 0:
//					i = new Intent(SettingActivity.this, ChangePasswordActivity.class);
//					startActivity(i);
//					break;
				case 0:
//				case 1:
					LogOutDialog logoutDialog = new LogOutDialog();
					logoutDialog.show(fm, "dialog");
					break;
//				case 2:
				case 1:
					ServiceOutDialog soDialog = new ServiceOutDialog();
					soDialog.show(fm, "dialog");
					break;
				}
			}
		});

	}

	public void setupSettingList() {
//		data.add("  비밀번호 변경");
		data.add("  로그아웃");
		data.add("  서비스 탈퇴");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			Intent i = new Intent(this, SettingActivity.class);
			NavUtils.navigateUpTo(this, i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
