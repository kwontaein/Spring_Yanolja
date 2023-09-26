<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	var roomPrice = '${room.price}';
</script>
</head>
<body>
	<div class="ReserveContainer">
		<header class="Reserveheader">
			<div class="headerdiv">
				<span>&lt;</span>
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
							<span>▽</span>
						</div>
						<div class="hotelcontent">
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
									<b id="totalprice"></b>원
								</span>
							</div>
							<div class="freepriod">무료취소는 예약일로부터 3일 전까지만 가능합니다. 그 이후엔 수수료가 발생할 수 있습니다.</div>
							<div class="comeway">방문수단 선택</div>
						</div>
					</div>
					<div class="infoContatiner">
						<div class="infotitle">예약자 정보*</div>
						<div class="infoName">
							<div>
								<label for="nameinput">성명</label>
							</div>
							<div>
								<input id="nameinput" type="text">
							</div>
						</div>
						<div class="infoNum">
							<div>휴대폰 번호</div>
							<c:choose>
								<c:when test="${empty username}">
									<button>인증하기</button>
								</c:when>
								<c:otherwise>
									<div>휴대폰 번호</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="infoContatiner">
						<div class="infotitle">이용자 정보*</div>
						<div class="title_info">상품 이용 시 필요한 필수 정보입니다</div>
						<div class="sameperson">
							<input type="checkbox">예약자 정보와 동일합니다
						</div>
						<div class="name">
							<div>성명</div>
							<div>
								<input id="nameinput2" type="text">
							</div>
						</div>
						<div class="Num">
							<div>휴대폰번호</div>
							<div>
								<input id="phoneinput" type="text">
							</div>
						</div>
						<div class="relax">입력하신 번호는 안심번호로 변경되어 숙소에 전달됩니다. 단, 안심번호로 처리가 어려운 경우에 한해 제한적으로 개인정보 제공 동의에 근거하여 실제 휴대폰번호가 전달 될 수 있습니다.</div>
					</div>
					<div class="infoContatiner">
						<div class="infotitle">결제 금액</div>
						<div class="bill">
							<span>상품 금액</span>
							<span id="totalprice2"></span>
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
										<span>?ㅇ</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="bill">
							<span> 총 결제 금액</span>
							<span id="totalprice3"></span>
						</div>
						<div class="glcontain">
							<button class="goinglogin" onclick="location.href='/tologin'">로그인 후 혜택 받기</button>
						</div>
					</div>
					<div class="infoContatiner">
						<div class="infotitle">혜택 정보</div>
						<div class="discon">
							<div class="dis1">NOL 카드로 야놀자 첫 결제 시, 2만원 청구할인></div>
							<div class="dis2">
								<div>
									<div>
										로그인시 최대
										<span id="discount"></span>
										<b>C</b> 적립예정
									</div>
									<div>이용 완료 후 후기 작성하면 자동 적립</div>
								</div>
								<div>▽</div>
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
								<input type="checkbox">필수 약관 전체 동의
							</div>
							<div class="agree">
								<input type="checkbox">[필수] 1
							</div>
							<div class="agree">
								<input type="checkbox">[필수] 2
							</div>
							<div class="agree">
								<input type="checkbox">[필수] 3
							</div>
							<div class="agree">
								<input type="checkbox">[선택] 4
							</div>
							<div class="agree">
								<input type="checkbox">[선택] 5
							</div>
						</div>
						<div class="explain">규칙 동의 후 결제</div>
						<div>
							<button class="payment"><span id="paymentprice"></span>결제하기</button>
						</div>
						<div class="explain2">(주)야놀자는 통신판매중개업자로서, 통신판매의 당사자가 아니라는 사실을 고지하며 상품의 결제, 이용 및 환불 등과 관련한 의무와 책임은 각 판매자에게 있습니다.</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>