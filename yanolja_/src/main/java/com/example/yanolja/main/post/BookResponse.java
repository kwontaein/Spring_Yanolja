package com.example.yanolja.main.post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookResponse {

	private int bookid;
	private int roomid;
	private String hotelname;
	private String roomname;
	private LocalDateTime reservedate;
	private String reservedate2;
	private LocalDateTime reserveenddate;
	private String reserveenddate2;
	private String user_name;
	private String canreview;
	private boolean reviewYN;
	private int review_cnt;

	public BookResponse(int bookid, int roomid, String hotelname, String roomname, String user_name,
			LocalDateTime reservedate, LocalDateTime reserveenddate, boolean reviewYN) {
		super();
		this.roomid = roomid;
		this.hotelname = hotelname;
		this.roomname = roomname;
		this.user_name = user_name;
		this.reservedate = reservedate;
		this.reserveenddate = reserveenddate;
		this.reviewYN = reviewYN;
	}

	public boolean isReviewYN() {
		return reviewYN;
	}

	public int getBookid() {
		return bookid;
	}

	public int getReview_cnt() {
		return review_cnt;
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

	public String getUser_name() {
		return user_name;
	}

	public LocalDateTime getReservedate() {
		return reservedate;
	}

	public String getReservedate2() {
		// 원하는 포맷을 지정합니다. 예: "yyyy-MM-dd"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String reservedate2 = reservedate.format(formatter);
		return reservedate2;
	}

	public LocalDateTime getReserveenddate() {
		return reserveenddate;
	}

	public String getReserveenddate2() {
		// 원하는 포맷을 지정합니다. 예: "yyyy-MM-dd"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String reserveenddate2 = reserveenddate.format(formatter);
		return reserveenddate2;
	}

	public String getCanreview() {
		// 현재 날짜와 시간 가져오기
		LocalDateTime now = LocalDateTime.now();
		// reservedate에 2주를 더한 날짜 계산
		LocalDateTime twoWeeksLater = reserveenddate.plusWeeks(2);
		if (now.isAfter(twoWeeksLater)) {
			return canreview = "후기 작성 가능 기간이 지났습니다";
		} else if (now.isBefore(reserveenddate)) {
			return canreview = "아직 후기 작성이 불가능합니다.";
		} else if (reviewYN) {
			return canreview = "작성완료";
		} else {
			return canreview = "후기 작성하기";
		}
	}

	@Override
	public String toString() {
		return "BookResponse [hotelname=" + hotelname + ", roomname=" + roomname + ", reservedate=" + reservedate
				+ ", user_name=" + user_name + "]";
	}

}
