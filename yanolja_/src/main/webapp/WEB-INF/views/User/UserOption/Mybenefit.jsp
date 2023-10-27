<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
	background-color: #f2f2f2;
	margin: 0;
	padding: 0;
}

header {
	background-color: white;
	display: flex;
	justify-content: center;
}

.header {
	width: 768px;
	height: 44px;
	display: flex;
	align-items: center;
	justify-content: space-between;
}

.bncontainer {
	display: flex;
	justify-content: center;
}

.bnwrapper {
	width: 768px;
}

.bn {
	width: 728px;
	padding: 20px;
	background-color: white;
	padding: 20px;
}

.other {
	width: 728px;
	padding: 20px;
	background-color: white;
}
.text{
	text-align : center;
}
h3 {
	margin-top: 0px;
}
</style>
</head>
<body>
	<header>
		<div class="header">
			<span>&lt;</span>
			<span>MY 혜택</span>
			<span></span>
		</div>
	</header>
	<div class="bncontainer">
		<div class="bnwrapper">
			<div class="bn">
				<h3>${username}님에게만드리는혜택</h3>
				<div class="text">
					<span>고객님에게 딱 맞는 혜택을 준비 중입니다</span>
					<p>알림을 통해 알려드릴게요</p>
				</div>
			</div>
			<div class="other">
				<h3>관심 가질만한 이벤트</h3>
				<div class="text">
					<span>고객님에게 딱 맞는 혜택을 준비 중입니다</span>
					<p>알림을 통해 알려드릴게요</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>