package com.who.onecupafterwork.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MailConfirmActivity extends ActionBarActivity {

	TextView mailTextView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mail_confirm_activity);

		ActionBar ab = getSupportActionBar();
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowHomeEnabled(false);

		SelectCompanyActivity select = SelectCompanyActivity.selectCompanyActivity;
		select.finish();

		Intent i = getIntent();
		mailTextView = (TextView) findViewById(R.id.mailTextView);
		mailTextView.setText(i.getStringExtra(InputMailActivity.FULL_MAIL));

		Button btn = (Button) findViewById(R.id.btnGoProfile);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MailConfirmActivity.this, ProfileActivity.class);
				startActivity(i);
				SelectCompanyActivity select = SelectCompanyActivity.selectCompanyActivity;
				select.finish();
				finish();
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Toast.makeText(MailConfirmActivity.this, "로그인 페이지로 돌아갑니다.", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(MailConfirmActivity.this, LoginActivity.class);
			startActivity(i);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
