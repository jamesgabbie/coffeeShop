var loadingCover = $("#loading-cover");
$(window).on('load', function(){
	loadingCover.fadeOut(400);
})


const scroll = new LocomotiveScroll({
    el: document.querySelector('[data-scroll-container]'),
    smooth: true,
    multiplier: .5,
    firefoxMultiplier: .75,
    lerp: .1,
    smartphone: {
	        smooth: false
	    },
	tablet: {
	        smooth: true
	    },
	firefoxMultiplier: .5,
	reloadOnContextChange : true
	
});


var cafeListContainer = $("#cafe-menu-section").find(".menu-list");
var kitchenListContainer = $("#kitchen-menu-section").find(".menu-list");
var cafeMenuList = $("#cafe-menu-section").find( ".menu-item" );
var kitchenMenuList = $("#kitchen-menu-section").find( ".menu-item" );

function showMenuItems(type){
	if(type != null){
		if(type == 'cafe'){
			var dtime = 100;
			cafeMenuList.each(function() {
				setTimeout(()=>{
					$(this).fadeTo(1000, 1);
				}, dtime);
				dtime = (dtime+70);
			});
			setTimeout(()=>{
				cafeListContainer.each(function(){
					$(this).css("border-bottom", ".5px solid var(--darkBrown)");
				});
			}, 1000);
		};
		if(type == 'kitchen'){
			var dtime = 100;
			kitchenMenuList.each(function() {
				setTimeout(()=>{
					$(this).fadeTo(1000, 1);
				}, dtime);
				dtime = (dtime+70);
			});
			kitchenListContainer.each(function(){
				setTimeout(()=> {
					$(this).css("border-bottom", ".5px solid var(--darkBrown)");
				}, 1000);
			});	
		};
	};
};


var ownerImage = $("#owner-image");
var locationImage = $("#location-img");
function focusImage(imgLoc){
	if(imgLoc != null){
		if(imgLoc == 'about'){
		};
	}
}
// LOCO-SCROLL DATA-CALL FUNCTIONS
scroll.on('call', func => {
    if(func != null){
		if(func == 'showCafeItems'){
			showMenuItems('cafe');
		}
		if(func == 'showKitchenItems'){
			showMenuItems('kitchen');
		}
		if(func == 'focusAboutImage'){
			ownerImage.css({transform:'scale(1)', opacity:'1', transition: 'all 1s ease'});
		}
		if(func == 'focusLocationImage'){
			locationImage.css({transform:'scale(1)', opacity:'1', transition: 'all 1s ease'});
		}
	}
});


// LOCO-SCROLL ON-SCROLL FUNCTIONS
scroll.on('scroll', (position) =>{
	var p = position.scroll.y.toFixed(0);
	if (p < 800){
			$('#top-link').fadeOut();
		} else {
			$('#top-link').fadeIn('slow');
		};
})


// CALL FROM HTML : Loco-Scroll 'scroll' call via '.canvas-nav-link'
function goToSection(section){
	var sctn = document.getElementById(section);
	scroll.scrollTo(sctn);
}


//HIDDEN ANCHOR TAG FOR CONTENT LINKS
var linkTag = $("#link-tag");
var yelpUrl = "https://www.yelp.com/biz/de-koffieschenkerij-amsterdam";
var instaUrl = "https://www.instagram.com/koffieschenkerij/?hl=en";
var mapUrl = "http://maps.google.com/?q=Oudekerksplein 27, 1012 GX Amsterdam, Netherlands";
function goToLink(linkType){
	if(linkType != null){
		if(linkType == "yelp"){
			linkTag.attr("href", yelpUrl);
			linkTag[0].click();
		} else if(linkType == "map"){
			linkTag.attr("href", mapUrl);
			linkTag[0].click();
		} else if(linkType == "instagram"){
			linkTag.attr("href", instaUrl);
			linkTag[0].click();
		}
	}
}