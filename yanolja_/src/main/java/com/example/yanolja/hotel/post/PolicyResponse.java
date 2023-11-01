package com.example.yanolja.hotel.post;

public class PolicyResponse {
	private String policyname;
	private String policycontent;
	
	public PolicyResponse(String policyname, String policycontent) {
		super();
		this.policyname = policyname;
		this.policycontent = policycontent;
	}
	
	public String getPolicyname() {
		return policyname;
	}
	public String getPolicycontent() {
		return policycontent;
	}
}
