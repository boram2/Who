package com.who.onecupafterwork.history;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.who.onecupafterwork.adapter.MyMatchingHistoryAdapter;
import com.who.onecupafterwork.data.MatchingGroup;
import com.who.onecupafterwork.show.SettingActivity;
import com.who.onecupafterwork.signin.R;

public class HistoryActivity extends ActionBarActivity {
	
	ListView matchingHistoryList;
	MyMatchingHistoryAdapter mAdapter;
	ArrayList<MatchingGroup> mHistoryData = new ArrayList<MatchingGroup>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar ab = getSupportActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowHomeEnabled(false);

		setContentView(R.layout.history_activity);
		matchingHistoryList = (ListView) findViewById(R.id.matchingHistoryList);

		// setMatchingHistoryData();
		mAdapter = new MyMatchingHistoryAdapter(HistoryActivity.this, mHistoryData);
		matchingHistoryList.setAdapter(mAdapter);
	}

	// private void setMatchingHistoryData() {
	// MatchingGroup mh = new MatchingGroup(R.drawable.abc_ic_search, R.drawable.abc_ic_search, "2014/01/01", "GangNam");
	//
	// mHistoryData.add(mh);
	// }

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
