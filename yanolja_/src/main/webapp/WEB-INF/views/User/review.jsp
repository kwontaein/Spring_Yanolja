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
		var star1;
		var star2;
		var star3;
		var star4;
		
        // 스타 1에 대한 설정
        $('input.star1[type="range"]').on('input', function() {
            drawStar(this, "star1");
        });

        // 스타 2에 대한 설정
        $('input.star2[type="range"]').on('input', function() {
            drawStar(this, "star2");
        });

        // 스타 3에 대한 설정
        $('input.star3[type="range"]').on('input', function() {
            drawStar(this, "star3");
        });

        // 스타 4에 대한 설정
        $('input.star4[type="range"]').on('input', function() {
            drawStar(this, "star4");
        });
    	  // JavaScript 코드
          const drawStar = (target, starClass) => {
        	   var selector = '.star.' + starClass + ' span';
        	    $(selector).css('width', target.value * 10 + '%');
			   }
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
					<div class="totalstar">
						<input type="text" name="totalstar" id="totalstar">
					</div>
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
					<textarea cols="90" rows="10"></textarea>
				</div>
			</div>
			<hr>
			<div class="save">
				<button class="savebtn">후기 저장하기</button>
			</div>
		</div>
	</div>
</body>
</html>