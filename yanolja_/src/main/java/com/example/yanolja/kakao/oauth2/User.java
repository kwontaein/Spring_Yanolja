package com.example.yanolja.kakao.oauth2;

import java.sql.Timestamp;

public class User {
	private int userid;
	private String name;
	private String email;
	private String password;
	private Timestamp createdate;
	private Timestamp modifydate;
	private int phone;
	private String masteryn;


	public int getUserid() {
		return userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public Timestamp getModifydate() {
		return modifydate;
	}

	public void setModifydate(Timestamp modifydate) {
		this.modifydate = modifydate;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getMasteryn() {
		return masteryn;
	}

	public void setMasteryn(String masteryn) {
		this.masteryn = masteryn;
	}

}
