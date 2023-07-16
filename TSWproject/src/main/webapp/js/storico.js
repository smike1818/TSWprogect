/**
 * 
 */
$(document).ready(function() {
    $(".dettagli").click(function() {
        var AcquistoID = $(this).closest('.acquisti-details').attr('id');
        var $dettagliButton = $(this); // Salva il riferimento all'elemento $(this)
        var artDetailsContainer = $dettagliButton.closest('.acquisti-details').next('.artDetailsContainer');
        artDetailsContainer.addClass('table-row');
        
        if (artDetailsContainer.is(':visible')) {
            // Se il container è visibile, esegui l'effetto fadeOut
            artDetailsContainer.slideUp();
        } else {
            // Altrimenti, esegui la richiesta AJAX per ottenere i dettagli
            $.ajax({
                url: "storico",
                method: "POST",
                dataType: "json",
                data: { data: AcquistoID, action: "mostra-dettagli"},
                success: function(response) {
                    artDetailsContainer.empty(); // Svuota il contenuto precedente

                    response.forEach(function(element) {
                        var div = $('<div class="artDetails">');
                        div.attr('id',element.id);
                        var secondiv = $('<div class="table-row">');
                        secondiv.append($('<h3 class="table-cell>').text("id articolo: " + element.id));
						secondiv.append($('<span class="table-cell">').text("Nome: ").css({'font-weight': 'bold', 'font-style': 'italic'}));
						secondiv.append($('<span class="table-cell">').text(element.nome));
						secondiv.append($('<span class="table-cell">').text("Prezzo: ").css({'font-weight': 'bold', 'font-style': 'italic'}));
						secondiv.append($('<span class="table-cell">').text(element.prezzo));
						secondiv.append($('<span class="table-cell">').text("Categoria: ").css({'font-weight': 'bold', 'font-style': 'italic'}));
						secondiv.append($('<span class="table-cell">').text(element.cat.nome));
                        div.append(secondiv);
                        
                        //in quest'altra chiamata ajax andrò a prendere tutte le immagini dei vari articoli
                        $.ajax({
                              url: "storico",
                              method: "POST",
                              dataType: "json",
                              data: { data: element.id, action: "mostra-immagini"},
                              success: function(response) {	
							  var cell = $('<div class="table-cell">');
							  var cycle = $('<div class="cycle-slideshow2" data-cycle-fx="scrollHorz" data-cycle-prev=".prev" data-cycle-next=".next" data-cycle-timeout = 2000 >');
							  cell.append(cycle);
							  secondiv.append(cell);	
							  div.append(secondiv);		
							  
							  var exist = false;   //verifico se c'è almeno un immagine
							  	  
                              response.forEach(function(element) {
                                      // Creazione degli elementi immagine e aggiunta al DOM
                                      var image = $('<img>').attr('src', 'img/'+element);
                                     cycle.append(image);
                                     exist = true;
                              });
                            	
                              //se non esiste nessuna immagine allora metto quella di defalt	
                              if(!exist){
								    var image = $('<img>').attr('src', 'img/default.png');
                                    cycle.append(image);
							  }   
							  						 
                              // Inizializzazione del plugin Cycle dopo aver aggiunto le immagini
                                  cycle.cycle({
                                    fx: 'scrollHorz',
                                    swipe: true,
                                    prev: '.prev',
                                    next: '.next',
                                    timeout: 2000, 
                                  });
                            },
                            error: function() {	
                                console.log("errore");
                            }
                           });

                        artDetailsContainer.append(div); // Aggiungi il div all'artDetailsContainer
                    });

                    // Esegui l'effetto fadeIn
                    artDetailsContainer.slideDown();
                },
                error: function() {
                    console.log("errore");
                }
            });
        }
    });
    
   $(".artDetailsContainer").click(function(){
	   var childID = $(this).children().attr('id');
       window.location.href = "dettaglio.jsp?id=" + childID;
   })
});





