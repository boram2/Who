package com.who.onecupafterwork.group;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.preferences.PropertyManager;
import com.who.onecupafterwork.show.InviteCancelDialog;
import com.who.onecupafterwork.show.QustomDialogBuilder;
import com.who.onecupafterwork.show.ShowAnotherPersonProfileActivity;
import com.who.onecupafterwork.signin.R;

public class Fragment2_WaitSame extends Fragment {
	
	ImageView imageViewMe, imageViewSame;
	ImageLoader imageLoader = ImageLoader.getInstance();
	String uphoto, aphotoB;
	public static final String ANOPROFILE_ID = "anoFriendId";

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment2_wait_same, container, false);

		imageViewMe = (ImageView) v.findViewById(R.id.imageViewMe);
		imageViewSame = (ImageView) v.findViewById(R.id.imageViewSame);

		/* 자신 정보 가지고 오기 */
		NetworkModel.getInstance().postApply(getActivity(), new OnNetworkResultListener<ResultData>() {
			@Override
			public void onResult(ResultData result) {
				if (result.result.equals("success")) {
					System.out.println("친구 기다릴때 내 사진 설정 성공");
					uphoto = result.uphoto;
					imageLoader.displayImage(uphoto, imageViewMe);
				}
			}
		});

		/* 친구 정보 가지고 오기 */
		NetworkModel.getInstance().postApplyChofriend(PropertyManager.getInstance().getSameId(), getActivity(),
				new OnNetworkResultListener<ResultData>() {

					@Override
					public void onResult(ResultData result) {
						if (result.result.equals("success")) {
							PropertyManager.getInstance().setSameId(result.aidB);

							aphotoB = result.aphotoB;
							imageLoader.displayImage(aphotoB, imageViewSame);
						}
					}
				});

		Log.i("요청한 friend : ", PropertyManager.getInstance().getSameId());

		Button btn = (Button) v.findViewById(R.id.btnCancel);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				QustomDialogBuilder builder = new QustomDialogBuilder(getActivity());
				builder.setTitle("친구 요청 취소");
				builder.setMessage("친구 요청을 취소하시겠습니까?");
				builder.setTitleColor("#8C76E7");
				builder.setDividerColor("#8C76E7");
				builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Log.i("cancel friend : ", PropertyManager.getInstance().getSameId());
						NetworkModel.getInstance().postApplyChofriendCancelmeg(PropertyManager.getInstance().getSameId(), getActivity(),
								new OnNetworkResultListener<ResultData>() {
									@Override
									public void onResult(ResultData result) {
										if (result.result.equals("success")) {
											PropertyManager.getInstance().setSameId(""); // 팀 매칭 취소하면 저장해둔 친구id 날린다.

											FragmentManager fm = getParentFragment().getChildFragmentManager();
											FragmentTransaction ft = fm.beginTransaction();
											ft.replace(R.id.fragment1_frame, new Fragment1_AddSame());
											ft.commit();
										}
									}
								});
					}
				});

				builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
				builder.create().show();
			}
		});

//		btn = (Button) v.findViewById(R.id.btnFix1);
//		btn.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				FragmentManager fm = getParentFragment().getChildFragmentManager();
//				FragmentTransaction ft = fm.beginTransaction();
//				ft.replace(R.id.fragment1_frame, new Fragment3_AddRegion());
//				ft.commit();
//			}
//		});
//
//		btn = (Button) v.findViewById(R.id.btnFix2);
//		btn.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(getActivity(), InviteActivity.class);
//				intent.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.WAIT_SAME); // 자신의 위치 번호를 담는다.
//				intent.addFlags(MainTabActivity.INVITE_VALUE); // 초대 화면을 넣을 때 플래그를 담는다.
//				// 프레그먼트에서 초대 액티비티를 호출하면 되돌아올 경우도 생각해야 하기에 항상 자신의 위치 정보를 전송하도록 한다.
//				startActivity(intent);
//				getActivity().finish();
//			}
//		});

		imageViewSame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getActivity(), ShowAnotherPersonProfileActivity.class);
				i.putExtra(ANOPROFILE_ID, PropertyManager.getInstance().getSameId());
				startActivity(i);
			}
		});

		return v;
	}
}
