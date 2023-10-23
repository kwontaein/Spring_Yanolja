<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<title></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<script src="${path}/js/Review/photoUpload.js?var=2023-10-23">
	</script>
	<div class="container" style="border: 5px solid lightgray; border-style: dashed; outline: none; width: 647px; display: flex; justify-content: center;">
		<form name="dataForm" id="dataForm">
			<div style="text-align: center; margin: 10px;">
				<input id="input_file" multiple="multiple" type="file" style="display: none;">
				<p style="font-size: 10px; color: gray;">※첨부파일은 최대 5개까지 등록이 가능합니다.</p>
				<p style="font-size: 10px; color: gray;">※파일 클릭시 첨부된 파일이 삭제됩니다.</p>
				<div class="data_file_txt" id="data_file_txt" style="margin: 20px;">
					<br />
					<div id="articlefileChange">
						<c:if test="${not empty imgs}">
							<c:forEach items="${imgs}" var="img" varStatus="loop">
								<img src="data:image/png;base64,${img.base64Image}" id="file${loop.index}" style="width: 200px; height: 132px;" alt="이미지" onClick="fileDelete(${loop.index})">
								<script>
								 // 이미지 데이터를 가져와서 content_files 배열에 넣는 JavaScript 코드

							    // 이미지 데이터를 가져옵니다.
							    var imgData = '${img.base64Image}';

							    // 이미지 데이터를 디코딩하여 바이트 문자열을 생성합니다.
							    var byteCharacters = atob(imgData);

							    // 바이트 문자열을 숫자 배열로 변환합니다.
							    var byteNumbers = new Array(byteCharacters.length);
							    for (var i = 0; i < byteCharacters.length; i++) {
							        byteNumbers[i] = byteCharacters.charCodeAt(i);
							    }

							    // 숫자 배열을 Uint8Array로 변환합니다.
							    var byteArray = new Uint8Array(byteNumbers);

							    // 이미지 데이터로부터 Blob을 생성합니다.
							    var blob = new Blob([byteArray], {
							        type: 'image/png' // 이미지의 MIME 타입을 지정합니다.
							    });
							    
							    var fileName = '${img.imgname}'; // 파일 이름을 설정
							    // Blob 객체에 파일 정보를 추가합니다.
							    blob.lastModifiedDate = new Date(); // 파일 수정일을 설정합니다.
							    blob.name = '${img.imgname}'; // 파일 이름을 설정합니다.

							    var file = new File([blob], fileName, { type: blob.type });
							    // Blob을 content_files 배열에 추가합니다.
							    content_files.push(file);

							    // 파일 카운트 변수를 증가시킵니다.
							    fileNum++;
							    fileCount++;

							    // content_files 배열을 로깅하여 확인합니다.
							    console.log(content_files);
								</script>
							</c:forEach>
						</c:if>
					</div>
				</div>
				<button id="btn-upload" type="button" style="border: 1px solid #ddd; outline: none;">클릭해서 파일 추가</button>
			</div>
		</form>
	</div>

</body>
</html>
