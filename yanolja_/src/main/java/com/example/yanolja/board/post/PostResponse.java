package com.example.yanolja.board.post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostResponse {
	 private Long id;                       // PK
	    private String title;                  // 제목
	    private String content;                // 내용
	    private String writer;                 // 작성자
	    private int viewCnt;                   // 조회 수
	    private Boolean noticeYn;              // 공지글 여부
		private Boolean deleteYn;              // 삭제 여부
	    private LocalDateTime createdDate;     // 생성일시
	    private String createdDate2;     // 생성일시
	    private LocalDateTime modifiedDate;    // 최종 수정일시
	   
		public PostResponse(Long id, String title, String content, String writer, int viewCnt, Boolean noticeYn,
				Boolean deleteYn, LocalDateTime createdDate, LocalDateTime modifiedDate) {
			super();
			this.id = id;
			this.title = title;
			this.content = content;
			this.writer = writer;
			this.viewCnt = viewCnt;
			this.noticeYn = noticeYn;
			this.deleteYn = deleteYn;
			this.createdDate = createdDate;
			this.modifiedDate = modifiedDate;
		}

		public Long getId() {
			return id;
		}

		public String getTitle() {
			return title;
		}

		public String getContent() {
			return content;
		}

		public String getWriter() {
			return writer;
		}

		public int getViewCnt() {
			return viewCnt;
		}

		public Boolean getNoticeYn() {
			return noticeYn;
		}

		public Boolean getDeleteYn() {
			return deleteYn;
		}

		public String getCreatedDate() {
			DateTimeFormatter  newDtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			createdDate2 = createdDate.format(newDtFormat);
			return createdDate2;
		}

		public LocalDateTime getModifiedDate() {
			return modifiedDate;
		}
}
