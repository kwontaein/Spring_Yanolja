<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
<title>파일업로드예제</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="container" style="border: 5px solid lightgray; border-style: dashed; outline: none; width:647px;">
		<form name="dataForm" id="dataForm">
			<button id="btn-upload" type="button" style="border: 1px solid #ddd; outline: none;">파일 추가</button>
			<input id="input_file" multiple="multiple" type="file" style="display: none;">
			<span style="font-size: 10px; color: gray;">※첨부파일은 최대 5개까지 등록이 가능합니다.</span>
			<div class="data_file_txt" id="data_file_txt" style="margin: 40px;">
				<span>첨부 파일</span>
				<br />
				<div id="articlefileChange"></div>
			</div>
			<button type="submit" style="border: 1px solid #ddd; outline: none;">전송</button>
		</form>
	</div>
	<!-- 파일 업로드 스크립트 -->
	<script>
		$(document).ready(function()
		// input file 파일 첨부시 fileCheck 함수 실행
		{
			$("#input_file").on("change", fileCheck);
		});

		/**
		 * 첨부파일로직
		 */
		$(function() {
			$('#btn-upload').click(function(e) {
				e.preventDefault();
				$('#input_file').click();
			});
		});

		// 파일 현재 필드 숫자 totalCount랑 비교값
		var fileCount = 0;
		// 해당 숫자를 수정하여 전체 업로드 갯수를 정한다.
		var totalCount = 5;
		// 파일 고유넘버
		var fileNum = 0;
		// 첨부파일 배열
		var content_files = new Array();

		function fileCheck(e) {
			var files = e.target.files;

			// 파일 배열 담기
			var filesArr = Array.prototype.slice.call(files);

			// 파일 개수 확인 및 제한
			if (fileCount + filesArr.length > totalCount) {
				$.alert('파일은 최대 ' + totalCount + '개까지 업로드 할 수 있습니다.');
				return;
			} else {
				fileCount = fileCount + filesArr.length;
			}

			// 각각의 파일 배열담기 및 기타
			filesArr
					.forEach(function(f) {
						var reader = new FileReader();
						reader.onload = function(e) {
							content_files.push(f);
							$('#articlefileChange')
									.append(
											'<div id="file'
													+ fileNum
													+ '" onclick="fileDelete(\'file'
													+ fileNum
													+ '\')">'
													+ '<font style="font-size:12px">'
													+ f.name
													+ '</font>'
													+ '<img src="/img/icon_minus.png" style="width:20px; height:auto; vertical-align: middle; cursor: pointer;"/>'
													+ '<div/>');
							fileNum++;
						};
						reader.readAsDataURL(f);
					});
			console.log(content_files);
			//초기화 한다.
			$("#input_file").val("");
		}

		// 파일 부분 삭제 함수
		function fileDelete(fileNum) {
			var no = fileNum.replace(/[^0-9]/g, "");
			content_files[no].is_delete = true;
			$('#' + fileNum).remove();
			fileCount--;
			console.log(content_files);
		}
	</script>
</body>
</html>
