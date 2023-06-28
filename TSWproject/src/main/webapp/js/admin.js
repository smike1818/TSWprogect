$(document).ready(function(){			
			
			
	//questa funzione toglie qualsiasi statement d'errore
	// appena l'utente clicca qualcosa sulla pagina
    
	 if(document.title === "Insert Page"){
		 if($(".error-statement").html != ""){
		     $(".categories-form").css('display', 'block');
	     }
	 }
	 
	  if(document.title === "Image Page"){
		 if($(".error-statement").html != ""){
		     $(".image-form").css('display', 'block');
	     }
	 }
	
   $("#show-image-form").click(function(){
          $(".image-form").css('display', 'block');
   });
   
   //quando non ci sono categorie disattivo il submit del form
   //la mancanza di categorie fa si che venga mostrato il tag <p id="no-categories">
   //al suo caricamento quindi viene eseguita questa funzione
   if ($("#no-categories").is(":visible")) {
      $("#insert-product-form #insert-product-submit").prop('disabled', true);
   }
   
  
$.ajaxSetup({
  beforeSend: function() {
    // Funzione da eseguire prima dell'invio della richiesta
    console.log("Inizio della richiesta AJAX");
  },
  complete: function() {
    // Funzione da eseguire dopo il completamento della richiesta (indipendentemente dal successo o dall'errore)
    console.log("Richiesta AJAX completata");
  }
});

$("#choise-type").change(function() {
  var selectedValue = $(this).val();

  // Effettua la chiamata AJAX
  $.ajax({
    url: "choise", // Specifica l'URL della servlet
    method: "GET", // Metodo HTTP da utilizzare (GET, POST, etc.)
    dataType: "json", // Tipo di dati attesi come risposta (opzionale)
    data: { type: selectedValue }, // Passa il valore selezionato come parametro nella richiesta
    success: function(json) {
      //funzione da eseguire in caso di successo, la stringa json non verrà  
      console.log("la servlet e' stata eseguita correttamente");
      console.log(json);
      
      if(json.value==1){
		  $(".tag-choise").css('display','none');
		  $(".product .type-col:contains('0')").closest("tr").hide();
		  $(".product .type-col:contains('1')").closest("tr").show();
		  $(".no-products").attr('colspan', '9');
		  $(".insert-corde").hide();
	  }else{
		  $(".tag-choise").css('display','block');
		   $(".product .type-col:contains('1')").closest("tr").hide();
		  $(".product .type-col:contains('0')").closest("tr").show();
		  $(".no-products").attr('colspan', '10');
		  $(".insert-corde").show()
	  }
    },
    error: function(error) {
      // Funzione da eseguire in caso di errore
      console.log("Errore durante la chiamata AJAX:", error);
    }
  });
});


//funzione per nascondere i td e i th che inseriscono il tipo dell'articolo
$(".type-col").hide();

//funzione per inserire la scelta del tipo di articoli da aggiungere nella pagina di inserimento
	 if(document.title === "Insert Page"){
		 var choise = $("#choise-type").val();
		 $("#insert-choise").val(choise);
	 }
	 
	 
//-------------PAGINA cambia.iva--------------
//cambio dell'iva tramite chiamata AJAX

$("#change-iva-submit").click(function(){
	var input = $("#change-iva-input").val();
	
	if(input!=""){
		$.ajax({
        url: "changeiva", // Specifica l'URL della servlet
        method: "POST", // Metodo HTTP da utilizzare (GET, POST, etc.)
        data: { iva: input }, // Passa il valore selezionato come parametro nella richiesta
        success: function(response) {
     
         console.log("la servlet e' stata eseguita correttamente");
          $(".correct-statement").empty();
          $(".correct-statement").html(response);
          $(".error-statement").empty();
      
        },
       error: function(jqXHR, textStatus) {
             var errorMessage = jqXHR.getResponseHeader("ErrorMessage");
             console.log("Errore durante la chiamata AJAX:", textStatus, errorMessage);
             $(".error-statement").empty();
             $(".error-statement").html(errorMessage);
             $(".correct-statement").empty();
         }
       });
	}else{
		$(".error-statement").html("scegli un valore");
	}
})

//-------------InsertAdmin.jsp----------------------

//mi seleziono il tipo di articolo a cui spetta la categoria
var selectedValue = $('#choise-type').val();
$('input[name="tipo"]').val(selectedValue);

})


