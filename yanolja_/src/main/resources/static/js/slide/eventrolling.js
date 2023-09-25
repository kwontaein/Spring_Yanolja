const swiper = new Swiper('.swiper', {
	// Optional parameters
	slidesPerView: 2.2,

	direction: 'horizontal',
	loop: true,

	// Navigation arrows
	navigation: {
		nextEl: '.swiper-button-next',
		prevEl: '.swiper-button-prev',
	},
	autoplay: {
		delay: 3000,
		disableOnInteraction: false,
	},
	speed: 500,
	loop: true,
	touchRatio: 0.2,
	observer: true,
	observeParents: true
});
// Toggle Autoplay 버튼 클릭 시 자동 재생 시작 또는 중지
const toggleButton = document.getElementById("toggleAutoplay");
let isAutoplayEnabled = true;

toggleButton.addEventListener("click", function() {
	if (isAutoplayEnabled) {
		swiper.autoplay.stop();
		isAutoplayEnabled = false;
		toggleButton.textContent = "Start Autoplay";
	} else {
		swiper.autoplay.start();
		isAutoplayEnabled = true;
		toggleButton.textContent = "Stop Autoplay";
	}
});