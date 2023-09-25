package com.example.yanolja.main.post;

import java.util.Arrays;
import java.util.List;

public class FacilityResponse {
	private String roomStructure;
	private String roomCompose;
	private String bedCompose;
	private String washTool;
	private String etx;
	private String internet;
	private String tv;
	private String drink;
	private String roomfacility;
	private int facilcnt;

	public FacilityResponse(String roomStructure, String roomCompose, String bedCompose, String washTool, String etx,
			String internet, String tv, String drink, String roomfacility) {
		super();
		this.roomStructure = roomStructure;
		this.roomCompose = roomCompose;
		this.bedCompose = bedCompose;
		this.washTool = washTool;
		this.etx = etx;
		this.internet = internet;
		this.tv = tv;
		this.drink = drink;
		this.roomfacility = roomfacility;
	}

	public String getRoomStructure() {
		return roomStructure;
	}

	public String getRoomCompose() {
		return roomCompose;
	}

	public String getBedCompose() {
		return bedCompose;
	}

	public String getWashTool() {
		return washTool;
	}

	public String getEtx() {
		return etx;
	}

	public String getInternet() {
		return internet;
	}

	public String getTv() {
		return tv;
	}

	public String getDrink() {
		return drink;
	}

	public String getRoomfacility() {
		return roomfacility;
	}

	public int getFacilcnt() {
		int totalFacilCount = 0;
		totalFacilCount += getRoomStructureList().size();
		totalFacilCount += getRoomComposeList().size();
		totalFacilCount += getBedComposeList().size();
		totalFacilCount += getWashToolList().size();
		totalFacilCount += getEtxList().size();
		totalFacilCount += getInternetList().size();
		totalFacilCount += getTvList().size();
		totalFacilCount += getDrinkList().size();
		totalFacilCount += getRoomfacilityList().size();

		return totalFacilCount;
	}

	public List<String> getRoomStructureList() {

		return Arrays.asList(roomStructure.split(","));
	}

	public List<String> getRoomComposeList() {
		return Arrays.asList(roomCompose.split(","));
	}

	public List<String> getBedComposeList() {
		return Arrays.asList(bedCompose.split(","));
	}

	public List<String> getWashToolList() {
		return Arrays.asList(washTool.split(","));
	}

	public List<String> getEtxList() {
		return Arrays.asList(etx.split(","));
	}

	public List<String> getInternetList() {
		return Arrays.asList(internet.split(","));
	}

	public List<String> getTvList() {
		return Arrays.asList(tv.split(","));
	}

	public List<String> getDrinkList() {
		return Arrays.asList(drink.split(","));
	}

	public List<String> getRoomfacilityList() {
		return Arrays.asList(roomfacility.split(","));
	}
}
