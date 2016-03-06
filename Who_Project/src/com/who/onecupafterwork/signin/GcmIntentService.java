package com.who.onecupafterwork.signin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.who.onecupafterwork.common.InviteActivity;
import com.who.onecupafterwork.common.MainTabActivity;
import com.who.onecupafterwork.database.DBManager;
import com.who.onecupafterwork.group.FrameFragment;
import com.who.onecupafterwork.preferences.PropertyManager;

public class GcmIntentService extends IntentService {

	private static final String TAG = "GcmIntengService";

	public static final int NOTIFICATION_ID = 1;
	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;

	public static final String MESSAGE_INVITE = "님으로부터 그룹 추가 요청이 도착하였습니다.";
	public static final String MESSAGE_REFUSE = "님께서 초대를 거절하였습니다. 친구를 추가하세요.";
	public static final String MESSAGE_CANCEL = "님께서 친구 신청을 취소하였습니다. 친구를 추가하세요.";
	public static final String MESSAGE_ACCEPT = "님께서 친구 신청을 수락하였습니다. 만남을 가지실 지역을 선택하세요.";
	public static final String MESSAGE_WHO = "추천 상대 리스트가 확인하세요.";
	public static final String MESSAGE_RESULT = "매칭 결과가 도착했습니다.";
	// public static final String MESSAGE_SUCCESS = "축하합니다. 매칭 상대가 결정되었습니다.";
	// public static final String MESSAGE_SELECT = "주선자께서 매칭을 신청하였습니다.";
	// public static final String MESSAGE_FAIL = "이성 매칭에 실패하셨습니다. 다음 시간대에 신청해주세요.";
	// public static final String MESSAGE_EVENT = "이벤트가 도착했습니다.";

	// public static final int TYPE_INVITE = 0;
	// public static final int TYPE_REFUSE = 1;
	// public static final int TYPE_CANCEL = 2;
	// public static final int TYPE_ACCEPT = 3;
	// public static final int TYPE_WHO = 4;
	// public static final int TYPE_SUCCESS = 5;
	// public static final int TYPE_SELECT = 6;
	// public static final int TYPE_FAIL = 7;
	// public static final int TYPE_EVENT = 8;

	SimpleDateFormat formatter = new SimpleDateFormat("MM.dd", Locale.KOREA);
	Date currentTime = new Date();
	String date = formatter.format(currentTime);

	public GcmIntentService() {
		super("GcmIntentService");
	}

	// server에서 받은 정보들이 Receiver를 거쳐 들어오는 부분
	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();

		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty()) { // has effect of unparcelling Bundle
			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
				// sendNotification("Send error: " + extras.toString());
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
				// sendNotification("Deleted messages on server: " +
				// extras.toString());
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) { // noti를 날리는 부분
				String notiType = intent.getStringExtra("key1"); // push로 Noti액션을 사용하려고
				Log.i("notiType", " type :" + notiType);
				if (notiType.equals("1")) {
					Log.i("Push", "Send 친구 요청");
					sendApplyNotification(intent, "Received: " + extras.toString());
				} else if (notiType.equals("2")) {
					Log.i("Push", "Send 친구가 거절");
					sendRefuseNotification(intent, "Received: " + extras.toString());
				} else if (notiType.equals("3")) {
					Log.i("Push", "Send 친추 취소");
					sendCancelNotification(intent, "Received: " + extras.toString());
				} else if (notiType.equals("4")) {
					Log.i("Push", "Send 친추 수락");
					sendAcceptNotification(intent, "Received: " + extras.toString());
				} else if (notiType.equals("5")) {
					Log.i("Push", "Send 추천 그룹");
					sendRecommendationNotification(intent, "Received: " + extras.toString());
				} else if (notiType.equals("6")) {
					Log.i("Push", "Send 매칭 결과");
					sendResultNotification(intent, "Received: " + extras.toString());
				}
			}
		}
		// Release the wake lock provided by the WakefulBroadcastReceiver.
		GcmBroadcastReceiver.completeWakefulIntent(intent);
	}

	// Put the message into a notification and post it.
	// This is just one simple example of what you might choose to do with
	// a GCM message.
	private void sendApplyNotification(Intent intent, String msg) { // notification 설정
		mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

		String senderId = intent.getStringExtra("key2");
		String senderName = intent.getStringExtra("key3");
		String senderPhoto = intent.getStringExtra("key4");

		DBManager.getInstance().insertAlarm(PropertyManager.getInstance().getUserId(), senderName + MESSAGE_INVITE, date);
		// Log.i("db invite : ", PropertyManager.getInstance().getUserId() + " " + senderName + MESSAGE_INVITE);

		Intent i = new Intent(this, InviteActivity.class);

		i.putExtra("senderId", senderId);
		i.putExtra("senderName", senderName);
		i.putExtra("senderPhoto", senderPhoto);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.splash_logo).setContentTitle("친구 요청")
				.setContentText(senderName + MESSAGE_INVITE).setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL);

		ActivityManager activityManager = (ActivityManager) getSystemService(Activity.ACTIVITY_SERVICE);
		List<RunningTaskInfo> info;
		info = activityManager.getRunningTasks(3);
		Iterator iterator = info.iterator();
		if (iterator.hasNext()) {
			RunningTaskInfo rtInfo = (RunningTaskInfo) iterator.next();
			if (rtInfo.baseActivity.getClassName().equals("com.who.onecupafterwork.common.MainTabActivity")) {
				Log.i("running App sendApply", "퇴근 후 Application is running");
				startActivity(i);

				// NotificationCompat.Builder mNowBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.splash_logo)
				// .setContentTitle("친구 요청").setContentText(senderName + MESSAGE_INVITE).setAutoCancel(true)
				// .setDefaults(Notification.DEFAULT_ALL);
				// mNotificationManager.notify(NOTIFICATION_ID, mNowBuilder.build());
			} else {
				mBuilder.setContentIntent(contentIntent);
				mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
			}
		}
		// PendingIntent contentIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
		//
		// NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.splash_logo).setContentTitle("친구 요청")
		// // .setStyle(new NotificationCompat.BigTextStyle().bigText(senderName+"님이 친구를 요청했습니다."))
		// .setContentText(senderName + MESSAGE_INVITE).setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL);
		//
		// mBuilder.setContentIntent(contentIntent);
		// mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build())
	}

	private void sendRefuseNotification(Intent intent, String msg) { // notification 설정
		mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

		String refuserId = intent.getStringExtra("key2");
		String refuserName = intent.getStringExtra("key5"); // 거절한 친구의 이름

		DBManager.getInstance().insertAlarm(PropertyManager.getInstance().getUserId(), refuserName + MESSAGE_REFUSE, date);

		Intent i = new Intent(this, MainTabActivity.class);
		i.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.ADD_SAME);
		// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.splash_logo).setContentTitle("친구 거절")
				.setContentText(refuserName + MESSAGE_REFUSE).setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL);

		ActivityManager activityManager = (ActivityManager) getSystemService(Activity.ACTIVITY_SERVICE);
		List<RunningTaskInfo> info;
		info = activityManager.getRunningTasks(3);
		Iterator iterator = info.iterator();
		if (iterator.hasNext()) {
			RunningTaskInfo rtInfo = (RunningTaskInfo) iterator.next();
			if (rtInfo.topActivity.getClassName().equals("com.who.onecupafterwork.common.MainTabActivity")) {
				Log.i("accept running App refuse", "퇴근 후 Application is running");
				// MainTabActivity.mainTabActivity.finish();
				// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.putExtra(MainTabActivity.INTENT_TYPE, "refuseIntent");
				startActivity(i);
			} else {
				// MainTabActivity.mainTabActivity.finish();
				mBuilder.setContentIntent(contentIntent);
				// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
			}
		}
	}

	private void sendCancelNotification(Intent intent, String msg) {
		mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

		String cancelId = intent.getStringExtra("key2");
		String cancelName = intent.getStringExtra("key5"); // 취소한 사람의 이름

		DBManager.getInstance().insertAlarm(PropertyManager.getInstance().getUserId(), cancelName + MESSAGE_CANCEL, date);

		Intent i = new Intent(this, MainTabActivity.class);
		i.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.ADD_SAME);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		// i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

		Log.i("cancelId : ", cancelId);

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.splash_logo).setContentTitle("친구 취소")
				.setContentText(cancelName + MESSAGE_CANCEL).setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL);

		ActivityManager activityManager = (ActivityManager) getSystemService(Activity.ACTIVITY_SERVICE);
		List<RunningTaskInfo> info;
		info = activityManager.getRunningTasks(3);
		Iterator iterator = info.iterator();
		if (iterator.hasNext()) {
			RunningTaskInfo rtInfo = (RunningTaskInfo) iterator.next();
			System.out.println("현재 앱 :"+rtInfo.topActivity.getClassName());
			if (rtInfo.topActivity.getClassName().equals("com.who.onecupafterwork.common.MainTabActivity")
					||rtInfo.topActivity.getClassName().equals("com.who.onecupafterwork.common.InviteActivity")) {
				Log.i("running App cancel", "퇴근 후 Application is running");
				i.putExtra(MainTabActivity.INTENT_TYPE, "cancelIntent");
				startActivity(i);
			} else {
				mBuilder.setContentIntent(contentIntent);
				mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
			}
		}
	}

	private void sendAcceptNotification(Intent intent, String msg) {
		mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

		String aidA = intent.getStringExtra("key2"); // 푸쉬보낸사람
		String aidB = intent.getStringExtra("key4");
		String anameB = intent.getStringExtra("key5"); // 수락한 친구의 이름

		DBManager.getInstance().insertAlarm(PropertyManager.getInstance().getUserId(), anameB + MESSAGE_ACCEPT, date);

		Log.i("Get Push Info", "In GCM INTENT METHod");

		Intent i = new Intent(this, MainTabActivity.class);
		i.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.ADD_REGION);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.splash_logo).setContentTitle("친구 수락")
				.setContentText(anameB + MESSAGE_ACCEPT).setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL);

		ActivityManager activityManager = (ActivityManager) getSystemService(Activity.ACTIVITY_SERVICE);
		List<RunningTaskInfo> info;
		info = activityManager.getRunningTasks(3);
		Iterator iterator = info.iterator();
		if (iterator.hasNext()) {
			RunningTaskInfo rtInfo = (RunningTaskInfo) iterator.next();
			if (rtInfo.topActivity.getClassName().equals("com.who.onecupafterwork.common.MainTabActivity")) {
				Log.i("running App Accept", "퇴근 후 Application is running");
				// MainTabActivity.mainTabActivity.finish();
				startActivity(i);
			} else {
				// MainTabActivity.mainTabActivity.finish();
				mBuilder.setContentIntent(contentIntent);
				mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
			}
		}

		Log.i("accept aidB : ", aidB);

		// PendingIntent contentIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
		//
		// NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.splash_logo).setContentTitle("친구 수락")
		// .setContentText(anameB + MESSAGE_ACCEPT).setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL);
		//
		// mBuilder.setContentIntent(contentIntent);
		// mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
	}

	private void sendRecommendationNotification(Intent intent, String msg) {
		mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

		String someone = intent.getStringExtra("key2"); // 주선자인지 비주선자인지 판단

		DBManager.getInstance().insertAlarm(PropertyManager.getInstance().getUserId(), MESSAGE_WHO, date);

		Log.i("Get Push Info", "In GCM INTENT METHod");

		Intent i = new Intent(this, MainTabActivity.class);
		if (someone.equals("agent")) { // 주선자
			// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.putExtra(MainTabActivity.INTENT_TYPE, "RecommendIntent");
			i.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.SELECT_WHO);
		} else if (someone.equals("friend")) { // 비주선자
			// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.putExtra(MainTabActivity.INTENT_TYPE, "RecommendFriendIntent");
			i.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.WAIT_WHO);
		}
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.splash_logo).setContentTitle("추천 그룹 도착")
				.setContentText(MESSAGE_WHO).setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL);

		ActivityManager activityManager = (ActivityManager) getSystemService(Activity.ACTIVITY_SERVICE);
		List<RunningTaskInfo> info;
		info = activityManager.getRunningTasks(3);
		Iterator iterator = info.iterator();
		if (iterator.hasNext()) {
			RunningTaskInfo rtInfo = (RunningTaskInfo) iterator.next();
			if (rtInfo.topActivity.getClassName().equals("com.who.onecupafterwork.common.MainTabActivity")) {
				Log.i("running App Matching recommand list", "퇴근 후 Application is running");
				// MainTabActivity.mainTabActivity.finish();
				startActivity(i);
			} else {
				// MainTabActivity.mainTabActivity.finish();
				mBuilder.setContentIntent(contentIntent);
				mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
			}
		}
	}

	private void sendResultNotification(Intent intent, String msg) {
		mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

		String result = intent.getStringExtra("key2"); // 매칭성공한 사람, 매칭 실패한사람 구분
		Log.i("result key 2 check", result);

		DBManager.getInstance().insertAlarm(PropertyManager.getInstance().getUserId(), MESSAGE_RESULT, date);

		Log.i("Get Push Info", "In GCM INTENT METHod");

		Intent i = new Intent(this, MainTabActivity.class);
		if (result.equals("Y")) { // 매칭 성공한사람
			Log.i("GCM Matching Result", "Yes"+result);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			i.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.SUCCESS);
		} else if (result.equals("N")) { // 매칭 실패한사람
			Log.i("GCM Matching Result", "No"+result);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			i.putExtra(MainTabActivity.INVITE_KEY, FrameFragment.FAIL);
		}

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.splash_logo).setContentTitle("추천 그룹 도착")
				.setContentText(MESSAGE_RESULT).setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL);

		ActivityManager activityManager = (ActivityManager) getSystemService(Activity.ACTIVITY_SERVICE);
		List<RunningTaskInfo> info;
		info = activityManager.getRunningTasks(3);
		Iterator iterator = info.iterator();
		if (iterator.hasNext()) {
			RunningTaskInfo rtInfo = (RunningTaskInfo) iterator.next();
			if (rtInfo.topActivity.getClassName().equals("com.who.onecupafterwork.common.MainTabActivity")) {
				Log.i("running App Matching result", "퇴근 후 Application is running");
				// MainTabActivity.mainTabActivity.finish();
				startActivity(i);
			} else {
				// MainTabActivity.mainTabActivity.finish();
				mBuilder.setContentIntent(contentIntent);
				mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
			}
		}
	}
}
