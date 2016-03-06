package com.who.onecupafterwork.show;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.who.onecupafterwork.signin.R;

public class RecommendFriendDialog extends DialogFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, R.style.dialog_light);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		QustomDialogBuilder builder = new QustomDialogBuilder(getActivity());
		// builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("피주선자 알림");
		builder.setMessage("주선자가 추천 받은 그룹을 선택하고 있습니다.\n미팅 성사 결과는 푸쉬로 안내됩니다.");
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
