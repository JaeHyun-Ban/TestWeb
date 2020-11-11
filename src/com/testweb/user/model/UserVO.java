package com.testweb.user.model;

import java.sql.Timestamp;

public class UserVO {
	
	private String id;
	private String password;
	private String name;
	private String phone1;
	private String phone2;
	private String phone3;
	private String email;
	private String eaddr;
	private String addr_basic;
	private String addr_detail;
	private Timestamp regdate;
	
	public UserVO() {}

	public UserVO(String id, String password, String name, String phone1, String phone2, String phone3, String email,
			String eaddr, String addr_basic, String addr_detail, Timestamp regdate) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.email = email;
		this.eaddr = eaddr;
		this.addr_basic = addr_basic;
		this.addr_detail = addr_detail;
		this.regdate = regdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEaddr() {
		return eaddr;
	}

	public void setEaddr(String eaddr) {
		this.eaddr = eaddr;
	}

	public String getAddr_basic() {
		return addr_basic;
	}

	public void setAddr_basic(String addr_basic) {
		this.addr_basic = addr_basic;
	}

	public String getAddr_detail() {
		return addr_detail;
	}

	public void setAddr_detail(String addr_detail) {
		this.addr_detail = addr_detail;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	
	
}