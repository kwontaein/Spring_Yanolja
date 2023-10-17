<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기작성</title>
<link rel="stylesheet" href="${path}/css/Review/writeReview.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<script>
    $(document).ready(function() {
    	
    	const urlParams = new URL(location.href).searchParams;

    	const roomid = urlParams.get('roomid');
    	
		var star1;
		var star2;
		var star3;
		var star4;
		
		var star1Value;
	    var star2Value;
	    var star3Value;
	    var star4Value;
	    var totalStar;
	    var bookid = ${bookid}
        // 스타 1에 대한 설정
        $('input.star1[type="range"]').on('input', function() {
        	star1Value = +(parseInt($(this).val()) / 2).toFixed(1); // 슬라이더 값 가져오기
          	console.log(star1Value);
            drawStar(this, "star1");
        	updateTotalStar();
        });

        // 스타 2에 대한 설정
        $('input.star2[type="range"]').on('input', function() {
        	star2Value = +(parseInt($(this).val()) / 2).toFixed(1); // 슬라이더 값 가져오기
          	console.log(star2Value);
            drawStar(this, "star2");
        	updateTotalStar();
        });

        // 스타 3에 대한 설정
        $('input.star3[type="range"]').on('input', function() {
        	star3Value =+(parseInt($(this).val()) / 2).toFixed(1); // 슬라이더 값 가져오기
          	console.log(star3Value);
            drawStar(this, "star3");
        	updateTotalStar();
        });

        // 스타 4에 대한 설정
        $('input.star4[type="range"]').on('input', function() {
        	star4Value = +(parseInt($(this).val()) / 2).toFixed(1); // 슬라이더 값 가져오기
          	console.log(star4Value);
            drawStar(this, "star4");
        	updateTotalStar();
        });
    	  // JavaScript 코드
          const drawStar = (target, starClass) => {
        	   var selector = '.star.' + starClass + ' span';
        	    $(selector).css('width', target.value * 10 + '%');
			   }
          
          // 평균 스타 업데이트 함수
        function updateTotalStar() {
        	   totalStar = +(parseInt(star1Value + star2Value + star3Value + star4Value)/ 4 ).toFixed(2); // 스타 값의 평균 계산
        	   	console.log("total : " + totalStar);
            	$('.totalstar2').css('width',totalStar * 20 + '%'); // 평균 값을 totalstar 입력란에 표시 (소수점 1자리까지)
        		
          }
     // 이벤트 핸들러: 버튼 클릭 또는 양식 제출 버튼 클릭
        $("#savebtn").on("click", function(event) {
            event.preventDefault(); // 기본 양식 제출 동작을 막음

            // textarea의 값을 가져옴
            var textareaValue = $("#reviewcontent").val();

            // Ajax POST 요청을 생성
            $.ajax({
                type: "POST",
                url: "/writeReview.do", // 서버 엔드포인트 URL
                data: { 	
                	rating1 : star1Value,
    				rating2 : star2Value,
    				rating3 : star3Value,
    				rating4 : star4Value,
    				textData: textareaValue,
    				roomid : roomid,
    				bookid : bookid
    				}, // 전송할 데이터 및 데이터 이름
                success: function(data) {
                    // 성공 시 서버 응답 처리
                    console.log(data);
                    window.location.href="/Reserve_history";
                    
                },
                error: function(error) {
                    // 오류 처리
                    console.error(error);
                }
            });
        });
    });
</script>
	<header style="height: 48px;">
		<div class="head">
			<div class="top">
				<span>&lt;</span>
				<span>후기작성</span>
				<span></span>
			</div>
		</div>
	</header>
	<div class="review_container">
		<div class="review_wrapper">
			<div class="review_info">
				<div>
					<span>숙소정보</span>
					<span>
						<b>${loadRs.hotelname} & ${loadRs.roomname} / ${loadRs.rentalType}</b>
					</span>
				</div>
				<div>
					<span>작성자명</span>
					<span>
						<b>${username}</b>
					</span>
				</div>
				<div>
					<span style="color: gray; font-size: 12px;">* 작성한 후기는 다른 사이트에서도 함께 보여질 수 있습니다.</span>
				</div>
			</div>
			<hr>
			<div style="display: flex; justify-content: center;">
				<div>
					<div>
						<b>이곳에서의 경험</b>은 어떠셨어요?
					</div>
					<span class="totalstar">
						★★★★★
						<span class="totalstar2">★★★★★</span>
						<input type="range" name="totalstar" id="totalstar" value="1" step="1" min="0" max="10">
					</span>
					<span id="pp"></span>
				</div>

			</div>
			<div class="rating">
				<div>
					<div>
						<b>서비스&친절도</b>는 어떠셨어요?
					</div>
					<span class="star star1">
						★★★★★
						<span>★★★★★</span>
						<input type="range" value="1" step="1" min="0" max="10" class="star1">
					</span>

				</div>
				<div>
					<div>
						<b>숙소&객실 청결도</b>는 어떠셨어요?
					</div>
					<span class="star star2">
						★★★★★
						<span>★★★★★</span>
						<input type="range" value="1" step="1" min="0" max="10" class="star2">
					</span>
				</div>
				<div>
					<div>
						<b>시설&편의성</b>은 어떠셨어요?
					</div>
					<span class="star star3">
						★★★★★
						<span>★★★★★</span>
						<input type="range" value="1" step="1" min="0" max="10" class="star3">
					</span>
				</div>
				<div>
					<div>
						<b>교통&위치접근성</b>은 어떠셨어요?
					</div>
					<span class="star star4">
						★★★★★
						<span>★★★★★</span>
						<input type="range" value="1" step="1" min="0" max="10" class="star4">
					</span>
				</div>
			</div>
			<hr>
			<div class="dowrite">
				<div>
					<h4>후기 작성</h4>
				</div>
				<div style="display: flex; justify-content: center;">
					<textarea id="reviewcontent" cols="90" rows="10"></textarea>
				</div>
			</div>
			<hr>
			<div class="save">
				<button class="savebtn" id="savebtn">후기 저장하기</button>
			</div>
		</div>
	</div>
</body>
</html>