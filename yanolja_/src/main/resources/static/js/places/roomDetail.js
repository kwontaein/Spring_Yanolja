$(document).ready(function() {
	function getParameterByName(name, url = window.location.href) {
		name = name.replace(/[\[\]]/g, '\\$&');
		var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
			results = regex.exec(url);
		if (!results) return null;
		if (!results[2]) return '';
		return decodeURIComponent(results[2].replace(/\+/g, ' '));
	}

	function showModal(modalSelector) {
		$(modalSelector).css({
			'display': 'block',
			'height': '100%'
		});
		$('#head').css('display', 'none');
		$('body').css('overflow', 'hidden');
	}

	function hideModal(modalSelector) {
		$(modalSelector).css('height', '0');
		setTimeout(function() {
			$(modalSelector).css('display', 'none');
		}, 600);
		$('#head').css('display', 'inline-block');
		$('body').css('overflow', 'hidden');
	}

	const savedStartDate = sessionStorage.getItem('selectedStartDate');
	const savedEndDate = sessionStorage.getItem('selectedEndDate');

	let selectedStartDate = savedStartDate ? new Date(savedStartDate) : new Date();
	let selectedEndDate = savedEndDate ? new Date(savedEndDate) : new Date();

	if (selectedStartDate.getTime() === selectedEndDate.getTime()) {
		selectedEndDate.setDate(selectedEndDate.getDate() + 1);
	}

	const options = { weekday: 'short', month: '2-digit', day: '2-digit' };
	const formattedStartDate = selectedStartDate.toLocaleDateString("ko-KR", options);
	const formattedEndDate = selectedEndDate.toLocaleDateString("ko-KR", options);

	const options2 = { year: 'numeric', month: '2-digit', day: '2-digit', weekday: 'short' };
	const formattedStartDate2 = selectedStartDate.toLocaleDateString("ko-KR", options2);
	const formattedEndDate2 = selectedEndDate.toLocaleDateString("ko-KR", options2);


	const datechoice = document.getElementById("datechoice2");

	const rentalday = document.getElementById("rentalday");
	const totalprice = document.getElementById("totalprice");
	const roomprice = document.getElementById("roomprice");

	const modaldata1 = document.getElementById("startDate");
	const night = document.getElementById("night");
	const modaldata2 = document.getElementById("endDate");

	if (modaldata1 != null && modaldata2 != null) {
		const timeDifference = selectedEndDate.getTime() - selectedStartDate.getTime();
		const daysDifference = Math.floor(timeDifference / (1000 * 60 * 60 * 24));

		modaldata1.textContent = `${formattedStartDate2}`;
		modaldata2.textContent = ` ${formattedEndDate2} `;
		night.textContent = ` ${daysDifference}박`;
		rentalday.textContent = `(${daysDifference}박)`;

		const totalPrice = daysDifference * price;

		if (daysDifference > 1) {
			roomprice.textContent = `${totalPrice.toLocaleString()} / ${daysDifference}박 `
			totalprice.textContent = totalPrice.toLocaleString();
		} else if (daysDifference == 1) {
			roomprice.textContent = `${price.toLocaleString()}원`;
			totalprice.textContent = `${price.toLocaleString()}원`;
		}
	}
	if (datechoice) {
		if (selectedStartDate != null && formattedEndDate != null) {
			const timeDifference = selectedEndDate.getTime() - selectedStartDate.getTime();
			const daysDifference = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
			datechoice.textContent = `${formattedStartDate} ~ ${formattedEndDate} · ${daysDifference}박`;
		}
	}

	calendarAjax();
	reviewAjax();

	$('#datechoice, #datechoice2').click(function() {
		showModal('.calendar_modal');
	});

	$('#seedefaultinfo').click(function() {
		showModal('.defaultinfomodal');
	});

	$('#seereserveinfo').click(function() {
		showModal('.reserveinfomodal');
	});
	
	$('#seeallfacil').click(function() {
		showModal('.facilmodal');
	});
	
	$('#roomchoicebtn').click(function() {
		showModal('.reservemodal');
	});

	$('.close').click(function() {
		hideModal('.reserveinfomodal');
		hideModal('.defaultinfomodal');
		hideModal('.reservemodal');
		hideModal('.facilmodal');
	});

	function calendarAjax(roomid) {
		var roomid = getParameterByName('roomid');
		$.ajax({
			type: 'get',
			url: '/calendar',
			data: {
				roomid: roomid,
			},
			success: function(data) {
				$('.calendar_modal').html(data);
			}
		});
	}

	function reviewAjax(roomid) {
		var roomid = getParameterByName('roomid');
		$.ajax({
			type: 'get',
			url: '/Review',
			data: {
				roomid: roomid,
			},
			success: function(data) {
				$('.review').html(data);
			}
		});
	}

	$('.calendar_modal').on('click', '#headerClose', function() {
		hideModal('.calendar_modal');
	});
});
