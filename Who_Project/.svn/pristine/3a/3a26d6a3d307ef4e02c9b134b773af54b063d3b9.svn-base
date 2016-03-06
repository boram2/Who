package com.who.onecupafterwork.group;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.who.onecupafterwork.signin.R;

public class FrameFragment extends Fragment {
	/* 전역 변수 */
	public static final String WAIT_START_SCREEN = "0200";
	public static final String ADD_SAME = "0300";
	public static final String WAIT_SAME = "0301";
	public static final String ADD_REGION = "0400";
	public static final String WAIT_RECOMMODATION_TIME = "0401";
	public static final String SELECT_WHO = "0500";
	public static final String WAIT_WHO = "0501";
	public static final String SUCCESS = "0600";
	public static final String CANCEL_SAME = "";
	public static final String FAIL = "0601";

	boolean isFirst = true;
	String fragmentType;

	Fragment[] fragment = { new Fragment0_WaitStartScreen(), new Fragment1_AddSame(), new Fragment2_WaitSame(), new Fragment3_AddRegion(),
			new Fragment4_WaitRecommendationTime(), new Fragment5_SelectWho(), new Fragment6_WaitWho(), new Fragment7_Success(),
			new Fragment8_CancelSame(), new Fragment9_Fail() };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.frame_fragment, container, false);
		Log.i("dasol fragmentType check", fragmentType);
		if (isFirst) {
			FragmentManager fm = getChildFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			switch(fragmentType){
			case "0200" :  
				ft.replace(R.id.fragment1_frame, new Fragment0_WaitStartScreen());
				ft.commit();
				break;
			case "0300" :  
				ft.replace(R.id.fragment1_frame, new Fragment1_AddSame());
				ft.commit();
				break;
			case "0301" :  
				ft.replace(R.id.fragment1_frame, new Fragment2_WaitSame());
				ft.commit();
				break;
			case "0400" :  
				ft.replace(R.id.fragment1_frame, new Fragment3_AddRegion());
				ft.commit();
				break;
			case "0401" :  
				ft.replace(R.id.fragment1_frame, new Fragment4_WaitRecommendationTime());
				ft.commit();
				break;
			case "0500" :  
				ft.replace(R.id.fragment1_frame, new Fragment5_SelectWho());
				ft.commit();
				break;
			case "0501" :  
				ft.replace(R.id.fragment1_frame, new Fragment6_WaitWho());
				ft.commit();
				break;
			case "0600" :  
				ft.replace(R.id.fragment1_frame, new Fragment7_Success());
				ft.commit();
				break;
			case "0601" :  
				ft.replace(R.id.fragment1_frame, new Fragment9_Fail());
				ft.commit();
				break;
			case "0700" :
				ft.replace(R.id.fragment1_frame, new Fragment8_CancelSame());
				ft.commit();
				break;
			default :
				ft.replace(R.id.fragment1_frame, new Fragment0_WaitStartScreen());
				ft.commit();
				break;
			}
			isFirst = false;
		}
		return v;
	}

	public void switchFragment(String fragmentType) {
		this.fragmentType = fragmentType;
	}
}
