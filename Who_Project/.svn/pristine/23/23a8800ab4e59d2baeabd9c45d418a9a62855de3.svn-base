package com.who.onecupafterwork.signin;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class CertifyPhoneNumCustomDialog extends DialogFragment {
	
	public static final String CERTIFY_PHONE_NUMBER = "certifyNum";
	String certifyNumber;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final Dialog dialog = getDialog();
		
		if (dialog != null) {
			Window w = dialog.getWindow();
			w.setTitle("Certify PN");
		}
		
		View v = inflater.inflate(R.layout.certify_phonenum_custom_dialog, container, false);
		
		EditText certifyNumberView = (EditText) v.findViewById(R.id.certifyNumberView);
		certifyNumber = certifyNumberView.getText().toString();
		Button btn = (Button) v.findViewById(R.id.btnCertifyNum);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				Intent i = new Intent(getActivity(), SignInActivity.class);
				i.putExtra(CERTIFY_PHONE_NUMBER, certifyNumber);
				i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(i);
			}
		});
		
		return v;
	}

}
