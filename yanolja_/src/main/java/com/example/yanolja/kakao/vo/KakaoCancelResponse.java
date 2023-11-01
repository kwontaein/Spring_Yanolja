package com.example.yanolja.kakao.vo;

public class KakaoCancelResponse {
	private String aid; // 요청 고유 번호
	private String tid; // 결제 고유 번호
	private String cid; // 가맹점 코드
	private String status; // 결제 상태
	private String partner_order_id; // 가맹점 주문 번호
	private String partner_user_id; // 가맹점 회원 ID
	private String payment_method_type; // 결제 수단
	private Amount amount; // 결제 금액 정보, 결제 요청 구현할때 이미 구현해놓음
	private ApprovedCancelAmount approved_cancel_amount; // 이번 요청으로 취소된 금액
	private CanceledAmount canceled_amount; // 누계 취소 금액
	private CancelAvailableAmount cancel_available_amount; // 남은 취소 금액
	private String item_name; // 상품 이름
	private String item_code; // 상품 코드
	private int quantity; // 상품 수량
	private String created_at; // 결제 준비 요청 시각
	private String approved_at; // 결제 승인 시각
	private String canceled_at; // 결제 취소 시각
	private String payload; // 취소 요청 시 전달한 값

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPartner_order_id() {
		return partner_order_id;
	}

	public void setPartner_order_id(String partner_order_id) {
		this.partner_order_id = partner_order_id;
	}

	public String getPartner_user_id() {
		return partner_user_id;
	}

	public void setPartner_user_id(String partner_user_id) {
		this.partner_user_id = partner_user_id;
	}

	public String getPayment_method_type() {
		return payment_method_type;
	}

	public void setPayment_method_type(String payment_method_type) {
		this.payment_method_type = payment_method_type;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public ApprovedCancelAmount getApproved_cancel_amount() {
		return approved_cancel_amount;
	}

	public void setApproved_cancel_amount(ApprovedCancelAmount approved_cancel_amount) {
		this.approved_cancel_amount = approved_cancel_amount;
	}

	public CanceledAmount getCanceled_amount() {
		return canceled_amount;
	}

	public void setCanceled_amount(CanceledAmount canceled_amount) {
		this.canceled_amount = canceled_amount;
	}

	public CancelAvailableAmount getCancel_available_amount() {
		return cancel_available_amount;
	}

	public void setCancel_available_amount(CancelAvailableAmount cancel_available_amount) {
		this.cancel_available_amount = cancel_available_amount;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getApproved_at() {
		return approved_at;
	}

	public void setApproved_at(String approved_at) {
		this.approved_at = approved_at;
	}

	public String getCanceled_at() {
		return canceled_at;
	}

	public void setCanceled_at(String canceled_at) {
		this.canceled_at = canceled_at;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public static class ApprovedCancelAmount {

		private int total; // 이번 요청으로 취소된 전체 금액
		private int tax_free; // 이번 요청으로 취소된 비과세 금액
		private int vat; // 이번 요청으로 취소된 부가세 금액
		private int point; // 이번 요청으로 취소된 포인트 금액
		private int discount; // 이번 요청으로 취소된 할인 금액
		private int green_deposit; // 컵 보증금

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public int getTax_free() {
			return tax_free;
		}

		public void setTax_free(int tax_free) {
			this.tax_free = tax_free;
		}

		public int getVat() {
			return vat;
		}

		public void setVat(int vat) {
			this.vat = vat;
		}

		public int getPoint() {
			return point;
		}

		public void setPoint(int point) {
			this.point = point;
		}

		public int getDiscount() {
			return discount;
		}

		public void setDiscount(int discount) {
			this.discount = discount;
		}

		public int getGreen_deposit() {
			return green_deposit;
		}

		public void setGreen_deposit(int green_deposit) {
			this.green_deposit = green_deposit;
		}
	}

	public static class CanceledAmount {

		private int total; // 취소된 전체 누적 금액
		private int tax_free; // 취소된 비과세 누적 금액
		private int vat; // 취소된 부가세 누적 금액
		private int point; // 취소된 포인트 누적 금액
		private int discount; // 취소된 할인 누적 금액
		private int green_deposit; // 컵 보증금

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public int getTax_free() {
			return tax_free;
		}

		public void setTax_free(int tax_free) {
			this.tax_free = tax_free;
		}

		public int getVat() {
			return vat;
		}

		public void setVat(int vat) {
			this.vat = vat;
		}

		public int getPoint() {
			return point;
		}

		public void setPoint(int point) {
			this.point = point;
		}

		public int getDiscount() {
			return discount;
		}

		public void setDiscount(int discount) {
			this.discount = discount;
		}

		public int getGreen_deposit() {
			return green_deposit;
		}

		public void setGreen_deposit(int green_deposit) {
			this.green_deposit = green_deposit;
		}
	}

	public static class CancelAvailableAmount {

		private int total; // 전체 취소 가능 금액
		private int tax_free; // 취소 가능 비과세 금액
		private int vat; // 취소 가능 부가세 금액
		private int point; // 취소 가능 포인트 금액
		private int discount; // 취소 가능 할인 금액
		private int green_deposit; // 컵 보증금

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public int getTax_free() {
			return tax_free;
		}

		public void setTax_free(int tax_free) {
			this.tax_free = tax_free;
		}

		public int getVat() {
			return vat;
		}

		public void setVat(int vat) {
			this.vat = vat;
		}

		public int getPoint() {
			return point;
		}

		public void setPoint(int point) {
			this.point = point;
		}

		public int getDiscount() {
			return discount;
		}

		public void setDiscount(int discount) {
			this.discount = discount;
		}

		public int getGreen_deposit() {
			return green_deposit;
		}

		public void setGreen_deposit(int green_deposit) {
			this.green_deposit = green_deposit;
		}
	}
}
