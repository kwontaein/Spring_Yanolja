<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>다놀자 ${resentViewHotelid}</title>
<link rel="stylesheet" href="${path}/css/Main/Main.css" />
</head>
<body>
	<c:set var="isMain" value="${pageName == 'Main'}" />
	<%@ include file="../../layout/header.jsp"%>
	<div id="Maindiv">
		<%@ include file="./Linkto.jsp"%>
		<%@ include file="../ads/Signevent.jsp"%>
		<%@ include file="./hotelslide.jsp"%>
		<%@ include file="./live.jsp"%>
		<%@ include file="./saleEvent.jsp"%>
		<%@ include file="./season.jsp"%>
		<%@ include file="./Now_hot.jsp"%>
		<%@ include file="./hokangs.jsp"%>
	</div>
	<%@ include file="../../layout/footer.jsp"%>
</body>
</html>