package com.who.onecupafterwork.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Area {
	public String areaName;
	public String areaNumber;

	public Area(String areaName, String areaNumber) {
		this.areaName = areaName;
		this.areaNumber = areaNumber;
	}

	public static final ArrayList<Area> getAreaList() {
		ArrayList<Area> arraylist = new ArrayList<Area>();
		Area area = new Area("서울", "A004001");
		arraylist.add(area);
		area = new Area("경기", "A004002");
		arraylist.add(area);
		area = new Area("인천", "A004003");
		arraylist.add(area);
		area = new Area("대전", "A004004");
		arraylist.add(area);
		area = new Area("광주", "A004005");
		arraylist.add(area);
		area = new Area("대구", "A004006");
		arraylist.add(area);
		area = new Area("울산", "A004007");
		arraylist.add(area);
		area = new Area("부산", "A004008");
		arraylist.add(area);
		area = new Area("제주", "A004009");
		arraylist.add(area);
		
		return arraylist;
	}
	
	public static final Map<String,String> getAreaMap(){
		Map<String,String> map = new HashMap<>();
		
		map.put("A004001", "서울");
		map.put("A004002", "경기");
		map.put("A004003", "인천");
		map.put("A004004", "대전");
		map.put("A004005", "광주");
		map.put("A004006", "대구");
		map.put("A004007", "울산");
		map.put("A004008", "부산");
		map.put("A004009", "제주");
		
		return map;
	} 

	@Override
	public String toString() {
		return areaName;
	}

	public String getNumber() {
		return areaNumber;
	}

}
