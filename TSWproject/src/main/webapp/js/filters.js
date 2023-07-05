/**
 * 
 */
$("#filter-submit").click(function(){
		var min = $("#filter-min").val();
		var max = $("#filter-max").val();
		var marca = $("#filter-marca").val();
		var tipologia = $("#filter-tipologia").val();
		
		var esito = validateFilters(min,max,marca,tipologia);
		
		if(esito === "ok"){
			makefilters(min, max, marca, tipologia);
			$(".error-statement").empty();
		}else{
			$(".error-statement").empty();
			$(".error-statement").html(esito);
			showAll();
		}
	})
	
	
	//i tag input non assegnati non varranno
   	function validateFilters(min, max, marca, tipologia) {
       var catalogo = $(".catalogo-items");
       var flag = true;

       if (min !== "" && max !== "") {
          if (min > max)
             return "min>max";
       }

       if (marca !== "") {
          flag = false;
          catalogo.each(function() {
             var item = $(this);
             var m = item.find(".item-marca").text();
             if (m === marca) {
               flag = true;
               return false; // Esci dal ciclo each se trovi una corrispondenza
             }
           });
        }

        if (flag === false)
           return "marca non corrispondente";

        if (tipologia !== "") {
           flag = false;
           catalogo.each(function() {
              var item = $(this);
              var t = item.find(".item-tipologia").text();
              if (t === tipologia) {
                  flag = true;
                  return false; // Esci dal ciclo each se trovi una corrispondenza
              }
           });
         }

        if (flag === false)
           return "tipologia non corrispondente";

        return "ok";
     }
	
	//se ci sono tag senza valore non si effettua nessun hide()
     function makefilters(min, max, marca, tipologia) {
		
        var catalogo = $(".catalogo-items");
  
        catalogo.each(function() {
           var item = $(this);
           var mar = item.find(".item-marca").text();
           var tipol = item.find(".item-tipologia").text();
           
           var prezzoText = item.find(".item-prezzo").text();
           var prezzo = parseFloat(prezzoText.replace(",", "."));     // Converte il prezzo in formato numerico
    
           if  ((min=="" || prezzo >= parseFloat(min)) && // Confronta come numeri e controlla se min è NaN
               (max=="" || prezzo <= parseFloat(max)) && // Confronta come numeri e controlla se max è NaN
               (mar === marca || marca === "") &&
               (tipol === tipologia || tipologia === "")) {
                    item.show();
               }else {
                    item.hide();
               }
        });
      }

	
	//mostro tutti i prodotti nel catalogo
	function showAll(){
		var catalogo = $(".catalogo-items");
		 
		 $.each(catalogo,function(){
			 catalogo.show();
		 })
	}