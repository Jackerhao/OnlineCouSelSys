package com.domain;

public class TimeChange {

	public static String reverse1(String s) {
		String week=null;
		
		switch(Integer.parseInt(s)) {
		case 1: week="����һ";
		break;
		case 2: week="���ڶ�";
		break;
		case 3: week="������";
		break;
		case 4: week="������";
		break;
		case 5: week="������";
		break;
		}
		
		return week;
	}
	
	public static String reverse2(String s) {
		String day=null;
		
		switch(Integer.parseInt(s)) {
		case 1: day="8:05~9:50";
		break;
		case 2: day="10:15~12:00";
		break;
		case 3: day="13:35~15:20";
		break;
		case 4: day="15:45~17:30";
		break;
		case 5: day="18:30~20:45";
		break;
		}
		
		return day;
	}
	
}
