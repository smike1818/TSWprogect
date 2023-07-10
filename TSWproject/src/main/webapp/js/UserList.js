/**
 * 
 */
//----------------------PAGINA UserList.jsp----------------------------------------

//appena scrivo un elemento nella barra di ricerca mi appare l'elenco 
//di utenti salvati nel db in base al valore del tag input
//io posso selezionare uno dei suggerimenti e cliccare 
//il bottone per indirizzarmi alla pagina dell'utente


$(document).ready(function() {
	

	   
  $("#search-user-input").on("input", function() {
    var selectedValue = $(this).val();

    $.ajax({
      url: "users",
      method: "GET",
      dataType: "json",
      data: { data: selectedValue, action: "autocomplete" },
      success: function(json) {
        // Rimuovi i suggerimenti precedenti
        $("#search-user-suggestions").empty();

        // Aggiungi i nuovi suggerimenti alla lista
        json.forEach(function(suggestion) {
          var listItem = $("<li>").text(suggestion);
          $("#search-user-suggestions").append(listItem);
        });

        // Mostra la lista di suggerimenti
        $("#search-user-suggestions").show();
      },
      error: function(error) {
        console.log("Errore durante la chiamata AJAX:", error);
      }
    });
  });

  // Nascondi la lista di suggerimenti quando si clicca altrove sulla pagina
  $(document).on("click", function(event) {
    if (!$(event.target).closest("#search-user-suggestions").length) {
      $("#search-user-suggestions").hide();
    }
  });

  // Gestisci il clic su un suggerimento
  $("#search-user-suggestions").on("click", "li", function() {
    var suggestion = $(this).text();
    $("#search-user-input").val(suggestion);
    $("#search-user-suggestions").hide();

    // Esegui altre azioni in base al suggerimento selezionato
    // ...
  });
  
  //fiuttosto che usare un form utilizzo una funzione js
  //che mi reindirizza a una pagina jsp cliccando sul bottone
  
  $("#search-user-submit").click(function(){
	  var element = $("#search-user-input").val();
	  
	  if(element === ""){
	      $(".error-statement").html("username non inserito");
	  }else{
		  $.ajax({
           url: "users",
           method: "GET",
          dataType: "json",
          data: { data: element, action: "autocomplete" },
      success: function(json) {
          //se json è vuoto mi stampa messaggio d'errore
          //altrimenti di indirizza a UserDetails
          if (Object.keys(json).length === 0) {       //controllo se nella stringa json c'è almeno una key
              $(".error-statement").html("username non trovato, riprova");
          }else{
			  //FIXED VULNERABILITY
			  //EFFETTUO CHIAMATA POST E NON GET
			  var form = document.createElement("form");
form.setAttribute("method", "post");
form.setAttribute("action", "UserDetails.jsp");

var input = document.createElement("input");
input.setAttribute("type", "hidden");
input.setAttribute("name", "us");
input.setAttribute("value", json[0]);

form.appendChild(input);
document.body.appendChild(form);

form.submit();
		  }
      },
      error: function(error) {
        console.log("Errore durante la chiamata AJAX:", error);
      }
    });
	  }
  })
   
  
  
  
  
});
