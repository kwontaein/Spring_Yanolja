<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
body {
	background-color: #f2f2f2;
	margin: 0;
	padding: 0;
}

.cartContainer {
	display: flex;
	justify-content: center;
}

.header {
	background-color: white;
	width: 100%;
	display: flex;
	justify-content: center;
}

.head {
	width: 100%;
}

.top {
	margin: 0 auto;
	display: flex;
	justify-content: space-between;
	width: 728px;
	padding: 10px 20px;
}

.mid {
	margin: 0 auto;
	border-bottom: 1px solid lightgray;
	display: flex;
	justify-content: center;
	padding: 0 20px;
}

.midcontent {
	display: flex;
	width: 728px;
	justify-content: flex-start;
}

.midcontent>div {
	padding-right: 20px;
	height: 30px;
	display: flex;
	align-items: center;
}

.btm {
	width: 728px;
	height: 48px;
	margin: 0 auto;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 0 20px;
}

.cartWrapper {
	width: 768px;
}

.cartlist {
	margin-top: 12px;
	background-color: white;
	padding: 12px 20px 20px 20px;
}

.goods {
	margin-top: 12px;
	background-color: white;
}
</style>
</head>
<body>
	<header class="header">
		<div class="head">
			<div class="top">
				<div>&lt;</div>
				<div>장바구니</div>
				<div>홈</div>
			</div>
			<div class="mid">
				<div class="midcontent">
					<div>전체</div>
					<div>숙소</div>
				</div>
			</div>
			<div class="btm">
				<div>전체 선택</div>
				<div>선택 삭제</div>
			</div>
		</div>
	</header>
	<div class="cartContainer">
		<div class="cartWrapper">
			<div class="contents">
				<div class="cartlist">찜 목록 리스트</div>
				<div class="goods">상품 총 금액</div>
			</div>
			<footer class="footer">
				<div class="footcontent">
					<div class="price">
						<div>총 a건</div>
						<div>결제 예상 금액</div>
					</div>
					<div class="reserve">예약하기</div>
				</div>
			</footer>
		</div>
	</div>
</body>
</html>