package com.who.onecupafterwork.show;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.who.onecupafterwork.signin.R;

public class ChangePasswordActivity extends ActionBarActivity {

	EditText currentPWView, newPWView, confirmNewPWView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_password_activity);

		currentPWView = (EditText) findViewById(R.id.currentPWView);
		newPWView = (EditText) findViewById(R.id.newPWView);
		confirmNewPWView = (EditText) findViewById(R.id.confirmNewPWView);

		Button btn = (Button) findViewById(R.id.btnSavePW);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String currentPW = currentPWView.getText().toString();
				String newPW = newPWView.getText().toString();
				String confirmNewPW = confirmNewPWView.getText().toString();

				if (!currentPW.equals("")) {// db에 있는 pw와 맞으면
					if (newPW.equals(confirmNewPW)) {
						// db에 새 비밀번호 저장
						Toast.makeText(ChangePasswordActivity.this, "save PW", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(ChangePasswordActivity.this, "new password is not matching", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(ChangePasswordActivity.this, "confirm password is not matching", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

}
