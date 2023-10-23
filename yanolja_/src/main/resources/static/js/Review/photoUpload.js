$(document).ready(function() {
	// input file 파일 첨부시 fileCheck 함수 실행
	$("#input_file").on("change", fileCheck);

});
// 파일 첨부 로직
$(function() {
	$('#btn-upload').click(function(e) {
		e.preventDefault(); 
		console.log(fileCount);
		if (fileCount < totalCount) {
            $('#input_file').click();
        } else {
            alert('이미지는 최대 ' + totalCount + '개까지 업로드 가능합니다.');
        }
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
		alert('파일은 최대 ' + totalCount + '개까지 업로드 할 수 있습니다.');
		return;
	} else {
		fileCount = fileCount + filesArr.length;
	}

	// 각각의 파일 배열담기 및 기타
	filesArr
		.forEach(function(f) {
			// 파일의 확장자 확인 (대소문자 구분 없이)
			var ext = f.name.split('.').pop().toLowerCase();
			if (ext !== 'jpg' && ext !== 'jpeg' && ext !== 'png') {
				alert('이미지 형식의 파일만 업로드할 수 있습니다.');
				return;
			}
			var reader = new FileReader();
			reader.onload = function(e) {
				content_files.push(f);
				var img = $('<img id="file' + fileNum
					+ '"onclick = "fileDelete(\'' + fileNum
					+ '\')" src="' + e.target.result + '" style="max-width: 200px; max-height: 132px;" />'); // 이미지 미리보기 추가
				$('#articlefileChange').append(img); // 이미지를 표시할 컨테이너 추가
				fileNum++;
			};
			reader.readAsDataURL(f);
		});
	console.log(content_files);
	// 초기화 한다.
	$("#input_file").val("");
}

// 파일 부분 삭제 함수
function fileDelete(fileNum) {
	content_files.splice(fileNum, 1); // 해당 인덱스의 요소를 배열에서 제거
	$('#file' + fileNum).remove(); // 연결된 UI 요소를 제거
	console.log('#file' + fileNum);
	fileCount--;
	console.log(content_files);
}