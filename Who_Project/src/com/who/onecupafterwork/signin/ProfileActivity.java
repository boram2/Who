package com.who.onecupafterwork.signin;

import java.util.ArrayList;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.who.onecupafterwork.adapter.MySpinnerAdapter;
import com.who.onecupafterwork.common.MainTabActivity;
import com.who.onecupafterwork.data.Area;
import com.who.onecupafterwork.data.Hobby;
import com.who.onecupafterwork.data.Profile;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.data.Study;
import com.who.onecupafterwork.group.FrameFragment;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.show.ShowMyProfileActivity;

public class ProfileActivity extends ActionBarActivity {

	EditText facebookView, temtationView;
	Spinner studySpinner, hobbySpinner, areaSpinner;
	RadioGroup bloodGroup, religionGroup;
	int studyPosition, hobbyPostion, locationPosition;
	MySpinnerAdapter studyAdapter, hobbyAdapter, areaAdapter;
	ArrayList studyList, hobbyList, areaList;
	String upblood, upreligion, upinterest, uphobby, upfacebook, upintro, uparea;

	Profile originalProfile;
	int religionNum = -1, bloodNum = -1;
	int religionId;

	ActionBar ab;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_activity);

		ab = getSupportActionBar();
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowHomeEnabled(false);

		facebookView = (EditText) findViewById(R.id.facebookView);
		temtationView = (EditText) findViewById(R.id.temtationView);
		studySpinner = (Spinner) findViewById(R.id.studySpinner);
		hobbySpinner = (Spinner) findViewById(R.id.hobbySpinner);
		bloodGroup = (RadioGroup) findViewById(R.id.bloodGroup);
		religionGroup = (RadioGroup) findViewById(R.id.religionGroup);
		areaSpinner = (Spinner) findViewById(R.id.areaSpinner);

		studyList = Study.getStudyList();
		hobbyList = Hobby.getHobbyList();
		areaList = Area.getAreaList();

		/*
		 * ArrayList<String> studyNameList = new ArrayList<String>(); for(int i = 0; i<studyList.size(); i++){
		 * studyNameList.add(studyList.get(i).toString()); }
		 * 
		 * ArrayList<String> hobbyNameList = new ArrayList<String>(); for(int i = 0; i<hobbyList.size(); i++){
		 * hobbyNameList.add(hobbyList.get(i).toString()); }
		 * 
		 * ArrayList<String> areaNameList = new ArrayList<String>(); for(int i = 0; i<areaList.size(); i++){
		 * areaNameList.add(areaList.get(i).toString()); }
		 */

		studyAdapter = new MySpinnerAdapter(ProfileActivity.this, studyList);
		hobbyAdapter = new MySpinnerAdapter(ProfileActivity.this, hobbyList);
		areaAdapter = new MySpinnerAdapter(ProfileActivity.this, areaList);

		studySpinner.setAdapter(studyAdapter);
		hobbySpinner.setAdapter(hobbyAdapter);
		areaSpinner.setAdapter(areaAdapter);

		studySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				studyPosition = position;
				upinterest = ((Study) studyList.get(position)).getNumber();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		hobbySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				hobbyPostion = position;
				uphobby = ((Hobby) hobbyList.get(position)).getNumber();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		areaSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				locationPosition = position;
				uparea = ((Area) areaList.get(position)).getNumber();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		Intent getIntent = getIntent();
		originalProfile = (Profile) getIntent.getSerializableExtra(ShowMyProfileActivity.PROFILE_PARAM);
		if (originalProfile != null) {
			System.out.println("success get original profile");
			facebookView.setText(originalProfile.upfacebook);
			temtationView.setText(originalProfile.upintro);
			if (originalProfile.upreligion.equals("무교")) {
				religionNum = 0;
				RadioButton religionButton = (RadioButton) findViewById(R.id.religionNone);
				religionButton.setChecked(true);
			} else if (originalProfile.upreligion.equals("기독교")) {
				religionNum = 1;
				RadioButton religionButton = (RadioButton) findViewById(R.id.religionProtestant);
				religionButton.setChecked(true);
			} else if (originalProfile.upreligion.equals("불교")) {
				religionNum = 2;
				RadioButton religionButton = (RadioButton) findViewById(R.id.religionBuddism);
				religionButton.setChecked(true);
			} else if (originalProfile.upreligion.equals("천주교")) {
				religionNum = 3;
				RadioButton religionButton = (RadioButton) findViewById(R.id.religionCatholic);
				religionButton.setChecked(true);
				// } else if (originalProfile.upreligion.equals("기타")) {
				// religionNum = 4;
				// RadioButton religionButton = (RadioButton) findViewById(R.id.religionEtc);
				// religionButton.setChecked(true);
			}
			// blood
			if (originalProfile.upblood.equals("A")) {
				religionNum = 0;
				RadioButton bloodButton = (RadioButton) findViewById(R.id.radioA);
				bloodButton.setChecked(true);
			} else if (originalProfile.upblood.equals("B")) {
				religionNum = 1;
				RadioButton bloodButton = (RadioButton) findViewById(R.id.radioB);
				bloodButton.setChecked(true);
			} else if (originalProfile.upblood.equals("O")) {
				religionNum = 2;
				RadioButton bloodButton = (RadioButton) findViewById(R.id.radioO);
				bloodButton.setChecked(true);
			} else if (originalProfile.upblood.equals("AB")) {
				religionNum = 3;
				RadioButton bloodButton = (RadioButton) findViewById(R.id.radioAB);
				bloodButton.setChecked(true);
			}

			Map areaMap = Area.getAreaMap();
			String originArea = areaMap.get(originalProfile.uparea).toString();
			int k = -1;
			for (int i = 0; i < areaList.size(); i++) {
				if ((areaList.get(i).toString()).equals(originArea)) {
					k = i;
					System.out.println("check area2" + k);
					areaSpinner.setSelection(k);
				}
			}

			Map studyMap = Study.getStudyMap();
			String OriginStudy = studyMap.get(originalProfile.upinterest).toString();
			for (int i = 0; i < studyList.size(); i++) {
				if ((studyList.get(i).toString()).equals(OriginStudy)) {
					k = i;
					studySpinner.setSelection(k);
				}
			}

			Map hobbyMap = Hobby.getHobbyMap();
			String origingHobby = hobbyMap.get(originalProfile.uphobby).toString();
			for (int i = 0; i < hobbyList.size(); i++) {
				if ((hobbyList.get(i).toString()).equals(origingHobby)) {
					k = i;
					hobbySpinner.setSelection(k);
				}
			}
		}

		Button btn = (Button) findViewById(R.id.btnProfileRegister);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				upfacebook = facebookView.getText().toString();
				upintro = temtationView.getText().toString();
				System.out.println("upIntro : " + upintro);
				if (upintro.equals("") || upintro == null) {
					Toast.makeText(ProfileActivity.this, "매력을 입력해주세요", Toast.LENGTH_SHORT).show();
				} else {
					religionId = religionGroup.getCheckedRadioButtonId();
					switch (religionId) {
					case R.id.religionNone:
						upreligion = "무교";
						break;
					case R.id.religionProtestant:
						upreligion = "기독교";
						break;
					case R.id.religionBuddism:
						upreligion = "불교";
						break;
					case R.id.religionCatholic:
						upreligion = "천주교";
						break;
					// case R.id.religionEtc:
					// upreligion = "기타";
					// break;
					}

					int bloodTypeId = bloodGroup.getCheckedRadioButtonId();
					upblood = null;
					switch (bloodTypeId) {
					case R.id.radioA:
						upblood = "A";
						break;
					case R.id.radioB:
						upblood = "B";
						break;
					case R.id.radioO:
						upblood = "O";
						break;
					case R.id.radioAB:
						upblood = "AB";
					}

					// public Profile(String upblood,String upreligion,String upinterest,String uphobby,String upfacebook, String upintro,String
					// uparea)
					Log.i("check profile", "" + upblood + upreligion + upinterest + uphobby + upfacebook + upintro + uparea);
					Profile profile = new Profile(upblood, upreligion, upinterest, uphobby, upfacebook, upintro, uparea);
					if (originalProfile == null) {
						NetworkModel.getInstance().postProfile(profile, ProfileActivity.this, new OnNetworkResultListener<ResultData>() {

							@Override
							public void onResult(ResultData result) {
								Log.i("network check", "Profile Add" + result.result);
								if (result.result.equals("success")) {
									Toast.makeText(ProfileActivity.this, "register profile", Toast.LENGTH_SHORT).show();
									Intent i = new Intent(ProfileActivity.this, MainTabActivity.class);
									i.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.ADD_SAME);
									i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
									startActivity(i);
									finish();
								} else if (result.result.equals("error")) {
									Toast.makeText(ProfileActivity.this, "server connect error profile", Toast.LENGTH_SHORT).show();
								}
							}
						});

					} else {
						NetworkModel.getInstance().postModifyProfiles(profile, ProfileActivity.this, new OnNetworkResultListener<ResultData>() {

							@Override
							public void onResult(ResultData result) {
								if (result.result.equals("success")) {
									Log.i("network check", "Profile Modify" + result.result);
									Intent i = new Intent(ProfileActivity.this, ShowMyProfileActivity.class);
									i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
									onResume();
									startActivity(i);
									finish();
								}
							}
						});
					}
				}
			}
		});
	}

}
