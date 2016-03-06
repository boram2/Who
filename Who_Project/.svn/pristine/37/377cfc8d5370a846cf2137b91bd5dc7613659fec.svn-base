package com.who.onecupafterwork.signin;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.who.onecupafterwork.adapter.MyCompanyAdapter;
import com.who.onecupafterwork.data.Company;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;

public class SelectCompanyActivity extends ActionBarActivity {

	MyCompanyAdapter mAdapter;
	ArrayAdapter<Company> nAdapter;

	ListView companyList;
	EditText companyNameView;
	ArrayList<Company> mCompanyData = new ArrayList<Company>();
	Intent companyIntent;

	Company selectCompany;
	Company[] list;

	boolean isChecked = false;

	public static SelectCompanyActivity selectCompanyActivity;

	public static final String PARAM_COMPANY_DOMIAN = "companyDomain";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_company_activity);
		ActionBar ab = getSupportActionBar();
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowHomeEnabled(false);

		selectCompanyActivity = SelectCompanyActivity.this;

		companyList = (ListView) findViewById(R.id.companyList);
		companyNameView = (EditText) findViewById(R.id.companyNameView);
		companyIntent = new Intent(SelectCompanyActivity.this, InputMailActivity.class);

		SetmCompanyData();

		mAdapter = new MyCompanyAdapter(SelectCompanyActivity.this, mCompanyData);
		nAdapter = new ArrayAdapter<Company>(this, android.R.layout.simple_list_item_single_choice, mCompanyData);
		companyList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// companyList.setAdapter(mAdapter);
		companyList.setAdapter(nAdapter);
		companyList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectCompany = (Company) companyList.getItemAtPosition(position);
				companyNameView.setText(selectCompany.companyName);
				companyIntent.putExtra(PARAM_COMPANY_DOMIAN, selectCompany.companyDomain);

				isChecked = true;
			}
		});

		Button btn = (Button) findViewById(R.id.btnCompany);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String companyName = companyNameView.getText().toString();
				if (companyName != null && !companyName.equals("")) {
					if (isChecked) {
						NetworkModel.getInstance().postCheckMail(selectCompany, SelectCompanyActivity.this,
								new OnNetworkResultListener<ResultData>() {

									@Override
									public void onResult(ResultData result) {
										if (result.result.equals("success")) {
											startActivity(companyIntent);
											finish();
										}
									}

								});
					} else {
						Toast.makeText(SelectCompanyActivity.this, "적어도 하나의 회사 선택 필요", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(SelectCompanyActivity.this, "please give a company name", Toast.LENGTH_SHORT).show();
				}
			}
		});

		btn = (Button) findViewById(R.id.btnAddNewCompany);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(SelectCompanyActivity.this, AddCompanyEmailActivity.class);
				startActivity(i);
			}
		});
	}

	private void SetmCompanyData() {
		list = new Company[] { new Company("A001001", "엘지전자", "@lgelec.co.kr"), new Company("A001002", "티아카데미", "@tmail.com"),
				new Company("A001003", "생활관마트", "@sangmail.com"), new Company("A001004", "삼성전자", "@sammail.com") };

		// Company c1 = new Company("A001001", "엘지전자", "@lgelec.co.kr");
		// Company c2 = new Company("A001002", "티아카데미", "@tmail.com");
		// Company c3 = new Company("A001003", "생활관마트", "@sangmail.com");
		// Company c4 = new Company("A001004", "삼성전자", "@sammail.com");

		// mCompanyData.add(c1);
		// mCompanyData.add(c2);
		// mCompanyData.add(c3);
		// mCompanyData.add(c4);

		for (int i = 0; i < list.length; i++) {
			mCompanyData.add(list[i]);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// if (keyCode == KeyEvent.KEYCODE_BACK) {
		// Toast.makeText(SelectCompanyActivity.this, "로그인 페이지로 돌아갑니다.", Toast.LENGTH_SHORT).show();
		// Intent i = new Intent(SelectCompanyActivity.this,LoginActivity.class);
		// startActivity(i);
		// finish();
		// return true;
		// }
		return super.onKeyDown(keyCode, event);
	}
}
