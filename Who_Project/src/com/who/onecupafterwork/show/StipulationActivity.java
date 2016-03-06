package com.who.onecupafterwork.show;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.who.onecupafterwork.signin.R;

public class StipulationActivity extends ActionBarActivity {

	ActionBar actionBar;
	TextView stipulationContentView;
	TextView privacyContentView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stipulation_activity);

		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);

		stipulationContentView = (TextView) findViewById(R.id.stipulationContentView);
		privacyContentView = (TextView) findViewById(R.id.privacyContentView);
		stipulationContentView.setText(R.string.using_info);
		privacyContentView.setText(R.string.private_info);
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
