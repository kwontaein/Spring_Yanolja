package com.example.yanolja.user.vo;

import java.sql.Timestamp;

import lombok.Getter;

@Getter
public class UserVo {

	private String name;
	private String email;
	private String password;
	private Timestamp createdate;
	private Timestamp modifydate;
	private String phone;
	private String masteryn;

	public UserVo(String name, String email, String password, Timestamp createdate, Timestamp modifydate, String phone,
			String masteryn) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.createdate = createdate;
		this.modifydate = modifydate;
		this.phone = phone;
		this.masteryn = masteryn;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMasteryn() {
		return masteryn;
	}

	public void setMasteryn(String masteryn) {
		this.masteryn = masteryn;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
