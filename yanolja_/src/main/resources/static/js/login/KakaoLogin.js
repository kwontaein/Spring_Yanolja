/**
 * 
 */
function openKakaoLoginPopup() {
	// 팝업 창을 열기 위한 JavaScript 코드
	const popupWindow = window.open('about:blank', 'KakaoLoginPopup', 'width=600,height=800');

	// Kakao 로그인 페이지 URL
	const kakaoLoginUrl = '${kakaoUrl}'; // 실제 URL로 변경해야 합니다.

	// 팝업 창에서 Kakao 로그인 페이지를 로드
	popupWindow.location.href = kakaoLoginUrl;

	// 팝업이 닫힐 때 이벤트 리스너 등록
	if (popupWindow) {
		const checkPopupClosed = setInterval(() => {
			if (popupWindow.closed) {
				clearInterval(checkPopupClosed);
				// 팝업이 닫혔을 때 실행할 코드를 여기에 작성
				window.location.href = '/mypage'; // mypage URL로 리디렉션
			}
		}, 1000); // 주기적으로 팝업이 닫힌지 확인
	}
}