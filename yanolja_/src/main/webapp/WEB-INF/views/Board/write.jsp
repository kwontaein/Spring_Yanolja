<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<title>글작성 페이지</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/dayjs/1.10.7/dayjs.min.js"></script>
<link rel="stylesheet" href="${path}/css/Main.css"/>
</head>
<body>
	<%@ include file="../../layout/header.jsp"%>
	<div class="page_tits">
		<h3>게시판 관리</h3>
		<p class="path">
			<strong>현재 위치 :</strong> <span>게시판 관리</span> <span>리스트형</span> <span>글작성</span>
		</p>
	</div>
	<div class="content">
		<section>
			<form id="saveForm" method="post" autocomplete="off">
				<!--/* 게시글 수정인 경우, 서버로 전달할 게시글 번호 (PK) */-->
				<c:if test="${post.id != null}">
					<input type="hidden" id="id" name="id" value="${post.id}">
				</c:if>

				<!--/* 서버로 전달할 공지글 여부 */-->
				<input type="hidden" id="noticeYn" name="noticeYn" />
				<table class="tb tb_row">
					<tbody>
						<tr>
							<th scope="row">공지글</th>
							<td>
								<span class="chkbox"><input type="checkbox" id="isNotice" name="isNotice" class="chk" /><i></i><label for="isNotice"> 설정</label></span>
							</td>
							<c:choose>
								<c:when test="${post.id != null}">
									<th scope="row">수정일</th>
								</c:when>
								<c:otherwise>
									<th scope="row">등록일</th>
								</c:otherwise>
							</c:choose>
							<td colspan="3">
								<input type="text" id="createdDate" name="createdDate" readonly />
							</td>
						</tr>

						<tr>
							<th>제목 <span class="es">필수 입력</span></th>
							<td colspan="3">
								<c:choose>
									<c:when test="${post.title != null}">
										<input type="text" id="title" name="title" maxlength="50" placeholder="제목을 입력해 주세요." value="${post.title}" />
									</c:when>
									<c:otherwise>
										<input type="text" id="title" name="title" maxlength="50" placeholder="제목을 입력해 주세요." />
									</c:otherwise>
								</c:choose>

							</td>
						</tr>

						<tr>
							<th>이름 <span class="es">필수 입력</span></th>
							<td colspan="3">
								<c:choose>
									<c:when test="${post.writer != null}">
										<input type="text" id="writer" name="writer" maxlength="10" placeholder="이름을 입력해 주세요." value="${post.writer }" />
									</c:when>
									<c:otherwise>
										<input type="text" id="writer" name="writer" maxlength="10" placeholder="이름을 입력해 주세요." />
									</c:otherwise>
								</c:choose>

							</td>
						</tr>

						<tr>
							<th>내용 <span class="es">필수 입력</span></th>
							<td colspan="3">
								<c:choose>
									<c:when test="${post.content != null}">
										<textarea id="content" name="content" cols="50" rows="10" placeholder="내용을 입력해 주세요.">${post.content}</textarea>
									</c:when>
									<c:otherwise>
										<textarea id="content" name="content" cols="50" rows="10" placeholder="내용을 입력해 주세요."></textarea>
									</c:otherwise>
								</c:choose>

							</td>
						</tr>
					</tbody>
				</table>
			</form>
			<script type="text/javascript"> 
        /*<![CDATA[*/

            window.onload = () => {
                initCreatedDate();
            }
            
            
            // 등록일 초기화
            function initCreatedDate() {
                document.getElementById('createdDate').value = dayjs().format('YYYY-MM-DD');
            }


            // 게시글 저장(수정)
            function savePost() {
                const form = document.getElementById('saveForm');
                const fields = [form.title, form.writer, form.content];
                const fieldNames = ['제목', '이름', '내용'];

            	/*  for (let i = 0, len = fields.length; i < len; i++) {
                    isValid(fields[i], fieldNames[i]);
                } */

                document.getElementById('saveBtn').disabled = true;
                form.noticeYn.value = form.isNotice.checked;
                form.action =  ${post.id == null}  ? '/board/write.do' : '/board/update.do';
                form.submit();
            }

        /*]]>*/
        </script>
			<p class="btn_set">
				<button type="button" id="saveBtn" onclick="savePost();" class="btns btn_st3 btn_mid">저장</button>
				<a href="/board/list.do" class="btns btn_bdr3 btn_mid">뒤로</a>
			</p>
		</section>
	</div>
	<%@ include file="../../layout/footer.jsp"%>
	<!--/* .content */-->
</body>
</html>