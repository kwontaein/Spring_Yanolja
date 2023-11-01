package com.example.yanolja.reserve.post;

public class CouponResponse {
	private int couponid;
	private int userid;
	private String createdate;
	private String deletedate;
	private int reduceprice;
	private String content;
	private String status;

	public CouponResponse(int couponid, int userid, String createdate, String deletedate, int reduceprice,
			String content, String status) {
		super();
		this.couponid = couponid;
		this.userid = userid;
		this.createdate = createdate;
		this.deletedate = deletedate;
		this.reduceprice = reduceprice;
		this.content = content;
		this.status = status;
	}

	public int getCouponid() {
		return couponid;
	}

	public int getUserid() {
		return userid;
	}

	public String getCreatedate() {
		return createdate;
	}

	public String getDeletedate() {
		return deletedate;
	}

	public int getReduceprice() {
		return reduceprice;
	}

	public String getContent() {
		return content;
	}

	public String getStatus() {
		return status;
	}

}
