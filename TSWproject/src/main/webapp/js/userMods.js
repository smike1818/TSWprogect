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
		
	//validazione dei campi: mi prendo l'id del bottone per verificare poi che tipo di campo vado a controllare
	  var result = true;
	  var idButton = editButton.attr("id");
	  
	  // Step 2: Create an input field with the initial text value
      var inputField = $('<input type="text" id="inputText">');
	  
	  //aggiunta del placeholder spcifico
      switch (idButton) {
      case "button1":
             inputField.attr("placeholder", "Mario");
      break;
      case "button2":
             inputField.attr("placeholder", "Rossi");
      break;
      case "button3":
             inputField.attr("placeholder", "example123");
      break;
      case "button4":
             inputField.attr("placeholder", "example123@gmail.com");
      break;
      case "button5":
             inputField.attr("placeholder", "XXXXXX00X0X000X");
      break;
      default:
             inputField.attr("placeholder", ""); // Valore predefinito nel caso in cui l'id del pulsante non corrisponda a nessun caso sopra
      }
   
	         
      // Step 3: Create a send button and hide it initially
      var sendButton = $('<button id="sendButton">Send</button>').hide();
      // Step 4: Create a new paragraph element to replace the campText element
      var paragraph = $('<p class="user-field"></p>').append(inputField, sendButton);

      var campText = $('#' + campTextId);

      // Step 5: Replace the campText element with the input field and send button
      campText.replaceWith(paragraph);
      editButton.hide();
      sendButton.show();

      sendButton.click(function() {
		  
	 		  
	   //prima di procedere controllo il campo
	  if(idButton === "button1" || idButton === "button2")
		result = allLetter(inputField);
	  if(idButton === "button3")
	    result = userid_validation(inputField,7,12);
	  if(idButton === "button4")
	    result = ValidateEmail(inputField);
	  if(idButton === "button5")
	    result = cf_validation(inputField);
		  
	  //se il testo passa i controlli allora proseguo
	  if(result){
		  
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
          error: function(xhr, status, error) {
             // Ottieni il codice HTML della risposta d'errore
             var errorMessageHTML = xhr.responseText;
             console.log(errorMessageHTML);
  
             // Trova l'inizio del messaggio di errore
             startIndex = errorMessageHTML.indexOf("<b>Message</b>");
             startIndex = errorMessageHTML.indexOf(">", startIndex) + 1;
  
             // Trova la fine del messaggio di errore
             var endIndex = errorMessageHTML.indexOf("</p>", startIndex);
  
             // Estrai il messaggio di errore desiderato
             var errorMessage = errorMessageHTML.substring(startIndex, endIndex);
             
             var startIndex = errorMessage.indexOf("</b>") + 5;
             var extractedMessage = errorMessage.substring(startIndex).trim();

             //stampalo a video
             $(".error-statement").html(extractedMessage);
           }

        });
        }
      });
    });
  }

  // Step 11: Call the setupEditSaveFunction with button
      setupEditSaveFunction('button1', 'name', 'nome'); // Use the button ID and campText ID here
      setupEditSaveFunction('button2', 'surname', 'cognome'); // Use the button ID and campText ID here
      setupEditSaveFunction('button3', 'user', 'username'); // Use the button ID and campText ID here
      setupEditSaveFunction('button4', 'email', 'email'); // Use the button ID and campText ID here
      setupEditSaveFunction('button5', 'cf', 'CF'); // Use the button ID and campText ID here

      $(document).click(function(event) {
        var target = $(event.target);
  
  // Verifica se l'elemento cliccato non è il campo di input e nemmeno il pulsante di invio
  if (!target.is("input#inputText") && !target.is("button#sendButton")) {
    var inputField = $("input#inputText");
    var sendButton = $("button#sendButton");
    var paragraph = $("<p class='user-field'></p>").text(originalText); // Ripristina il testo originale
    
    // Sostituisci il campo di input e il pulsante di invio con il paragrafo contenente il testo originale
    inputField.replaceWith(paragraph);
    sendButton.hide();
    paragraph.parent().find("#" + buttonId).show();
  }
});
    
    });
    
    
    
    

