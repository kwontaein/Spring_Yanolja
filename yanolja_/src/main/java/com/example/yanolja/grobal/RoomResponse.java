package com.example.yanolja.grobal;

import java.util.Arrays;
import java.util.Base64;

public class RoomResponse {
	private String hotelname;
	private int hotelid;
	private int roomid;
	private int maxManCnt;
	private String roomname;
	private int price;
	private String roominfo;
	private int somke;
	private String booknoti;
	private String roomserve;
	private int defaultmancnt;
	private String bed;
	private int bedcnt;
	private String rentalType;
	private String checkIn;
	private String checkout;
	private int roomcnt;

	private String defaultinfo;

	private String reserveinfo;

	private String somke2;
	int defaultinfoLineCnt;
	int reserveinfoLineCnt;

	private String date1;
	private String date2;

	private String loc;

	private byte[] image;
	private String base64Image; // Base64로 인코딩

	// findRoomDetail
	public RoomResponse(String hotelname, int roomid, int MaxManCnt, String roomname, int price, String roominfo,
			int somke, String booknoti, String roomserve, int defaultmancnt, String bed, int bedcnt, String rentalType,
			String checkIn, String checkout, int roomcnt, String defaultinfo, String reserveinfo) {
		super();

		this.hotelname = hotelname;
		this.roomid = roomid;
		this.maxManCnt = MaxManCnt;
		this.roomname = roomname;
		this.price = price;
		this.roominfo = roominfo;
		this.somke = somke;
		this.booknoti = booknoti;
		this.roomserve = roomserve;
		this.defaultmancnt = defaultmancnt;
		this.bed = bed;
		this.bedcnt = bedcnt;
		this.rentalType = rentalType;
		this.checkout = checkout;
		this.checkIn = checkIn;
		this.roomcnt = roomcnt;
	}

	// findRoomBydate
	public RoomResponse(int roomid, int MaxManCnt, String roomname, int price, String roominfo, int somke,
			String booknoti, String roomserve, int defaultmancnt, String bed, int bedcnt, String checkIn, int roomcnt,
			String rentalType, byte[] image) {
		super();
		this.roomid = roomid;
		this.maxManCnt = MaxManCnt;
		this.roomname = roomname;
		this.price = price;
		this.roominfo = roominfo;
		this.somke = somke;
		this.booknoti = booknoti;
		this.roomserve = roomserve;
		this.defaultmancnt = defaultmancnt;
		this.bed = bed;
		this.bedcnt = bedcnt;
		this.checkIn = checkIn;
		this.roomcnt = roomcnt;
		this.rentalType = rentalType;
		this.image = image;
	}

	// 카트 정보
	public RoomResponse(String hotelname, int hotelid, String rentalType, String loc, int roomid, int maxManCnt,
			String roomname, int price, int defaultmancnt, String checkIn, String checkout) {
		super();
		this.hotelname = hotelname;
		this.hotelid = hotelid;
		this.rentalType = rentalType;
		this.loc = loc;
		this.roomid = roomid;
		this.maxManCnt = maxManCnt;
		this.roomname = roomname;
		this.price = price;
		this.defaultmancnt = defaultmancnt;
		this.checkIn = checkIn;
		this.checkout = checkout;
	}

	public byte[] getImage() {
		return image;
	}

	public String getBase64Image() {
		base64Image = Base64.getEncoder().encodeToString(image);
		return base64Image;
	}

	public RoomResponse() {
		// TODO Auto-generated constructor stub
	}

	public int getHotelid() {
		return hotelid;
	}

	public int getRoomid() {
		return roomid;
	}

	public int getMaxManCnt() {
		return maxManCnt;
	}

	public String getRoomname() {
		return roomname;
	}

	public int getPrice() {
		return price;
	}

	public String getRoominfo() {
		return roominfo;
	}

	public int getSomke() {

		return somke;
	}

	public String getSomke2() {
		if (somke == 0) {
			somke2 = "금연객실";
		} else {
			somke2 = "흡연객실";
		}
		return somke2;
	}

	public String getBooknoti() {
		return booknoti;
	}

	public String getRoomserve() {
		return roomserve;
	}

	public int getDefaultmancnt() {
		return defaultmancnt;
	}

	public String getBed() {
		return bed;
	}

	public int getBedcnt() {
		return bedcnt;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public int getRoomcnt() {
		return roomcnt;
	}

	public String getHotelname() {
		return hotelname;
	}

	public String getRentalType() {
		return rentalType;
	}

	public String getCheckout() {
		return checkout;
	}

	public int getReserveinfoLineCnt() {
		String[] lines = reserveinfo.split("\\n");
		return lines.length;
	}

	public int getDefaultinfoLineCnt() {
		String[] lines = defaultinfo.split("\\n");
		return lines.length;
	}

	public String getDefaultinfo() {
		defaultinfo = defaultinfo.replaceAll("\\n", "</div>" + "<div>");
		return defaultinfo;
	}

	public String getReserveinfo() {
		reserveinfo = reserveinfo.replaceAll("\\n", "</div>" + "<div>");
		return reserveinfo;
	}

	public String getDate1() {
		return date1;
	}

	public String getDate2() {
		return date2;
	}

	public String getLoc() {
		return loc;
	}

	// ------------------------------------------------------
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}

	public void setHotelid(int hotelid) {
		this.hotelid = hotelid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public void setMaxManCnt(int maxManCnt) {
		this.maxManCnt = maxManCnt;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setDefaultmancnt(int defaultmancnt) {
		this.defaultmancnt = defaultmancnt;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public void setRentalType(String rentalType) {
		this.rentalType = rentalType;
	}

}
