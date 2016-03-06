package com.who.onecupafterwork.signin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCompanyEmailActivity extends ActionBarActivity {

	EditText newCompanyView, newEmailView, newDomainView;
	String fullMail;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_company_email_activity);
		ActionBar ab = getSupportActionBar();
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowHomeEnabled(false);
		newCompanyView = (EditText) findViewById(R.id.newCompanyView);
		newEmailView = (EditText) findViewById(R.id.newEmailView);
		newDomainView = (EditText) findViewById(R.id.newDomainView);

		Button btn = (Button) findViewById(R.id.btnRegisterNewCompany);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// db에 company랑 fullmail보내기
				AlertDialog.Builder builder = new AlertDialog.Builder(AddCompanyEmailActivity.this);
				builder.setTitle("Certify Email ");
				builder.setMessage("인증 메일을 전송하였습니다.");
				builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 서버로 fullmail전송하기
						fullMail = newEmailView.getText().toString() + "@" + newDomainView.getText().toString();
						dialog.dismiss();
						Intent intent = new Intent(AddCompanyEmailActivity.this, MailConfirmActivity.class);
						startActivity(intent);
						finish();
					}
				});
				builder.create().show();

			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// if (keyCode == KeyEvent.KEYCODE_BACK) {
		// Toast.makeText(AddCompanyEmailActivity.this, "로그인 페이지로 돌아갑니다.", Toast.LENGTH_SHORT).show();
		// Intent i = new Intent(AddCompanyEmailActivity.this,LoginActivity.class);
		// startActivity(i);
		// finish();
		// return true;
		// }
		return super.onKeyDown(keyCode, event);
	}
}
