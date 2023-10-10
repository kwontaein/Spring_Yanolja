package com.example.yanolja.kakaoPay.vo;

public class Paymentvo {
	private String hotelname;
	private String roomname;
	private String roomid;
	private String checkIn;
	private String price;
	private String username;

	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Paymentvo [hotelname=" + hotelname + ", roomname=" + roomname + ", roomid=" + roomid + ", checkIn="
				+ checkIn + ", price=" + price + ", username=" + username + "]";
	}
}
