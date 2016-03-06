package com.who.onecupafterwork.common;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.who.onecupafterwork.data.Contact;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.preferences.PropertyManager;
import com.who.onecupafterwork.signin.R;

public class EvaluateActivity extends ActionBarActivity {

	ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evaluate_activity);

		actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);

		Button btn = (Button) findViewById(R.id.btnOk);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				NetworkModel.getInstance().postMatchingMlike(PropertyManager.getInstance().getUserId(), PropertyManager.getInstance().getSameCode(),
						EvaluateActivity.this, new OnNetworkResultListener<ResultData>() {

							@Override
							public void onResult(ResultData result) {
								if (result.result.equals("success")) {
									Contact c = new Contact();
									c.acode = result.mcode;
									c.aidA = result.mid;
									c.uphotoA = result.mphoto;
									c.aidB = result.midF;
									c.uphotoB = result.mphotoF;
								}
							}
						});
				finish();
			}
		});
	}

}
