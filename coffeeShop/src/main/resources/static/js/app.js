window.onload = function() { // can also use window.addEventListener('load', (event) => {
    alert('Page loaded');

  };

/* Variables */
	/* NavBar Variables */
	var navBar = $("#navBar");
	var mstrContainer = $("#mstr-view-container");
	/* Welcome Section Variables */
	var welcomeSection = $('#welcome-section');
	var welcomeLogo = $("#welcome-title");
	var welcomeSubContainer = $("#welcome-subtitle-container");
	var welcomeSubText = $("#welcome-subtitle");
	var welcomeImage = $("#welcome-image");

/* Locomotie Scroll MSTR Constructor */
const scroll = new LocomotiveScroll({
    el: document.querySelector('[data-scroll-container]'),
    smooth: true,
    multiplier: .3,
    lerp: .1,
    mobile: {
        smooth: false
    },
});
	
	
/* Locomotive CALLS */
scroll.on('call', (value, args, obj) => {
	/* Fade In Welcome Message */
	/* if(value === '#'){
		if(args ==='enter'){
			
		};
	}; */
});


