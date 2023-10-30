<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/Review/MyReview.css" />
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// "id='del'" 버튼을 클릭하면 모달을 표시합니다.
		$('.del').click(function() {
			$('#myModal').show();
			// "data-reviewid" 속성에서 reviewid 값을 추출합니다.
			var reviewId = $(this).data('reviewid');
			// 모달 내의 숨김 필드에 reviewId 값을 설정합니다.
			$('#reviewIdField').val(reviewId);
		});

		// 모달 내의 닫기 버튼을 클릭하면 모달을 숨깁니다.
		$('.close').click(function() {
			$('#myModal').hide();
		});

		// 모달 내의 "삭제" 버튼 클릭을 처리합니다.
		$('.delete').click(function() {
			// 모달 내의 숨김 필드에서 reviewId 값을 추출합니다.
			var reviewId = $('#reviewIdField').val();

			// 여기에서 AJAX POST 요청을 수행합니다.
			$.ajax({
				type : "POST",
				url : "/DeleteReview.Do", // 실제 URL로 대체하세요
				data : {
					reviewid : reviewId
				// 삭제할 후기의 실제 ID로 대체하세요
				},
				success : function(data) {
					// 성공 처리, 예를 들어 모달을 숨기고 UI를 업데이트합니다.
					$('#myModal').hide();
					// 변경 사항을 반영하기 위해 페이지를 다시 로드하거나 새로 고칠 수 있습니다.
					location.reload();
					alert("삭제되었습니다.");
				},
				error : function(err) {
					// 오류 처리
					console.error("오류:", err);
				}
			});
		});
	});
</script>
</head>
<body>
	<header>
		<div class="mrheader">
			<div class="mrhcontainer">
				<span onclick="window.history.back();">&lt;</span>
				<span>나의 후기(${room_cnt})</span>
				<span> </span>
			</div>
		</div>
	</header>
	<div class="mrContainer">
		<div class="mrWrapper">
			<div class="Reviewinfo">
				<ul>
					<li>후기는 작성 후 48시간 이내에 본문만 수정이 가능하며, 작성자는 현재 닉네임으로 변경됩니다. (사전 수정 불가)</li>
					<li>바른후기 정책 시행 이전에 작성된 후기는 노출되지 않습니다.</li>
					<li>삭제한 후기는 복구할 수 없으며 후기 작성으로 획득한 포인트 또는 코인은 회수될 수 있습니다.</li>
					<li>후기 삭제는 후기 작성일로부터 30일 이후에 가능합니다.</li>
				</ul>
			</div>
			<c:forEach items="${review}" var="rev">
				<div class="mr">
					<div class="mrHotel">
						<div>
							<div>
								<b>${rev.hotelname}</b>
							</div>
							<div>${rev.kindhotel}</div>
						</div>
						<div>
							<c:choose>
								<c:when test="${rev.daydiff == '수정'}">
									<!-- '수정'인 경우 페이지 이동 -->
									<a href="/writeReview?reviewid=${rev.reviewid}" class="upd">수정</a>
								</c:when>
								<c:when test="${rev.daydiff == '삭제'}">
									<!-- '삭제'인 경우 모달 창 열기 -->
									<div class="del" id="del" data-reviewid="${rev.reviewid}">삭제</div>
								</c:when>
								<c:otherwise>
									<div class="del" id="del" data-reviewid="${rev.reviewid}">삭제</div>
									<!-- <span>삭제 불가</span>
								 -->
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="mrReview">
						<div>
							<div class="listrating">
								<span class="star">
									★★★★★
									<span style="width:${rev.rating * 20}%">★★★★★</span>
								</span>
							</div>
							<div>${rev.username}&nbsp;|&nbsp;${rev.roomname}•${rev.rentalType}</div>
						</div>
						<div class="date">${rev.ratingdate2}</div>
					</div>
					<div>${rev.reviewcontent}</div>
					<script>
						var mySwiper = new Swiper('.swiper-container', {
							direction : 'horizontal',
							slidesPerView : 3,
							pagination : {
								el : '.swiper-pagination',
							},
						});
					</script>
					<div class="swiper-container">
						<div class="swiper-wrapper">
							<c:forEach items="${img}" var="image">
								<c:if test="${rev.reviewid == image.reviewid}">
									<div class="swiper-slide">
										<img src="data:image/png;base64,${image.base64Image}" alt="이미지">
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
				<hr>
			</c:forEach>
		</div>
	</div>
	<div id="myModal" class="modal">
		<div class="modal-content">
			<div class="modal_wrapper">
				<b>정말 삭제하시겠어요?</b>
				<p>삭제하신 후기는 복구할 수 없으며, 후기 작성으로 획득한 포인뜨 또는 코인은 회수될 수 있습니다.</p>
				<!-- Add the hidden input field for reviewId -->
				<input type="hidden" id="reviewIdField" name="reviewId" value="">
				<div class="byn_choose">
					<span class="close">아니요</span>
					<span class="delete">삭제하기</span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>