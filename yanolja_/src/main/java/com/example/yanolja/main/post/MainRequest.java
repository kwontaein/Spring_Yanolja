package com.example.yanolja.main.post;

public class MainRequest {
	private Long hotelid; // PK
	private String hotelname; // 제목
	private String hotelcall; // 내용
	private String notification;
	private String kindhotel;
	private String status;
	private int price;

	public Long getHotelid() {
		return hotelid;
	}

	public void setHotelid(Long hotelid) {
		this.hotelid = hotelid;
	}

	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		
		this.hotelname = hotelname;
	}

	public String getHotelcall() {
		return hotelcall;
	}

	public void setHotelcall(String hotelcall) {
		this.hotelcall = hotelcall;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getKindhotel() {
		return kindhotel;
	}

	public void setKindhotel(String kindhotel) {
		this.kindhotel = kindhotel;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}