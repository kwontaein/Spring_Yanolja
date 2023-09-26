<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>객실 리스트</title>
<link rel="stylesheet" href="${path}/css/places/roomlist.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="${path}/js/places/roomlist.js">
	
</script>
</head>
<body>
	<div class="roomlistContainer">
		<div class="roomlistWrapper">
			<div class="roomset">
				<div class="roomserWrapper">
					<c:choose>
						<c:when test="${sessionScope.selectedStartDate != null}">
							<button class="datechoice" id="datechoice2"></button>
						</c:when>
						<c:otherwise>
							<button class="datechoice" id="datechoice">${currentDate}~${tomorrowDate}·1박</button>
						</c:otherwise>
					</c:choose>
					<div class="manCnt" id="manCnt">인원수 자리</div>
				</div>
			</div>
			<div class="roomview">
				<c:forEach items="${post}" var="room" varStatus="loop">
					<div class="roomlist" <c:if test="${loop.index % 2 == 0}">
            				<c:set var="style">style="border-right: 1px lightgray solid;"</c:set>
 						</c:if><c:if test="${loop.index % 2 != 0}">
            				<c:set var="style">style="border-right: none;"</c:set>
 						</c:if>
         				${style}
         			>
						<div class="roomimg">
							<div>이미지 슬라이드</div>
						</div>
						<a href="/places/roomView?roomid=${room.roomid}">
							<div class="roomtitle">
								<div class="roomname">
									<h3>${room.roomname}</h3>
								</div>
								<div class="roominfo">
									<span>${room.roominfo}</span>
								</div>
							</div>
							<div class="roomdetail">
								<b><span>기준 ${room.defaultmancnt}</span> / <span>최대 ${room.maxManCnt}인</span></b>
								<span> &#183; ${room.bed} ${room.bedcnt}개</span>
								<span> &#183; ${room.somke2}</span>
							</div>
							<c:choose>
								<c:when test="${room.roomcnt > 0}">
									<c:set var="roomprice" value="roomable" />
									<c:set var="roomchoicebtn" value="roomchoicebtnable" />
								</c:when>
								<c:otherwise>
									<c:set var="roomprice" value="roomnoable" />
									<c:set var="roomchoicebtn" value="roomchoicebtnnoable" />
								</c:otherwise>
							</c:choose>
							<div class="${roomprice}">
								<span>${room.rentalType}(${room.checkIn})~</span>
								<span>
									<b id="pricebydate-${loop.index}"></b>
									<script type="text/javascript">
										// JavaScript 코드에서 가격을 계산하고 출력
										// 이 부분에 해야 forEach문의 각각 부분에 출력 가능
										var totalDate = sessionStorage
												.getItem('totalDate');
										if(totalDate == null){
											totalDate = 1;
										}
										var roomPrice = '${room.price}'; // room.price는 서버 측에서 가져오는 값
										var totalPrice = roomPrice * totalDate;
										document
												.getElementById('pricebydate-${loop.index}').textContent = new Intl.NumberFormat(
												'ko-KR', {
													style : 'currency',
													currency : 'KRW'
												}).format(totalPrice)
												+ '원~ / ' + totalDate + '박';
									</script>
								</span>
							</div>
							<c:if test="${room.roomcnt <=0}">
								<div class="endreserve">예약마감</div>
							</c:if>
							<div class="roomchoice">
								<button class="${roomchoicebtn}" id="roomchoicebtn">객실 선택하기</button>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>
			<div></div>
		</div>
	</div>
	<div class="calendar_modal"></div>
</body>
</html>