package com.who.onecupafterwork.alarm;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.who.onecupafterwork.adapter.AlarmAdapter;
import com.who.onecupafterwork.database.AlarmData;
import com.who.onecupafterwork.database.DBManager;
import com.who.onecupafterwork.preferences.PropertyManager;
import com.who.onecupafterwork.signin.R;

public class AlarmFragment extends Fragment {

	// boolean isFirst = true;

	ListView listViewAlarm;

	ArrayList<AlarmData> data = new ArrayList<AlarmData>();
	ArrayList<AlarmData> alarmList = new ArrayList<AlarmData>();
	AlarmAdapter mAdapter;

	// ArrayAdapter<String> mAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line, alarmList);

	public static final int ALARM_FLAG = 200;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.alarm_fragment, container, false);
		listViewAlarm = (ListView) v.findViewById(R.id.listViewAlarm);
		mAdapter = new AlarmAdapter(getActivity(), alarmList);
		listViewAlarm.setAdapter(mAdapter);

		data.clear();
		data.addAll(DBManager.getInstance().getAlarmList());

		for (int i = 0; i < data.size(); i++) {
			if ((data.get(i).userId).equals(PropertyManager.getInstance().getUserId())) {
				mAdapter.add(data.get(i));
				Log.i("in alarm fragment", data.get(i).message);
			}
		}

		// mAdapter.setOnAdapterButtonClickListener(new OnAdapterButtonClickListener() {
		//
		// @Override
		// public void onAdapterItemClick(View v) {
		//
		// }
		// });

		/* 알람 데이터 type에 따라 다른 이벤트 처리 */
		// listViewAlarm.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
		//
		// if (position == 0 || position == 1 || position == 2 || position == 3 || position == 4) {
		//
		// Intent fragmentIntent = new Intent(getActivity(), MainTabActivity.class);
		//
		// switch (position) {
		// case 0: // 이성 선택
		// fragmentIntent.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.SELECT_WHO);
		// break;
		// case 1: // 매칭 성공
		// fragmentIntent.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.SUCCESS);
		// break;
		// case 2: // 친추 거절당함
		// fragmentIntent.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.ADD_SAME);
		// break;
		// case 3: // 주선자의 이성매칭 신청
		// fragmentIntent.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.WAIT_WHO);
		// break;
		// case 4: // 매칭 실패
		// fragmentIntent.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.FAIL);
		// break;
		// }
		//
		// fragmentIntent.addFlags(ALARM_FLAG);
		// startActivity(fragmentIntent);
		// getActivity().finish();
		// } else if (position == 5 || position == 6) {
		//
		// switch (position) {
		// case 5: // 지인 초대
		// Intent intent2 = new Intent(getActivity(), InviteActivity.class);
		// startActivity(intent2);
		// break;
		// case 6: // 이벤트
		// Intent intent3 = new Intent(getActivity(), NoticeActivity.class);
		// startActivity(intent3);
		// break;
		// }
		//
		// }
		// }
		// });

		return v;
	}
}
