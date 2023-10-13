<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${path}/css/Reserve/Reserve_history.css" />
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<script type="text/javascript">
	function oninputPhone(target) {
		target.value = target.value.replace(/[^0-9]/g, '').replace(
				/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g,
				"$1-$2-$3");
	}
</script>
<body>
	<header>
		<div class="head">
			<c:choose>
				<c:when test="${not empty username}">
					<div class="top">
						<span>&lt;</span>
						<span>국내여행 예약내역</span>
						<span></span>
					</div>
					<div class="btm">
						<div class="category">
							<span>카테고리 전체</span>
							<span></span>
						</div>
						<div class="period">
							<span>최근 6개월</span>
							<span></span>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="top">
						<span>&lt;</span>
						<span>비회원 국내여행 예약내역</span>
						<span>검색 / 집</span>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</header>
	<div class="rh_container">
		<div class="rh_wrapper">
			<c:choose>
				<c:when test="${not empty username}">
					<div class="rh_list">최근 3개월 ~ 2년까지의 예약내역</div>
				</c:when>
				<c:otherwise>
					<div class="NoMember">
						<div class="reserve_status">
							<div class="rss">
								<input type="text" id="name" class="rsinput" name="reserveinfo" placeholder="예약자 성명" />
								<hr>
							</div>
							<div class="rss">
								<input type="text" id="ordernumber" class="rsinput" name="reserveinfo" placeholder="통합 주문번호 또는 상품 예약번호" />
								<hr>
							</div>
							<div class="rss">
								<input type="text" id="phone" class="rsinput" name="reserveinfo" placeholder="휴대폰번호" oninput="oninputPhone(this)" maxlength="14" />
								<hr>
							</div>
							<div class="phonecheck">
								<div class="postbtn">
									<button>인증번호 전송</button>
								</div>
								<div class="checkinput">
									<input type="text" id="check" class="rsinput" placeholder="인증번호 입력" oninput="oninputPhone(this)">
									<hr>
								</div>
							</div>
							<div class="rsvbtn">
								<button>비회원 예약조회</button>
							</div>
							<div class="ps">
								<span>회원으로 예약한 경우 로그인 후 이용해주세요</span>
							</div>
						</div>
					</div>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
</body>
</html>