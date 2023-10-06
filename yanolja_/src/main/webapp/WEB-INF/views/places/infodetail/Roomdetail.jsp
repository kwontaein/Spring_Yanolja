<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="${path}/css/places/RoomDetail.css" rel="stylesheet">
<script src="${path}/js/places/roomDetail.js"></script>
</head>
<body>
	<c:set var="price" value="${room.price}" />
	<script>
		// JSTL을 사용하여 서버 측 데이터를 JavaScript 변수에 할당
		var price = '<c:out value="${price}" />';
		var roomid = "${room.roomid}";
	</script>
	<%@include file="../../../layout/RoomHeader.jsp"%>
	<div class="rdetailContainer">
		<div class="rdetailWrapper">
			<div class="rimageslide">스와이퍼 자리</div>
			<div class="rdetailContent">
				<div class="roomtitle">
					<div class="titletop">
						<div class="name">
							<h2>${room.roomname}</h2>
							<span>공유</span>
						</div>
						<div>${room.roominfo}</div>
						<div>
							<button class="goback" onclick="history.back()">${room.hotelname}&gt;</button>
						</div>
					</div>
					<div class="titlebtm">
						<div>
							<img width="43" height="31" src="https://yaimg.yanolja.com/stay/static/images/pic-theme-member.svg" class="css-19xh2rn">
							<div class="mancnt">
								<div>${room.defaultmancnt}인/</div>
								<div>최대 ${room.maxManCnt}인</div>
							</div>
						</div>
						<div>
							<img width="43" height="31" src="https://yaimg.yanolja.com/stay/static/images/pic-theme-bed.svg" class="css-19xh2rn">
							<div>${room.bed}</div>
						</div>
						<div>
							<img width="43" height="31" src="https://yaimg.yanolja.com/stay/static/images/pic-theme-nonesmoking.svg" alt="금연객실 아이콘" class="css-19xh2rn">
							<div>${room.somke2}</div>
						</div>
					</div>
				</div>
				<div class="reserve">
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
						<div>
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
							<span class="infocontent">${room.rentalType}</span>
							<div class="roominfo">
								<span class="infoname">인원</span>
								<span class="infocontent">${room.defaultmancnt} / ${room.maxManCnt}</span>
								<span class="infoname">체크인</span>
								<span class="infocontent">${room.checkIn}</span>
								<span class="infoname">체크아웃</span>
								<span class="infocontent">${room.checkout}</span>
							</div>
							<c:if test="${compare != null}">
								<div class="cannotrefund">취소 및 환불 불가</div>
							</c:if>
							<div class="${roomprice}">
								<b id="roomprice"></b>
							</div>
							<c:if test="${room.roomcnt <=0}">
								<div class="endreserve">예약마감</div>
							</c:if>
							<div class="roomchoice">
								<button class="${roomchoicebtn}" id="roomchoicebtn">객실 예약하기</button>
							</div>
							<div class="leftroom">남은 객실 ${room.roomcnt}개</div>
						</div>
					</div>
				</div>
				<div class="info">
					<div class="infotitle">기본 정보</div>
					<div class="defaultinfocontent">
						<div>${room.defaultinfo}</div>
					</div>
				</div>
				<c:if test="${room.defaultinfoLineCnt >= 13}">
					<div class="seeallbtn">
						<button id="seedefaultinfo">전체보기</button>
					</div>
				</c:if>
				<div class="notice">
					<div class="noticeitle">예약 공지</div>
					<div class="reserveinfocontent">
						<div>${room.reserveinfo}</div>
					</div>
				</div>
				<c:if test="${room.reserveinfoLineCnt >= 13}">
					<div class="seeallbtn">
						<button id="seereserveinfo">전체보기</button>
					</div>
				</c:if>
				<div class="facil">
					<div class="facilinfo">시설 및 서비스</div>
					<div class="facilsection">
						<div class="facilName">객실구조</div>
						<div class="facilcontent">
							<c:forEach items="${Fc.roomStructure}" var="content">
								<div>${content}</div>
							</c:forEach>
						</div>
					</div>
					<div class="facilsection">
						<div class="facilName">객실구성</div>
						<div class="facilcontent">
							<c:forEach items="${Fc.roomCompose}" var="content">
								<div>${content}</div>
							</c:forEach>
						</div>
					</div>
					<div class="facilsection">
						<div class="facilName">베드구성</div>
						<div class="facilcontent">
							<c:forEach items="${Fc.bedCompose}" var="content">
								<div>${content}</div>
							</c:forEach>
						</div>
					</div>
					<div class="facilsection">
						<div class="facilName">욕조/사우나</div>
						<div class="seeallbtn">
							<button id="seeallfacil">전체보기</button>
						</div>
					</div>
				</div>
				<div class="review"></div>
			</div>
		</div>
	</div>
	<div class="calendar_modal"></div>
	<c:if test="${room.defaultinfoLineCnt >= 13}">
		<div class="defaultinfomodal">
			<div class="dcontainer">
				<div class="dwrapper">
					<header class="title">
						<span class="close">⨉</span>
						<span>기본 정보</span>
						<span> </span>
					</header>
					<div>${room.defaultinfo}</div>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${room.reserveinfoLineCnt >= 13}">
		<div class="reserveinfomodal">
			<div class="rcontainer">
				<div class="rwrapper">
					<header class="title">
						<span class="close">⨉</span>
						<span>예약 공지</span>
						<span> </span>
					</header>
					<div>${room.reserveinfo}</div>
				</div>
			</div>
		</div>
	</c:if>
	<div class="facilmodal">
		<div class="facilmdcontainer">
			<div class="facilmdwrapper">
				<header class="title">
					<span class="close">⨉</span>
					<span>예약 공지</span>
					<span> </span>
				</header>
				<div class="facil">
					<div class="facilmdinfo">시설 및 서비스</div>
					<div class="facilsection">
						<div class="facilmdName">객실구조</div>
						<div class="facilmdcontent">
							<c:forEach items="${Fc.roomStructure}" var="content">
								<div>${content}</div>
							</c:forEach>
						</div>
					</div>
					<div class="facilsection">
						<div class="facilmdName">객실구성</div>
						<div class="facilmdcontent">
							<c:forEach items="${Fc.roomCompose}" var="content">
								<div>${content}</div>
							</c:forEach>
						</div>
					</div>
					<div class="facilsection">
						<div class="facilmdName">베드구성</div>
						<div class="facilmdcontent">
							<c:forEach items="${Fc.bedCompose}" var="content">
								<div>${content}</div>
							</c:forEach>
						</div>
					</div>
					<div class="facilsection">
						<div class="facilmdName">세면도구</div>
						<div class="facilmdcontent">
							<c:forEach items="${Fc.washTool}" var="content">
								<div>${content}</div>
							</c:forEach>
						</div>
					</div>
					<div class="facilsection">
						<div class="facilmdName">기타사항</div>
						<div class="facilmdcontent">
							<c:forEach items="${Fc.etx}" var="content">
								<div>${content}</div>
							</c:forEach>
						</div>
					</div>
					<div class="facilsection">
						<div class="facilmdName">인터넷</div>
						<div class="facilmdcontent">
							<c:forEach items="${Fc.internet}" var="content">
								<div>${content}</div>
							</c:forEach>
						</div>
					</div>
					<div class="facilsection">
						<div class="facilmdName">TV</div>
						<div class="facilmdcontent">
							<c:forEach items="${Fc.tv}" var="content">
								<div>${content}</div>
							</c:forEach>
						</div>
					</div>
					<div class="facilsection">
						<div class="facilmdName">식음료 시설 / 서비스</div>
						<div class="facilmdcontent">
							<c:forEach items="${Fc.drink}" var="content">
								<div>${content}</div>
							</c:forEach>
						</div>
					</div>
					<div class="facilsection">
						<div class="facilmdName">객실 구비 시설</div>
						<div class="facilmdcontent">
							<c:forEach items="${Fc.roomfacility}" var="content">
								<div>${content}</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="reservemodal">
		<div class="rmcontainer">
			<div class="rmwrapper">
				<header class="title">
					<span class="close">⨉</span>
					<span>숙박 예약</span>
					<span> </span>
				</header>
				<div class="period">
					<div>
						<div>
							<div class="check">체크인</div>
							<div id="startDate"></div>
							<div>${room.checkIn}</div>
						</div>
					</div>
					<div>
						<span id="night"></span>
					</div>
					<div>
						<div>
							<div class="check">체크아웃</div>
							<div id="endDate"></div>
							<div>${room.checkout}</div>
						</div>
					</div>
				</div>
				<c:if test="${compare != null}">
					<div class="cannotrefund2">
						<b>취소 및 환불 불가</b>
						<span>i</span>
					</div>
				</c:if>
				<ul class="rminfo">
					<li>· 텔의 경우 예약 완료 시점부터 10분 이내 전액 취소가 가능합니다. (MY야놀자 → 예약 내역)</li>
					<li>· 단, 입실 시간이 경과된 경우 예약완료 후 10분 이내라도 취소 및 환불이 불가합니다.</li>
					<li>· '취소불가'로 표기되더라도 객실별 기본 정보의 취소규정이 '취소가능'으로 제공되는 경우 고객센터를 통해 취소 가능합니다.</li>
				</ul>
				<div class="lastinfo">
					<span>${room.rentalType}
						<span id="rentalday"></span>
					</span>
					<div>
						<span id="totalprice"></span>
					</div>
				</div>
				<div class="btns">
					<button class="cart">장바구니 담기</button>
					<button class="nowreserve" onclick="window.open('http://localhost:8080/Reserve?roomid=${room.roomid}')">바로 예약</button>
				</div>
			</div>
		</div>
	</div>
	<div class='toast' style='display: none'>
		장바구니에 상품이 담겼습니다
		<a href="../cart">장바구니 보기</a>
	</div>
</body>
</html>