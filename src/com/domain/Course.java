package com.domain;

public class Course {
	private String course_id;
	private String course_name;
	private String teacher;
	private int point;
	private String time_1;
	private String time_2;
	private String location;
	private int limitied;
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getTime_1() {
		return time_1;
	}
	public void setTime_1(String time_1) {
		this.time_1 = time_1;
	}
	public String getTime_2() {
		return time_2;
	}
	public void setTime_2(String time_2) {
		this.time_2 = time_2;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getLimitied() {
		return limitied;
	}
	public void setLimitied(int limitied) {
		this.limitied = limitied;
	}
	public Course(String course_id, String course_name, String teacher, int point, String time_1, String time_2,
			String location, int limitied) {
		super();
		this.course_id = course_id;
		this.course_name = course_name;
		this.teacher = teacher;
		this.point = point;
		this.time_1 = time_1;
		this.time_2 = time_2;
		this.location = location;
		this.limitied = limitied;
	}
	public Course() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Course [course_id=" + course_id + ", course_name=" + course_name + ", teacher=" + teacher + ", point="
				+ point + ", time_1=" + time_1 + ", time_2=" + time_2 + ", location=" + location + ", limitied="
				+ limitied + "]";
	}
	
	
}
