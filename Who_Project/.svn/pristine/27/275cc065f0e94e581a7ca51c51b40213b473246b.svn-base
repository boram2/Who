package com.who.onecupafterwork.group;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.who.onecupafterwork.adapter.MySpinnerAdapter;
import com.who.onecupafterwork.data.Contact;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.preferences.PropertyManager;
import com.who.onecupafterwork.signin.R;

/* 이 페이지는 주선자만 가능하다. */

public class Fragment3_AddRegion extends Fragment {
	
	MySpinnerAdapter mAdapter, nAdapter;
	Spinner spinnerSi;
	Spinner spinnerGu;

	String checkedSi, checkedGu;
	String regionCodeSi, regionCodeGu;
	String uphoto, aphotoB;
	String[] code;
	
	boolean isFirst = true;

	ImageView imageViewMe, imageViewSame;
	ImageLoader imageLoader = ImageLoader.getInstance();

	private int[] regionList = { R.array.SeoItems, R.array.SooItems, R.array.InItems, R.array.DaeItems, R.array.KwangItems, R.array.DaeguItems,
			R.array.UlItems, R.array.BuItems };

	private int[] codeList = { R.array.SeoCode, R.array.SooCode, R.array.InCode, R.array.DaeCode, R.array.KwangCode, R.array.DaeguCode,
			R.array.UlCode, R.array.BuCode };

	private void giveSiCode() {
		if (checkedSi.equals("서울")) {
			regionCodeSi = "B001";
		} else if (checkedSi.equals("수원")) {
			regionCodeSi = "B002";
		} else if (checkedSi.equals("인천")) {
			regionCodeSi = "B003";
		} else if (checkedSi.equals("대전")) {
			regionCodeSi = "B004";
		} else if (checkedSi.equals("광주")) {
			regionCodeSi = "B005";
		} else if (checkedSi.equals("대구")) {
			regionCodeSi = "B006";
		} else if (checkedSi.equals("울산")) {
			regionCodeSi = "B007";
		} else if (checkedSi.equals("부산")) {
			regionCodeSi = "B008";
		} else {
			regionCodeSi = "Error";
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment3_add_region, container, false);

		String[] dataSiDo = getResources().getStringArray(R.array.SiItems);

		imageViewMe = (ImageView) v.findViewById(R.id.imageViewMe);
		imageViewSame = (ImageView) v.findViewById(R.id.imageViewSame);

		/* 자신 정보 가지고 오기 */
		NetworkModel.getInstance().postApply(getActivity(), new OnNetworkResultListener<ResultData>() {
			@Override
			public void onResult(ResultData result) {
				if (result.result.equals("success")) {
					uphoto = result.uphoto;
					imageLoader.displayImage(uphoto, imageViewMe);
				}
			}
		});

		imageLoader.displayImage(PropertyManager.getInstance().getProfilePath(), imageViewMe);
		
		/* 친구 정보 가지고 오기 */
		NetworkModel.getInstance().postApplyChofriend(PropertyManager.getInstance().getSameId(), getActivity(),
				new OnNetworkResultListener<ResultData>() {

					@Override
					public void onResult(ResultData result) {
						if (result.result.equals("success")) {
							PropertyManager.getInstance().setSameId(result.aidB);

							aphotoB = result.aphotoB;
							imageLoader.displayImage(aphotoB, imageViewSame);
							isFirst = false; // 성공 시에 boolean을 막아준다.
						}
					}
				});

		spinnerSi = (Spinner) v.findViewById(R.id.spinnerSi);
		spinnerGu = (Spinner) v.findViewById(R.id.spinnerGu);
		spinnerGu.setClickable(false);
		mAdapter = new MySpinnerAdapter(getActivity(), dataSiDo);

		spinnerSi.setAdapter(mAdapter);
		spinnerSi.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

				checkedSi = parent.getItemAtPosition(position).toString();

				giveSiCode(); // 시 코드를 주는 함수

				Toast.makeText(getActivity(), regionCodeSi, Toast.LENGTH_SHORT).show();

				int resId = regionList[position];

				// if (resId != MainTabActivity.NEGATIVE_CODE) {
				String[] array = getActivity().getResources().getStringArray(resId);
				code = getActivity().getResources().getStringArray(codeList[position]);

				spinnerGu.setClickable(true);
				nAdapter = new MySpinnerAdapter(getActivity(), array);
				spinnerGu.setAdapter(nAdapter);
				spinnerGu.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						// if (position != 0) { // 0번째 아이템은 구, 시를 선택하라는 메세지
						checkedGu = parent.getItemAtPosition(position).toString();
						regionCodeGu = code[position];
						Toast.makeText(getActivity(), regionCodeGu, Toast.LENGTH_SHORT).show();
						// }
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						Toast.makeText(getActivity(), "구를 선택하세요.", Toast.LENGTH_SHORT).show();
					}
				});
				// }
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Button btn = (Button) v.findViewById(R.id.btnAddRegion);

		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (checkedSi != null && !checkedSi.equals("") && checkedGu != null && !checkedGu.equals("")) {
					FragmentManager fm = getParentFragment().getChildFragmentManager();
					FragmentTransaction ft = fm.beginTransaction();
					ft.replace(R.id.fragment1_frame, new Fragment4_WaitRecommendationTime());
					ft.commit();

					/* 주선자만 가능함 -> 이 화면 들어오는 자체가 주선자이다. */
					Contact c = new Contact();
					c.aidA = PropertyManager.getInstance().getUserId();
					c.aidB = PropertyManager.getInstance().getSameId();

					NetworkModel.getInstance().postChoLocation(c, regionCodeSi, regionCodeGu, getActivity(),
							new OnNetworkResultListener<ResultData>() {

								@Override
								public void onResult(ResultData result) {
									if (result.result.equals("success")) {
										// ///////////////////////////////////////////
										PropertyManager.getInstance().setSameCode(result.acode); // 그룹 번호를 가져옴
										Log.i("Register group number", "No. " + result.acode);
									}
								}
							});

					Toast.makeText(getActivity(), checkedSi + " " + checkedGu, Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getActivity(), "시, 도를 올바르게 선택하세요.", Toast.LENGTH_SHORT).show();
				}
			}
		});

		return v;
	}
}
