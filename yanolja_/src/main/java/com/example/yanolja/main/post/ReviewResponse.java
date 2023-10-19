package com.example.yanolja.main.post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Locale;

public class ReviewResponse {
	private String hotelname;
	private String kindhotel;
	private String rentalType;
	private String username;
	private String roomname;

	private String reviewcontent;
	private LocalDateTime ratingdate;
	private String ratingdate2;

	private byte[] image;
	private String base64Image; // Base64로 인코딩
	private int userid;

	private boolean shouldMaskUsername;

	private int review_cnt;
	private float rating;
	private float kindness;
	private float convenience;
	private float cleanliness;
	private float loc_satisfy;

	// review
	public ReviewResponse(float rating, String username, String roomname, String reviewcontent,
			LocalDateTime ratingdate, byte[] image, int userid) {
		super();
		this.rating = rating;
		this.username = username;
		this.roomname = roomname;
		this.reviewcontent = reviewcontent;
		this.ratingdate = ratingdate;
		this.image = image;
		this.userid = userid;
		this.shouldMaskUsername = true;
	}

	// rating
	public ReviewResponse(int review_cnt, float rating, float kindness, float convenience, float cleanliness,
			float loc_satisfy) {
		super();
		this.review_cnt = review_cnt;
		this.rating = rating;
		this.kindness = kindness;
		this.convenience = convenience;
		this.cleanliness = cleanliness;
		this.loc_satisfy = loc_satisfy;
	}

	// userbyreview
	public ReviewResponse(float rating, String username, String hotelname, String kindhotel, String roomname,
			String rentalType, String reviewcontent, LocalDateTime ratingdate, byte[] image) {
		super();
		this.rating = rating;
		this.username = username;
		this.hotelname = hotelname;
		this.kindhotel = kindhotel;
		this.roomname = roomname;
		this.rentalType = rentalType;
		this.reviewcontent = reviewcontent;
		this.ratingdate = ratingdate;
		this.image = image;
		this.shouldMaskUsername = false;
	}

	public String getRentalType() {
		return rentalType;
	}

	public String getKindhotel() {
		return kindhotel;
	}

	public String getHotelname() {
		return hotelname;
	}

	public String getUsername() {
		if (shouldMaskUsername && username != null && username.length() >= 2) {
			String firstTwoChars = username.substring(0, 2);
			String asterisks = "*".repeat(username.length() - 2);
			return firstTwoChars + asterisks;
		}
		return username;
	}

	public String getBase64Image() {
		base64Image = Base64.getEncoder().encodeToString(image);
		return base64Image;
	}

	// 이미지 데이터를 바이트 배열로 반환
	public byte[] getImage() {
		return image;
	}

	public String getRoomname() {
		return roomname;
	}

	public String getReviewcontent() {
		return reviewcontent;
	}

	public int getReview_cnt() {
		return review_cnt;
	}

	public float getRating() {
		return rating;
	}

	public float getKindness() {
		return kindness;
	}

	public float getConvenience() {
		return convenience;
	}

	public float getCleanliness() {
		return cleanliness;
	}

	public float getLoc_satisfy() {
		return loc_satisfy;
	}

	public LocalDateTime getRatingdate() {
		return ratingdate;
	}

	public String getRatingdate2() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY.MM.dd", Locale.KOREAN);
		String ratingdate2 = ratingdate.format(formatter);
		return ratingdate2;
	}

	@Override
	public String toString() {
		return "ReviewResponse [username=" + username + ", roomname=" + roomname + ", reviewcontent=" + reviewcontent
				+ ", ratingdate=" + ratingdate + ", ratingdate2=" + ratingdate2 + ", review_cnt=" + review_cnt + "]";
	}

}
