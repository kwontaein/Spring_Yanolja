<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
												<span>적용 가능한 쿠폰 없음</span>
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
											<input type="radio" checked="checked" name="selectcoupon" value="no_select" onclick="showSelectedValue(this)">
											적용 안함
										</div>
										<div class="coupon_selected">
											<span>
												<input type="radio" name="selectcoupon" value="select" onclick="showSelectedValue(this)">
												쿠폰
											</span>
											<span>
												적용 가능한 쿠폰 :
												<span>없음</span>
											</span>
										</div>
										<div class="choose_coupon" id="choose_coupon">
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
							<input type="radio" name="coupon" id="coupon${couponnumber.index}">${cp.reduceprice}원
							할인
						</div>
						<div>&nbsp;${cp.content}</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>