package com.who.onecupafterwork.show;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.preferences.PropertyManager;
import com.who.onecupafterwork.signin.LoginActivity;
import com.who.onecupafterwork.signin.R;

public class ServiceOutDialog extends DialogFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, R.style.dialog_light);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		QustomDialogBuilder builder = new QustomDialogBuilder(getActivity());
		builder.setTitle("서비스탈퇴");
		builder.setMessage("정말로 Who를 탈퇴 하시겠어요?");
		builder.setTitleColor("#8C76E7");
		builder.setDividerColor("#8C76E7");
		
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getActivity(), "그동안 이용해 주셔서 감사합니다.", Toast.LENGTH_SHORT).show();

				NetworkModel.getInstance().postSignOut(PropertyManager.getInstance().getUserId(), getActivity(),
						new OnNetworkResultListener<ResultData>() {

							@Override
							public void onResult(ResultData result) {
								/* 회원 탈퇴 되면 정보 날린다. */
								PropertyManager.getInstance().setUserId("");
								PropertyManager.getInstance().setUserPassword("");
								PropertyManager.getInstance().setSameCode("");
								PropertyManager.getInstance().setSameId("");
								PropertyManager.getInstance().setProfilePath("");
								PropertyManager.getInstance().setUserStatus("");
								PropertyManager.getInstance().setRegistrationId("");
								// PropertyManager.getInstance().setSamePhone("");
							}
						});

				Intent i = new Intent(getActivity(), LoginActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(i);
			}
		});

		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
//				Toast.makeText(getActivity(), "service Out cancle", Toast.LENGTH_SHORT).show();
			}
		});

		return builder.create();
	}

}
