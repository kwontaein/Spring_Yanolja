
// 체크박스 상태가 변경될 때 호출되는 함수
function handleCheckboxChange() {
	const nameInput = $('#nameinput');
	const nameInput2 = $('#nameinput2');

	const phoneInput = $('#phoneinput');
	const phoneInput2 = $('#phoneinput2');

	// 체크박스가 선택되면 nameinput2의 값을 nameinput과 동일하게 설정
	if (samePersonCheckbox.checked) {
		nameInput2.val(nameInput.val());
		if (phoneInput) { //phoneInput이 존재할 경우에 실행
			phoneInput2.val(phoneInput.val());
		}
	} else {
		// 체크박스가 선택 해제되면 nameinput2를 초기화
		nameInput2.value = '';
		phoneInput2.value = '';
	}
}

function checkAgreements() {
	// 모든 필수 약관 체크박스를 선택합니다.
	var requiredCheckboxes = $('input[name="agreed"][value^="require"]');
	var allRequiredChecked = true;

	// 모든 필수 약관이 선택되었는지 확인합니다.
	requiredCheckboxes.each(function() {
		if (!$(this).prop('checked')) {
			allRequiredChecked = false;
			return false; // break the loop
		}
	});

	// 결제 버튼을 선택합니다.
	var paymentButton = $('.payment');

	// 모든 필수 약관이 선택되었으면 버튼을 활성화하고, 그렇지 않으면 비활성화합니다.
	if (allRequiredChecked) {
		paymentButton.prop('disabled', false);
		paymentButton.css('background-color', 'deeppink');
	} else {
		paymentButton.prop('disabled', true);
		paymentButton.css('background-color', '#cccccc');
	}
}

function selectAll(checkbox) {
	// 전체 동의 체크박스의 상태에 따라 모든 약관 체크박스를 선택 또는 해제합니다.
	var isChecked = checkbox.checked;
	$('input[name="agreed"]').prop('checked', isChecked);

	// 체크 상태 변경 후, 약관 체크 상태를 확인하여 결제 버튼을 업데이트합니다.
	checkAgreements();
}
// 모달 열기
function openModal() {
	$("#myModal").css("display", "block");
}

// 모달 닫기
function closeModal() {
	$("#myModal").css("display", "none");
}

// 모달 열기
function openCModal() {
	$("#couponModal").css("display", "block");
	$("body").css("overflow", "hidden");
}

// 모달 닫기
function closeCModal() {
	$("#couponModal").css("display", "none");
	$("body").css("overflow", "auto");
}

// 라디오 체크 값에 따라 다르게
function showSelectedValue(radio) {
	var useCoupon = $("#choose_coupon");
	if (radio.value == 'select') {
		useCoupon.css("display", "flex");
	} else {
		useCoupon.css("display", "none");
	}
	useCoupon.click(function() {
		openCcModal();
	});
}

function showSelectedValues(radio, index) {
	var useCoupon = $("#choose_coupon" + index);
	if (radio.value == 'select' + index) {
		useCoupon.css("display", "flex");
	} else {
		useCoupon.css("display", "none");
	}
	useCoupon.click(function() {
		openCcModal(index);
	});
}
// 결제 성공 메시지 표시 및 모달 열기
function displayPaymentSuccessMessage() {
	openModal(); // 모달 열기
}
// 결제 성공 메시지 표시 및 모달 열기
function payCancel() {
	$.ajax({
		url: '/refund', // 서버의 엔드포인트 URL
		method: 'post',     // HTTP GET 요청
		success: function(data) {
			// 서버로부터 받은 데이터를 화면에 표시
			alert("결제가 취소되었습니다.");
			closeModal(); // 모달 닫기
		},
		error: function() {
			// 에러 처리
			alert("에러");
		}
	});

}
// 결제 성공 메시지 표시 및 모달 열기
function payAgree() {
	$.ajax({
		url: '/Reserve_Agree', // 서버의 엔드포인트 URL
		method: 'post',     // HTTP GET 요청
		success: function(data) {
			// 서버로부터 받은 데이터를 화면에 표시
			alert("결제 성공");
			closeModal(); // 모달 닫기
			window.location.href = '/Reserve_history?ordernumber=' + data;
		},
		error: function() {
			// 에러 처리
			alert("에러");
		}
	});
}

$(document).ready(
	function() {

		const coupon = document.getElementById('selectdiscount');

		// 클릭 이벤트를 추가
		coupon.addEventListener('click', function() {
			openCModal();
		});

		checkAgreements();
		// 체크박스 요소 가져오기
		const samePersonCheckbox = document.getElementById('samePersonCheckbox');


		const savedStartDate = sessionStorage
			.getItem('selectedStartDate');
		const savedEndDate = sessionStorage
			.getItem('selectedEndDate');

		let selectedStartDate = savedStartDate ? new Date(
			savedStartDate) : new Date();
		let selectedEndDate = savedEndDate ? new Date(savedEndDate)
			: new Date();

		if (selectedStartDate.getTime() === selectedEndDate
			.getTime()) {
			selectedEndDate.setDate(selectedEndDate.getDate() + 1);
		}
		const options = {
			month: '2-digit',
			day: '2-digit',
			weekday: 'short'
		};

		const formattedStartDate = selectedStartDate
			.toLocaleDateString("ko-KR", options);

		const formattedEndDate = selectedEndDate
			.toLocaleDateString("ko-KR", options);

		const options2 = {
			year: 'numeric',
			month: '2-digit',
			day: '2-digit',
			weekday: 'short'
		};

		const formattedStartDate2 = selectedStartDate
			.toLocaleDateString("ko-KR", options2);
		const formattedEndDate2 = selectedEndDate
			.toLocaleDateString("ko-KR", options2);

		const intime = $("#intime");
		const outtime = $("#outtime");

		const indate = $("#indate");
		const outdate = $("#outdate");

		const date = $("#date");

		const totalpricea = $("#totalpricea");
		const totalprice = $("#totalprice");
		const totalprice3 = $("#totalprice3");

		const paymentprice = $("#paymentprice");

		const discount = $("#discount");
		const discount2 = $("#discount2");
		const discount3 = $("#discount3");
		const reducePrice = $("#reducePrice");

		if (intime != null && outtime != null & roomPrice != 0) {
			const timeDifference = selectedEndDate.getTime()
				- selectedStartDate.getTime();
			const daysDifference = Math.floor(timeDifference
				/ (1000 * 60 * 60 * 24));
			price = roomPrice * daysDifference;
			console.log(price);


			intime.textContent = `${formattedStartDate2}`;
			outtime.textContent = ` ${formattedEndDate2} `;

			indate.textContent = `${formattedStartDate}`;
			outdate.textContent = ` ${formattedEndDate} `;

			date.textContent = `${daysDifference}박`;

			totalpricea.textContent = `${price}원`;
			totalprice.textContent = `${price}원`;
			totalprice3.textContent = `${price}원`;

			discount.textContent = `${(price) / 200}`;
			discount2.textContent = `${(price) / 200}`;
			discount3.textContent = `${(price) / 200}`;

			paymentprice.textContent = `${price}원 `;
		}

		var seemore = document.getElementById("seemore");
		var moreContent = document.querySelector(".more-content");

		if (seemore && moreContent) {
			seemore.addEventListener("click", function() {
				if (moreContent.style.display === "none" || moreContent.style.display === "") {
					moreContent.style.display = "block";
					seemore.innerText = "△"; // 내용이 보이면 △로 변경
				} else {
					moreContent.style.display = "none";
					seemore.innerText = "▽"; // 내용이 숨겨지면 ▽로 변경
				}
			});
		}


		var dismore = document.getElementById("dismore");
		var seemoredis = document.querySelector(".seemoredis");
		if (dismore && seemoredis) {
			dismore.addEventListener("click", function() {
				if (seemoredis.style.display === "none" || seemoredis.style.display === "") {
					seemoredis.style.display = "block";
					dismore.innerText = "△"; // 내용이 보이면 △로 변경
				} else {
					seemoredis.style.display = "none";
					dismore.innerText = "▽"; // 내용이 숨겨지면 ▽로 변경
				}
			});
		}
	});

