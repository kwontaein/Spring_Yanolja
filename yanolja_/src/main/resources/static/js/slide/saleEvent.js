const swiper2 = new Swiper('.swiper', {
	// Optional parameters
	slidesPerView: 3.3,

	direction: 'horizontal',

	// Navigation arrows
	navigation: {
		nextEl: '.swiper-button-next-2',
		prevEl: '.swiper-button-prev-2',
	},
	speed: 500,
	touchRatio: 0.2,
});
