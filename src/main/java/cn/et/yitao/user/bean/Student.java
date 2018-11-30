package cn.et.yitao.user.bean;

import java.io.Serializable;

public class Student extends Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1749104997737727217L;
	private String id;
	private String adrr;
	private String classId;

	public Student() {
	}

	private Student(String id) {
		this.setId(id);
	}

	public Student(String name, Integer age, String sex, String id, String adrr, String classid) {
		super(name, age, sex);
		this.id = id;
		this.adrr = adrr;
		this.classId = classid;
	}

	void goToClass() {
		System.out.println("�Ͽ�");
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", adrr=" + adrr + ", classid=" + classId + ", name=" + name + ", age=" + age
				+ ", sex=" + sex + "]";
	}

	public void goToWB() {

	}

	private void play() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdrr() {
		return adrr;
	}

	public void setAdrr(String adrr) {
		this.adrr = adrr;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classid) {
		this.classId = classid;
	}
}
