<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>별점 평가</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.star {
	position: relative;
	font-size: 2rem;
	color: #ddd;
}

.star input {
	width: 100%;
	height: 100%;
	position: absolute;
	left: 0;
	opacity: 0;
	cursor: pointer;
	z-index: 10;
}

.star span {
	width: 0;
	position: absolute;
	left: 0;
	color: red;
	overflow: hidden;
	pointer-events: none;
}
</style>
<script>

        $(document).ready(function() {
            $('input[type="range"]').on('input', function() {
            	console.log("실행");
                drawStar(this);
            });
            // jQuery 코드
            const drawStar = (target) => {
            	console.log(target.value +"됨");
                $('.star span').css('width', (target.value * 10)+ "%");
                
            };
        });
    </script>
</head>
<body>
	<span class="star">
		★★★★★
		<span>★★★★★</span>
		<input type="range" value="1" step="1" min="0" max="10" style="pointer-events: auto;">
	</span>
</body>
</html>

