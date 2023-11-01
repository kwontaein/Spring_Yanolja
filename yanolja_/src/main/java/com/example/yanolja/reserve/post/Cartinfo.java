package com.example.yanolja.reserve.post;

public class Cartinfo {
	// 장바구니 정보 담을 변수
	private int roomid;
	private String date1;
	private String date2;

	public Cartinfo(int roomid, String date1, String date2) {
		super();
		this.roomid = roomid;
		this.date1 = date1;
		this.date2 = date2;
	}

	public int getRoomid() {
		return roomid;
	}

	public String getDate1() {
		return date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	@Override
	public String toString() {
		return "Cartinfo [roomid=" + roomid + ", date1=" + date1 + ", date2=" + date2 + "]";
	}


}
