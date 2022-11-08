# coffeeShop
JAVA | SPRING | THYMELEAF : Website and Backend Management System for Amsterdam Coffee Shop
<br>
Commit Note: 
	- Redesigned Back End for Content Data
		- Front End Functionality for Admin updated
		- Images and Text Content Are Functioning
			- Need To Change Admin Controller 'viewModel'(s) to a main admin content component
				- When an admin logs in, a 'Content' component class is filled with all of the master users' data
				- The 'Content' component is then sent to the HTML via Thymeleaf and content tags need to be upated with th:if statements
					- The Image /admin/getIMG HTTP Response may need to be reformated to accept the new 'content' component field/attribute

<br>
To Do:
	- complete Back End Item Creation
		- create master DEFAULT Item JSONArray and parse to a string to store as static file
		once all items are uploaded.  
	- complete front end admin dash functions
		- change password
		- change email
		- logout
		- go to site
	- import new data to main site
	- complete JS functionality for main site