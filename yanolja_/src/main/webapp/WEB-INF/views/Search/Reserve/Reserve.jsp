<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/Reserve/Reserve.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="${path}/js/places/Reserve.js"></script>
<script>
	var totalPrice = 0; // 전체 가격을 저장할 변수
</script>
</head>
<body>
	<div class="ReserveContainer">
		<header class="Reserveheader">
			<div class="headerdiv">
				<span>
					<a href='javascript:window.close();' style="color: black; text-decoration: none;">&lt;</a>
				</span>
				<span>예약</span>
				<span></span>
			</div>
		</header>
		<div class="ReserveWrapper">
			<div class="ReserveContent">
				<div class="ContentWrapper">
					<c:if test="${empty username}">
						<div class="loginbenefit">
							<div class="gologin">
								<a href="/tologin">로그인하고 혜택 받기></a>
							</div>
							<div class="loginbenefitinfo">
								<b>할인</b>과 <b>적립 혜택</b>을 받을 수 있습니다
							</div>
						</div>
					</c:if>
					<div class="infoContatiner">
						<div class="hotelkind">숙소</div>
						<div class="freecancel">
							<span>예약 완료 후 무료 취소 안내</span>
							<span id="seemore">▽</span>
						</div>
						<div class="more-content" style="display: none;">
							<div>
								<ul class="cntul">
									<li>예약일시 기준 체크인 시각 이전일 경우 무료취소가 가능합니다.</li>
									<li>숙소 정책에 따라 일부 상품은 무료취소가 불가능합니다.</li>
								</ul>
							</div>
							<div class="more-detail">
								<span>호텔/펜션/게하</span>
								<div>
									<b>10분 이내 무료 취소</b>
									<p>(단, 숙소 정책에 따라 취소수수료 부가 예외 규정이 적용되지 않을 수 있습니다.)</p>
								</div>
							</div>
						</div>
						<c:choose>
							<c:when test="${room2 != null}">
								<c:set var="prevHotelName" value="" />
								<c:forEach items="${room2}" var="room" varStatus="loop">
									<div class="hotelcontent">
										<script>
											$(document).ready(
												function() {
												
												var price = '${room.price}';

												var date1Str = '${room.date1}';
												var date2Str = '${room.date2}';
												// Date 객체로 변환
												var date1 = new Date(date1Str);
												var date2 = new Date(date2Str);
												var timeDifference = date2 - date1;

												var daysDifference = timeDifference / (1000 * 60 * 60 * 24);

												var totalPriceForRoom = daysDifference * price;
												totalPrice += totalPriceForRoom;

												document.getElementById('date${loop.index}').textContent = daysDifference + '박'; // 결과를 원하는 DOM 요소에 표시
												document.getElementById('totalpricea${loop.index}').textContent = daysDifference * price; // 결과를 원하는 DOM 요소에 표시
												document.getElementById('totalprice').textContent = totalPrice; // 결과를 원하는 DOM 요소에 표시
												
												document.getElementById("totalprice3").textContent = totalPrice +'원';
												
												document.getElementById("discount").textContent = totalPrice / 200;
												document.getElementById("discount2").textContent = totalPrice / 200;
												document.getElementById("discount3").textContent = totalPrice / 200;
												
												document.getElementById("paymentprice").textContent = totalPrice + '원';
											});

												
										</script>
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
										<div class="price">
											<span class="pdate">${room.rentalType}
												/
												<span id="date${loop.index}"></span>
											</span>
											&nbsp;
											<span class="tprice">
												<b id="totalpricea${loop.index}"></b>원
											</span>
										</div>
										<div class="freepriod">무료취소는 예약일로부터 3일 전까지만 가능합니다. 그 이후엔 수수료가 발생할 수 있습니다.</div>
										<div class="comeway">방문수단 선택</div>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
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
									<div class="price">
										<span class="pdate">${room.rentalType}
											/
											<span id="date"></span>
										</span>
										&nbsp;
										<span class="tprice">
											<b id="totalpricea"></b>원
										</span>
									</div>
									<div class="freepriod">무료취소는 예약일로부터 3일 전까지만 가능합니다. 그 이후엔 수수료가 발생할 수 있습니다.</div>
									<div class="comeway">방문수단 선택</div>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="infoContatiner">
						<div class="infotitle">예약자 정보*</div>
						<div class="name">
							<div>
								<label for="nameinput">성명</label>
							</div>
							<div>
								<input name="name" id="nameinput" type="text" placeholder="성명을 입력해 주세요">
							</div>
						</div>
						<div class="Num">
							<div>휴대폰 번호</div>
							<c:choose>
								<c:when test="${empty username}">
									<button class="phonecheck">인증하기</button>
								</c:when>
								<c:otherwise>
									<div class="hasnamephone">
										<input type="text" id="phoneinput" name="phone" value="${phone}" readonly />
										<div>
											<button>번호 변경</button>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="infoContatiner">
						<div class="infotitle">이용자 정보*</div>
						<div class="title_info">상품 이용 시 필요한 필수 정보입니다</div>
						<div class="sameperson">
							<input id="samePersonCheckbox" type="checkbox" onclick="handleCheckboxChange()">&nbsp;예약자 정보와 동일합니다
						</div>
						<div class="name">
							<div>성명</div>
							<div>
								<input name="name" id="nameinput2" type="text" placeholder="성명을 입력해 주세요">
							</div>
						</div>
						<div class="Num">
							<div>휴대폰번호</div>
							<div>
								<input name="phone" id="phoneinput2" type="text" placeholder="휴대폰 번호를 입력해 주세요">
							</div>
						</div>
						<div class="relax">입력하신 번호는 안심번호로 변경되어 숙소에 전달됩니다. 단, 안심번호로 처리가 어려운 경우에 한해 제한적으로 개인정보 제공 동의에 근거하여 실제 휴대폰번호가 전달 될 수 있습니다.</div>
					</div>
					<div class="infoContatiner">
						<div class="infotitle">결제 금액</div>
						<div class="bill">
							<span>상품 금액</span>
							<span id="totalprice"></span>
						</div>
						<div class="billborder">
							<div class="bill">
								<span> 할인 금액</span>
								<c:choose>
									<c:when test="${empty username}">
										<span>로그인 후 사용 가능</span>
									</c:when>
									<c:otherwise>
										<span>?원</span>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="bill">
								<span> 포인트</span>
								<c:choose>
									<c:when test="${empty username}">
										<span>로그인 후 사용 가능</span>
									</c:when>
									<c:otherwise>
										<span>?원</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="bill">
							<span> 총 결제 금액</span>
							<span id="totalprice3"></span>
						</div>
						<c:if test="${empty username}">
							<div class="glcontain">
								<button class="goinglogin" onclick="location.href='/tologin'">로그인 후 혜택 받기</button>
							</div>
						</c:if>
					</div>
					<div class="infoContatiner">
						<div class="infotitle">혜택 정보</div>
						<div class="discon">
							<div class="dis1" style="font-size: 14px;">NOL 카드로 야놀자 첫 결제 시, 2만원 청구할인></div>
							<div class="dis2">
								<div>
									<div style="font-size: 14px;">
										로그인시 최대
										<span id="discount" style="font-size: 14px;"></span>
										<b>C</b> 적립예정
									</div>
									<div style="font-size: 12px;">이용 완료 후 후기 작성하면 자동 적립</div>
								</div>
								<div id="dismore">▽</div>
							</div>
							<div class="seemoredis" style="display: none;">
								<div class="points" style="display: flex; align-items: center; justify-content: space-between;">
									<div style="font-size: 14px;">야놀자 코인</div>
									<div style="font-size: 14px;">
										최대
										<span id="discount2"></span>
										<b>C</b> 적립 예정
									</div>
								</div>
								<div class="reason" style="font-size: 12px; display: flex; padding: 20px 0;">
									<div style="flex: 0 0 auto; width: 52px;">후기</div>
									<div style="flex: 1 1 auto;">후기 기본 0.5%</div>
									<div style="flex: 0 0 auto;">
										<span id="discount3"></span>
										<b>C</b>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="infoContatiner">
						<div class="adkko">
							<img src="https://image6.yanolja.com/payment/iXzQDaHnEdJELK5x" alt="결제 혜택 프로모션 메인배너">
						</div>
						<div class="infotitle">결제 수단</div>
						<ul class="credit">
							<li class="kcredit"><img src="https://image6.yanolja.com/payment/KQS5BVf5P2vhdQe0" alt="카카오페이" class="kakaopay"></li>
							<li>토스</li>
							<li>신용카드</li>
							<li>kb</li>
							<li>네이버</li>
							<li>페이코</li>
							<li>휴대폰</li>
							<li>스마일페이</li>
							<li>ssg</li>
							<li>L페이</li>
							<li>계좌이체</li>
						</ul>
						<div class="creditbenefit">
							<div>
								<img src="https://image6.yanolja.com/payment/KQS5BVf5P2vhdQe0" alt="카카오페이" class="kakaopay">
							</div>
							<div class="dicount_content1">
								<div>
									<b>5만원 이상 결제 할인</b>
								</div>
								<div>기간내 1회</div>
							</div>
							<div class="dicount_content2">
								<div>
									<b>2만원 이상 2천원</b>
								</div>
								<div>기간 내 1회</div>
							</div>
						</div>
						<div class="seeallbenefit">
							<button>혜택 전체 보기</button>
						</div>
					</div>
					<div class="infoContatiner">
						<ul class="warning">
							<li class="wli">
								<div class="wtitle">현장결제</div>
								<div>가서 확인해라</div>
							</li>
							<li class="wli1">
								<div class="wtitle">수수료</div>
								<div>붙을지도 모른다</div>
							</li>
							<li class="wli1">
								<div class="wtitle">미성년자</div>
								<div>오지마라</div>
							</li>
						</ul>
						<div>
							<div class="agreetitle">
								<input type="checkbox" name="agreedall" value='selectall' onclick='selectAll(this)'>필수 약관 전체 동의
							</div>
							<div class="agree">
								<input type="checkbox" name="agreed" value="require1" onchange="checkAgreements()">[필수] 1
							</div>
							<div class="agree">
								<input type="checkbox" name="agreed" value="require2" onchange="checkAgreements()">[필수] 2
							</div>
							<c:if test="${empty username}">
								<div class="agree">
									<input type="checkbox" name="agreed" value="require3" onchange="checkAgreements()">[필수] 3
								</div>
								<div class="agree">
									<input type="checkbox" name="agreed" value="norequire4">[선택] 4
								</div>
								<div class="agree">
									<input type="checkbox" name="agreed" value="norequire5">[선택] 5
								</div>
							</c:if>
						</div>
						<div class="explain">규칙 동의 후 결제</div>
						<div>
							<button class="payment" onclick="openPaymentPage()">
								<span id="paymentprice"></span>
								결제하기
							</button>
							<script>
								function openPaymentPage() {
									var usernameInput = document
											.getElementById('nameinput');
									var username = usernameInput.value;
									// 전달하려는 room 데이터 가져오기
									var roomData = {
										hotelname : '${room.hotelname}',
										roomname : '${room.roomname}',
										roomid : '${room.roomid}',
										price : price,
										username : username,
									// 필요한 경우 다른 room 데이터 속성을 추가하세요
									};
									// room 데이터를 JSON 문자열로 변환
									var roomDataJson = JSON.stringify(roomData);
									// URL 매개변수로 전달하기 위해 JSON 문자열을 인코딩
									var roomDataUrlEncoded = encodeURIComponent(roomDataJson);
									// room 데이터를 쿼리 매개변수로 포함한 URL 구성
									var paymentPageUrl = 'KakaoPayPage?roomData='
											+ roomDataUrlEncoded;
									// 해당 URL로 새 창 열기
									window.open(paymentPageUrl, '_blank');
								}
							</script>
						</div>
						<div class="explain2">(주)야놀자는 통신판매중개업자로서, 통신판매의 당사자가 아니라는 사실을 고지하며 상품의 결제, 이용 및 환불 등과 관련한 의무와 책임은 각 판매자에게 있습니다.</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>