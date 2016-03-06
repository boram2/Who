package com.who.onecupafterwork.group;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.who.onecupafterwork.signin.R;

public class Fragment9_Fail extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment9_fail, container, false);
		Button btn = (Button) v.findViewById(R.id.btnFix1);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fm = getParentFragment().getChildFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				ft.replace(R.id.fragment1_frame, new Fragment1_AddSame());
				ft.commit();
			}
		});
		return v;
	}
}
