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
		
		
		$(".purchase-item").each(function() {
            var td = $(this).find(".date");
            var tdContent = td.html();
            console.log(tdContent);
            fine += " 23:59:59";

            if (tdContent >= inizio && tdContent <= fine) {
               $(this).show();
            } else {
               $(this).hide();
            }
         });


	} 
	
  });
  
  $("#reset-date").click(function(){
	  $(".purchase-item").each(function() {
		     $(this).show();
     });
  });
  
  
});