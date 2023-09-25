package com.example.yanolja.main.post;

public class InfoResponse {
	private String infoName;
	private String infoDetail;
	private String infoDetail2;
	
	public InfoResponse(String infoName, String infoDetail) {
		super();
		this.infoName = infoName;
		this.infoDetail = infoDetail;
	}

	public String getInfoName() {
		return infoName;
	}

	public String getInfoDetail() {
		return infoDetail;
	}
	
	public String getInfoDetail2() {
		infoDetail2=infoDetail.replaceAll("\\n", "</div><div>");
		return infoDetail2;
	}
}
