<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="${path}/css/calendar/calendar.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
// JSTL을 사용하여 comparisonList 데이터를 JavaScript 배열로 변환
var comparisonList = [
    <c:forEach items="${comparisonList}" var="comparison" varStatus="status">
        {
            reserveDate: '<c:out value="${comparison.reserveDate}" />',
            hasRoom: ${comparison.hasRoom}
        }
        <c:if test="${!status.last}">,</c:if>
    </c:forEach>
];
</script>
<title>1년 달력</title>
</head>
<body>
	<div class="calendardiv" id="calendardiv">
		<%@include file="../../layout/dateheader.jsp"%>
		<div class="calendarContainer">
			<div class="calendarWrapper">
				<table border="1">
					<c:forEach var="date" items="${datesInRange}">
						<!-- 각 월의 첫 번째 날인 경우 월을 표시 -->
						<c:if test="${date.dayOfMonth == 1}">
							<tr class="month-label">
								<td colspan="7" class="yearandmonthtd">
									<div class="yearandmonth">
										<div class="monthValue">${date.monthValue}</div>
										<div class="yearandmonthvalue">
											<div>월</div>
											<div>${currentDate.year}</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<!-- 시작 날짜가 일요일이 아니면 빈 칸 추가 -->
								<c:if test="${date.dayOfWeek.value != 7}">
									<c:forEach begin="1" end="${date.dayOfWeek.value}">
										<td class="empty-cell"></td>
									</c:forEach>
								</c:if>
						</c:if>
						<c:set var="isPastDay" value="${date.isBefore(currentDate)}" />
						<c:set var="isFutureDay" value="${date.isAfter(currentDate.plusMonths(6))}" />
						<td class="calendar-day ${isPastDay ? 'past-day' : (isFutureDay ? 'past-day' : 'able-day')}" data-date="${date}">${date.dayOfMonth}</td>
						<c:if test="${date.dayOfWeek.value == 6}">
							<!-- 토요일마다 주 닫고 열기 -->
							</tr>
						</c:if>
					</c:forEach>
				</table>
				<script type="text/javascript" src="${path}/js/calendar/calendar.js">
					// 페이지 로딩 시 실행되는 함수
				</script>
				<div class="datebtn">
					<button id="dateRangeButton" class="dateRangeButton">${currentDate}체크인검색</button>
				</div>
			</div>
		</div>
	</div>
	<div id="toast"></div>
</body>
</html>
