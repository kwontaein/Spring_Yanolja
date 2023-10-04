package com.example.yanolja.main.post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainResponse {
	private Long hotelid;
	private String hotelname;
	private String hotelcall;
	private String notification;
	private float rating;
	private int reviewcount;
	private String kindhotel;
	private LocalDateTime hotelcreate;
	private String hotelcreate2;
	private LocalDateTime hotelmodified;
	private String status;
	private int price;
	private int regionid;
	private int regiondetail;
	private String roadloc;

	public MainResponse(Long hotelid, String hotelname, String hotelcall, String notification, float rating,
			int reviewcount, String kindhotel, LocalDateTime hotelcreate, LocalDateTime hotelmodified, String status,
			int price, int regionid, int regiondetail, String roadloc) {
		super();
		this.hotelid = hotelid;
		this.hotelname = hotelname;
		this.hotelcall = hotelcall;
		this.notification = notification;
		this.rating = rating;
		this.reviewcount = reviewcount;
		this.kindhotel = kindhotel;
		this.hotelcreate = hotelcreate;
		this.hotelmodified = hotelmodified;
		this.status = status;
		this.price = price;
		this.regionid = regionid;
		this.regiondetail = regiondetail;
		this.roadloc = roadloc;
	}

	public Long getHotelid() {
		return hotelid;
	}

	public String getHotelname() {
		return hotelname;
	}

	public String getHotelcall() {
		return hotelcall;
	}

	public String getNotification() {
		return notification;
	}

	public float getRating() {
		return rating;
	}

	public int getReviewcount() {
		return reviewcount;
	}

	public String getKindhotel() {
		return kindhotel;
	}

	public LocalDateTime getHotelmodified() {
		return hotelmodified;
	}

	public String getStatus() {
		return status;
	}

	public int getPrice() {
		return price;
	}

	public int getRegionid() {
		return regionid;
	}

	public int getRegiondetail() {
		return regiondetail;
	}

	public String getRoadloc() {
		return roadloc;
	}

	public String getHotelcreate() {
		DateTimeFormatter newDtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		hotelcreate2 = hotelcreate.format(newDtFormat);
		return hotelcreate2;
	}

	@Override
	public String toString() {
		return "MainResponse [hotelid=" + hotelid + ", hotelname=" + hotelname + ", hotelcall=" + hotelcall
				+ ", notification=" + notification + ", rating=" + rating + ", reviewcount=" + reviewcount
				+ ", kindhotel=" + kindhotel + ", hotelcreate=" + hotelcreate + ", hotelcreate2=" + hotelcreate2
				+ ", hotelmodified=" + hotelmodified + ", status=" + status + ", price=" + price + ", regionid="
				+ regionid + ", regiondetail=" + regiondetail + ", roadloc=" + roadloc + "]";
	}

}