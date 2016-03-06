package com.who.onecupafterwork.show;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.who.onecupafterwork.signin.R;

public class RecommendDialog extends DialogFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, R.style.dialog_light);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		QustomDialogBuilder builder = new QustomDialogBuilder(getActivity());
		// builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("주선자 알림");
		builder.setMessage("3팀의 이성 그룹이 도착했습니다.\n사진을 누르시면 프로필을 확인할 수 있습니다.\n최대 3그룹까지 선택 가능합니다.");
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
