/**
 * 
 */
$(document).ready(function() {
 
  $("#add-image-button").click(function(){
	  
	var tags = [];  
	$(".image-form input:not([type='submit'])").each(function() {
         tags.push(this);
    });
   
    
    var formData = new FormData();
    formData.append('action', 'addImage');
    formData.append('code', $(tags[1]).val());
    formData.append('file', $(tags[2])[0].files[0]);
      
       $.ajax({
          url: 'imagespage', 
          method: 'POST',
          data: formData, 
          processData: false,
          contentType: false,
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
         
  })
  
});

});
