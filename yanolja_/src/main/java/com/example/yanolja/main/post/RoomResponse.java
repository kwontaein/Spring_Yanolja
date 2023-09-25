package com.example.yanolja.main.post;

public class RoomResponse {
	private String hotelname;
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

	public RoomResponse(int roomid, int MaxManCnt, String roomname, int price, String roominfo, int somke,
			String booknoti, String roomserve, int defaultmancnt, String bed, int bedcnt, String checkIn, int roomcnt, String rentalType) {
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

}
