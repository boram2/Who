package com.who.onecupafterwork.group;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.who.onecupafterwork.signin.R;

public class Fragment4_WaitRecommendationTime extends Fragment {
	/* 팀 짠거 결과 보여주기, 추천 시간을 기다림 */
	TextView hour, min, sec;
	long IAvailableDate;
	long ILeaveDay;
	Integer mDay, mHour, mMin, mSec;
	// TextView timer_text;
	String timer_sec;

	private TimerTask second;
	private final Handler handler = new Handler();
	
	public static String TIME;

	int dYear;
	int dMonth; // 1월은 0부터
	int dDay;
	int dHour;
	int dMin;
	int dSec;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment4_wait_recommendation_time, container, false);

		Calendar cal = java.util.Calendar.getInstance();
		dYear = cal.get(cal.YEAR);
		dMonth = cal.get(cal.MONTH);
		dDay = cal.get(cal.DATE);

		dHour = cal.get(cal.HOUR_OF_DAY);
		dMin = cal.get(cal.MINUTE);
		dSec = cal.get(cal.SECOND);

		hour = (TextView) v.findViewById(R.id.f4_time_hour);
		min = (TextView) v.findViewById(R.id.f4_time_min);
		sec = (TextView) v.findViewById(R.id.f4_time_sec);

		if (dHour >= 10 && dHour < 12) {
			setTimer(dYear, dMonth, dDay, 12, 00, 00);
			TIME = "T1";
		} else if (dHour >= 12 && dHour < 14) {
			setTimer(dYear, dMonth, dDay, 14, 00, 00);
			TIME = "T2";
		} else if (dHour >= 14 && dHour < 16) {
			setTimer(dYear, dMonth, dDay, 16, 00, 00);
			TIME = "T3";
		} else {
			hour.setText("fi");
			min.setText("ni");
			sec.setText("sh");
			TIME = "T1";
		}
		testStart();

//		Button btn = (Button) v.findViewById(R.id.btnFix1);
//		btn.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				FragmentManager fm = getParentFragment().getChildFragmentManager();
//				FragmentTransaction ft = fm.beginTransaction();
//				ft.replace(R.id.fragment1_frame, new Fragment5_SelectWho());
//				ft.commit();
//			}
//		});
		return v;
	}

	public void setTimer(int Year, int Month, int Day, int Hour, int Min, int Sec) {
		this.dYear = Year;
		this.dMonth = Month;
		this.dDay = Day;
		this.dHour = Hour;
		this.dMin = Min;
		this.dSec = Sec;
	}

	public void testStart() {
		Calendar gameDate = null;
		gameDate = Calendar.getInstance();
		gameDate.set(dYear, dMonth, dDay, dHour, dMin, dSec);
		IAvailableDate = gameDate.getTimeInMillis();

		second = new TimerTask() {

			@Override
			public void run() {

				Calendar gameDate2 = null;
				gameDate2 = Calendar.getInstance();
				ILeaveDay = IAvailableDate - gameDate2.getTimeInMillis();

				if (ILeaveDay > 0) {
					gameDate2.setTimeInMillis(ILeaveDay);
					mDay = gameDate2.get(Calendar.DATE);
					if (mDay > 1) {
						// 며칠인지 표현 안하고 시간으로만 표시하기 위해 처리
						mHour = ((mDay - 1) * 24) + (gameDate2.get(Calendar.HOUR_OF_DAY) - 9);
					} else {
						mHour = gameDate2.get(Calendar.HOUR_OF_DAY) - 9;
					}
					mMin = gameDate2.get(Calendar.MINUTE);
					mSec = gameDate2.get(Calendar.SECOND);
				}

				if (mHour == null && mMin == null && mSec == null || mHour == 0 && mMin == 0 && mSec == 0) {
					timer_sec = "종료";
				} else {
					// hour.setText(mHour);
					// min.setText(mMin);
					// sec.setText(mSec);
					// timer_sec = "남은시간 "+mHour+" : "+mMin+" : "+mSec;
					Update();
				}

			}
		};
		Timer timer = new Timer();
		timer.schedule(second, 0, 1000);
	}

	protected void Update() {
		Runnable updater = new Runnable() {
			public void run() {
				if (mHour < 10) {
					hour.setText("0" + mHour.toString());
				} else {
					hour.setText(mHour.toString());
				}
				if (mMin < 10) {
					min.setText("0" + mMin.toString());
				} else {
					min.setText(mMin.toString());
				}
				if (mSec < 10) {
					sec.setText("0" + mSec.toString());
				} else {
					sec.setText(mSec.toString());
				}
			}
		};
		handler.post(updater);
	}

}
