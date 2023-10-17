<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${path}/css/places/review.css" />
<script type="text/javascript">
	// JavaScript
	// 모달 열기 버튼 클릭 시 모달 열기
	document.getElementById('selectroom').addEventListener('click', function() {
		document.getElementById('myModal').style.display = 'block';
	});

	// 모달 닫기 버튼 클릭 시 모달 닫기
	document.querySelector('.close').addEventListener('click', function() {
		document.getElementById('myModal').style.display = 'none';
	});
	
	// 객실 선택 시 리뷰 필터링 및 표시
    const roomElements = document.querySelectorAll('.rooms');
    roomElements.forEach(roomElement => {
        roomElement.addEventListener('click', function() {
            const selectedRoom = this.getAttribute('data-room');
            const reviewElements = document.querySelectorAll('.listroom');

            reviewElements.forEach(reviewElement => {
                const reviewRoom = reviewElement.querySelector('.listroomname').textContent;
                if (selectedRoom === 'all' || selectedRoom === reviewRoom) {
                    reviewElement.parentElement.style.display = 'block';
                } else {
                    reviewElement.parentElement.style.display = 'none';
                }
            });
            const selectedRoomName = this.textContent;
            document.getElementById('selectroom').textContent = selectedRoomName + ' ▼';

            document.getElementById('myModal').style.display = 'none';
            
        });
    });
</script>
</head>
<body>
	<div class="ratingContainer">
		<div class="ratingWrapper">
			<div class="ratingHead">
				<div class="ratingtitle">
					<c:if test="${not empty review}">
						<span>후기 (${cnt})</span>
						<span>후기정책</span>
					</c:if>
				</div>
				<c:if test="${not empty review_detail}">
					<div class="rating_content">
						<div>
							<div class="alltotalrating">
								<div>
									<svg width="2.8rem" height="2.8rem" viewBox="0 0 14 14" fill="none" xmlns="http://www.w3.org/2000/svg" class="css-12cnc2s">
								<linearGradient id="half">
								<stop offset="50%" stop-color="#fdbd00"></stop>
								<stop offset="0" stop-color="#ffffff"></stop></linearGradient>
								<path d="M7.12095 11.3897C7.04568 11.3481 6.95432 11.3481 6.87905 11.3897L3.0935 13.4823C3.05697 13.5025 3.01324 13.4717 3.01996 13.4305L3.74674 8.975C3.75951 8.89669 3.73435 8.81696 3.67894 8.76016L0.586662 5.59082C0.558242 5.56169 0.574668 5.51262 0.614898 5.50648L4.87464 4.85565C4.95705 4.84305 5.02777 4.79021 5.06319 4.71474L6.95474 0.684808C6.97273 0.646468 7.02727 0.646467 7.04526 0.684808L8.93681 4.71474C8.97223 4.79021 9.04295 4.84305 9.12536 4.85565L13.3851 5.50648C13.4253 5.51262 13.4418 5.56169 13.4133 5.59082L10.3211 8.76016C10.2656 8.81696 10.2405 8.89669 10.2533 8.975L10.98 13.4305C10.9868 13.4717 10.943 13.5025 10.9065 13.4823L7.12095 11.3897Z" fill="#fdbd00" stroke="#E7AC00" stroke-width="0.5" stroke-linejoin="round"></path></svg>
								</div>
								<strong>${review_detail.rating}</strong>/5
							</div>
							<div class="ratingab">최근 12개월 누적 평점</div>
						</div>
						<div>
							<div class="totalrating">
								<span>친절도</span>
								<div id="progress-container">
									<div id="progress-bar" style="width : ${review_detail.kindness*20}%"></div>
								</div>
								<span>${review_detail.kindness}</span>
							</div>
							<div class="totalrating">
								<span>청결도</span>
								<div id="progress-container">
									<div id="progress-bar" style="width : ${review_detail.convenience*20}%"></div>
								</div>
								<span>${review_detail.convenience}</span>
							</div>
							<div class="totalrating">
								<span>편의성</span>
								<div id="progress-container">
									<div id="progress-bar" style="width : ${review_detail.cleanliness*20}%"></div>
								</div>
								<span>${review_detail.cleanliness}</span>
							</div>
							<div class="totalrating">
								<span>위치만족도</span>
								<div id="progress-container">
									<div id="progress-bar" style="width : ${review_detail.loc_satisfy*20}%"></div>
								</div>
								<span>${review_detail.loc_satisfy}</span>
							</div>
						</div>
					</div>
					<div class="ratinginfo">평가 기준 안내</div>
				</c:if>
			</div>
			<div>
				<c:if test="${not empty review}">
					<c:if test="${not empty review_detail}">
						<div class="selectroom" id="selectroom">객실 전체 ▼</div>
					</c:if>
					<div class="reviewlist">
						<div class="reviewoption">
							<div class="option1" id="desc">최근작성순 ▼</div>
							<div class="option2">
								<span>포토후기만 보기</span>
								<input type="checkbox" id="onlyphoto">
							</div>
						</div>
						<c:forEach items="${review}" var="review">
							<div class="listcontent">
								<div class="listrating">
									<span class="star">
										★★★★★
										<span style="width:${review.rating * 20}%">★★★★★</span>
									</span>
									<span>•••</span>
								</div>
								<div class="listinfo">${review.username}|${review.ratingdate2}</div>
								<div class="listroom">
									<span>객실명</span>
									<span class="listroomname">${review.roomname}</span>
								</div>
								<div class="listrvcontent">${review.reviewcontent}</div>
							</div>
						</c:forEach>
						<div class="lastreview">
							<span>마지막 리뷰입니다</span>
						</div>
					</div>
				</c:if>
				<c:if test="${empty review}">
					<div class="nocontent">
						<h2>호텔을 예약하고 첫 후기 작성자가 되어보세요!</h2>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	<!-- HTML -->
	<div id="myModal" class="modal">
		<div class="modal-content">
			<div class="modal_wrapper">
				<span class="close">&times;</span>
				<div class="roomlists">
					<div class="rooms" data-room="all">객실 전체</div>
					<c:forEach items="${roomnameList}" var="room">
						<div class="rooms" data-room="${room}">${room}</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>