package com.who.onecupafterwork.group;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.who.onecupafterwork.data.Contact;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.show.ShowAnotherPersonProfileActivity;
import com.who.onecupafterwork.signin.R;

public class Fragment5_SelectWho extends Fragment {
	/* 3쌍의 이성 보기 */
	ImageView imageViewWho1, imageViewWho2, imageViewWho3, imageViewWho4, imageViewWho5, imageViewWho6;

	CheckBox check1;
	CheckBox check2;

	CheckBox check3;
	public static boolean checked1 = false;
	public static boolean checked2 = false;
	public static boolean checked3 = false;

	int count;

	Contact[] whoList = new Contact[3];

	ImageLoader imageLoader = ImageLoader.getInstance();

	public static final String SELECT_KEY = "selectKey";
	public static final String SHOW_PROFILE_KEY = "showProfileKey";

	int dHour;
	String time;

	// can select by 3 teams
	private boolean isChecked() {
		if (!checked1 && !checked2 && !checked3) {
			return false;
		} else if (checked1 && checked2 && checked3) {
			return true;
		} else {
			return true;
		}
	}

	public static int getChecked() {
		if (checked1 && checked2 && checked3) {
			return 3;
		} else if ((checked1 && checked2) || (checked2 && checked3) || (checked3 && checked1)) {
			return 2;
		} else if ((checked1 && !checked2 && !checked3) || (!checked1 && checked2 && !checked3) || (!checked1 && !checked2 && checked3)) {
			return 1;
		} else
			return 0;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment5_select_who, container, false);

		Calendar cal = java.util.Calendar.getInstance();

		dHour = cal.get(cal.HOUR_OF_DAY);

		if (dHour >= 10 && dHour < 12) {
			time = "T1";
		} else if (dHour >= 12 && dHour < 14) {
			time = "T2";
		} else if (dHour >= 14 && dHour < 16) {
			time = "T3";
		} else {
			time = "T1";
		}

		/* 이 프레그먼트가 불려지는 그 자체가 이성 추천을 보이는 것이다. */
		NetworkModel.getInstance().postMatching(time, getActivity(), new OnNetworkResultListener<ResultData>() {

			@Override
			public void onResult(ResultData result) {
				System.out.println("Matching그룹 불러오기 " + result.result);

				if (result.result.equals("success")) {
					System.out.println("추천 그룹 불러오기 성공");
					whoList[0] = result.groupID[0];
					whoList[1] = result.groupID[1];
					whoList[2] = result.groupID[2];

					Log.i("who1 id ", whoList[0].acode + " " + whoList[0].aidA + " " + whoList[0].aidB + " " + whoList[0].uphotoA + " "
							+ whoList[0].uphotoB);
					Log.i("who2 id ", whoList[1].acode + " " + whoList[1].aidA + " " + whoList[1].aidB + " " + whoList[1].uphotoA + " "
							+ whoList[1].uphotoB);
					Log.i("who3 id ", whoList[2].acode + " " + whoList[2].aidA + " " + whoList[2].aidB + " " + whoList[2].uphotoA + " "
							+ whoList[2].uphotoB);

					imageLoader.displayImage(whoList[0].uphotoA, imageViewWho1);
					imageLoader.displayImage(whoList[0].uphotoB, imageViewWho2);
					imageLoader.displayImage(whoList[1].uphotoA, imageViewWho3);
					imageLoader.displayImage(whoList[1].uphotoB, imageViewWho4);
					imageLoader.displayImage(whoList[2].uphotoA, imageViewWho5);
					imageLoader.displayImage(whoList[2].uphotoB, imageViewWho6);

					/* id 가져오기 */
					// String who1idA = whoList[0].aidA;
					// String who1idB = whoList[0].aidB;
					// String who2idA = whoList[1].aidA;
					// String who2idB = whoList[1].aidB;
					// String who3idA = whoList[2].aidA;
					// String who3idB = whoList[2].aidB;
				}
			}
		});

		check1 = (CheckBox) v.findViewById(R.id.checkBox1);
		check2 = (CheckBox) v.findViewById(R.id.checkBox2);
		check3 = (CheckBox) v.findViewById(R.id.checkBox3);

		check1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				checked1 = isChecked;
			}
		});

		check2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				checked2 = isChecked;
			}
		});

		check3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				checked3 = isChecked;
			}
		});

		Button btn = (Button) v.findViewById(R.id.btnAddWho);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isChecked()) {
					FragmentManager fm = ((FragmentActivity) getActivity()).getSupportFragmentManager();
					SelectWhoDialog dialog = new SelectWhoDialog();

					Bundle b = new Bundle();

					if (checked1 && checked2 && checked3) {
						b.putSerializable("selectKey1", whoList[0]);
						b.putSerializable("selectKey2", whoList[1]);
						b.putSerializable("selectKey3", whoList[2]);
					} else if (checked1 && checked2) {
						b.putSerializable("selectKey1", whoList[0]);
						b.putSerializable("selectKey2", whoList[1]);
					} else if (checked2 && checked3) {
						b.putSerializable("selectKey1", whoList[1]);
						b.putSerializable("selectKey2", whoList[2]);
					} else if (checked1 && checked3) {
						b.putSerializable("selectKey1", whoList[0]);
						b.putSerializable("selectKey2", whoList[2]);
					} else if (checked1 && !checked2 && !checked3) {
						b.putSerializable("selectKey1", whoList[0]);
					} else if (!checked1 && checked2 && !checked3) {
						b.putSerializable("selectKey1", whoList[1]);
					} else if (!checked1 && !checked2 && checked3) {
						b.putSerializable("selectKey1", whoList[2]);
					} else {

					}

					// selectWho 다이얼로그에 3개의 그룹 정보 전송하기
					dialog.setArguments(b);
					dialog.show(fm, "dialog");
				} else {
					Toast.makeText(getActivity(), "적어도 한 팀은 신청해주세요.", Toast.LENGTH_SHORT).show();
				}
			}
		});

		imageViewWho1 = (ImageView) v.findViewById(R.id.imageViewWho1);
		imageViewWho1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), ShowAnotherPersonProfileActivity.class);
				i.putExtra(Fragment2_WaitSame.ANOPROFILE_ID, whoList[0].aidA);
				startActivity(i);
			}
		});

		imageViewWho2 = (ImageView) v.findViewById(R.id.imageViewWho2);
		imageViewWho2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), ShowAnotherPersonProfileActivity.class);
				i.putExtra(Fragment2_WaitSame.ANOPROFILE_ID, whoList[0].aidB);
				startActivity(i);
			}
		});

		imageViewWho3 = (ImageView) v.findViewById(R.id.imageViewWho3);
		imageViewWho3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), ShowAnotherPersonProfileActivity.class);
				i.putExtra(Fragment2_WaitSame.ANOPROFILE_ID, whoList[1].aidA);
				startActivity(i);
			}
		});

		imageViewWho4 = (ImageView) v.findViewById(R.id.imageViewWho4);
		imageViewWho4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), ShowAnotherPersonProfileActivity.class);
				i.putExtra(Fragment2_WaitSame.ANOPROFILE_ID, whoList[1].aidB);
				startActivity(i);
			}
		});

		imageViewWho5 = (ImageView) v.findViewById(R.id.imageViewWho5);
		imageViewWho5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), ShowAnotherPersonProfileActivity.class);
				i.putExtra(Fragment2_WaitSame.ANOPROFILE_ID, whoList[2].aidA);
				startActivity(i);
			}
		});

		imageViewWho6 = (ImageView) v.findViewById(R.id.imageViewWho6);
		imageViewWho6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), ShowAnotherPersonProfileActivity.class);
				i.putExtra(Fragment2_WaitSame.ANOPROFILE_ID, whoList[2].aidB);
				startActivity(i);
			}
		});

		return v;
	}
}
