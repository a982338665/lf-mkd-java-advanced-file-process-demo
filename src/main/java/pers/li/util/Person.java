package pers.li.util;

import java.util.Date;

public class Person {
 
	private String name;
	private int age;
	private String job;
	private Integer num;
	private Date createTime;
	private boolean aBoolean;
	private Double aDouble;

	public Integer getNum() {
		return num;
	}

	public Person(String name, int age, String job, Integer num, Date createTime,Double aDouble, boolean aBoolean) {
		this.name = name;
		this.age = age;
		this.job = job;
		this.num = num;
		this.createTime = createTime;
		this.aBoolean = aBoolean;
		this.aDouble = aDouble;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public boolean isaBoolean() {
		return aBoolean;
	}

	public void setaBoolean(boolean aBoolean) {
		this.aBoolean = aBoolean;
	}

	public Double getaDouble() {
		return aDouble;
	}

	public void setaDouble(Double aDouble) {
		this.aDouble = aDouble;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Person(String name, int age, String job) {
		super();
		this.name = name;
		this.age = age;
		this.job = job;
	}
 
	public Person(String name, int age, String job,Date createTime) {
		super();
		this.name = name;
		this.age = age;
		this.job = job;
		this.createTime = createTime;
	}
	public Person(String name, int age, String job,Date createTime,Double aDouble,boolean aBoolean) {
		super();
		this.name = name;
		this.age = age;
		this.job = job;
		this.createTime = createTime;
		this.aBoolean=aBoolean;
		this.aDouble=aDouble;
	}

	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public Integer getAge() {
		return age;
	}
 
	public void setAge(int age) {
		this.age = age;
	}
 
	public String getJob() {
		return job;
	}
 
	public void setJob(String job) {
		this.job = job;
	}
}
