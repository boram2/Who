package com.who.onecupafterwork.show;

import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.who.onecupafterwork.data.Area;
import com.who.onecupafterwork.data.Hobby;
import com.who.onecupafterwork.data.Profile;
import com.who.onecupafterwork.data.ProfilesData;
import com.who.onecupafterwork.data.Study;
import com.who.onecupafterwork.group.Fragment2_WaitSame;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.signin.R;

public class ShowAnotherPersonProfileActivity extends ActionBarActivity {

	ImageView profileImageView;
	TextView bloodTypeView, religionView, introView, interestView, hobbyView, facebookView, areaView, likeScoreView;
	ImageLoader imageLoader;
	Profile profilesData;

	public static final String PROFILE_PARAM = "profileParam";
	public static final int RESET_PROFILE_INTENT = 0;

	ActionBar ab;

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.profile_exit, menu);
		MenuItem item = menu.findItem(R.id.menuExit);
		// item.setIcon(R.drawable.navitop_close);
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				finish();
				return false;
			}
		});
		return true;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_anotherperson_profile_activity);

		ab = getSupportActionBar();
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowHomeEnabled(false);
		ab.setDisplayHomeAsUpEnabled(true);

		facebookView = (TextView) findViewById(R.id.anoProfileFacebookView);
		profileImageView = (ImageView) findViewById(R.id.AnoprofileImageView);
		bloodTypeView = (TextView) findViewById(R.id.AnoprofilebloodType);
		religionView = (TextView) findViewById(R.id.AnoprofileReligion);
		introView = (TextView) findViewById(R.id.anoProfileIntro);
		hobbyView = (TextView) findViewById(R.id.AnoprofileHobby);
		interestView = (TextView) findViewById(R.id.AnoprofileStudy);
		likeScoreView = (TextView) findViewById(R.id.anoProfileLikeView);
		areaView = (TextView) findViewById(R.id.profileArea);

		imageLoader = ImageLoader.getInstance();

		Intent profileIntent = getIntent();
		NetworkModel.getInstance().postProfiles(profileIntent.getStringExtra(Fragment2_WaitSame.ANOPROFILE_ID),
				ShowAnotherPersonProfileActivity.this, new OnNetworkResultListener<ProfilesData>() {

					@Override
					public void onResult(ProfilesData result) {
						System.out.println(result.result);
						if (result.result.equals("successCHOSEID")) {
							System.out.println("check!");
							profilesData = result.message;

							Log.i("profile check", profilesData.uphoto);
							Map<String, String> map = Hobby.getHobbyMap();
							String hName = map.get(profilesData.uphobby);
							map = Area.getAreaMap();
							String aName = map.get(profilesData.uparea);
							map = Study.getStudyMap();
							String sName = map.get(profilesData.upinterest);

							facebookView.setText(profilesData.upfacebook);
							bloodTypeView.setText(profilesData.upblood);
							religionView.setText(profilesData.upreligion);
							hobbyView.setText(hName);
							interestView.setText(sName);
							introView.setText(profilesData.upintro);
							areaView.setText(aName);
							likeScoreView.setText(profilesData.uscore);

							imageLoader.displayImage(result.uphoto, profileImageView);

						} else {
//							Toast.makeText(ShowAnotherPersonProfileActivity.this, "result :" + result.result, Toast.LENGTH_SHORT).show();
							Toast.makeText(ShowAnotherPersonProfileActivity.this, "네트워크 에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
						}
					}
				});
	}

}