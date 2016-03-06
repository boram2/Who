package com.who.onecupafterwork.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.who.onecupafterwork.database.DBConstant.AlarmTable;
import com.who.onecupafterwork.network.MyApplication;

public class DBManager {
	
	private static DBManager instance; // 싱글톤 사용

	MyDBOpenHelper openHelper;

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() {
		openHelper = new MyDBOpenHelper(MyApplication.getContext());
	}

	public void insertAlarm(String id, String message, String date) {
		SQLiteDatabase db = openHelper.getWritableDatabase(); // DB를 가져옴

		/* 함수 사용하기 */
		ContentValues values = new ContentValues();
		values.put(AlarmTable.USER_ID, id);
		values.put(AlarmTable.MESSAGE, message);
		values.put(AlarmTable.DATE, date);

		db.insert(AlarmTable.TABLE_NAME, null, values); // 함수 사용할 때만
		db.close();
	}
	
	public void deletePerson(int _id) {
		if (_id != -1) {
			SQLiteDatabase db = openHelper.getWritableDatabase();
			String whereClause = AlarmTable._ID + " =?";
			String[] whereArgs = {"" + _id};
			db.delete(AlarmTable.TABLE_NAME, whereClause, whereArgs);
			db.close();
		}
	}

	public ArrayList<AlarmData> getAlarmList() { // 알림 목록을 요청하는 함수
		ArrayList<AlarmData> list = new ArrayList<AlarmData>();
		
		String sql = "select " + DBConstant.AlarmTable._ID + ", "
							   + DBConstant.AlarmTable.USER_ID + ", "
							   + DBConstant.AlarmTable.MESSAGE + ", "
							   + DBConstant.AlarmTable.DATE + " from "
							   + DBConstant.AlarmTable.TABLE_NAME;
		
		SQLiteDatabase db = openHelper.getReadableDatabase();
		Cursor c = db.rawQuery(sql, null); // 커서를 통해 데이터를 가져온다.

		while (c.moveToNext()) { // moveToNext()가 true면 가지고 갈 데이터가 있다는 뜻
			AlarmData p = new AlarmData();
			p._id = c.getLong(c.getColumnIndex(AlarmTable._ID));
			p.userId = c.getString(c.getColumnIndex(DBConstant.AlarmTable.USER_ID));
			p.message = c.getString(c.getColumnIndex(DBConstant.AlarmTable.MESSAGE));
			p.date = c.getString(c.getColumnIndex(DBConstant.AlarmTable.DATE));
			Log.i("db : ", p.userId + " " + p.message + " " + p.date);
			list.add(p);
		}
		
		c.close();
		
		return list;
	}
	
}
