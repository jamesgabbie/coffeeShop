							// ITEM/MENU Content Functions

// ############################
// ADD MENU ITEM FUNCTIONS
// ############################

// - Item Input	Variables
var inputType = $("#type-input");
var inputCat = $("#category-input");
var inputName = $("#name-input");
var inputDetail = $("#detail-input");
var inputPrice = $("#price-input");

// - Item Value	Variables
var valueType = $("#menu-type-input");
var valueCat = $("#menu-category-input");
var valueName = $("#menu-name-input");
var valueDetail = $("#menu-detail-input");
var valuePrice = $("#menu-price-input");

// - AddItemForm
var addItemForm = $("#addItemForm");

// - Item Action Buttons
var addItemBtn = $("#addItem-button");
var activeURL = "";

//OnClick Add Form Button Clicked :: matches form values with table inputs and sends call to send post request
// Send addItem form
function sendItemForm(){
	addItemForm.prop('action', activeURL); 
	addItemForm.submit();
}
function addItem(){
	activeURL = "/admin/addItem"
	// fill form values
	valueType.val(inputType.val());
	valueCat.val(inputCat.val()); 
	valueName.val(inputName.val());
	valueDetail.val(inputDetail.val());
	valuePrice.val(inputPrice.val());
	
	
	var objArray = [inputType, inputCat, inputName, inputPrice];
	objArray.forEach(el =>{
		el.removeClass('needInputAlert');
	})
	var errorObjArray = []
	//Get UnValidated Input Values
	if(inputType.val() == "-Choose-"){
		errorObjArray.push(inputType);
	}
	if(inputCat.val() == "-Choose-"){
		errorObjArray.push(inputCat);
	}
	if(inputName.val() == "" || inputName.val() == null){
		errorObjArray.push(inputName);
	}
	if(inputPrice.val() == "" || inputPrice.val() == null){
		errorObjArray.push(inputPrice);
	}
	if(errorObjArray.length > 0){
		console.log("Test");
		for(var i = 0; i <errorObjArray.length; i++){
			errorObjArray[i].addClass('needInputAlert');
		}
	} else{
		sendItemForm();
	}
}

// SELECT-INPUT FUNCTIONS :: Category options displayed based on 
var typeChangeNum = 0;
function configCatagory(val){
	var cafeCats = $(".cafe-option");
	var kitchenCats = $(".kitchen-option");

	if(val == 'kitchen'){
		inputCat.attr('disabled', false);
		cafeCats.hide(function(){
			kitchenCats.show();
		});
	} else if(val == 'cafe'){
		inputCat.attr('disabled', false);
		kitchenCats.hide(function(){
			cafeCats.show();
		})
	} else if(val == "-Choose-"){
		inputCat.attr('disabled', true);
		cafeCats.hide();
		kitchenCats.hide();
	};
	
	
	if(typeChangeNum == 0){
		typeChangeNum++
	} else if(typeChangeNum != 0){
		inputCat.val('-Choose-')		
	}
}

// ############################
// UPDATE MENU ITEM FUNCTIONS
// ############################
//		-Variables


var activeItemId;
var activeItemRow;

var activeItemTypeInput;
var activeItemCategoryInput;
var activeItemNameInput;
var activeItemDetailInput;
var activeItemPriceInput;

var activeActionBox;
var activeActionConfirmBox;

var activeEditItemBtn;
var activeTrashItemBtn;
var activeUpdateItemBtn;
var activeCancelItemBtn;

var activeUpdateUrl = "";
var contentChangeClicker = 0;

function nullifyActiveVariables(){
	activeItemId = null;
	activeItemRow = null;
	activeItemTypeInput = null;
	activeItemCategoryInput = null;
	activeItemNameInput = null;
	activeItemDetailInput = null;
	activeItemPriceInput = null;
	activeActionBox = null;
	activeActionConfirmBox = null;
	activeEditItemBtn = null;
	activeTrashItemBtn = null;
	activeUpdateItemBtn = null;
	activeCancelItemBtn = null;
	activeUpdateUrl = null;
	contentChangeClicker = 0;
}

function resetActionBoxes(){
	$('.dash-item-action-td').removeClass('hidden-td');
	$('.confirm-action-box').addClass('hidden-td');
	$('.delete-item-button').hide();
	$('.update-item-button').show();
}

function resetRowStyles(){
	$('.menu-row').removeClass('showcase-row');
}


function enableItemInputs(){
	activeItemTypeInput.prop("disabled", false);
	activeItemCategoryInput.prop("disabled", false);
	activeItemNameInput.prop("disabled", false);
	activeItemDetailInput.prop("disabled", false);
	activeItemPriceInput.prop("disabled", false);
}

function disableItemInputs(){
	$('.dash-viewItem-input').prop("disabled", true);
}

function cancelUpdateItem(){
	resetActionBoxes();
	resetRowStyles();
	disableItemInputs();
	$('.dash-viewItem-input').removeClass('needInputAlert');
}


function configureUpdateCatagory(val){
	var cafeOptions = $(".cafe-option");
	var kitchenOptions = $(".kitchen-option");
	if(val == 'kitchen' || val == 'Kitchen'){
		activeItemCategoryInput.attr('disabled', false);
		cafeOptions.hide(function(){
			kitchenOptions.show();
		});
	} else if(val == 'cafe' || val == 'Cafe'){
		activeItemCategoryInput.attr('disabled', false);
		kitchenOptions.hide(function(){
			cafeOptions.show();
		})
	} else if(val == "-Choose-" || val == ''){
		activeItemCategoryInput.attr('disabled', true);
		cafeOptions.hide();
		kitchenOptions.hide();
	};

	if(contentChangeClicker == 0){
		contentChangeClicker++;
	} else if(contentChangeClicker != 0){
		activeItemCategoryInput.val('-Choose-');	
	}
}


function deleteItem(){
	var activeTrashItemLink = $('#trashItemLink');
	var trashURL = "/admin/deleteItem/"+activeItemId;
	activeTrashItemLink.attr('href', trashURL);
	console.log(activeTrashItemLink.attr('href'));
	activeTrashItemLink[0].click();
	
}

function showcaseItemRow(el, type){
	resetRowStyles();
	resetActionBoxes();
	nullifyActiveVariables();
	disableItemInputs();
	// Assign Active Variables
	activeItemId = $(el).attr('id');
	activeEditItemBtn = $(el);
	activeActionBox = $(el).parent('td.dash-item-action-td');
	activeTrashItemBtn = activeActionBox.children('i.trash-item-button');
	activeActionConfirmBox = activeActionBox.siblings('td.confirm-action-box');
	activeUpdateItemBtn = activeActionConfirmBox.children('i.update-item-button');
	activeCancelItemBtn = activeActionConfirmBox.children('i.cancel-item-button');
	
	activeItemRow = activeActionBox.parent('tr.menu-row');
	activeItemTypeInput = activeItemRow.children('td').children('.type-input');
	activeItemCategoryInput = activeItemRow.children('td').children('.category-input');
	activeItemNameInput = activeItemRow.children('td').children('.name-input');
	activeItemDetailInput = activeItemRow.children('td').children('.detail-input');
	activeItemPriceInput = activeItemRow.children('td').children('.price-input');
	
	
	if(type == 'edit'){
		enableItemInputs();
		console.log(activeItemTypeInput.val());
		activeItemRow.addClass('showcase-row');
	} else if(type == 'trash'){
		$('.delete-item-button').show();
		$('.update-item-button').hide();
	};
	// Config Input Values and Functionality
	
	

	// Apply Styling
	activeActionBox.toggleClass('hidden-td');
	activeActionConfirmBox.toggleClass('hidden-td');
	
	
};



function updateItem(){
	activeURL = "/admin/updateItem/"+activeItemId;
	// fill form values
	valueType.val(activeItemTypeInput.val());
	valueCat.val(activeItemCategoryInput.val()); 
	valueName.val(activeItemNameInput.val());
	valueDetail.val(activeItemDetailInput.val());
	valuePrice.val(activeItemPriceInput.val());
	
	
	var objArray = [activeItemTypeInput, activeItemCategoryInput, activeItemNameInput, activeItemPriceInput];
	objArray.forEach(el =>{
		el.removeClass('needInputAlert');
	})
	var errorObjArray = []
	//Get UnValidated Input Values
	if(activeItemTypeInput.val() == "-Choose-"){
		errorObjArray.push(activeItemTypeInput);
	}
	if(activeItemCategoryInput.val() == "-Choose-"){
		errorObjArray.push(activeItemCategoryInput);
	}
	if(activeItemNameInput.val() == "" || activeItemNameInput.val() == null){
		errorObjArray.push(activeItemNameInput);
	}
	if(activeItemPriceInput.val() == "" || activeItemPriceInput.val() == null){
		errorObjArray.push(activeItemPriceInput);
	}
	if(errorObjArray.length > 0){
		console.log("Test");
		for(var i = 0; i <errorObjArray.length; i++){
			errorObjArray[i].addClass('needInputAlert');
		}
	} else{
		sendItemForm();
	}
}


