/**
 * In summary, this script performs the following steps:
 * 
 * Retrieves the initial text from the campText element.
 * Creates an input field with the initial text value.
 * Creates a send button and hides it initially.
 * Creates a new paragraph element to replace the campText element.
 * Replaces the campText element with the input field and send button when the edit button is clicked.
 * Retrieves the new text from the input field when the send button is clicked.
 * Performs an AJAX POST request to a specified servlet URL.
 * Sends the new text as a parameter to the servlet.
 * Reloads the page after a successful AJAX call.Handles the error case if necessary.
 * Calls the setupEditSaveFunction function with the button and campText IDs 
 * 	to enable the functionality for a specific button and campText element.
 * 
 * This script allows you to dynamically edit the text content by clicking an edit button, 
 * enter new text in an input field, and send the updated text to a servlet using AJAX. 
 * After a successful update, the page is reloaded to reflect the changes made.
 */

$(document).ready(function() {
  function setupEditSaveFunction(buttonId, campTextId, parameter) {
    // Step 1: Get the initial text from the campText element
    var originalText = $('#' + campTextId).text();
    var editButton = $('#' + buttonId);

    editButton.click(function() {
		console.log("sono dentro");
      // Step 2: Create an input field with the initial text value
      var inputField = $('<input type="text" id="inputText">');
      // Step 3: Create a send button and hide it initially
      var sendButton = $('<button id="sendButton">Send</button>').hide();
      // Step 4: Create a new paragraph element to replace the campText element
      var paragraph = $('<p></p>').append(inputField, sendButton);

      var campText = $('#' + campTextId);

      // Step 5: Replace the campText element with the input field and send button
      campText.replaceWith(paragraph);
      editButton.hide();
      sendButton.show();

      sendButton.click(function() {
        // Step 6: Get the new text from the input field
        var newText = inputField.val();
        $.ajax({
          url: 'userMods', // Step 7: Specify the URL of the servlet
          method: 'POST',
          data: { 
			  newText: newText, 
			  parameter: parameter
          		}, // Step 8: Send the new text as a parameter to the servlet
          success: function() {
            // Step 9: Reload the page after a successful AJAX call
            location.reload();
          },
          error: function() {
            // Step 10: Handle error case if needed
            alert("error");
          }
        });
      });
    });
  }

  // Step 11: Call the setupEditSaveFunction with button
      setupEditSaveFunction('button1', 'name', 'nome'); // Use the button ID and campText ID here
      setupEditSaveFunction('button2', 'surname', 'cognome'); // Use the button ID and campText ID here
      setupEditSaveFunction('button3', 'user', 'username'); // Use the button ID and campText ID here
      setupEditSaveFunction('button4', 'email', 'email'); // Use the button ID and campText ID here
      setupEditSaveFunction('button5', 'cf', 'CF'); // Use the button ID and campText ID here
    });