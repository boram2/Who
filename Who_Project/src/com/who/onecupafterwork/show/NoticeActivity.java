package com.who.onecupafterwork.show;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.who.onecupafterwork.adapter.NoticeAdapter;
import com.who.onecupafterwork.data.Message;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.signin.R;

public class NoticeActivity extends ActionBarActivity {

	ListView noticeListView;
	NoticeAdapter mAdapter;
	ArrayList<Message> mNoticeList = new ArrayList<Message>();

	Message[] mg;

	public static final String NOTICE_CONTENT = "noticeContent";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notice_activity);

		ActionBar ab = getSupportActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowHomeEnabled(false);

		// NetworkModel.getInstance().postNoticeList(NoticeActivity.this, new OnNetworkResultListener<ResultData>() {
		//
		// @Override
		// public void onResult(ResultData result) {
		// if (result.result.equals("success")) {
		// /* 데이터 초기화 */
		// mg = new Message[result.notylist.length];
		// for (int i = 0; i < result.notylist.length; i++) {
		// mg[i] = new Message();
		// }
		//
		// mg = result.notylist;
		//
		// // for (int i = 0; i < mg.length; i++) {
		// // mNoticeList.add(mg[i]);
		// //
		// // System.out.println(mg[i].imageurl);
		// // }
		// }
		// }
		// });

		noticeListView = (ListView) findViewById(R.id.noticeListView);
		mAdapter = new NoticeAdapter(NoticeActivity.this, mNoticeList);
		// setNoticeList();
		NetworkModel.getInstance().postNoticeList(NoticeActivity.this, new OnNetworkResultListener<ResultData>() {

			@Override
			public void onResult(ResultData result) {
				if (result.result.equals("success")) {
					/* 데이터 초기화 */
					mg = new Message[result.notylist.length];
					for (int i = 0; i < mg.length; i++) {
						mg[i] = new Message();
						mg[i] = result.notylist[i];
						mg[i].regdate = mg[i].regdate.replaceAll("-", ".");
						mg[i].regdate = (String) mg[i].regdate.subSequence(5, 10);
						mAdapter.add(mg[i]);
					}
				}
			}
		});
		noticeListView.setAdapter(mAdapter);

		noticeListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
				Intent i = new Intent(NoticeActivity.this, NoticeContentActivity.class);
				i.putExtra(NOTICE_CONTENT, mg[position].imageurl);
				startActivity(i);
			}
		});

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
