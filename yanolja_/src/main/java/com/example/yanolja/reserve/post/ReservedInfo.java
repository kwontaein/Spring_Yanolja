package com.example.yanolja.reserve.post;

public class ReservedInfo {
	private String reservePrice;
	private String orderNumber;
	private String kakaoTid;

	public ReservedInfo(String reservePrice, String orderNumber, String kakaoTid) {
		super();
		this.reservePrice = reservePrice;
		this.orderNumber = orderNumber;
		this.kakaoTid = kakaoTid;
	}

	public String getReservePrice() {
		return reservePrice;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public String getKakaoTid() {
		return kakaoTid;
	}

}
