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
	    $("#input_file").on("change", fileCheck);
	    const roomid = ${roomid};
	    
	    // 스타 그래픽 그리는 함수
	    const drawStar = (target, starClass) => {
	        const selector = '.star.' + starClass + ' span';
	        console.log(selector);
	        $(selector).css('width', target.value * 10 + '%');
	        $(selector).css('width', target * 10 + '%');
	    };
	    
	    var starValues = [${loadRs.kindness}, ${loadRs.cleanliness}, ${loadRs.convenience}, ${loadRs.loc_satisfy}];
	    var starNames = ["star1", "star2", "star3", "star4"];
		var fitotal = 0;
	    for (var i = 0; i < starValues.length; i++) {
	        drawStar(starValues[i] * 2, starNames[i]);
	        fitotal += starValues[i];
	    }
	    
	    $('.totalstar2').css('width',fitotal * 5 + '%');
	    
	    let stars = [0, 0, 0, 0]; // 스타 등급을 배열로 저장

	    // 스타 1에 대한 설정
        $('input.star1[type="range"]').on('input', function() {
        	starValues[0] = +(parseInt($(this).val()) / 2).toFixed(1); // 슬라이더 값 가져오기
        	stars[0] = starValues[0];
            drawStar(this, "star1");
        	updateTotalStar();
        });

        // 스타 2에 대한 설정
        $('input.star2[type="range"]').on('input', function() {
        	starValues[1] = +(parseInt($(this).val()) / 2).toFixed(1); // 슬라이더 값 가져오기
        	stars[1] = starValues[1];
            drawStar(this, "star2");
        	updateTotalStar();
        });

        // 스타 3에 대한 설정
        $('input.star3[type="range"]').on('input', function() {
        	starValues[2] =+(parseInt($(this).val()) / 2).toFixed(1); // 슬라이더 값 가져오기
        	stars[2] = starValues[2];
            drawStar(this, "star3");
        	updateTotalStar();
        });

        // 스타 4에 대한 설정
        $('input.star4[type="range"]').on('input', function() {
        	starValues[3] = +(parseInt($(this).val()) / 2).toFixed(1); // 슬라이더 값 가져오기
        	stars[3] = starValues[3];
            drawStar(this, "star4");
        	updateTotalStar();
        });
	 
	    // 평균 스타 업데이트 함수
        function updateTotalStar() {
        	   	totalStar = +(parseInt(starValues[0] + starValues[1] + starValues[2] + starValues[3])/ 4 ).toFixed(2); // 스타 값의 평균 계산
            	$('.totalstar2').css('width',totalStar * 20 + '%'); // 평균 값을 totalstar 입력란에 표시 (소수점 1자리까지)
          }
	    
	    // 저장 버튼 이벤트 핸들러
	    $("#savebtn").on("click", function(event) {
	        event.preventDefault();
	    	console.log("stars : " + stars);
	        if (stars.includes(0) || !totalStar) {
	            alert("평가를 완료해주세요.");
	            return;
	        }

	        const textareaValue = $("#reviewcontent").val();

	        if (!textareaValue) {
	            alert("후기 내용을 입력해주세요.");
	            return;
	        }

	        const form = $("form")[0];
	        const formData = new FormData(form);

	        // 여기에 추가적으로 폼 데이터를 첨부하는 코드를 넣어주세요...
			formData.append("textData", textareaValue);
			formData.append("rating1", stars[0]);
			formData.append("rating2", stars[1]);
			formData.append("rating3", stars[2]);
			formData.append("rating4", stars[3]);
	        formData.append("roomid", roomid);
	        for (var pair of formData.entries()) {
	            console.log(pair[0] + ', ' + pair[1]);
	        }
	        $.ajax({
	            type: "POST",
	            url: "/UpdateReview.do",
	            data: formData,
	            processData: false,
	            contentType: false,
	            success: function(data) {
	                console.log(data);
	            },
	            error: function(error) {
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
				<span>후기수정</span>
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
					<textarea id="reviewcontent" cols="90" rows="10" placeholder="'ㅋㅋㅋㅋ'와 같이 내용이 의미가 없는 후기는 보여지지 않을 수 있습니다"></textarea>
				</div>
			</div>
			<div class="photoupload">
				<c:if test="${empty img}">
					<%@include file="./photoUpload.jsp"%>
				</c:if>
			</div>
			<hr>
			<div class="save">
				<button class="savebtn" id="savebtn">후기 저장하기</button>
			</div>
		</div>
	</div>
</body>
</html>