package com.example.yanolja.hotel.post;

public class TrafficResponse {

	private String roadloc;
	private String roadinfo;
	private String parking;

	public TrafficResponse() {

	}

	public TrafficResponse(String roadloc, String roadinfo, String parking) {
		super();
		this.roadloc = roadloc;
		this.roadinfo = roadinfo;
		this.parking = parking;
	}

	public String getRoadloc() {
		return roadloc;
	}

	public String getRoadinfo() {
		return roadinfo;
	}

	public String getParking() {
		return parking;
	}

	@Override
	public String toString() {
		return "TrafficResponse [roadloc=" + roadloc + ", roadinfo=" + roadinfo + ", parking=" + parking + "]";
	}
}