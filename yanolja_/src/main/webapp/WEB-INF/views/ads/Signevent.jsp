<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.event_register {
	margin: 15px 0 15px 0;
	background-color: white;
	width: 768px;
	height: 5rem;
	align-items: center; /* 수직 가운데 정렬 */
	border: 1px blue solid;
	border-radius: 5px;
}

.event_register a {
	height: 100%;
	display: flex; /* 부모 컨테이너를 flex로 설정 */
	margin: 0 auto;
	text-align: center; /* 텍스트를 수평 가운데 정렬 */
	align-items: center; /* 수직 가운데 정렬 */
	flex-wrap: wrap;
	padding-left: 20px;
	padding-right: 20px;
}

.event_img {
	width: 4rem;
	height: 4rem;
}
.event_msg{
 text-align: left;
 
}
.RecordLink_forwardIcon__2NvgZ {
	width: 2rem;
	height: 2rem;
	display: inline-block;
	background:
		url(//yaimg.yanolja.com/joy/sunny/static/images/my/icon-arrow-small-black.svg)
		50% no-repeat;
		margin-left:25rem;
}
</style>
</head>
<body>
	<div class="event_register">
		<a href="/event">
			<img class="event_img" src="https://yaimg.yanolja.com/2023/04/28/14/644bdd2309d610.85892872.jpg" alt="개인화 혜택 배너 이미지">
			<div class="event_msg">
				<span>야놀자가 처음이신가요?</span>
				<br>
				<span> 회원가입후 혜택을 받아보세요! </span>
			</div>
			<span class="RecordLink_forwardIcon__2NvgZ"></span>
		</a>
	</div>
</body>
</html>