$(document).ready(function() {
      var jspCode = '<jsp:include page="InsertCards.jsp"></jsp:include>';
      var isCodeVisible = false;
      
      // Event handler for the "Generate" button
      $(document).on("click", "#generateBtn", function() {
        var jspCodeContainer = $("#jspCodeContainer");
        var addButton = $("#addButton");
        /*var inputContainer = $("<div>");
        var param1 = $("<input>").attr("type", "text").attr("id", "param1").attr("placeholder", "Parameter 1");
        var param2 = $("<input>").attr("type", "text").attr("id", "param2").attr("placeholder", "Parameter 2");
        var sendButton = $("<button>").text("Send Parameters");*/
        
        // Event handler for the "Add Payment Method" button
        $(document).off("click", "#addButton").on("click", "#addButton", function() {
          inputContainer.append(jspCode);
          inputContainer.append(sendButton);
          addButton.slideUp(); // Slide up the "Add Payment Method" button
        });
        
        // Event handler for the "Send Parameters" button
        $(document).off("click", "#sendButton").on("click", "#sendButton", function() {
          var param1Value = $("#param1").val();
          var param2Value = $("#param2").val();
          
          // Send the parameters to the servlet via AJAX
          $.ajax({
            url: "your-servlet-url", // Replace with the actual servlet URL
            method: "POST",
            data: { param1: param1Value, param2: param2Value },
            success: function() {
              alert("Parameters sent successfully!");
              
              // Update the payment methods by reloading the JSP code container
              jspCodeContainer.load("your-jsp-page.jsp #jspCodeContainer > *", function() {
                addButton.slideDown(); // Slide down the "Add Payment Method" button
                inputContainer.empty();
              });
            },
            error: function() {
              alert("Error occurred while sending parameters!");
            }
          });
        });
        
        var content = $("#content");
        content.empty().append(jspCodeContainer, addButton);
        
        if (isCodeVisible) {
          jspCodeContainer.slideUp(); // Slide up the generated code
          addButton.slideUp(); // Slide up the "Add Payment Method" button
          $("#generateBtn").text("Generate"); // Change the button text
          isCodeVisible = false;
        } else {
          jspCodeContainer.html(jspCode).slideDown(); // Slide down the generated code
          addButton.slideDown(); // Slide down the "Add Payment Method" button
          content.append(inputContainer);
          $("#generateBtn").text("Hide Generated Code"); // Change the button text
          isCodeVisible = true;
        }
      });
    });
    
/**
 * This jQuery code provides functionality for generating and managing payment methods in a web application. 
 * Here's a description of what the code does:

	1. The code sets up event handlers and initializes variables when the document is ready.

	2. When the "Generate" button is clicked, the code performs the following actions:
   	- It retrieves the JSP code container and the "Add Payment Method" button.
   	- It creates a new div element to hold the input form for adding payment methods.
  	- It creates input fields for capturing parameter values.
   	- It creates a "Send Parameters" button.
   	- It attaches an event handler to the "Add Payment Method" button, 
   		which shows the input form and hides the button when clicked.
   	- It attaches an event handler to the "Send Parameters" button, 
   		which sends the parameter values to a servlet via AJAX and updates the payment methods.
   	- It prepares the content container by emptying its contents.
   	- If the generated code is already visible, it slides up the code container and the "Add Payment Method" button, 
   		changes the text of the "Generate" button to "Generate", and sets the visibility flag to false.
   	- If the generated code is not visible, it inserts the JSP code into the code container and slides it down, 
   		along with the "Add Payment Method" button. It also appends the input form to the content container, changes the text of the "Generate" button to "Hide Generated Code", and sets the visibility flag to true.

	3. The code sets up the initial HTML structure, including the "Generate" button, 
		the content container, the JSP code container (hidden by default), and the "Add Payment Method" button 
		(hidden by default).

	Overall, this code provides a user interface for generating and managing payment methods 
	by dynamically loading JSP code, displaying an input form for adding new payment methods, 
	and sending parameter values to a servlet for processing.
 */