package com.nishant.QuizDemo.utils;

import java.util.*;
import java.lang.*;
import java.text.SimpleDateFormat;

public class Utility {

	// function to sort hashmap by values
	public static HashMap<String, Long> sortByValue(HashMap<String, Long> hm) {
		// Create a list from elements of HashMap
		List<Map.Entry<String, Long>> list = new LinkedList<Map.Entry<String, Long>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
			public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<String, Long> temp = new LinkedHashMap<String, Long>();
		for (Map.Entry<String, Long> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	} 

	public static String minTohhmmss(long min) {
		String hhmmss = "";
		long hr = min/60;
		min = min%60;
		return String.format("%02d", hr) +":"+String.format("%02d", min)+":00";
	}
	public static String hhmmTohhmmss(String time) {
		time = time+":00";
//		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss"); // HH for 0-23
//		hhmmss = df.fo(time);
//		
//		
//		if(time.isEmpty())
//			return secTohhmmss(0);
//		int sec =0;
//		String[] tmp = time.split(":");
//		System.out.println("here nishant"+tmp[0]);
//		
//		sec = sec + (Integer.parseInt(tmp[0])*3600);
//		if(tmp.length>=2)
//			sec = sec+(Integer.parseInt(tmp[1])*60);
//		return secTohhmmss(sec);
		return time;
		
	}
	public static String addhhmmss(String startTime,String allocatedTime) {
		int endTime=0;
		endTime = hhmmssToSec(startTime) + hhmmssToSec(allocatedTime);
		return minTohhmmss(endTime/60);
		
	}
	public static int hhmmssToSec(String time) {
		int timeSec=0;
		String[] timeArr = time.split(":");
		timeSec = (Integer.parseInt(timeArr[0])*3600) +
				(Integer.parseInt(timeArr[1])*60) +
				(Integer.parseInt(timeArr[2])) ;
		return timeSec;
		
	}
}
