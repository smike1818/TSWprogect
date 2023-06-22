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
		  
	   //prima di procedere controllo il campo
	  if(idButton === "button1" || idButton === "button2")
		result = allLetter(newText);
	  if(idButton === "button3")
	    result = userid_validation(newText);
	  if(idButton === "button4")
	    result = ValidateEmail(newText);
	  if(idButton === "button5")
	    result = cf_validation(newText);
		  
	  //se il testo passa i controlli allora proseguo
	  if(result){
		  
        
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
    });
    
    
//VALIDAZIONE NOME E COGNOME
function allLetter(uname){     
	var letters = /^[A-Za-z]+$/;
    if(uname.match(letters)){		
        return true;
    }else{
       $(".error-statement").html("il nome e il cognome devono avere solo caratteri alfabetici");
       return false;
    }
}

//VAIDAZIONE EMAIL
function ValidateEmail(uemail)
{
     var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
     if(uemail.match(mailformat)){
           return true;
     }else{
        $(".error-statement").html("email non valida");
        return false;
     }
}

//VALIDAZIONE USERNAME
function userid_validation(uid,mx,my)
{
    var uid_len = uid.length;
    if (uid_len == 0 || uid_len >= my || uid_len < mx){
        $(".error-statement").html("lo username deve avere dai "+mx+" ai "+my+" caratteri");
        return false;
    }
    
    return alphanumeric(uid);
}

//CONTINUO USERNAME
function alphanumeric(uadd)   //VERIFICA SIA ALPHANUMERICO 
{ 
    var letters = /^[0-9a-zA-Z]+$/;
    if(uadd.match(letters)){
         return true;
    }else{
         $(".error-statement").html("lo username deve avere solo caratteri alfanumerici");
         return false;
    }
}

//VALIDAZIONE CODICE FISCALE
function cf_validation(ucf){
	var letters = /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/;
    if(ucf.match(letters)){
         return true;
    }else{
         $(".error-statement").html("Codice Fiscale non valido");
         return false;
    }
}
