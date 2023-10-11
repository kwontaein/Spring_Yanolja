
// 체크박스 상태가 변경될 때 호출되는 함수
function handleCheckboxChange() {
	const nameInput = document.getElementById('nameinput');
	const nameInput2 = document.getElementById('nameinput2');

	const phoneInput = document.getElementById('phoneinput');
	const phoneInput2 = document.getElementById('phoneinput2');

	// 체크박스가 선택되면 nameinput2의 값을 nameinput과 동일하게 설정
	if (samePersonCheckbox.checked) {
		nameInput2.value = nameInput.value;
		if (phoneInput) { //phoneInput이 존재할 경우에 실행
			phoneInput2.value = phoneInput.value
		}
	} else {
		// 체크박스가 선택 해제되면 nameinput2를 초기화
		nameInput2.value = '';
		phoneInput2.value = '';
	}
}
function checkAgreements() {
	// 모든 필수 약관 체크박스를 가져옵니다.
	var requiredCheckboxes = document.querySelectorAll('input[name="agreed"][value^="require"]');
	var allRequiredChecked = true;

	// 모든 필수 약관이 선택되었는지 확인합니다.
	for (var i = 0; i < requiredCheckboxes.length; i++) {
		if (!requiredCheckboxes[i].checked) {
			allRequiredChecked = false;
			break;
		}
	}
	// 결제 버튼을 가져옵니다.
	var paymentButton = document.querySelector('.payment');

	// 모든 필수 약관이 선택되었으면 버튼을 활성화하고, 그렇지 않으면 비활성화합니다.
	if (allRequiredChecked) {
		paymentButton.disabled = false;
		paymentButton.style.backgroundColor = 'deeppink'; // 버튼의 배경색을 변경합니다.
	} else {
		paymentButton.disabled = true;
		paymentButton.style.backgroundColor = '#cccccc'; // 버튼의 배경색을 변경합니다.
	}
}

function selectAll(checkbox) {
	// 전체 동의 체크박스의 상태에 따라 모든 약관 체크박스를 선택 또는 해제합니다.
	var isChecked = checkbox.checked;
	var allAgreementCheckboxes = document.querySelectorAll('input[name="agreed"]');
	for (var i = 0; i < allAgreementCheckboxes.length; i++) {
		allAgreementCheckboxes[i].checked = isChecked;
	}

	// 체크 상태 변경 후, 약관 체크 상태를 확인하여 결제 버튼을 업데이트합니다.
	checkAgreements();
}

$(document).ready(
	function() {
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
			year: 'numeric',
			weekday: 'short',
			month: '2-digit',
			day: '2-digit'
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

		const intime = document.getElementById("intime");
		const outtime = document.getElementById("outtime");
		const date = document.getElementById("date");
		
		const totalpricea = document.getElementById("totalpricea");
		const totalprice = document.getElementById("totalprice");
		const totalprice3 = document.getElementById("totalprice3");
		
		const paymentprice = document.getElementById("paymentprice");
		
		const discount = document.getElementById("discount");
		const discount2 = document.getElementById("discount2");
		const discount3 = document.getElementById("discount3");

		if (intime != null && outtime != null) {
			const timeDifference = selectedEndDate.getTime()
				- selectedStartDate.getTime();
			const daysDifference = Math.floor(timeDifference
				/ (1000 * 60 * 60 * 24));
			price = roomPrice * daysDifference;
			console.log(price);
			intime.textContent = `${formattedStartDate2}`;
			outtime.textContent = ` ${formattedEndDate2} `;
			
			date.textContent = `${daysDifference}박`;
			
			totalpricea.textContent = `${price}`;
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