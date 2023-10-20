<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${path}/css/places/review.css" />
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				 // Local Storage에서 'onlyphoto' 항목 가져오기
			    const onlyPhoto2 = localStorage.getItem('onlyphoto') === 'true';
				 
			    // 'onlyphoto' 체크박스 상태 설정
			    $('#onlyphoto').prop('checked', onlyPhoto2);
			  
			    
			    
				const onlyPhotoCheckbox = $('#onlyphoto');
				const myModal = $('#myModal');
				const orderMyModal = $('#order_myModal');

				var roomname = '${selectedroomname}'; // 초기 설정할 값으로 변경
				$('#selectroomname').text(roomname);

				// 정렬 이름 설정
				const orderNames = {
					'ratingdate asc' : '최근작성순',
					'rating asc' : '별점 높은 순',
					'rating desc' : '별점 낮은 순'
				};

				const initialOrderBy = localStorage.getItem('orderbyn')
						|| 'ratingdate asc';
				const orderName = orderNames[initialOrderBy];
				$('#orderbyname').text(orderName);

				// 공통 함수로 모달 열기
				function openModal(modal) {
					modal.css('display', 'block');
				}

				// 공통 함수로 모달 닫기
				function closeModal(modal) {
					modal.css('display', 'none');
				}

				// 객실 및 정렬 모달 열기
				$('#selectroom, #orderby').on('click', function() {
					if ($(this).attr('id') === 'selectroom') {
						openModal(myModal);
					} else {
						openModal(orderMyModal);
					}
				});

				// 객실 및 정렬 모달 닫기
				$('.close, .order_close').on('click', function() {
					if ($(this).hasClass('close')) {
						closeModal(myModal);
					} else {
						closeModal(orderMyModal);
					}
				});
			});
	
	function getParameterByName(name, url = window.location.href) {
		name = name.replace(/[\[\]]/g, '\\$&');
		var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
			results = regex.exec(url);
		if (!results) return null;
		if (!results[2]) return '';
		return decodeURIComponent(results[2].replace(/\+/g, ' '));
	}
	
	// 객실 선택 시 리뷰 필터링 및 표시
	$('.rooms').on('click', function() {
		const roomname = $(this).data('room');
		const orderby = 'ratingdate asc';
		const onlyPhoto = $('#onlyphoto').checked;
		doajax(roomname, orderby, onlyPhoto);
		document.getElementById('myModal').style.display = 'none';
	});

	// 정렬 시 리뷰 필터링 및 표시
	$('.order_by').on('click', function() {
		const roomname = '${selectedroomname}';
		const orderby = $(this).data('order');
		const onlyPhoto = $('#onlyphoto').checked;
		localStorage.setItem('orderbyn', orderby);
		doajax(roomname, orderby, onlyPhoto);
		document.getElementById('myModal').style.display = 'none';
	});

	// '포토후기만 보기' 체크박스의 상태 변경 이벤트를 감지
	$('#onlyphoto').on('change', function() {
		const roomname = '${selectedroomname}';
		const orderby = localStorage.getItem('orderbyn');
		const onlyPhoto = this.checked;
		localStorage.setItem('onlyphoto', onlyPhoto);
		doajax(roomname, orderby, onlyPhoto);
	});

	function doajax(roomname, orderby, onlyPhoto) {
		var hotelid = getParameterByName('hotelid');
		var roomid = getParameterByName('roomid');
		var onlyPhoto2 ;
		if(onlyPhoto2= localStorage.getItem('onlyphoto') === 'true'){
			var op = onlyPhoto2;
		}else{
			var op = onlyPhoto;
		}
		
		console.log("ajax실행");
		$.ajax({
			type : 'Get',
			url : '/Review', // 실제 서버 엔드포인트 URL로 변경
			data : {
				hotelid : hotelid,
				roomid : roomid,
				roomname : roomname,
				orderby : orderby,
				onlyPhoto : op,
			},
			success : function(data) {
				$(".changedinfo").html(data);
				$(".review").html(data);
			},
			error : function(error) {
				console.error(error);
			},
		});
	}
</script>
</head>
<body>
	<div class="reviewall">
		<div class="ratingContainer">
			<div class="ratingWrapper">
				<div class="ratingHead">
					<div class="ratingtitle">
						<c:if test="${not empty review}">
							<span>후기 (${review_detail.review_cnt})</span>
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
					<c:if test="${ not empty roomnameList}">
						<div class="selectroom" id="selectroom">
							<span id="selectroomname">객실 전체</span>
							<span>▼</span>
						</div>
					</c:if>
					<div class="reviewlist">
						<div class="reviewoption">
							<div class="option1" id="orderby">
								<span id="orderbyname">최근작성순</span>
								<span>▼</span>
							</div>
							<div class="option2">
								<span>포토후기만 보기</span>
								<input type="checkbox" id="onlyphoto">
							</div>
						</div>
						<c:if test="${not empty review}">
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
									<script>
										var mySwiper = new Swiper('.swiper-container', {
											direction : 'horizontal',
											loop : true,
											pagination : {
												el : '.swiper-pagination',
											},
										});
									</script>
									<div class="swiper-container">
										<div class="swiper-wrapper">
											<c:forEach items="${images}" var="image">
												<c:if test="${review.reviewid == image.reviewid}">
													<div class="swiper-slide">
														<img src="data:image/png;base64,${image.base64Image}" alt="이미지">
													</div>
												</c:if>
											</c:forEach>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:if>
						<div class="lastreview">
							<span>마지막 리뷰입니다</span>
						</div>
					</div>
					<c:if test="${empty review}">
						<div class="nocontent">
							<h2>호텔을 예약하고 첫 후기 작성자가 되어보세요!</h2>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<!-- HTML -->
	<div id="myModal" class="modal">
		<div class="modal-content">
			<div class="modal_wrapper">
				<span class="close">&times;</span>
				<div class="roomlists">
					<div class="rooms" data-room="객실 전체">객실 전체</div>
					<c:forEach items="${roomnameList}" var="room">
						<div class="rooms" data-room="${room}">${room}</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div id="order_myModal" class="order_modal">
		<div class="order-modal-content">
			<div class="order_modal_wrapper">
				<span class="order_close">&times;</span>
				<div class="order_lists">
					<div class="order_by" data-order="ratingdate asc">최근작성순</div>
					<div class="order_by" data-order="rating asc">별점 높은 순</div>
					<div class="order_by" data-order="rating DESC">별점 낮은 순</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>