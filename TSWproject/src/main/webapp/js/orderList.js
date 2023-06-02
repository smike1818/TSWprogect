/**
 * 
 */
/**
 * 
 */
//----------------------PAGINA orderList.jsp----------------------------------------

//in questa pagina si andranno a mostrare una tabella di acquisti
//basati su una data di inizio e di fine

$(document).ready(function() {
	
	
 	//DIVIETO DI ACCESSO PER CHI ACCEDE A PAGINE INCLUSE
	if(document.title === "")
	   window.location.href = "403.jsp";
	   
  $("#form-data-button").click(function() {
    var inizio = $("#inizio").val();
    var fine = $("#fine").val();
    if(inizio === "" && fine === ""){
		$(".error-statement").html("inizio e fine non inseriti");
    }else if(inizio === ""){
		$(".error-statement").html("inizio non inserito");
	}else if(fine === ""){
		$(".error-statement").html("fine non inserito");
	}else if(inizio>fine){
		$(".error-statement").html("inizio > fine");
	}else{
		
		$(".error-statement").html("");
		
		//seleziono ogni tr della tabella, tranne i th
		//il tutto lo faccio non selezionando i tr primi figli (ovvero
		//quelli che contengono i th)
		
		$(".table-acquisti tr:not(:first-child)").each(function() {
            var td = $(this).find(".date-td");
            var tdContent = td.html();
            fine += " 23:59:59";

            if (tdContent >= inizio && tdContent <= fine) {
               $(this).show();
            } else {
               $(this).hide();
            }
         });


	}
  }); 
});