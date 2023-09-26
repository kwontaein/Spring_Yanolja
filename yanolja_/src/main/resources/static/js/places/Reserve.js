$(document).ready(
	function() {
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

		console.log(formattedStartDate2);
		console.log(formattedEndDate2);

		const intime = document.getElementById("intime");
		const outtime = document.getElementById("outtime");
		const date = document.getElementById("date");
		const totalprice = document.getElementById("totalprice");
		const totalprice2 = document.getElementById("totalprice2");
		const totalprice3 = document.getElementById("totalprice3");
		const paymentprice = document.getElementById("paymentprice");
		const discount = document.getElementById("discount");
		if (intime != null && outtime != null) {
			const timeDifference = selectedEndDate.getTime()
				- selectedStartDate.getTime();
			const daysDifference = Math.floor(timeDifference
				/ (1000 * 60 * 60 * 24));

			intime.textContent = `${formattedStartDate2}`;
			outtime.textContent = ` ${formattedEndDate2} `;
			date.textContent = `${daysDifference}박`;
			totalprice.textContent = `${roomPrice * daysDifference}`;
			totalprice2.textContent = `${roomPrice * daysDifference}원`;
			totalprice3.textContent = `${roomPrice * daysDifference}원`;
			discount.textContent =`${(roomPrice * daysDifference)/200}`;
			paymentprice.textContent = `${roomPrice * daysDifference}원 `;
		}
	});