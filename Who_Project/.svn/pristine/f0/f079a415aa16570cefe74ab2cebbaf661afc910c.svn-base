package com.who.onecupafterwork.signin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InputMailActivity extends ActionBarActivity {

	EditText mailIdView;
	TextView mailDomainView;
	String fullMailAddress, mailDomain;

	public static final String FULL_MAIL = "fullMail";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input_mail_activity);

		ActionBar ab = getSupportActionBar();
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowHomeEnabled(false);

		Intent i = getIntent();
		mailIdView = (EditText) findViewById(R.id.mailIdView);
		mailDomainView = (TextView) findViewById(R.id.mailDomainView);

		mailDomain = i.getStringExtra(SelectCompanyActivity.PARAM_COMPANY_DOMIAN);
		mailDomainView.setText(mailDomain);

		Button btn = (Button) findViewById(R.id.btnMailCertify);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mailIdView != null && !mailIdView.equals("")) {
					fullMailAddress = mailIdView.getText().toString() + mailDomain;
					// 서버에 넘기기
					AlertDialog.Builder builder = new AlertDialog.Builder(InputMailActivity.this);
					builder.setTitle("Certify Email ");
					builder.setMessage("인증 메일을 전송하였습니다.");
					builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							Intent intent = new Intent(InputMailActivity.this, MailConfirmActivity.class);
							intent.putExtra(FULL_MAIL, fullMailAddress);
							startActivity(intent);
							finish();
						}
					});
					builder.create().show();
				} else {
					Toast.makeText(InputMailActivity.this, "메일을 입력하세요.", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

}
