package com.example.yanolja.main.post;

public class BookResponse {
	
	private String hotelname;
	private String roomname;
	private String reservedate;
	private String user_name;
	
	public BookResponse(String hotelname, String roomname, String reservedate, String user_name) {
		super();
		this.hotelname = hotelname;
		this.roomname = roomname;
		this.reservedate = reservedate;
		this.user_name = user_name;
	}

	public String getHotelname() {
		return hotelname;
	}

	public String getRoomname() {
		return roomname;
	}

	public String getReservedate() {
		return reservedate;
	}

	public String getUser_name() {
		return user_name;
	}

	@Override
	public String toString() {
		return "BookResponse [hotelname=" + hotelname + ", roomname=" + roomname + ", reservedate=" + reservedate
				+ ", user_name=" + user_name + "]";
	}

}
