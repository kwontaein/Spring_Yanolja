package com.example.yanolja.main.post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReserveResponse {

	private LocalDateTime reserveDate;
	private String reserveDate2;
	private int reserveCnt;
	private boolean hasRoom; // 크거나 같은지 여부를 저장하는 변수

	public ReserveResponse(LocalDateTime reserveDate, int reserveCnt) {
		super();
		this.reserveDate = reserveDate;
		this.reserveCnt = reserveCnt;
	}

	public ReserveResponse(LocalDateTime reserveDate, int reserveCnt, int compareValue) {
		this.reserveDate = reserveDate;
		this.reserveCnt = reserveCnt;
		this.hasRoom = reserveCnt > compareValue;
	}

	public String getReserveDate2() {
		// 원하는 포맷을 지정합니다. 예: "yyyy-MM-dd"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String reserveDate2 = reserveDate.format(formatter);
		return reserveDate2;
	}

	public LocalDateTime getReserveDate() {
		return reserveDate;
	}

	public int getReserveCnt() {
		return reserveCnt;
	}

	public boolean isHasRoom() {
		return hasRoom;
	}

	@Override
	public String toString() {
		return "ReserveResponse [reserveDate=" + reserveDate + ", reserveCnt=" + reserveCnt + "]";
	}
}
