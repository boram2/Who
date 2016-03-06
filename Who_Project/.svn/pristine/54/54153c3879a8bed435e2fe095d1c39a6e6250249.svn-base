package com.who.onecupafterwork.network;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.http.Header;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.who.onecupafterwork.common.SplashActivity;
import com.who.onecupafterwork.data.Company;
import com.who.onecupafterwork.data.Contact;
import com.who.onecupafterwork.data.Login;
import com.who.onecupafterwork.data.Person;
import com.who.onecupafterwork.data.Profile;
import com.who.onecupafterwork.data.ProfilesData;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.preferences.PropertyManager;
import com.who.onecupafterwork.signin.LoginActivity;

public class NetworkModel {

	AsyncHttpClient client; // AsyncHttpClient 객체

	private static NetworkModel instance;

	public static NetworkModel getInstance() {
		if (instance == null) {
			instance = new NetworkModel();
		}
		return instance;
	}

	private NetworkModel() {
		client = new AsyncHttpClient();
		client.setCookieStore(new PersistentCookieStore(MyApplication.getContext()));
		client.setTimeout(30000); // 타임아웃 30초로 설정
	}

	public interface OnNetworkResultListener<T> { // 결과를 받을 리스너
		public void onResult(T result);
	}

	public void cancelRequests(Context context) {
		client.cancelRequests(context, false);
	}

	public void postLogin(Login login, final Context context, final OnNetworkResultListener<ResultData> listener) {

		String url = "http://54.178.147.232/login";

		RequestParams params = new RequestParams();
		Login logData = login;
		params.put("uid", logData.uid);
		params.put("upass", logData.upass);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class); // 결과를
																				// 받아온다.
				listener.onResult(result);
				Log.i("Login result", result.result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				listener.onResult(null);
				
				Toast.makeText(context, "네트워크가 연결되어 있지 않습니다.(check Network Login)", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(context, LoginActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				context.startActivity(i);

				SplashActivity sa = (SplashActivity) SplashActivity.splashActivity;
				sa.finish();
				PropertyManager.getInstance().setUserId("");
				PropertyManager.getInstance().setUserPassword("");
			}
		});
	}

	public void postSignIn(Person person, Context context, final OnNetworkResultListener<ResultData> listener) {

		String url = "http://54.178.147.232/signup";

		RequestParams params = new RequestParams();
		try {
			params.put("uphoto", person.uphoto);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		params.put("uname", person.uname);
		params.put("uage", person.uage);
		params.put("uid", person.uid);
		params.put("upass", person.upass);
		params.put("uphon", person.uphon);
		params.put("ugen", person.ugen);
		params.put("umail", person.umail);
		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class); // 결과를
																				// 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}

		});
	}

	public void postCheckMail(Company ucompany, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/signup/checkmail";

		RequestParams params = new RequestParams();
		params.put("ucompc", ucompany.companyCode);
		params.put("ucompn", ucompany.companyName);
		params.put("ucmail", ucompany.companyDomain);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class); // 결과를
																				// 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
			
			}
		});
	}

	public void postCheckId(String uid, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/signup/checkid";

		RequestParams params = new RequestParams();
		params.put("uid", uid);
		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class); // 결과를
																				// 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	public void postCheckPhon(String uname, String uphon, Context context, final OnNetworkResultListener<ResultData> listener) {

		String url = "http://54.178.147.232/signup/checkphon";

		RequestParams params = new RequestParams();
		params.put("uname", uname);
		params.put("uphon", uphon);
		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class); // 결과를
																				// 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	public void postProfile(Profile profileData, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/profilew";

		RequestParams params = new RequestParams();
		params.put("upblood", profileData.upblood);
		params.put("upreligion", profileData.upreligion);
		params.put("upinterest", profileData.upinterest);
		params.put("uphobby", profileData.uphobby);
		params.put("upfacebook", profileData.upfacebook);
		params.put("upintro", profileData.upintro);
		params.put("uparea", profileData.uparea);
		// params.put("") - 좋아요어떻게 할건가
		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData profile = gson.fromJson(content, ResultData.class);
				listener.onResult(profile);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
			}
		});
	}

	public void postLogOut(String uid, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/logout";

		RequestParams params = new RequestParams();
		params.put("uid", uid);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class); // 결과를
																				// 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}

		});
	}

	public void postSignOut(String uid, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/memdelete";

		RequestParams params = new RequestParams();
		params.put("uid", uid);
		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class); // 결과를
																				// 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}

		});
	}

	public void postApply(Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/apply";

		RequestParams params = new RequestParams();
		// params.put("aidA", aidA);
		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class); // 결과를
																				// 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				System.out.println("apply fail");
			}
		});
	}

	public void postChoLocation(Contact c, String si, String gu, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/apply/cholocation";

		RequestParams params = new RequestParams();
		// params.put("aidA", c.aidA);
		params.put("aidB", c.aidB);
		params.put("alocation1", si);
		params.put("alocation2", gu);
		System.out.println("enter choLocation Network" + si + gu);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class); // 결과를
																				// 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	public void postMatching(String date, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/matching";

		RequestParams params = new RequestParams();
		params.put("applytime", date);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class); // 결과를
																				// 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				listener.onResult(null);
			}
		});
	}

	public void postMatchingChoose(String time, String[] whoCodes, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/matching/choose";

		RequestParams params = new RequestParams();
		params.put("applytime", time);
		params.put("acode", PropertyManager.getInstance().getSameCode());
		// params.put("acode", "318");
		params.put("chooseG", whoCodes);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class); // 결과를
																				// 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				listener.onResult(null);
			}
		});
	}

	public void postMatchingResult(String mtime, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/matching/mresult";

		RequestParams params = new RequestParams();
		params.put("applytime", mtime);
		// params.put("uacode", uacode);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class); // 결과를
																				// 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				listener.onResult(null);
			}
		});
	}

	public void postProfiles(String uid, Context context, final OnNetworkResultListener<ProfilesData> listener) {
		String url = "http://54.178.147.232/profiles";

		Log.i("남의 profiles ", uid + "의 프로필을 불러옴");
		RequestParams params = new RequestParams();
		params.put("choid", uid);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ProfilesData result = gson.fromJson(content, ProfilesData.class); // 결과를 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	public void postMyProfiles(Context context, final OnNetworkResultListener<ProfilesData> listener) {
		String url = "http://54.178.147.232/profiles";

		RequestParams params = new RequestParams();
		// params.put("choid", uid);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ProfilesData result = gson.fromJson(content, ProfilesData.class); // 결과를 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	public void postModifyProfiles(Profile profileData, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/update";

		RequestParams params = new RequestParams();
		params.put("upblood", profileData.upblood);
		params.put("upreligion", profileData.upreligion);
		params.put("upinterest", profileData.upinterest);
		params.put("uphobby", profileData.uphobby);
		params.put("upfacebook", profileData.upfacebook);
		params.put("upintro", profileData.upintro);
		params.put("uparea", profileData.uparea);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class); // 결과를
																				// 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	public void postMatchingMlike(String uid, String mcode, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/matching/mlike";

		RequestParams params = new RequestParams();
		params.put("uid", uid);
		params.put("mcode", mcode);
		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class); // 결과를
																				// 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	// 그냥 매칭 결과 테이블에는 상대방만 있지만 나의 정보도 함께 담긴 매칭 히스토리 테이블이 따로있어야 할듯
	public void postMatchingHistory(String uid, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/matching/mhistory";

		RequestParams params = new RequestParams();
		params.put("uid", uid);
		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class);
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	public void postNoticeList(String lastnum, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/notice/list";

		RequestParams params = new RequestParams();
		params.put("lastnum", lastnum);
		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class);
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	// public void postReadNotice(String readnum, Context context, final
	// OnNetworkResultListener<ResultData> listener) {
	// String url = "http://54.178.147.232/notice/read";
	//
	// RequestParams params = new RequestParams();
	// params.put("readnum", readnum);
	// client.post(context, url, params, new AsyncHttpResponseHandler() {
	//
	// @Override
	// public void onSuccess(int statusCode, Header[] headers, byte[]
	// responseBody) {
	// String content = new String(responseBody);
	// Gson gson = new Gson();
	// ResultData result = gson.fromJson(content, ResultData.class);
	// listener.onResult(result);
	// }
	//
	// @Override
	// public void onFailure(int statusCode, Header[] headers, byte[]
	// responseBody, Throwable error) {
	//
	// }
	// });
	// }

	public void postApplyListFriend(String[] ufriendList, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/apply/listfriend";

		RequestParams params = new RequestParams();
		params.put("ufriendlist", ufriendList);
		// Log.i("parms : ", params.toString());
		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class);
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				listener.onResult(null);
				System.out.println("친구 목록 불러오기 실패");
			}
		});
	}

	// push알림
	public void postApplyChofriend(String fid, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/apply/chofriend";

		RequestParams params = new RequestParams();
		params.put("fid", fid);
		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class);
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	public void postNoticeList(Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/notice/list";

		RequestParams params = new RequestParams();

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class);
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	public void postRegBlockPhoneNum(String phoneNum, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/settings/regBlockPhoneNum";

		RequestParams params = new RequestParams();
		params.put("phoneNum", phoneNum);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class);
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) { // TODO
																											// Auto-generated
																											// method stub

			}
		});
	}

	public void postinqBlockPhoneNum(Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/settings/inqBlockPhoneNum";

		RequestParams params = new RequestParams();

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class);
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) { // TODO
																											// Auto-generated
																											// method stub

			}
		});
	}

	public void postdelBlockPhoneNum(String phoneNum, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/settings/delBlockPhoneNum";

		RequestParams params = new RequestParams();
		params.put("phoneNum", phoneNum);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class);
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) { 
				
			}
		});
	}

	public void postSetToggle(int status, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/settings/udUserEnvironment";

		RequestParams params = new RequestParams();
		params.put("pushSetting", status);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class);
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) { // TODO
																											// Auto-generated
																											// method stub

			}
		});
	}

	// push
	public void postRegister(String regId, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/register";

		RequestParams params = new RequestParams();
		params.put("regid", regId);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class);
				listener.onResult(result);

			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				
			}
		});
	}

	// noti method
	public void postApplyChofriendSendmeg(String fid, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/apply/chofriend/sendmeg";

		RequestParams params = new RequestParams();
		params.put("fid", fid);
		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class); // 결과를
																				// 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	// noti method
	public void postApplyChofriendRefusemeg(String aidA, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/apply/chofriend/refusemeg";

		RequestParams params = new RequestParams();
		params.put("aidA", aidA);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class);
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	// noti method
	public void postApplyChofriendCancelmeg(String fid, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/apply/chofriend/cancelmeg";

		RequestParams params = new RequestParams();
		params.put("fid", fid);
		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class); // 결과를
																				// 받아온다.
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	// noti method
	public void postApplyChofriendAcceptmeg(String aidA, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/apply/chofriend/acceptmeg";

		RequestParams params = new RequestParams();
		params.put("aidA", aidA);
		Log.i("승낙 푸쉬 전송 성공", aidA + "님의 친구수락 푸쉬 전송");
		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class);
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	public void postApplyListFriend2(File file, Context context, final OnNetworkResultListener<ResultData> listener) {
		String url = "http://54.178.147.232/apply/listfriend";

		RequestParams params = new RequestParams();
		try {
			params.put("upfile", file, "text/plain");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Log.i("parms : ", params.toString());
		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String content = new String(responseBody);
				Gson gson = new Gson();
				ResultData result = gson.fromJson(content, ResultData.class);
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				listener.onResult(null);
			}
		});
	}
}
