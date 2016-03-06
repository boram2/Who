package com.who.onecupafterwork.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBOpenHelper extends SQLiteOpenHelper {

	private final static String DB_NAME = "mydb.db"; // DB 이름
	private final static int DB_VERSION = 1; // DB 버전

	public MyDBOpenHelper(Context context) { // 생성자를 꼭 이렇게 만들어주어야 한다.
		super(context, DB_NAME, null, DB_VERSION); // 컨텍스트, DB 이름, 커서 팩토리, DB 버전
	}

	@Override
	public void onCreate(SQLiteDatabase db) { // 최초로 DB가 생성될 때 호출되는 함수
		
		String sql = "CREATE TABLE " + DBConstant.AlarmTable.TABLE_NAME + "(" 
				+ DBConstant.AlarmTable._ID + " integer PRIMARY KEY autoincrement, "
				+ DBConstant.AlarmTable.USER_ID + " text, "
				+ DBConstant.AlarmTable.MESSAGE + " text, "
				+ DBConstant.AlarmTable.DATE + " text);"; 
		// 테이블 생성. 테이블을 만들고 속성으로 자동으로 1씩 증가하는 기본키 id와 이름, 메세지를 정했다.
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // DB 버전이 올라갔을 때 호출된다. -> 기존의 DB 테이블에서 바뀐 거 처리 등

	}

}
