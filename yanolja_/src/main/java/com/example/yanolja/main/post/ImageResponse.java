package com.example.yanolja.main.post;

import java.util.Base64;

public class ImageResponse {

	private int imgid;
	private int hotelid;
	private int reviewid;
	private byte[] image;
	private String base64Image; // Base64로 인코딩
	private String imgname;

	public ImageResponse(int imgid, int hotelid, int reviewid, byte[] image) {
		super();
		this.imgid = imgid;
		this.hotelid = hotelid;
		this.reviewid = reviewid;
		this.image = image;
	}

	public ImageResponse(int imgid, String imgname, byte[] image) {
		super();
		this.imgid = imgid;
		this.imgname = imgname;
		this.image = image;
	}

	public String getImgname() {
		return imgname;
	}

	public int getImgid() {
		return imgid;
	}

	public int getHotelid() {
		return hotelid;
	}

	public int getReviewid() {
		return reviewid;
	}

	public byte[] getImage() {
		return image;
	}

	public String getBase64Image() {
		base64Image = Base64.getEncoder().encodeToString(image);
		return base64Image;
	}
}
