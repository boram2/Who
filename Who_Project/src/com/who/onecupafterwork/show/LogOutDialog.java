package com.who.onecupafterwork.show;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.who.onecupafterwork.common.MainTabActivity;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.preferences.PropertyManager;
import com.who.onecupafterwork.signin.LoginActivity;
import com.who.onecupafterwork.signin.R;

public class LogOutDialog extends DialogFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, R.style.dialog_light);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		QustomDialogBuilder builder = new QustomDialogBuilder(getActivity());
		// builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("로그아웃");
		builder.setMessage("정말로 로그아웃 하시겠어요?");
		builder.setTitleColor("#8C76E7");
		builder.setDividerColor("#8C76E7");

		// Button okButton = builder.create().getButton(DialogInterface.BUTTON_POSITIVE);
		// set OK button color here
		// okButton.setBackgroundColor(R.style.dialogButtonStyle);

		// Button noButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
		// set NO button color here
		// noButton.setBackgroundColor(R.color.GhostWhite);

		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				// 사용자 id를 가지고 와서 logout 시킨다.
				NetworkModel.getInstance().postLogOut(PropertyManager.getInstance().getUserId(), getActivity(),
						new OnNetworkResultListener<ResultData>() {

							@Override
							public void onResult(ResultData result) {

							}
						});

				PropertyManager.getInstance().setUserId("");
				PropertyManager.getInstance().setUserPassword("");
				PropertyManager.getInstance().setSameCode("");
				PropertyManager.getInstance().setSameId("");
				PropertyManager.getInstance().setRegistrationId("");
				PropertyManager.getInstance().setProfilePath("");
				PropertyManager.getInstance().setUserStatus("");
				// PropertyManager.getInstance().setSamePhone("");

				NetworkModel.getInstance().postRegister("", getActivity(), new OnNetworkResultListener<ResultData>() {

					@Override
					public void onResult(ResultData result) {
						System.out.println(result.result);
						if (result.result.equals("success")) {
							System.out.println("registerId is registerd null");
						}
					}
				});

				Intent i = new Intent(getActivity(), LoginActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(i);

				getActivity().finish();

				MainTabActivity mainTabActivity = (MainTabActivity) MainTabActivity.mainTabActivity;
				mainTabActivity.finish();
			}
		});

		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Toast.makeText(getActivity(), "Logout cancle", Toast.LENGTH_SHORT).show();
				dismiss();
			}
		});

		return builder.create();
	}

}
