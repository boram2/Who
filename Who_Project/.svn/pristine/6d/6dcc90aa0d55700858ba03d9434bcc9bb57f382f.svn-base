package com.who.onecupafterwork.group;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.who.onecupafterwork.data.FriendList;
import com.who.onecupafterwork.data.Person;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.preferences.PropertyManager;
import com.who.onecupafterwork.show.QustomDialogBuilder;
import com.who.onecupafterwork.signin.R;

public class Fragment1_AddSame extends Fragment {

	public static final int CONTACTS_SIZE = -1; // 무작위 숫자

	ImageView imageViewMe;
	ImageView imageViewSame;
	int position = CONTACTS_SIZE;

	Person[] list;
	FriendList[] ufriendlist;
	CharSequence[] cfriendList;

	String[] contacts;

	boolean canSelectFriend = false;
	// String[] thumbnail;

	ImageLoader imageLoader = ImageLoader.getInstance();

	File file;

	public void getList() { // 휴대 전화 정보 가지고 오기
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String[] projection = new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME };
		String[] selectionArgs = null;

		// 정렬
		String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

		// 조회해서 가져온다
		Cursor contactCursor = getActivity().managedQuery(uri, projection, null, selectionArgs, sortOrder);

		// 정보를 담을 array 설정
		ArrayList<String> list = new ArrayList<String>();

		if (contactCursor.moveToFirst()) {
			do {
				list.add(contactCursor.getString(0));
			} while (contactCursor.moveToNext());
		}

		contacts = new String[list.size()];

		for (int i = 0; i < contacts.length; i++) {
			contacts[i] = list.get(i).replaceAll("-", "");
			System.out.println(contacts[i]);
		}

		file = new File("/sdcard/Who/contacts.txt");
		FileOutputStream fos = null;

		try {
			// fos = getActivity().openFileOutput("OneCupContacts.txt", Context.MODE_PRIVATE);
			fos = new FileOutputStream(file);
			for (int i = 0; i < contacts.length; i++) {
				// if (i != contacts.length - 1) {
				fos.write((contacts[i] + ",").getBytes());
				// } else {
				// fos.write(contacts[i].getBytes());
				// }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment1_add_same, container, false);

		imageViewMe = (ImageView) v.findViewById(R.id.imageViewMe);
		imageViewSame = (ImageView) v.findViewById(R.id.imageViewSame);

		// try { // 전화번호 목록 가지고 온다.
		// getList();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		String str = Environment.getExternalStorageState();
		if (str.equals(Environment.MEDIA_MOUNTED)) {
			String dirPath = "/sdcard/Who";
			File file = new File(dirPath);
			if (!file.exists()) { // 원하는 경로에 폴더가 있는지 확인
				file.mkdirs();
			} else {
				Log.i("sdcard check fail", "true");
			}
		}

		getList();

		/* 내사진 설정하기 */
		NetworkModel.getInstance().postApply(getActivity(), new OnNetworkResultListener<ResultData>() {

			@Override
			public void onResult(ResultData result) {
				if (result.result.equals("success")) {
					PropertyManager.getInstance().setProfilePath(result.uphoto); // 내 사진 가지고 오기
					imageLoader.displayImage(result.uphoto, imageViewMe);
					System.out.println(result.uphoto);
					imageLoader.displayImage(PropertyManager.getInstance().getProfilePath(), imageViewMe);
				} else if (result.result.equals("error")) {
					Toast.makeText(getActivity(), "사진을 불러올 수 없습니다. 재실행해 주세요.", Toast.LENGTH_SHORT).show();
				}
			}
		});

		/* 네트워크로 주소록을 올리고, 친구 받아온다. */
		// NetworkModel.getInstance().postApplyListFriend(contacts, getActivity(), new OnNetworkResultListener<ResultData>() {
		//
		// @Override
		// public void onResult(ResultData result) {
		// if (result.result.equals("success")) {
		// ufriendlist = new FriendList[result.data.length];
		//
		// for (int i = 0; i < ufriendlist.length; i++) {
		// ufriendlist[i] = new FriendList();
		// }
		// ufriendlist = result.data;
		// cfriendList = new CharSequence[ufriendlist.length];
		//
		// for (int i = 0; i < cfriendList.length; i++) {
		// cfriendList[i] = new String();
		// cfriendList[i] = ufriendlist[i].uname + "  " + ufriendlist[i].uage;
		// }
		//
		// canSelectFriend = true;
		// } else {
		// Toast.makeText(getActivity(), "친구 목록을 가져오기 못했습니다.", Toast.LENGTH_SHORT).show();
		// }
		// }
		// });

		NetworkModel.getInstance().postApplyListFriend2(file, getActivity(), new OnNetworkResultListener<ResultData>() {

			@Override
			public void onResult(ResultData result) {
				if (result.result.equals("success")) {
					ufriendlist = new FriendList[result.data.length];

					for (int i = 0; i < ufriendlist.length; i++) {
						ufriendlist[i] = new FriendList();
					}
					ufriendlist = result.data;
					cfriendList = new CharSequence[ufriendlist.length];

					for (int i = 0; i < cfriendList.length; i++) {
						cfriendList[i] = new String();
						cfriendList[i] = ufriendlist[i].uname + " " + ufriendlist[i].uage;
					}

					canSelectFriend = true;

					Log.i("delete check", "" + file.delete());
				} else {
					Toast.makeText(getActivity(), "친구 목록을 가져오기 못했습니다.", Toast.LENGTH_SHORT).show();
				}
			}
		});

		imageViewSame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (canSelectFriend) {
					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

					builder.setItems(cfriendList, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							position = which;

							NetworkModel.getInstance().postApplyChofriend(ufriendlist[position].uid, getActivity(),
									new OnNetworkResultListener<ResultData>() {

										@Override
										public void onResult(ResultData result) {
											if (result.result.equals("success")) {
												PropertyManager.getInstance().setSameId(result.aidB);

												String aphotoB = result.aphotoB;
												imageLoader.displayImage(aphotoB, imageViewSame);
											}
										}
									});
							Toast.makeText(getActivity(), "선택한 사람 : " + cfriendList[which], Toast.LENGTH_SHORT).show();
							dialog.dismiss();
						}
					});
					builder.setOnCancelListener(new DialogInterface.OnCancelListener() {

						@Override
						public void onCancel(DialogInterface arg0) {

						}
					});
					builder.create().show();
				} else {
					Toast.makeText(getActivity(), "네트워크 연결이 원활하지 않습니다. 앱을 재시작 해주세요.", Toast.LENGTH_SHORT).show();
				}
			}
		});

		Button btn = (Button) v.findViewById(R.id.btnAddSame);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (position == CONTACTS_SIZE) { // 리스트 아이템 클릭 안한 것과 같음
					Toast.makeText(getActivity(), "지인을 선택하셔야 다음 페이지로 넘어갑니다.", Toast.LENGTH_SHORT).show();
				} else {
					QustomDialogBuilder builder = new QustomDialogBuilder(getActivity());
					builder.setTitle("친구 요청");
					builder.setMessage(cfriendList[position] + "님께 친구를 요청하겠습니다.");
					builder.setTitleColor("#8C76E7");
					builder.setDividerColor("#8C76E7");
					builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							NetworkModel.getInstance().postApplyChofriendSendmeg(ufriendlist[position].uid, getActivity(),
									new OnNetworkResultListener<ResultData>() {

										@Override
										public void onResult(ResultData result) {
											if (result.result.equals("success")) {

												Log.i("초대 푸쉬 전송 성공", "친구요청 푸쉬 전송했습니다.");

												FragmentManager fm = getParentFragment().getChildFragmentManager();
												FragmentTransaction ft = fm.beginTransaction();
												ft.replace(R.id.fragment1_frame, new Fragment2_WaitSame());
												ft.commit();
											} else if (result.result.equals("fail")) {
												Toast.makeText(getActivity(), "해당 친구는 이미 초대를 받았습니다.", Toast.LENGTH_SHORT).show();
											} else {
												Toast.makeText(getActivity(), "서버가 연결되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
											}
										}
									});
						}
					});
					builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
					builder.create().show();
				}
			}
		});

		return v;
	}

}
