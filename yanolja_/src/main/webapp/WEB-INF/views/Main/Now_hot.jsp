<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<link rel="stylesheet" href="${path}/css/Viewlist/Nowhot.css" />
</head>
<body>
	<div class="nhdiv">
		<div>
			<div class="nhtitle">
				<h2>지금 예약 많은 지역</h2>
			</div>
		</div>
		<div class="nhbtns">
			<ul class="nh_ul_btn" id="nhListnh">
				<!-- 동적으로 생성될 li 요소들이 들어갈 위치 -->
			</ul>
		</div>
		<div class="nhcontents"></div>
	</div>
	<script src="${path}/js/slide/nowhot.js?var=23-09-11"></script>
</body>
</html>
