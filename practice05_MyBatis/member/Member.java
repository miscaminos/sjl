package com.encore.member;

import java.sql.Date;

public class Member {
	
	private String id;//C0001 - customer, E0001 - employee, admin - administrator
	private String name;
	private String password;
	private String passportnum;
	private String type;
	private Date login_time;
	//Customer, Employee, Administrator 
	
	public Member() {
		
	}
	
	public Member(String id, String name, String password, String passportnum, String type, Date login_time) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.passportnum = passportnum;
		this.type = type;
		this.login_time = login_time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassportnum() {
		return passportnum;
	}

	public void setPassportnum(String passportnum) {
		this.passportnum = passportnum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", password=" + password + ", passportnum=" + passportnum
				+ ", type=" + type + ", login_time=" + login_time + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		
		if(arg0!=null && arg0 instanceof Member) {
			if(((Member)arg0).getId()==id) {
				return true;
			}
		}
		return false;
	}
}
