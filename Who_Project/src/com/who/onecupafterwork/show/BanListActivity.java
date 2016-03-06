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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.who.onecupafterwork.adapter.BanListAdapter;
import com.who.onecupafterwork.adapter.BanListAdapter.OnAdapterButtonClickListener;
import com.who.onecupafterwork.adapter.MySpinnerAdapter;
import com.who.onecupafterwork.data.BanNumList;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.signin.R;

public class BanListActivity extends ActionBarActivity {
	
	Spinner phoneNum1View;
	EditText phoneNum2View, phoneNum3View;
	String fullBanPhoneNum, phoneNum1;
	ListView banListView;
	BanListAdapter banAdapter;
	MySpinnerAdapter phoneNum1Adapter;
	String[] phone1Spinner = { "010", "011", "016", "019" };
	ArrayList<String> banPhoneNumList = new ArrayList<String>();
	BanNumList[] banNumList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ban_list_activity);

		ActionBar ab = getSupportActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowHomeEnabled(false);

		setBanList();
		
		phoneNum1View = (Spinner) findViewById(R.id.banPhoneNum1View);
		phoneNum2View = (EditText) findViewById(R.id.banPhoneNum2View);
		phoneNum3View = (EditText) findViewById(R.id.banPhoneNum3View);
		banListView = (ListView) findViewById(R.id.banListView);

		phoneNum1Adapter = new MySpinnerAdapter(this, phone1Spinner);
		phoneNum1View.setAdapter(phoneNum1Adapter);
		phoneNum1View.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				phoneNum1 = (String) phoneNum1View.getItemAtPosition(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}

		});
		
		banAdapter = new BanListAdapter(BanListActivity.this, new ArrayList<String>());
		banListView.setAdapter(banAdapter);

		Button btn = (Button) findViewById(R.id.btnAddBanPN);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				fullBanPhoneNum = phoneNum1 + phoneNum2View.getText().toString() + phoneNum3View.getText().toString();
				banAdapter.add(fullBanPhoneNum);
				banListView.setAdapter(banAdapter);
				NetworkModel.getInstance().postRegBlockPhoneNum(fullBanPhoneNum, BanListActivity.this, new OnNetworkResultListener<ResultData>() {

					@Override
					public void onResult(ResultData result) {
						System.out.println(result.result);
						if(result.result.equals("success")){
							Toast.makeText(BanListActivity.this, "success banPhoneNum in db", Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
		});

		banAdapter.setOnAdapterButtonClickListener(new OnAdapterButtonClickListener() {

			@Override
			public void onAdapterItemClick(View v) {
				Toast.makeText(BanListActivity.this, "check click btn", Toast.LENGTH_SHORT).show();

			}
		});
	}

	ArrayList<String> getBanList = new ArrayList<String>();
	private void setBanList() {
		NetworkModel.getInstance().postinqBlockPhoneNum(BanListActivity.this, new OnNetworkResultListener<ResultData>() {

			@Override
			public void onResult(ResultData result) {
				System.out.println(result.result+" banList");
				if(result.result.equals("success")){
					banNumList = new BanNumList[result.inqBlockPhoneNum.length];
					for(int i = 0; i<banNumList.length; i++){
						banNumList[i] = result.inqBlockPhoneNum[i];
						banPhoneNumList.add(banNumList[i].blocknum);
						System.out.println(banPhoneNumList.get(i));
						banAdapter.add(banPhoneNumList.get(i));
					}
				}
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
