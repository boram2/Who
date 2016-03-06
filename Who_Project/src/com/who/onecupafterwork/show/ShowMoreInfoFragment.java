package com.who.onecupafterwork.show;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.who.onecupafterwork.signin.R;

public class ShowMoreInfoFragment extends Fragment {

	ArrayList<String> data = new ArrayList<String>();
	ArrayAdapter<String> mAdapter;
	ListView listViewShowMoreInfo;
	boolean ifFirst = true;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		setDataList();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.show_more_info_fragment, container, false);

		listViewShowMoreInfo = (ListView) v.findViewById(R.id.listviewSM);
		mAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data);
		listViewShowMoreInfo.setAdapter(mAdapter);
		
		listViewShowMoreInfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i;
				FragmentManager fm = getActivity().getSupportFragmentManager();
				switch (position) {
				case 0:
					i = new Intent(getActivity(), NoticeActivity.class);
					startActivity(i);
					break;
				case 1:
					i = new Intent(getActivity(), SettingActivity.class);
					startActivity(i);
					break;
				case 2:
					i = new Intent(getActivity(), BanListActivity.class);
					startActivity(i);
					break;
				case 3:
					i = new Intent(getActivity(), StipulationActivity.class);
					startActivity(i);
					break;
				case 4:
					CreatorCustomDialog dialog = new CreatorCustomDialog();
					dialog.show(fm, "dialog");
				}
			}

		});
		return v;
	}
	
	public void setDataList(){
		data.add("  공지사항");
		data.add("  환경설정");
		data.add("  매칭차단 전화번호 등록");
		data.add("  약관");
		data.add("  만든이");
	}
	
}
