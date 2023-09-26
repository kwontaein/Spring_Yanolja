<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.tsearchWrapper {
	padding: 20px;
}

.transport {
	height: 30px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	border: 1px lightgray solid;
	padding: 10px;
	margin : 10px 0 10px 0;
}
</style>
</head>
<body>
	<div class="tsearchContainer">
		<div class="tsearchWrapper">
			<div class="tsearchtop">
				<div class="transport">
					<span>고속버스</span>
					<span>출발/도착 &gt;</span>
				</div>
				<div class="transport">
					<span>기차</span>
					<span>출발/도착 &gt;</span>
				</div>
				<div class="transport">
					<span>항공권</span>
					<span>출발/도착 &gt;</span>
				</div>
				<div class="transport">
					<span>렌터카</span>
					<span>상품 선택 &gt;</span>
				</div>
			</div>
			<div class="searchbottom"></div>
		</div>
	</div>
</body>
</html>