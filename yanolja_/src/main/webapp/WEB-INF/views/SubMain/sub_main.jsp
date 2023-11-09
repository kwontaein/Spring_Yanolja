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
<link rel="stylesheet" href="${path}/css/Main/4list.css" />
<link rel="stylesheet" href="${path}/css/Viewlist/hokangs.css" />
<link rel="stylesheet" href="${path}/css/List/Tophotellist.css" />
<link rel="stylesheet" href="${path}/css/List/seasonlist.css" />
<script type="text/javascript">
	var kind = '${kind}';
</script>
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
		<div>
			<c:if test="${not empty resentViewHotelid && rskindbykor eq kind}">
				<div id="Resent"></div>
				<script>
					$(document).ready(function() {
						// AJAX 요청을 만듭니다.
						$.ajax({
							url : "/ResentRelated",
							type : "GET",
							success : function(data) {
								// AJAX 요청이 성공하면 응답을 받아서 Resent 요소에 추가합니다.
								$("#Resent").html(data);
							},
							error : function() {
								console.log("AJAX 요청 실패");
							}
						});
					});
				</script>
			</c:if>
		</div>
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