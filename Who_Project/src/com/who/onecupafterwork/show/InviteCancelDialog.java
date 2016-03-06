package com.who.onecupafterwork.show;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.who.onecupafterwork.signin.R;

public class InviteCancelDialog extends DialogFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, R.style.dialog_light);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		QustomDialogBuilder builder = new QustomDialogBuilder(getActivity());
		// builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("친구 요청 거절");
		builder.setMessage("친구가 그룹 요청을 거절하셨습니다.");
		builder.setTitleColor("#8C76E7");
		builder.setDividerColor("#8C76E7");

		builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dismiss();
			}
		});

		return builder.create();
	}

}
