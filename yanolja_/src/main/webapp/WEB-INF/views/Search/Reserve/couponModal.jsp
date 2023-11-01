<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
//선택한 쿠폰을 저장할 변수
var selectedCoupon = "";
var cnt;
var reducedPrice = 0;
var totalSelectedCouponPrice = 0;
// 모달 닫기
function closeCcModal() {
	var modal = document.getElementById("couponchild");
	modal.style.display = "none";
}

// 모달 창을 닫고 선택한 쿠폰을 '쿠폰 선택' 부분에 표시하는 함수
function closeCcModal() {
	// '쿠폰 선택' 부분에 선택한 쿠폰을 표시
	if (selectedCoupon) {
		$("#choose_coupon" + cnt).text(selectedCoupon);
	}
	var modal = $("#couponchild");
	// 부모 문서에서 #paymentprice 요소를 찾아 내용 변경

	reducePrice.textContent = totalSelectedCouponPrice +'원';
	if(totalPrice == 0){
		reducedPrice = price - totalSelectedCouponPrice;
	}else{
		reducedPrice = totalPrice - totalSelectedCouponPrice;
	}
	$('#totalprice3').text((reducedPrice) + '원');
    $('#paymentprice').text((reducedPrice)+ '원');
	modal.hide();
	console.log(totalSelectedCouponPrice);
}

// 쿠폰 선택 함수
function selectCoupon(couponIndex) {
	var couponElement = $("#coupon" + couponIndex);
	var coupontext = $("#cc" + couponIndex);
	var couponPrice = parseInt($(couponElement).val()); // 선택한 쿠폰의 가격을 정수로 변환

	var index = couponIndex;
	// 이미 선택된 쿠폰이 있는지 확인
	if (selectedCoupon) {
		if (selectedCoupon === $(coupontext).text()) {
			alert("이미 같은 쿠폰이 선택되었습니다.");
		} else {
			totalSelectedCouponPrice += couponPrice;
			selectedCoupon = $(coupontext).text(); // 선택한 쿠폰 저장
			closeCcModal(); // 모달 창 닫기
		}
	} else {
		totalSelectedCouponPrice = couponPrice;
		selectedCoupon = $(coupontext).text(); // 선택한 쿠폰 저장
		closeCcModal(); // 모달 창 닫기
	}
}

// 모달 열기
function openCcModal(index) {
	var modal = document.getElementById("couponchild");
	modal.style.display = "block";
	if (index != null)
		cnt = index;
}

</script>
<div id="couponModal" class="couponModal">
	<div class="couponModal-content">
		<header class="coupon_header">
			<div class="coupon_header2">
				<span class="close" onclick="closeCModal()">&times;</span>
				<span>쿠폰 할인</span>
				<span></span>
			</div>
		</header>
		<div class="coupon_container">
			<div class="coupon_wrapper">
				<c:choose>
					<c:when test="${room2 != null}">
						<c:set var="prevHotelName" value="" />
						<c:forEach items="${room2}" var="room" varStatus="loop">
							<div class="coupon_hotel">
								<div class="hotelcontent" style="border-right: 1px solid lightgray; padding: 0;">
									<div class="hotelandroom">
										<c:if test="${prevHotelName ne room.hotelname}">
											<div class="hotelname">${room.hotelname}</div>
										</c:if>
										<c:set var="prevHotelName" value="${room.hotelname}" />
										<div class="roomname">${room.roomname}</div>
									</div>
									<div class="period">
										<div class="checkin">
											<div class="cktitle">체크인</div>
											<div class="ckdate">${room.date1}</div>
											<div class="cktime">${room.checkIn}</div>
										</div>
										<div class="checkout">
											<div class="cktitle">체크아웃</div>
											<div class="ckdate">${room.date2}</div>
											<div class="cktime">${room.checkout}</div>
										</div>
									</div>
								</div>
								<div class="select_coupon">
									<div>
										<div class="no_coupon">
											<input type="radio" checked="checked" name="selectcoupon${loop.index}" value="no_select${loop.index}" onclick="showSelectedValues(this, ${loop.index})">
											적용 안함
										</div>
										<div class="coupon_selected">
											<span>
												<input type="radio" name="selectcoupon${loop.index}" value="select${loop.index}" onclick="showSelectedValues(this, ${loop.index})">
												쿠폰
											</span>
											<c:if test="${not empty coupon}">
												<span>적용 가능한 쿠폰 없음</span>
											</c:if>
										</div>
										<div class="choose_coupon" id="choose_coupon${loop.index}">
											<span id="choose_coupon${loop.index}">쿠폰 선택</span>
											<span>▽</span>
										</div>
									</div>
								</div>

							</div>
							<br>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="coupon_hotel">
							<div class="hotelcontent">
								<script>
										var roomPrice = '${room.price}';
										var price = '${room.price}';
									</script>
								<div class="hotelandroom">
									<div class="hotelname">${room.hotelname}</div>
									<div class="roomname">${room.roomname}</div>
								</div>
								<div class="period">
									<div class="checkin">
										<div class="cktitle">체크인</div>
										<div class="ckdate" id="intime"></div>
										<div class="cktime">${room.checkIn}</div>
									</div>
									<div class="checkout">
										<div class="cktitle">체크아웃</div>
										<div class="ckdate" id="outtime"></div>
										<div class="cktime">${room.checkout}</div>
									</div>
								</div>
							</div>
							<div class="select_coupon">
								<div>
									<div class="no_coupon">
										<input type="radio" checked="checked" name="selectcoupon0" value="no_select0" onclick="showSelectedValues(this,0)">
										적용 안함
									</div>
									<div class="coupon_selected">
										<span>
											<input type="radio" name="selectcoupon0" value="select0" onclick="showSelectedValues(this,0)">
											쿠폰
										</span>
										<span>
											적용 가능한 쿠폰 :
											<span>없음</span>
										</span>
									</div>
									<div class="choose_coupon" id="choose_coupon0" onclick="choose_coupon(0)">
										<span>쿠폰 선택</span>
										<span>▽</span>
									</div>
								</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>
<div id="couponchild" class="coupon_child_modal">
	<div class="coupon_child_content">
		<span class="close" onclick="closeCcModal()">&times;</span>
		<div class="cc_wrapper">
			<c:forEach items="${coupon}" varStatus="couponnumber" var="cp">
				<div class="cc_info">
					<div>
						<input type="radio" name="coupon" id="coupon${couponnumber.index}" value="${cp.reduceprice}" onclick="selectCoupon(${couponnumber.index})">
						<label for="coupon${couponnumber.index}">${cp.reduceprice}원 할인</label>
					</div>
					<div id="cc${couponnumber.index}">${cp.content}</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>