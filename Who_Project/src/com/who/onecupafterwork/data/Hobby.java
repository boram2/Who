package com.who.onecupafterwork.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hobby {
	public String hobbyName;
	public String hobbyNumber;

	public Hobby(String hobbyName, String hobbyNumber) {
		this.hobbyName = hobbyName;
		this.hobbyNumber = hobbyNumber;
	}

	public static final ArrayList<Hobby> getHobbyList() {
		ArrayList<Hobby> hobbyList = new ArrayList<Hobby>();
		
		Hobby hobby = new Hobby("음악감상", "A002001");
		hobbyList.add(hobby);
		hobby = new Hobby("독서", "A002002");
		hobbyList.add(hobby);
		hobby = new Hobby("헬스", "A002003");
		hobbyList.add(hobby);
		hobby = new Hobby("댄스", "A002004");
		hobbyList.add(hobby);
		hobby = new Hobby("수상레저", "A002005");
		hobbyList.add(hobby);
		hobby = new Hobby("윈터스포츠", "A002006");
		hobbyList.add(hobby);
		hobby = new Hobby("여행", "A002007");
		hobbyList.add(hobby);
		
		return hobbyList;
	}

	public static final Map<String, String> getHobbyMap() {
		Map<String, String> hMap = new HashMap<>();

		hMap.put("A002001", "음악감상");
		hMap.put("A002002", "독서");
		hMap.put("A002003", "헬스");
		hMap.put("A002004", "댄스");
		hMap.put("A002005", "수상레저");
		hMap.put("A002006", "윈터스포츠");
		hMap.put("A002007", "여행");

		return hMap;
	}

	@Override
	public String toString() {
		return hobbyName;
	}

	public String getNumber() {
		return hobbyNumber;
	}
}
