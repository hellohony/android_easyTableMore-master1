package bean;

import java.io.Serializable;

public class Course implements Serializable {

	// ѧ�š�ѧ�� ������������������Ҫ��Ϊʲôû�������أ���Ϊ�ܼ򵥣�ѧ�ž������룬��������
	// �γ�id���γ�������ʦ���ص㡢����----�γ�id��Ӧ���Ͽε������
	private String account;
	private int courseId;
	private String name;
	private String teacher;
	private String place;
	private int term;
	private String week;



	public Course(String account, int courseId, String name, String teacher,
			String place, int term, String week) {
		super();
		this.account = account;
		this.courseId = courseId;
		this.name = name;
		this.teacher = teacher;
		this.place = place;
		this.term = term;
		this.week = week;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", name=" + name + ", teacher="
				+ teacher + ", place=" + place + ", term=" + term + ", week="
				+ week + "]";
	}

}
