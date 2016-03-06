package com.who.onecupafterwork.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.who.onecupafterwork.network.MyApplication;

public class PropertyManager {

	private static PropertyManager instance;

	private static final String PREF_NAME = "my_prefs.xml"; // Preferences 파일 이름
	SharedPreferences mPrefs; // 값을 읽어오는 객체
	SharedPreferences.Editor mEditor; // 값을 저장하는 객체

	public static PropertyManager getInstance() {
		if (instance == null) {
			instance = new PropertyManager();
		}
		return instance;
	}

	private PropertyManager() {
		mPrefs = MyApplication.getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE); // Context가 있어야 한다. -> MyApplication 만들기
		// Context.MODE_PRIVATE -> 다른 앱에서 접근 못하는 모드
		mEditor = mPrefs.edit();
	}

	/* 레지스터 코드 */
	private static final String FIELD_REGISTRATION_ID = "registrationId";
	private String registrationId;

	public String getRegistrationId() {
		if (registrationId == null) {
			registrationId = mPrefs.getString(FIELD_REGISTRATION_ID, "");
		}
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
		mEditor.putString(FIELD_REGISTRATION_ID, registrationId);
		mEditor.commit();
	}

	// private static final String FIELD_SERVER_ADDRESS = "serverAddress";
	// private String serverAddress;
	//
	// public String getServerAddress() {
	// if (serverAddress == null) {
	// serverAddress = mPrefs.getString(FIELD_SERVER_ADDRESS, "");
	// }
	// return serverAddress;
	// }
	//
	// public void setServerAddress(String serverAddress) {
	// this.serverAddress = serverAddress;
	// mEditor.putString(FIELD_SERVER_ADDRESS, serverAddress);
	// mEditor.commit();
	// }

	private static final String FIELD_USER_ID = "userId";
	private String userId;

	public String getUserId() {
		if (userId == null) {
			userId = mPrefs.getString(FIELD_USER_ID, ""); // 디폴드 값은 ""이다.
		}
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
		mEditor.putString(FIELD_USER_ID, userId);
		mEditor.commit();
	}

	private static final String FIELD_USER_PASSWORD = "password";
	private String userPassword;

	public String getUserPassword() {
		if (userPassword == null) {
			userPassword = mPrefs.getString(FIELD_USER_PASSWORD, ""); // 디폴드 값은 ""이다.
		}
		return userPassword;
	}

	public void setUserPassword(String password) {
		this.userPassword = password;
		mEditor.putString(FIELD_USER_PASSWORD, password);
		mEditor.commit();
	}

	// SharedPreferences가 저장할 수 있는 값 boolean, float, long, String 등등

	/* 친구의 Id */
	private static final String FILED_SAME_ID = "sameId";
	private String sameId;

	public String getSameId() {
		if (sameId == null) {
			sameId = mPrefs.getString(FILED_SAME_ID, ""); // 디폴드 값은 ""이다.
		}
		return sameId;
	}

	public void setSameId(String sameId) {
		this.sameId = sameId;
		mEditor.putString(FILED_SAME_ID, sameId);
		mEditor.commit();
	}

	private static final String FILED_USER_STATUS = "userstatus";
	private String userStatus;

	public String getUserStatus() {
		if (userStatus == null) {
			userStatus = mPrefs.getString(FILED_USER_STATUS, "");
		}
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
		mEditor.putString(FILED_USER_STATUS, userStatus);
		mEditor.commit();
	}

	/* 친구의 전화번호 */
	// private static final String FILED_SAME_PHONE = "samePhone";
	// private String samePhone;
	//
	// public String getSamePhone() {
	// if (samePhone == null) {
	// samePhone = mPrefs.getString(FILED_SAME_PHONE, ""); // 디폴드 값은 ""이다.
	// }
	// return samePhone;
	// }
	//
	// public void setSamePhone(String samePhone) {
	// this.samePhone = samePhone;
	// mEditor.putString(FILED_SAME_PHONE, samePhone);
	// mEditor.commit();
	// }

	/* 그룹 코드 */
	private static final String FILED_SAME_CODE = "sameCode";
	private String sameCode;

	public String getSameCode() {
		if (sameCode == null) {
			sameCode = mPrefs.getString(FILED_SAME_CODE, ""); // 디폴드 값은 ""이다.
		}
		return sameCode;
	}

	public void setSameCode(String sameCode) {
		this.sameCode = sameCode;
		mEditor.putString(FILED_SAME_CODE, sameCode);
		mEditor.commit();
	}

	// 자신의 프로필 사진 
	private static final String FILED_PROFILE_PATH = "profilePath";
	private String profilePath;

	public String getProfilePath() {
		if (profilePath == null) {
			profilePath = mPrefs.getString(FILED_PROFILE_PATH, ""); // 디폴드 값은 ""이다.
		}
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
		mEditor.putString(FILED_PROFILE_PATH, profilePath);
		mEditor.commit();
	}

}
