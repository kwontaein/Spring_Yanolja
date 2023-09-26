<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${path}/css/auth/mypage.css" />
<link rel="stylesheet" href="${path}/css/Main/html.css" />
<meta charset="UTF-8">
</head>
<body>
	<c:set var="isMypage" value="true" />
	<%@ include file="../../layout/header.jsp"%>
	<div class="mypage_container">
		<div class="mypage">
			<div class="mypage1">
				<div>
					<h2>MY Page</h2>
				</div>
				<div class="tologin">
					<c:choose>
						<c:when test="${empty username}">
							<a href="/tologin" class="tologin2">
								<p>가입하고 놀자, 계산적으로</p>
								<h2>로그인 및 회원가입하기 ></h2>
							</a>
						</c:when>
						<c:otherwise>
							<a href="/myManage" class="myManage">
								<div class="myprofile">
									<img src="//yaimg.yanolja.com/joy/sunny/static/images/my/img-my-profile-50.svg" alt="">
								</div>
								<div>
									<h2>${username}님</h2>
									<span>내정보 관리 </span>
								</div>
							</a>
						</c:otherwise>
					</c:choose>
					<a href="/Mybenefit" class="Mybenefit">
						<div class="myevent">
							<span>MY 혜택</span>
						</div>
					</a>
				</div>

				<div class="myoption">
					<ul class="myinfo">
						<c:choose>
							<c:when test="${empty username}">
								<c:set var="urlPrefix1" value="/tologin" />
								<c:set var="urlPrefix2" value="/tologin" />
								<c:set var="urlPrefix3" value="/tologin" />
								<c:set var="urlPrefix4" value="/tologin" />
								<c:set var="urlPrefix4" value="/tologin" />
							</c:when>
							<c:otherwise>
								<c:set var="urlPrefix1" value="/Point" />
								<c:set var="urlPrefix2" value="/Coin" />
								<c:set var="urlPrefix3" value="/coupon" />
								<c:set var="urlPrefix4" value="/myreview" />
								<c:set var="urlPrefix5" value="/like" />
							</c:otherwise>
						</c:choose>
						<li class="a"><a href="${urlPrefix1}">
								<div class="Recordlink_div">
									<img class="RecordLink_icon__3CqzC" src="//yaimg.yanolja.com/joy/sunny/static/images/my/icon-point-line-1.svg" alt="">
									<span>야놀자 포인트</span>
								</div>
								<div class="RecordLink_right__2qs6s">
									<span class="RecordLink_forwardIcon__2NvgZ"></span>
								</div>
							</a></li>
						<li class="b"><a href="${urlPrefix2}">
								<div class="Recordlink_div">
									<img class="RecordLink_icon__3CqzC" src="//yaimg.yanolja.com/joy/sunny/static/images/my/icon-ycoin-line-1.svg" alt="">
									<span>야놀자 코인</span>
								</div>
								<div class="RecordLink_right__2qs6s">
									<span class="RecordLink_forwardIcon__2NvgZ"></span>
								</div>
							</a></li>
						<li class="c"><a href="${urlPrefix3}">
								<div class="Recordlink_div">
									<img class="RecordLink_icon__3CqzC" src="//yaimg.yanolja.com/joy/sunny/static/images/my/icon-coupon-2-line-1.svg" alt="">
									<span>쿠폰함</span>
								</div>
								<div class="RecordLink_right__2qs6s">
									<span class="RecordLink_forwardIcon__2NvgZ"></span>
								</div>
							</a></li>
						<li class="d"><a href="${urlPrefix4}">
								<div class="Recordlink_div">
									<img class="RecordLink_icon__3CqzC" src="//yaimg.yanolja.com/joy/sunny/static/images/my/icon-review-line-1.svg" alt="">
									<span>나의 후기</span>
								</div>
								<div class="RecordLink_right__2qs6s">
									<span class="RecordLink_forwardIcon__2NvgZ"></span>
								</div>
							</a></li>
						<li class="e"><a href="${urlPrefix5}">
								<div class="Recordlink_div">
									<img class="RecordLink_icon__3CqzC" src="//yaimg.yanolja.com/joy/sunny/static/images/my/icon-favorite-border-line-1.svg" alt="">
									<span>찜</span>
								</div>
								<div class="RecordLink_right__2qs6s">
									<span class="RecordLink_forwardIcon__2NvgZ"></span>
								</div>
							</a></li>
					</ul>
				</div>
			</div>
			<div class="ads">
				<%@ include file="../ads/ad1.jsp"%>
			</div>
			<div class="mypage1">
				<h2>비회원 예약 내역</h2>
				<div class="booklist">
					<div>
						<a>
							<span>국내숙소/놀거리/교통 예약내역 </span>
							<span class="RecordLink_forwardIcon__2NvgZ"></span>
						</a>
					</div>
					<div>
						<a>
							<span>해외숙소 예약내역</span>
							<span class="RecordLink_forwardIcon__2NvgZ"></span>
						</a>
					</div>
				</div>
			</div>
			<div class="ads">
				<%@ include file="../ads/ad2.jsp"%>
				</div>
			<div class="mypage2">
				<div class="event">
					<div>
						<a>
							<img class="MyPageEventMenu_icon__32Ct_" src="//yaimg.yanolja.com/joy/sunny/static/images/my/ic-system-event-36.svg" alt="">
							<span>이벤트</span>
						</a>
					</div>
					<div>
						<a>
							<img class="MyPageEventMenu_icon__32Ct_" src="//yaimg.yanolja.com/joy/sunny/static/images/my/ic-system-exhibitions-36.svg" alt="">
							<span>기획전</span>
						</a>
					</div>
					<div>
						<a>
							<img class="MyPageEventMenu_icon__32Ct_" src="//yaimg.yanolja.com/joy/sunny/static/images/my/ic-system-winner-36.svg" alt="">
							<span>당첨자 발표</span>
						</a>
					</div>
				</div>
			</div>
			<div class="mypage_space"></div>
			<div class="mypage3">
				<div>
					<ul class="callcenter">
						<li class="a"><a>
								<div class="Recordlink_div">
									<h2>
										<img class="MyPageCSInfo_headingIcon__2Eit5" src="//yaimg.yanolja.com/joy/sunny/static/images/icon-helpdesk-line-2.svg">
										고객센터
									</h2>
								</div>
								<div class="Recordlink_div">
									<span> 365일 오전 9시 ~ 익일 오전 3시 운영 </span>
								</div>
								<div class="RecordLink_right__2qs6s">
									<span class="RecordLink_forwardIcon__2NvgZ"></span>
								</div>
							</a></li>
						<li class="c"><a>
								<div class="Recordlink_div">
									<span>카카오톡 1:1 문의</span>
								</div>
								<div class="RecordLink_right__2qs6s">
									<span class="RecordLink_forwardIcon__2NvgZ"></span>
								</div>
							</a></li>
					</ul>
				</div>
			</div>
			<div class="mypage_space"></div>
			<div class="mypage4">
				<ul class="myinfo2">
					<li class="a"><a href="/UserOption/FAQ">
							<div class="Recordlink_div">
								<span>자주 묻는 질문 FAQ</span>
							</div>
							<div class="RecordLink_right__2qs6s">
								<span class="RecordLink_forwardIcon__2NvgZ"></span>
							</div>
						</a></li>
					<li class="b"><a href="/UserOption/Noti">
							<div class="Recordlink_div">
								<span>공지사항</span>
							</div>
							<div class="RecordLink_right__2qs6s">
								<span class="RecordLink_forwardIcon__2NvgZ"></span>
							</div>
						</a></li>
					<li class="c"><a href="/UserOption/Yanoljainfo">
							<div class="Recordlink_div">
								<span>야놀자 정보</span>
							</div>
							<div class="RecordLink_right__2qs6s">
								<span class="RecordLink_forwardIcon__2NvgZ"></span>
							</div>
						</a></li>
					<c:if test="${!empty username}">
						<li class="d"><a href="/UserOption/Setting">
								<div class="Recordlink_div">
									<span>설정</span>
								</div>
								<div class="RecordLink_right__2qs6s">
									<span class="RecordLink_forwardIcon__2NvgZ"></span>
								</div>
							</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<%@ include file="../../layout/footer.jsp"%>
</body>
</html>
