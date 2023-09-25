const swiper2 = new Swiper('.swiper', {
	// Optional parameters
	slidesPerView: 3.3,

	direction: 'horizontal',

	// Navigation arrows
	navigation: {
		nextEl: '.swiper-button-next',
		prevEl: '.swiper-button-prev',
	},
	speed: 500,
	touchRatio: 0.2,
});
