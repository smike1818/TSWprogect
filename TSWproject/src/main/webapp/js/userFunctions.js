/**
 * 
 */

function showAddCardForm(){
  var element = document.getElementById('card-form');
  element.style.display = "block"; 
  // o 'inline', 'flex', 'grid', ecc. a seconda del tipo di visualizzazione desiderata
}
$(document).ready(function(){
	
	$(window).on('beforeunload', function() {
      location.reload();
    });
	

//nascondo la barra di ricerca se si entra in determinate pagine	
if(document.title === "Confirm Payment" || document.title === "Acquisto effettuato"){
	$(".search-products").css('display','none');
 }
 
	//attivo la barra di ricerca
 $(".search-product-input").on("input", function() {
  var selectedValue = $(this).val();

  // Effettua la chiamata AJAX
  $.ajax({
    url: "catalogo", // Specifica l'URL della servlet
    method: "GET", // Metodo HTTP da utilizzare (GET, POST, etc.)
    dataType: "json", // Tipo di dati attesi come risposta (opzionale)
    data: { data: selectedValue, auto: "autocomplete"}, // Passa il valore selezionato come parametro nella richiesta
    success: function(json) {
      //funzione da eseguire in caso di successo 
      console.log("la servlet e' stata eseguita correttamente");
      console.log(json);
      
       // Rimuovi i suggerimenti precedenti
        $(".search-product-suggestions").empty();

        // Aggiungi i nuovi suggerimenti alla lista
        json.forEach(function(suggestion) {
          var listItem = $("<li>").text(suggestion);
          $(".search-product-suggestions").append(listItem);
        });

        // Mostra la lista di suggerimenti
        $(".search-product-suggestions").show();
        $(".search-product-suggestions").css("z-index","100");
    },
    error: function(error) {
      // Funzione da eseguire in caso di errore
      console.log("Errore durante la chiamata AJAX:", error);
    }
  });
});


// Nascondi la lista di suggerimenti quando si clicca altrove sulla pagina
  $(document).on("click", function(event) {
    if (!$(event.target).closest(".search-product-suggestions").length) {
      $(".search-product-suggestions").hide();
    }
  });

  // Gestisci il clic su un suggerimento
  $(".search-product-suggestions").on("click", "li", function() {
    var suggestion = $(this).text();
    $(".search-product-input").val(suggestion);
    $(".search-product-suggestions").hide();
  });
  
    //fiuttosto che usare un form utilizzo una funzione js
  //che mi reindirizza a una pagina jsp cliccando sul bottone
  
  $(".search-product-submit").click(function(){
	  var str = $(".search-product-input").val();
	  
	  //verifico se il formato della stringa è corretto
	  if(str!=="" && (str.includes("(") || str.includes(")"))){
	  var array = str.split("(");
	  var element = array[0];
	  var id = array[1].split(")")[0];
	  console.log(element,id);
	  
	  
	  if(element === ""){
	      $(".error-statement").html("nome articolo non inserito");
	  }else{
		  $.ajax({
           url: "catalogo",
           method: "GET",
           dataType: "json",
           data: { data: element, key: id, auto: "autocomplete" },
           success: function(json) {
          //se json è vuoto mi stampa messaggio d'errore
          //altrimenti di indirizza a UserDetails
          if (Object.keys(json).length === 0) {       //controllo se nella stringa json c'è almeno una key
              $(".error-statement").html("articolo non trovato, riprova");
          }else{
			  window.location.href = "dettaglio.jsp?id="+id;
		  }
      },
      error: function(error) {
        console.log("Errore durante la chiamata AJAX:", error);
      }
    });
	  }}
  })
  
  //disabilito la possibilità di aggiunta al carrello
  //se l'articolo non è disponibile
  //quindi se ha quantità = 0
 
  var catalogo = $(".catalogo-items");
  catalogo.each(function() {
    var item = $(this);
    if (!item.find(".product-available").is(":visible")) {
      var button = item.find(".addtocart-submit");
      button.prop("disabled", true);
    }
   });
   
   //-------------CARRELLO-----------------------
   //funzioni per il carrello
   
   
   //incremento quantità nel carrello
   $(".piu").click(function() {
      var inputField = $(this).closest(".add-quantity").find("[class^='input-number']");
      var currentValue = parseInt(inputField.val());
      var max = parseInt($(this).closest(".add-quantity").find("[class^='max-quantity']").val());
      var base = parseFloat($(this).closest(".add-quantity").find("[class^='prezzo-base']").val());
      
      //aggiorno la grandezza della quantità
      //per far vedere numeri grandi che verranno troncati
      
      if(currentValue+1<=max){
      inputField.val(currentValue + 1);
      
	  var id = $(this).closest(".add-quantity").find("[class^='id-item']").val();
	  
	  $.ajax({
        url: "cart", // Specifica l'URL della servlet
        method: "GET", // Metodo HTTP da utilizzare (GET, POST, etc.)
        data: { data: id, action: "increment"}, // Passa il valore selezionato come parametro nella richiesta
        success: function() {
           //funzione da eseguire in caso di successo 
           console.log("la servlet e' stata eseguita correttamente");
     
        },
        error: function() {
           console.log("errore perche' non ritorna una response");
        }
     });
     
     //modifico il prezzo del prodotto mostrato nel carrello
     var priceblock = $(this).parents(".table-row").find(".table-cell .prezzo-item-block h4");
     var priceText = $.trim(priceblock.text()).replace(",",".");
     var price = parseFloat(priceText) + base;
     priceblock.text((price).toFixed(2).replace(".",","));

      //modifico il prezzo finale
      priceblock = $(this).parents(".cart-section").find(".total-price .text-price .price");
      priceText = $.trim(priceblock.text()).replace(",",".");
      price = parseFloat(priceText) + base;
      priceblock.text((price).toFixed(2).replace(".",","));
     

     }  
   });
   
    //decremento quantità nel carrello
   $(".meno").click(function() {
      var inputField = $(this).closest(".add-quantity").find("[class^='input-number']");
      var currentValue = parseInt(inputField.val());
      var base = parseFloat($(this).closest(".add-quantity").find("[class^='prezzo-base']").val());
    
      if(currentValue-1>=1){
       inputField.val(currentValue - 1);
	  
	  var id = $(this).closest(".add-quantity").find("[class^='id-item']").val();
	  
	  $.ajax({
        url: "cart", // Specifica l'URL della servlet
        method: "GET", // Metodo HTTP da utilizzare (GET, POST, etc.)
        data: { data: id, action: "decrement"}, // Passa il valore selezionato come parametro nella richiesta
        success: function() {
           //funzione da eseguire in caso di successo 
           console.log("la servlet e' stata eseguita correttamente");
     
        },
        error: function() {
           console.log("errore perche' non ritorna una response");
        }
     });
     
     var priceblock = $(this).parents(".table-row").find(".table-cell .prezzo-item-block h4");
     var priceText = $.trim(priceblock.text()).replace(",",".");
     var price = parseFloat(priceText);
     priceblock.text((price - base).toFixed(2).replace(".",","));
     
     //modifico il prezzo finale
      priceblock = $(this).parents(".cart-section").find(".total-price .text-price .price");
      priceText = $.trim(priceblock.text()).replace(",",".");
      price = parseFloat(priceText) - base;
      priceblock.text((price).toFixed(2).replace(".",","));
      }
   });




//------------cardsPage.jsp---------------------------
//------------FUNZIONI PER LA PAGINA------------------

//mostra inserimento di metodi di pagamento

var click = 0;
$(".add-InsertCards-link").click(function(){
	$(this).hide();
	click++;
	var div = $(".show-InsertCards");
	
	if(click%2==0){
		div.empty();
	}else{
      var xhttp = new XMLHttpRequest();      //oggetto per fare richieste dinamiche
    
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {    //appena arriva la risposta e tutto è andato a buon fine
           div.html(this.responseText);        //aggiungo il corpo della pagina jsp
        }
      };
      xhttp.open("GET", "InsertCards.jsp", true);    //preparo richiesta
      xhttp.send(); 
   }            //invio richiesta
});



//----------------AddIndirizzo.jsp---------------------------
//----------------FUNZIONI PER LA PAGINA------------------

//mostra inserimento di metodi di pagamento

var click = 0;
$(".add-AddIndirizzo-link").click(function(){
	click++;
	var div = $(".show-AddIndirizzo");
	
	if(click%2==0){      //se l'utente clicca in modo alternato una volta mostra il form 
		div.empty();     //e l'altra volta lo nasconde
	}else{
      var xhttp = new XMLHttpRequest();      //oggetto per fare richieste dinamiche
    
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {    //appena arriva la risposta e tutto è andato a buon fine
           div.html(this.responseText);        //aggiungo il corpo della pagina jsp
        }
      };
      xhttp.open("GET", "AddIndirizzo.jsp", true);    //preparo richiesta
      xhttp.send(); 
   }            //invio richiesta
});


// DISABILITARE IL BOTTONE ADD TO CART QUANDO LA QUANTITA' DEGLI ITEM E' 0

if ($(".product-no-available").is(":visible")) {
	//list-product
  $(".product-no-available").closest(".product-container").find("button").prop("disabled", true);
  //catalogofiltrato
   $(".product-no-available").closest(".table-row").find("button").prop("disabled", true);
   //dettaglio
   $("#details-addtocart").prop("disabled", true);
}
   
})







