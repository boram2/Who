package com.who.onecupafterwork.signin;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;

public class StipulationCustomDialog extends DialogFragment {

	TextView stipulationContent, privacyContent;
	ScrollView stipulView;
	CheckBox checkStipulation, checkPrivacy;

	public final static String CHECK_STIPULATION = "checkStipulation";
	public final static String CHECK_PRIVACY = "checkPrivacy";

	// private void readFile(File file) {
	// int readcount = 0;
	// if (file != null && file.exists()) {
	// try {
	// FileInputStream fis = new FileInputStream(file);
	// readcount = (int) file.length();
	// byte[] buffer = new byte[readcount];
	// fis.read(buffer);
	// for (int i = 0; i < file.length(); i++) {
	// Log.i("CHECK : ", "" + buffer[i]);
	// }
	// fis.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final Dialog dialog = getDialog();

		if (dialog != null) {
			dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
			dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}

		View v = inflater.inflate(R.layout.stipulation_custom_dialog, container, false);

		stipulationContent = (TextView) v.findViewById(R.id.stipulationContentView);
		privacyContent = (TextView) v.findViewById(R.id.privacyContentView);
		stipulView = (ScrollView) v.findViewById(R.id.stipulationScrollView);
		stipulView.setScrollbarFadingEnabled(false);

		checkStipulation = (CheckBox) v.findViewById(R.id.checkStipulation);
		checkPrivacy = (CheckBox) v.findViewById(R.id.checkPrivacyUsage);

		stipulationContent.setText(R.string.private_info);
		privacyContent.setText(R.string.using_info);
		
		Button btn = (Button) v.findViewById(R.id.btnCheckStipulation);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				Intent i = new Intent(getActivity(), SignInActivity.class);
				i.putExtra(CHECK_STIPULATION, checkStipulation.isChecked());
				i.putExtra(CHECK_PRIVACY, checkPrivacy.isChecked());
				i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(i);
			}
		});
		return v;
	}

	// public String getStipulation() {
	// String s = "stipulation" + "\n";
	// for (int i = 0; i < 20; i++) {
	// s += i + "\n";
	// }
	// return s;
	// }
	//
	// public String getPrivacy() {
	// String s = "privacy" + "\n";
	// for (int i = 0; i < 20; i++) {
	// s += i + "\n";
	// }
	// return s;
	// }
}
