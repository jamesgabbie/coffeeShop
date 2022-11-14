			//##################################
			//	Setting Functions
			//##################################
var siteBtn = $('#goToSiteLink');
var logoutBtn = $('#logoutDash');
function visitSite(){
	siteBtn[0].click();
}
function logoutDash(){
	logoutBtn[0].click();
}

var settingsMenuContainer = $("#settingsMenu");
var changePassContainer = $("#changePassForm");

function viewSettings(){
	changePassContainer.css("display", "none");
	if(settingsMenuContainer.css("display") == 'none'){
		settingsMenuContainer.fadeIn(300);
	} else if(settingsMenuContainer.css("display") != 'none'){
		settingsMenuContainer.fadeOut(300);
	}
}

function viewChangePassForm(){
	if(changePassContainer.css("display") == 'none'){
		changePassContainer.fadeIn(300);
	} else if(changePassContainer.css("display") != 'none'){
		changePassContainer.fadeOut(300);
	}
}

function sendChangePassword(){
	var changePassForm = $("#changePassForm");
	changePassForm.submit();
}



//TEXT CONTENT VIEW

// Text Variables
var textViewBox = document.getElementById("content-text-input");
var textViewTitle = document.getElementById("content-text-title");

var textViewEditBtn = $("#dash-text-edit-button");
var textViewSaveBtn = $("#dash-text-save-button");
var textViewCancelBtn = $("#dash-text-cancel-button");


var welcomeViewBtn = $("#welcome-textView-btn");
var welcomeValue = document.getElementById("welcomeText-view").value;

var aboutViewBtn = $("#about-textView-btn");
var aboutValue = document.getElementById("aboutText-view").value;

var contactViewBtn = $("#contact-textView-btn");
var contactValue = document.getElementById("contactText-view").value;


//Static Variables
var activeTextCall;
var activeTextTitle;
var activeText;
	
	
			//##################################
			//	Text Content Button Functions
			//##################################


//Click/View Functions to view text
function showTextContent(type){
	activeTextCall = type;
	//Remove Active BTN Class from All Text Btns	
	if(activeTextCall == "welcome"){
		activeText = welcomeValue;
		activeTextTitle = "-Welcome Message-"
		
	} else if(activeTextCall == "about"){
		activeText = aboutValue;
		activeTextTitle = "-About Message-"
	} else if(activeTextCall == "contact"){
		activeText = contactValue;
		activeTextTitle = "-Contact Message-"
	}
	textViewTitle.innerHTML = activeTextTitle;
	textViewBox.innerHTML = activeText;
	
	function highlightTextContentButton(type){
		$(".dash-content-list-title").removeClass('dash-content-list-title-active');
		
		if(type == "welcome"){
			welcomeViewBtn.addClass('dash-content-list-title-active');
		} else if(type == "about"){
			aboutViewBtn.addClass('dash-content-list-title-active');
		}else if(type == "contact"){
			contactViewBtn.addClass("dash-content-list-title-active");
		};
	};

	highlightTextContentButton(type);
}

setTimeout(()=>{
	showTextContent("welcome");
},500);


var contentTextInput = $("#content-text-input"); 
var contentTextForm = $("#updateTextContent-form");
var contentTextFormData = $("#updateTextContent-input");

// 
function updateTextContent(){
	var inputData = contentTextInput.val();
	contentTextFormData.val(inputData);
	var url = "/admin/updateContent/"+activeTextCall;
	contentTextForm.prop("action", url);
	contentTextFormData.prop("value", inputData);
	contentTextForm.submit();
}


// On-CLICK TEXT CONTENT ACTION FUNCTION
$(".dash-text-content-button").click(function(el){
 	var buttonType = el.target.dataset.buttonType;	
	if( buttonType == "edit"){
		
		textViewEditBtn.fadeOut(function(){
			textViewSaveBtn.fadeIn();
			textViewCancelBtn.fadeIn();
		});
		contentTextInput.prop("disabled", false);
	} else {
		if(buttonType == "cancel"){
			textViewCancelBtn.fadeOut();
			textViewSaveBtn.fadeOut(function(){
				textViewEditBtn.fadeIn();
			});	
			location.reload();
		} else if(buttonType == "save"){
			textViewCancelBtn.fadeOut();
			setTimeout(()=> {
				textViewSaveBtn.fadeOut(function(){
					updateTextContent();
				})
			}, 500)
		}
		contentTextInput.prop("disabled", true);
	};
})

//##################################
//Image Content Button Functions
//##################################
var imageForm = $("#updateImageContent-form");
var imageFormData = $("#updateImageContent-input");

var activeImgType = null;
var activeImgEl = null;

var activeImageIcon = null;
var activeImageTextTag = null;
var activeImageCancelBtn = null;
var activeImagePreviewContainer = null;
var actionUrl = "#";


function cancelImageUpload(){
	location.reload();
	
}


function showPreview(){
	console.log(activeImagePreviewContainer);
    var src = URL.createObjectURL(event.target.files[0]);
    var preview = activeImagePreviewContainer;
    preview.attr('src', src);
}

function submitContentImage(){
	imageForm.submit();
}


// ON-IMAGE FORM DATA INPUT CHANGE FUNCTION
imageFormData.change(function(){
	// Show IMG icon
	activeImageIcon = $(activeImgEl).children('.dash-img-icon');
	activeImageIcon.fadeIn();
	// Change IMG text
	activeImageTextTag = $(activeImgEl).children('.dash-img-text');
	activeImageTextTag.text("Confirm Upload");
	
	activeImageCancelBtn = $(activeImgEl).siblings('.dash-image-container').children('.dash-image-cancel-button');
	activeImageCancelBtn.fadeIn();

	//Show Preview of Image by setnding current file in input tag
	activeImagePreviewContainer = $(activeImgEl).siblings('.dash-image-container').children('.dash-image');
	showPreview();
		
	//Config img form attributes and path
	actionUrl="/admin/updateContentImage/"+activeImgType;
	imageForm.prop("action", actionUrl);
	
	//Change dash image button 'onclick' attribute from Upload to Submit
	$(activeImgEl).attr("onclick", "submitContentImage()");
})

//On-Dash-Image-Button' click : load dash image function is called
function loadDashImage(imgType, el){
	if(activeImageCancelBtn != null){
		cancelImageUpload();
	}
	activeImgType = imgType;
	activeImgEl = el;
	imageFormData.click();
};
	
	
	
	