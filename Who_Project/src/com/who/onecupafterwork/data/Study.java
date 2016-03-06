package com.who.onecupafterwork.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Study {
	
	public String studyName;
	public String studyNumber;

	public Study(String studyName, String studyNumber) {
		this.studyName = studyName;
		this.studyNumber = studyNumber;
	}

	public static final ArrayList<Study> getStudyList() {
		ArrayList<Study> studyList = new ArrayList<Study>();

		Study study = new Study("역사", "A003001");
		studyList.add(study);
		study = new Study("철학", "A003002");
		studyList.add(study);
		study = new Study("IT사이언스", "A003003");
		studyList.add(study);
		study = new Study("문학", "A003004");
		studyList.add(study);
		study = new Study("기계공학", "A003005");
		studyList.add(study);
		study = new Study("창업", "A003006");
		studyList.add(study);
		study = new Study("심리학", "A003007");
		studyList.add(study);

		return studyList;
	}

	public static final Map<String, String> getStudyMap() {
		Map<String, String> map = new HashMap<>();

		map.put("A003001", "역사");
		map.put("A003002", "철학");
		map.put("A003003", "IT사이언스");
		map.put("A003004", "문학");
		map.put("A003005", "기계공학");
		map.put("A003006", "창업");
		map.put("A003006", "심리학");

		return map;
	}

	@Override
	public String toString() {
		return studyName;
	}

	public String getNumber() {
		return studyNumber;
	}
}
