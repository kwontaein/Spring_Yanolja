<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>board</title>
<link rel="stylesheet" href="${path}/css/Main/submain.css" />
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="${path}/js/slide/selectlocation.js"></script>
</head>
<body>
	<%@ include file="../../layout/header.jsp"%>
	<div class="subMaindiv">
		<%@ include file="./select_location.jsp"%>
		<c:choose>
			<c:when test="${kind eq 'hotel'}">
				<%@ include file="./eventlist/hotel_event_list.jsp"%>
			</c:when>
			<c:when test="${kind eq 'motel'}">
				<%@ include file="./eventlist/motel_event_list.jsp"%>
			</c:when>
			<c:when test="${kind eq 'pension'}">
				<%@ include file="./eventlist/pension_event_list.jsp"%>
			</c:when>
			<c:when test="${kind eq 'guest'}">
				<%@ include file="./eventlist/hotel_event_list.jsp"%>
			</c:when>
		</c:choose>
		<%@ include file="../ads/ad3.jsp"%>
		<%@ include file="./sub_slide.jsp"%>
		<%@ include file="./list/4list.jsp"%>
		<%@ include file="./list/4list2.jsp"%>
		<%@ include file="./list/4list3.jsp"%>
	</div>
	<div class="Rocmodal">
		<%@include file="./location_modal.jsp"%>
	</div>
</body>
</html>