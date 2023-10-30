<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${path}/css/Reserve/Reserve_history.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<script type="text/javascript">
/* 	function oninputPhone(target) {
		target.value = target.value.replace(/[^0-9]/g, '').replace(
				/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g,
				"$1-$2-$3");
	} */
	function loadReservationData(selectedPeriod) {
		$.ajax({
			url : '/Reserve_List',
			method : 'GET',
			data : {
				period : selectedPeriod
			},
			success : function(data) {
				// AJAX 요청 성공 처리
				$('#reservationList').html(data);
			},
			error : function() {
				// AJAX 요청 실패 처리
				alert("예약 정보가 없습니다.");
			}
		});
	}
	function sendDataViaAjax() {
		var name = document.getElementById("name").value;
		var ordernumber = document.getElementById("ordernumber").value;
		var phone = document.getElementById("phone").value;

		$.ajax({
			type : "Get", 
			url : "/Reserve_history_NotUser",
			data : {
				name : name,
				order_number : ordernumber,
				phone : phone
			},
			success : function(data) {
				$('#reserve_status').html(data);
			},
			error : function(error) {
				console.error("오류 발생: " + error);
			}
		});
	}
	$(document).ready(function() {
		// 초기 데이터 로딩
		loadReservationData(90); // 초기 선택 값 (3개월)

		// 모달 열기
		$("#openPeriodModal").click(function() {
			$("#periodModal").css("display", "block");
		});

		// 모달 닫기
		$("#closemodal button").click(function() {
			$("#periodModal").css("display", "none");
		});

		// 선택한 기간을 AJAX로 서버로 보내기
		$(".periodOption").click(function() {
			var selectedPeriod = $(this).data('value');
			loadReservationData(selectedPeriod);
			$("#periodModal").css("display", "none");
		});
	});
</script>
<body>
	<header>
		<div class="head">
			<c:choose>
				<c:when test="${not empty username}">
					<div class="top">
						<span onclick="window.history.back();">&lt;</span>
						<span>국내여행 예약내역</span>
						<span></span>
					</div>
					<div class="btm">
						<div class="category">
							<span>카테고리 전체</span>
							<span></span>
						</div>
						<div class="period" id="openPeriodModal">
							<span>최근 6개월</span>
							<span></span>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="top">
						<span onclick="window.history.back();">&lt;</span>
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
					<div class="rh_list" id="reservationList">최근 3개월 ~ 2년까지의 예약내역</div>
				</c:when>
				<c:otherwise>
					<div class="NoMember">
						<div class="reserve_status" id="reserve_status">
							<div class="rss">
								<input type="text" id="name" class="rsinput" name="reserveinfo" placeholder="예약자 성명" />
								<hr>
							</div>
							<div class="rss">
								<input type="text" id="ordernumber" class="rsinput" name="reserveinfo" placeholder="통합 주문번호 또는 상품 예약번호" />
								<hr>
							</div>
							<div class="rss">
								<input type="text" id="phone" class="rsinput" name="reserveinfo" placeholder="휴대폰번호" maxlength="14" />
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
								<button onclick="sendDataViaAjax()">비회원 예약조회</button>
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
	<div id="periodModal" class="modal">
		<div id="closemodal" class="modal-content">
			<button>X</button>
			<h4>최근 기간 선택</h4>
			<div class="periodOption" data-value="90">최근 3개월</div>
			<div class="periodOption" data-value="180">최근 6개월</div>
			<div class="periodOption" data-value="365">최근 12개월</div>
			<div class="periodOption" data-value="720">최근 24개월</div>
			<button id="submitPeriod">확인</button>
		</div>
	</div>
</body>
</html>