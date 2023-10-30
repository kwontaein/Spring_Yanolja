package com.example.yanolja.main.post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReserveResponse {

	private int roomid;
	private int user_phone;
	private String hotelname;
	private String rentalType;
	private String roomname;
	private String user_name;
	private String order_number;
	private LocalDateTime reserveDate;
	private LocalDateTime reserveEndDate;
	private String reserveDate2;
	private String reserveEndDate2;
	private int reserveCnt;
	private boolean hasRoom; // 크거나 같은지 여부를 저장하는 변수

	// reserve_possible
	public ReserveResponse(LocalDateTime reserveDate, int reserveCnt) {
		super();
		this.reserveDate = reserveDate;
		this.reserveCnt = reserveCnt;
	}

	// reserve_possible2
	public ReserveResponse(LocalDateTime reserveDate, int reserveCnt, int compareValue) {
		this.reserveDate = reserveDate;
		this.reserveCnt = reserveCnt;
		this.hasRoom = reserveCnt > compareValue;
	}

	// select_p_Reserve
	public ReserveResponse(String hotelname, String roomname, LocalDateTime reserveDate, LocalDateTime reserveEndDate,
			String order_number) {
		super();
		this.hotelname = hotelname;
		this.roomname = roomname;
		this.reserveDate = reserveDate;
		this.reserveEndDate = reserveEndDate;
		this.order_number = order_number;
	}

	// selectReserve_order
	public ReserveResponse(int roomid, String hotelname, String roomname, LocalDateTime reserveDate, String user_name,
			int user_phone) {
		super();
		this.roomid = roomid;
		this.hotelname = hotelname;
		this.roomname = roomname;
		this.user_name = user_name;
		this.reserveDate = reserveDate;
	}

	// selectForReview
	public ReserveResponse(String hotelname, String rentalType, String roomname, LocalDateTime reserveDate) {
		super();
		this.hotelname = hotelname;
		this.rentalType = rentalType;
		this.roomname = roomname;
		this.reserveDate = reserveDate;
	}

	public int getRoomid() {
		return roomid;
	}

	public String getRentalType() {
		return rentalType;
	}

	public String getOrder_number() {
		return order_number;
	}

	public LocalDateTime getReserveDate() {
		return reserveDate;
	}

	public String getReserveDate2() {
		// 원하는 포맷을 지정합니다. 예: "yyyy-MM-dd"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String reserveDate2 = reserveDate.format(formatter);
		return reserveDate2;
	}

	public LocalDateTime getReserveEndDate() {
		return reserveEndDate;
	}

	public String getReserveEndDate2() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String reserveEndDate2 = reserveEndDate.format(formatter);
		return reserveEndDate2;
	}

	public int getReserveCnt() {
		return reserveCnt;
	}

	public boolean isHasRoom() {
		return hasRoom;
	}

	public String getHotelname() {
		return hotelname;
	}

	public String getRoomname() {
		return roomname;
	}

	public String getUser_name() {
		return user_name;
	}

	@Override
	public String toString() {
		return "ReserveResponse [hotelname=" + hotelname + ", roomname=" + roomname + ", user_name=" + user_name
				+ ", reserveDate=" + reserveDate + ", reserveDate2=" + reserveDate2 + ", reserveCnt=" + reserveCnt
				+ ", hasRoom=" + hasRoom + "]";
	}

}
