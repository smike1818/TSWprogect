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
   if ($(".no-categories").is(":visible")) {
      $("#insert-product-form #insert-product-submit").prop('disabled', true);
   }
   
   //funzione per inserire la scelta del tipo di articoli da aggiungere nella pagina di inserimento
   //PER CHI LEGGERA' IL CODICE 
   //HO MISCHIATO I TAG DI 2 PAGINE, QUELLE CITATE NELL'IF QUA SOTTO
   //PERDONATEMI SONO FATTO COSI'...
   
	 if(document.title === "Insert Page" || document.title === "CatalogoAdmin"){
		 var choise = $("#choise-type").val();
		 $("#insert-choise").prop("value",choise);
		 
		 if(choise !== "strumento"){
			 $("#categoria .false").hide();
		     $("#categoria .true").show();
		     $(".type-col:contains('0')").closest(".table-row").show();
		     $(".type-col:contains('1')").closest(".table-row").hide();
		     
		     // Imposta l'elemento selezionato sul primo option non nascosto
             var primoOptionVisibile = $("#categoria .true").first();
             if (primoOptionVisibile.length) {
			   $("#categoria").show();
               $("#label-categoria").show();
               $(".no-categories").hide();
               $("#insert-product-form #insert-product-submit").prop('disabled', false);
               primoOptionVisibile.prop("selected", true);
             }else{
			   $("#categoria").hide();
               $("#label-categoria").hide();
               $(".no-categories").show();
               $("#insert-product-form #insert-product-submit").prop('disabled', true);
		     }
          		     		     
		 }else{
			 $("#categoria .true").hide();
		     $("#categoria .false").show();
		     $(".type-col:contains('1')").closest(".table-row").hide();
		     $(".type-col:contains('0')").closest(".table-row").show();
		     
		      // Imposta l'elemento selezionato sul primo option non nascosto
             var primoOptionVisibile = $("#categoria .false").first();
             if (primoOptionVisibile.length) {
			   $("#categoria").show();
               $("#label-categoria").show();
               $(".no-categories").hide();
               $("#insert-product-form #insert-product-submit").prop('disabled', false);
               primoOptionVisibile.prop("selected", true);
             }else{
			   $("#categoria").hide();
               $("#label-categoria").hide();
               $(".no-categories").show();
               $("#insert-product-form #insert-product-submit").prop('disabled', true);
		     }
		 }
		 
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
  $('input[name="tipo"]').val(selectedValue);
  var choise = $("#choise-type").val();
  $("#insert-choise").val(choise);

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
		  $(".type-col:contains('0')").closest(".table-row").hide();
		  $(".type-col:contains('1')").closest(".table-row").show();
		  $(".no-products").attr('colspan', '10');
		  $(".insert-corde").hide();
		  
		  $("#categoria .false").hide();
		  $("#categoria .true").show();
		  
		  // Imposta l'elemento selezionato sul primo option non nascosto
          var primoOptionVisibile = $("#categoria .true").first();         
          if (primoOptionVisibile.length) {
			   $("#categoria").show();
               $("#label-categoria").show();
               $(".no-categories").hide();
               $("#insert-product-form #insert-product-submit").prop('disabled', false);
               primoOptionVisibile.prop("selected", true);
          }else{
			  $("#categoria").hide();
              $("#label-categoria").hide();
              $(".no-categories").show();
              $("#insert-product-form #insert-product-submit").prop('disabled', true);
		  }
		           
	  }else{
		  $(".tag-choise").css('display','block');
		  $(".type-col:contains('1')").closest(".table-row").hide();
		  $(".type-col:contains('0')").closest(".table-row").show();
		  $(".no-products").attr('colspan', '11');
		  $(".insert-corde").show()
		  
	      $("#categoria .true").hide();
	      $("#categoria .false").show();
	      
	      // Imposta l'elemento selezionato sul primo option non nascosto
          var primoOptionVisibile = $("#categoria .false").first();
          if (primoOptionVisibile.length) {
			   $("#categoria").show();
               $("#label-categoria").show();
               $(".no-categories").hide();
               $("#insert-product-form #insert-product-submit").prop('disabled', false);
               primoOptionVisibile.prop("selected", true);
          }else{
			  $("#categoria").hide();
              $("#label-categoria").hide();
              $(".no-categories").show();              
              $("#insert-product-form #insert-product-submit").prop('disabled', true);
		  }

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
//non dipende dall'evento change
//l'ho riscritto per assegnare un valore qunado si carica la pagina

var selectedValue = $('#choise-type').val();
$('input[name="tipo"]').val(selectedValue);


})


