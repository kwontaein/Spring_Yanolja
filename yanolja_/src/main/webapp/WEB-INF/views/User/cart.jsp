<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="${path}/css/Reserve/cart.css" />
<script src="${path}/js/places/cart.js?var=2023-10-10"></script>
</head>
<body>
	<header class="header">
		<div class="head">
			<div class="top">
				<div>
					<a href="javascript:window.history.back();">&lt;</a>
				</div>
				<div>장바구니</div>
				<div>
					<a href="/">홈</a>
				</div>
			</div>
			<c:if test="${Cartroom != null}">
				<div class="mid" id="mid">
					<div class="midcontent">
						<div>전체</div>
						<div>숙소</div>
					</div>
				</div>
				<div class="btm">
					<div>
						<input type="checkbox" id="selectAll" onclick='toggleCheckboxes(this)'>
						전체 선택 (
						<span id="choice_cnt">0</span>
						/
						<span id="cnt_cart"></span>
						)
					</div>
					<div id="deleteSelected">선택 삭제</div>
				</div>
			</c:if>
		</div>
	</header>
	<div class="cartContainer">
		<div class="cartWrapper">
			<div class="contents">
				<c:choose>
					<c:when test="${Cartroom != null}">
						<div class="cartlist">
							<c:set var="prevHotelName" value="" />
							<c:forEach items="${Cartroom}" var="Cartroom" varStatus="loop">
								<!-- 이전 항목의 hotelname과 현재 항목의 hotelname 비교 -->
								<c:if test="${prevHotelName ne Cartroom.hotelname}">
									<!-- 호텔 그룹 시작 -->
									<h3>${Cartroom.hotelname}</h3>
									<div>
										<span>숙소</span>
										|
										<span>${Cartroom.loc}</span>
									</div>
									<c:set var="prevHotelName" value="${Cartroom.hotelname}" />
								</c:if>
								<hr>
								<div class="listdiv">
									<div class="listcheckbox">
										<input type="checkbox" id="check${loop.index}" class="individualCheckbox" name="checkdel" data-roomid="${Cartroom.roomid}">
										<script type="text/javascript">
											$(document)
													.ready(
															function() {
																$(
																		"#del${loop.index}")
																		.click(
																				function() {
																					var selectedIds = []; // 선택된 체크박스의 roomid를 저장할 배열
																					selectedIds
																							.push($(
																									this)
																									.data(
																											"roomid")); // 해당 아이템의 roomid 가져오기
																					deleteCartItem(selectedIds);
																				});

																// date1과 date2를 문자열로 가져오기
																var date1Str = '${Cartroom.date1}';
																var date2Str = '${Cartroom.date2}';

																var price = '${Cartroom.price}';

																// Date 객체로 변환
																var date1 = new Date(
																		date1Str);
																var date2 = new Date(
																		date2Str);

																var timeDifference = date2
																		- date1;

																var daysDifference = timeDifference
																		/ (1000 * 60 * 60 * 24);

																document
																		.getElementById('nights${loop.index}').textContent = '/'
																		+ daysDifference
																		+ '박'; // 결과를 원하는 DOM 요소에 표시
																document
																		.getElementById('price${loop.index}').textContent = daysDifference
																		* price; // 결과를 원하는 DOM 요소에 표시
															});
										</script>
									</div>
									<div class="img">사진 자리</div>
									<div class="htcontent">
										<div class="info">${Cartroom.roomname}</div>
										<div>
											<span>${Cartroom.date1}</span>
											~
											<span>${Cartroom.date2}</span>
											<span id="nights${loop.index}"></span>
										</div>
										<div>
											<span>체크인 ${Cartroom.checkIn}</span>
											|
											<span>체크아웃 ${Cartroom.checkout}</span>
										</div>
										<div>
											<span> 기준 ${Cartroom.defaultmancnt}명</span>
											/
											<span>최대 ${Cartroom.maxManCnt}명</span>
										</div>
										<div class="price">
											<br> <b id="price${loop.index}"></b>원
										</div>
									</div>
									<div class="dellist" id="del${loop.index}" data-roomid="${Cartroom.roomid}">x</div>
								</div>
							</c:forEach>
						</div>
						<div class="goods">
							<div class="goods_content">
								<h3>예약 상품</h3>
							</div>
							<div class="goods_content">
								<span>상품 금액</span>
								<span id="expected_price">0원</span>
							</div>
							<div class="goods_content">
								<span>할인 금액</span>
								<span>-0원</span>
							</div>
							<div class="final_expected goods_content">
								<span>결제 예상 금액</span>
								<span id="expected_price2">0원</span>
							</div>
							<div class="point">
								<div>NOL 카드 결제 시,&nbsp;</div>
								<div style="color: red;">10% 적립</div>
								<div style="color: red;">&nbsp;⊛</div>
							</div>
							<%@include file="../ads/ad4.jsp"%>
							<div>
								<ul style="color: gray; font-size: 12px; padding: 0; list-style: none;">
									<li>• 장바구니에 담긴 상품은 최대 30일간 보관되며 최대 20개의 상품을 담을 수 있습니다.</li>
									<li>• 일부 상품의 경우, 장바구니에서 수량 및 상세 옵션 수정이 불가하므로 삭제 후 다시 담아주시기 바랍니다.</li>
									<li>• 쿠폰 및 포인트는 예약화면에서 적용할 수 있습니다.</li>
									<li>• 기차는 국내숙소와 '묶음예약'만 가능합니다. 숙소를 제외한 다른 상품은 기차와 함께 예약할 수 없습니다.</li>
									<li>• 기차와 국내숙소를 '묶음예약'하면, 일부 상품만 취소할 수 없고 전체 취소만 가능합니다.</li>
								</ul>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="cartlist">
							<h3>장바구니에 담긴 상품이 없습니다!</h3>
							<p>원하는 상품을 담아보세요</p>
							<a href="/">홈으로 가기</a>
						</div>
					</c:otherwise>
				</c:choose>
			</div>

		</div>
	</div>
	<c:if test="${Cartroom != null}">
		<footer class="footer">
			<div class="footcontent">
				<div class="footerprice">
					<div>
						총
						<span id="cnt_pay"></span>
						건
					</div>
					<div class="final_pay">
						<span>결제 예상 금액®&nbsp;</span>
						<div style="font-size: 20px;" id="final_price"></div>
					</div>
				</div>
				<div class="reserve" id="reserve">예약하기</div>
				<div class="more" id="seebottom">(주)야놀자는 통신판매중개업자로서, 통신판매의 당사자가 아니라는 사실을 고지하며 상품의 예약, 이용 및 환불 등과 관련한 의무와 책임은 각 판매자에게 있습니다.</div>
			</div>
		</footer>
	</c:if>
</body>
</html>