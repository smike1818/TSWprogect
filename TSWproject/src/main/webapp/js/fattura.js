
$(document).ready(function(){
  
  $(".fattura").on("click", function(e) {
  e.preventDefault(); // Blocca l'azione predefinita del link

  // Effettua una richiesta AJAX alla servlet
  $.ajax({
    url: "fattura",
    method: "GET",
    data: {acquisto: $(this).attr("id")},
    xhrFields: {
      responseType: "blob" // Specifica il tipo di risposta come blob
    },
    success: function(data) {
      // Crea un URL oggetto per il blob
      var url = URL.createObjectURL(data);

      // Crea un elemento link temporaneo per il download
      var link = document.createElement("a");
      link.href = url;
      link.download = "fattura.pdf"; // Specifica il nome del file
      link.click(); // Simula il click sul link
    }
  });
});

})