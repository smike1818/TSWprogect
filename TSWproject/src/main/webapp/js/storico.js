/**
 * 
 */
$(document).ready(function() {
    $(".dettagli").click(function() {
        var AcquistoID = $(this).closest('.acquisti-details').attr('id');
        var $dettagliButton = $(this); // Salva il riferimento all'elemento $(this)
        var artDetailsContainer = $dettagliButton.closest('.acquisti-details').next('.artDetailsContainer');
        
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
                        var secondiv = $('<div class="text">');
                        secondiv.append($('<h3>').text("id articolo: "+element.id));
                        secondiv.append($('<br>'));
                        secondiv.append($('<br>'));
                        secondiv.append($('<span>').text("nome: "+element.nome));
                        secondiv.append($('<br>'));
                        secondiv.append($('<span>').text("prezzo: "+ element.prezzo));
                        secondiv.append($('<br>'));
                        secondiv.append($('<span>').text("categoria: "+element.cat.nome));
                        div.append(secondiv);
                        
                        //in quest'altra chiamata ajax andrò a prendere tutte le immagini dei vari articoli
                        $.ajax({
                              url: "storico",
                              method: "POST",
                              dataType: "json",
                              data: { data: element.id, action: "mostra-immagini"},
                              success: function(response) {	
							  
							  var cycle = $('<div class="cycle-slideshow2" data-cycle-fx="scrollHorz" data-cycle-prev=".prev" data-cycle-next=".next" data-cycle-timeout = 2000 >');
							  div.append(cycle);				  
                              response.forEach(function(element) {
                                      // Creazione degli elementi immagine e aggiunta al DOM
                                      var image = $('<img>').attr('src', 'img/'+element);
                                     cycle.append(image);
                              });
                            								 
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





