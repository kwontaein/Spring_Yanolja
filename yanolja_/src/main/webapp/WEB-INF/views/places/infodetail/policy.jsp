<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/places/policy.css" />
</head>
<body>
	<div class="policyContainer">
		<div class="policyWrapper">
			<div class="guide">
				<div class="title"><b>안내</b></div>
				<div class="useguide">
					<c:forEach items="${info}" var="info">
						<div>
							<div class="infotitle">
								<b>${info.infoName}</b>
							</div>
							<div class="infocontent">
								<div>${info.infoDetail2}</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="introduce">
					<div>
						<b>숙소 소개</b>
					</div>
					<div class="intro">
						<div>${intro}</div>
					</div>
				</div>
			</div>
		</div>
		<div class="policy">
			<div class="title"><b>정책</b></div>
			<c:forEach items="${policy}" var="policy" varStatus="loop">
				<div class="policydiv" <c:if test="${loop.index >= 1}" >style="border-top: 1px lightgray solid;"</c:if>>
					<div class="policytitle">
						<b>${policy.policyname}</b>
					</div>
					<div class="policycontent">${policy.policycontent}</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>