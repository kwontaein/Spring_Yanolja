package com.example.yanolja.main.post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookResponse {
	
	private int roomid;
	private String hotelname;
	private String roomname;
	private LocalDateTime reservedate;
	private String reservedate2;
	private String user_name;
	
	public BookResponse(int roomid,String hotelname, String roomname, String user_name, LocalDateTime reservedate) {
		super();
		this.roomid = roomid;
		this.hotelname = hotelname;
		this.roomname = roomname;
		this.user_name = user_name;
		this.reservedate = reservedate;
	}

	public int getRoomid() {
		return roomid;
	}
	
	public String getHotelname() {
		return hotelname;
	}

	public String getRoomname() {
		return roomname;
	}

	public LocalDateTime getReservedate() {
		return reservedate;
	}

	public String getUser_name() {
		return user_name;
	}
	
	public String getReservedate2() {
		// 원하는 포맷을 지정합니다. 예: "yyyy-MM-dd"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String reservedate2 = reservedate.format(formatter);
		return reservedate2;
	}

	@Override
	public String toString() {
		return "BookResponse [hotelname=" + hotelname + ", roomname=" + roomname + ", reservedate=" + reservedate
				+ ", user_name=" + user_name + "]";
	}

}
