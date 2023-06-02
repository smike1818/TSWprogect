/**
 * 
 */
 	//DIVIETO DI ACCESSO PER CHI ACCEDE A PAGINE INCLUSE
	if(document.title === "" || document.title === "SERVER ERROR" || document.title === "NOT FOUND")
	   window.location.href = "403.jsp";