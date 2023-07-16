/**
 * 
 */
$(document).ready(function() {
	
  //INIZIO VALIDAZIONE DEI LOGIN
  $("#login-form").submit(function(event) {
    event.preventDefault();
    validateLogin($(this)); // Effettua la validazione e memorizza il risultato
  });
  
  //INIZIO VALIDAZIONE REGISTRAZIONE
  $("#registration-form").submit(function(event) {
    event.preventDefault();
    validateReg($(this)); // Effettua la validazione e memorizza il risultato
  });
  
  //INIZIO VALIDAZIONE CARTA
  $("#card-form").submit(function(event) {
    event.preventDefault();
    validateCard($(this)); // Effettua la validazione e memorizza il risultato
  });
  
  //INIZIO VALIDAZIONE INDIRIZZO
  $("#address-form").submit(function(event) {
    event.preventDefault();
    validateAddress($(this)); // Effettua la validazione e memorizza il risultato
  });
  
  //INIZIO VALIDAZIONE NUMERO
  $("#phone-form").submit(function(event) {
    event.preventDefault();
    validatePhone($(this)); // Effettua la validazione e memorizza il risultato
  });
  
  //INIZIO VALIDAZIONE INSERIMENTO CATEGORIA
  $("#categories-form").submit(function(event) {
    event.preventDefault();
    validateCategoria($(this)); // Effettua la validazione e memorizza il risultato
  });
  
  //INIZIO VALIDAZIONE INSERIMENTO PRODOTTO
  $("#insert-product-form").submit(function(event) {
    event.preventDefault();
    validateProdotto($(this)); // Effettua la validazione e memorizza il risultato
  });
});



// FUNZIONE DI VALIDAZIONE LOGIN 


function validateLogin(form) {
  var valid = true;
  
  var username = form.find("input[name='un']");
  var password = form.find("input[name='pw']");
  
  if (!userid_validation(username, 7, 12)) {
    valid = false;
  }
  if (!passid_validation(password, 7, 12)) {
    valid = false;
  }
  
  if (valid) {
	$(".error-statement").empty();
    form.get(0).submit(); // Sottometti il modulo
  }
}


//FUNZIONE DI VALIDAZIONE REGISTRAZIONE


function validateReg(form) {
  var valid = true;
  
  var username = form.find("input[name='us']");
  var password = form.find("input[name='pws']");
  var nome = form.find("input[name='name']");
  var cognome = form.find("input[name='cognome']");
  var email = form.find("input[name='email']");
  var cf = form.find("input[name='cf']");
  var conferma = form.find("input[name='pws1']");
  
  if (!userid_validation(username, 7, 12)) {
     valid = false;
  }
  if(!allLetter(nome)){ 
	  valid=false;
  }
  if(!allLetter(cognome)){
	 valid=false;  
  }
  if(!ValidateEmail(email)){
	  valid=false;
  }
  if(!cf_validation(cf)){
	  valid=false;
  }
  if (!passid_validation(password, 7, 12)) {
    valid = false;
  }else if(!pass_confirm(conferma,password)){
	  valid = false;
  }
  
  if (valid) {
	$(".error-statement").empty();
    form.get(0).submit(); // Sottometti il modulo
  }
}


//FUNZIONE DI VALIDAZIONE CARTA

function validateCard(form){
  var valid = true;
  
  var numero = form.find("input[name='number-card']");
  var cvv = form.find("input[name='cvv']");
  var iban = form.find("input[name='IBAN']");
  
  if (!n_card_validation(numero)) {
    valid = false;
  }
  if (!cvv_validation(cvv)) {
    valid = false;
  }
  if(!iban_validation(iban)){
	  valid=false;
  }
  
  if (valid) {
	$(".error-statement").empty();
    form.get(0).submit(); // Sottometti il modulo
  }	
}


//VALIDAZIONE INDIRIZZO


function validateAddress(form){
  var valid = true;
  
  var via = form.find("input[name='via']");
  var civico = form.find("input[name='civico']");
  var citta = form.find("input[name='citta']");
  var cap = form.find("input[name='CAP']");
  
  if (!add_validation(via,civico,cap)) {
     valid = false;
  }
  if(!citta_validation(citta)){
	  valid=false;
  }
  
  if (valid) {
	$(".error-statement").empty();
    form.get(0).submit(); // Sottometti il modulo
  }	
}



//FUNZIONE DI VALIDAZIONE NUMERO



function validatePhone(form){
  var valid = true;
  
  var numero = form.find("input[name='numero']");
  
  if (!checkPhonenumber(numero)) {
    valid = false;
  }
  
  if (valid) {
	$(".error-statement").empty();
    form.get(0).submit(); // Sottometti il modulo
  }	
}



//FUNZIONE DI VALIDAZIONE CATEGORIA
function validateCategoria(form){
  var valid = true;
  
  var nome = form.find("input[name='name']");
  var descrizione = form.find("textarea");
  
  if (!allLetterWithSpace(nome)) {
    valid = false;
  }
  if(!firstBeLetter(descrizione)){
	valid = false;
  }
  
  if (valid) {
	$(".error-statement").empty();
    form.get(0).submit(); // Sottometti il modulo
  }	
}


//FUNIOBE DI VALIDAZIONE DEL PRODOTTO
function validateProdotto(form){
	 var valid = true;
  
  var nome = form.find("input[name='name']");
  var tipologia = form.find("input[name='tipologia']");
  var descrizione = form.find("textarea");
  var prezzo = form.find("input[name='price']");
  var quantita = form.find("input[name='quantity']");
  var marca = form.find("input[name='marca']");
  var corde = form.find("input[name='corde']");
  
  if(!allLetterWithSpace(nome)){ 
	  valid=false;
  }
  if(!firstBeLetter(tipologia)){
	 valid=false;  
  }
  if(!firstBeLetter(descrizione)){
	  valid=false;
  }
  if(!firstBeLetter(marca)){
	  valid=false;
  }
  if(!soloCifre(quantita)){
	  valid=false;
  }
  if(!soloCifre(corde)){
	  valid=false;
  }
  if(!price_validation(prezzo)){
	  valid=false;
  }

  if (valid) {
	$(".error-statement").empty();
    form.get(0).submit(); // Sottometti il modulo
  }
}

//VALIDAZIONE QUANTITA E CORDE
function soloCifre(item){
	var letters = /^[0-9]+$/;
	if(!item.val().match(letters)){
		$(".error-statement").html("il campo selezionato deve avere solo cifre");
		item.focus();
        return false;
	}
	return true;
}


//VALIDAZIONE PREZZO
function price_validation(price){
	var letters = /^(?:[1-9]\d{0,5}|0)(?:\.\d{1,2})?$/	;
	console.log(price.val());	
	if(!price.val().match(letters)){
		$(".error-statement").html("esempio di prezzo: 111.11");
		price.focus();
        return false;
	}
	return true;
}


//VALIDAZIONE DESCRIZIONE
function firstBeLetter(utext){
	var letters = /^[A-Za-z].*/;
	if(!utext.val().match(letters)){
		$(".error-statement").html("il campo selezionato deve iniziare con una lettera");
		utext.focus();
        return false;
	}
	return true;
}

//VALIDAZIONE NOME, COGNOME
function allLetter(uname){     
	var letters = /^[A-Z][a-z]+$/;
    if(uname.val().match(letters)){
		var letters = /^[A-Z][a-z]{3,}$/;
        if(uname.val().match(letters)){	
           return true;
        }else{
			$(".error-statement").html("almeno 3 caratteri");
			uname.focus();
            return false;
		}
    }else{
       $(".error-statement").html("parola selezionata non valida");
       uname.focus();
       return false;
    }
}

//VALIDAZIONE NOMI PRODOTTO E CATEGORIA
function allLetterWithSpace(uname){     
	var letters = /^([A-Z][a-z]*)(\s[a-z]*)*$/;
    if(uname.val().match(letters)){
           return true;
    }else{
       $(".error-statement").html("parola selezionata non valida");
       uname.focus();
       return false;
    }
}

//VAIDAZIONE EMAIL
function ValidateEmail(uemail) {
    var esito = false;
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    
    if (!uemail.val().match(mailformat)) {
        $(".error-statement").html("email non valida");
        uemail.focus();
        esito = false;
    } else {
        // verifica email già esistente;
        $.ajax({
            url: "register",
            method: "POST",
            data: { data: uemail.val(), action: "ajax" },
            success: function(response) {
                console.log(response);
                esito = true;
            },
            error: function() {
                $(".error-statement").html("email gia' esistente");
                uemail.focus();
                esito = false;
            },
            async: false // Imposta la chiamata AJAX in modo sincrono
        });
    }
    
    return esito;
}


//VALIDAZIONE PASSWORD
function passid_validation(passid,mx,my)
{
   var passid_len = passid.val().length;
   if (passid_len == 0 ||passid_len >= my || passid_len < mx)
   {
        $(".error-statement").html("la password deve avere dai "+mx+" ai "+my+" caratteri"); 
        passid.focus();      
        return false;
   }
   return true;
}

// VALIDAZIONE CONFERMA PASSWORD 
function pass_confirm(pass1, pass2)
{
   if(pass1.val()!=pass2.val()){
	   $(".error-statement").html("hai inserito 2 password diverse");
	   pass1.focus();
	   return false;
   }
   return true;
}

//VALIDAZIONE USERNAME
function userid_validation(uid,mx,my)
{	
    var uid_len = uid.val().length;
    if (uid_len == 0 || uid_len >= my || uid_len < mx){
        $(".error-statement").html("lo username deve avere dai "+mx+" ai "+my+" caratteri");  
        uid.focus();   
        return false;
    } else {
        // verifica username già esistente;
        var esito;
        $.ajax({
            url: "validation",
            method: "POST",
            data: { data: uid.val(), action: "username" },
            success: function(response) {
                console.log(response);
                esito = true;
            },
            error: function() {
                $(".error-statement").html("username gia' esistente");
                uid.focus();
                esito = false;
            },
            async: false // Imposta la chiamata AJAX in modo sincrono
        });
        
        if(esito==false)   return false;
    }
    
    return alphanumeric(uid);
}

//CONTINUO USERNAME
function alphanumeric(uadd)   //VERIFICA SIA ALPHANUMERICO 
{ 
    var letters = /^[0-9a-zA-Z]+$/;
    if(!uadd.val().match(letters)){
         $(".error-statement").html("lo username deve avere solo caratteri alfanumerici");
         uadd.focus();
         return false;
    }
    
    return true;
}

//VALIDAZIONE CODICE FISCALE
function cf_validation(ucf){
	var letters = /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/;
    if(!ucf.val().match(letters)){
         $(".error-statement").html("Codice Fiscale non valido");
         ucf.focus();
         return false;
    } else {
        // verifica +codice fiscale già esistente;
        var esito;
        $.ajax({
            url: "validation",
            method: "POST",
            data: { data: ucf.val(), action: "cf" },
            success: function(response) {
                console.log(response);
                esito = true;
            },
            error: function() {
                $(".error-statement").html("codice fiscale gia' esistente");
                ucf.focus();
                esito = false;
            },
            async: false // Imposta la chiamata AJAX in modo sincrono
        });
        
       return esito;
    }
}

//VALIDAZIONE NUMERO DI CARTA
function n_card_validation(unum){
	var letters = /^\d{16}$/;

    if(!unum.val().match(letters)){   
         $(".error-statement").html("numero di carta non valido");
         unum.focus();
         return false;
    } else {
        // verifica numero carta già esistente;
        var esito;
        $.ajax({
            url: "validation",
            method: "POST",
            data: { data: unum.val(), action: "num-carta" },
            success: function(response) {
                console.log(response);
                esito = true;
            },
            error: function() {
                $(".error-statement").html("numero carta gia' esistente");
                unum.focus();
                esito = false;
            },
            async: false // Imposta la chiamata AJAX in modo sincrono
        });
        
       return esito;
    }
}

//VALIDAZIONE CVV
function cvv_validation(cvv){
	var letters = /^\d{3}$/;

    if(!cvv.val().match(letters)){
         $(".error-statement").html("cvv non valido");
         cvv.focus();
         return false;
    }
    
    return true;
}

//VALIDAZIONE IBAN
function iban_validation(iban){
	//è così lunga perchè valida tutte le carte
	var letters = /^(?:(?:IT|SM)\d{2}[A-Z]\d{10}[0-9A-Z]{12}|CY\d{2}[A-Z]\d{23}|NL\d{2}[A-Z]{4}\d{10}|LV\d{2}[A-Z]{4}\d{13}|(?:BG|BH|GB|IE)\d{2}[A-Z]{4}\d{14}|GI\d{2}[A-Z]{4}\d{15}|RO\d{2}[A-Z]{4}\d{16}|KW\d{2}[A-Z]{4}\d{22}|MT\d{2}[A-Z]{4}\d{23}|NO\d{13}|(?:DK|FI|GL|FO)\d{16}|MK\d{17}|(?:AT|EE|KZ|LU|XK)\d{18}|(?:BA|HR|LI|CH|CR)\d{19}|(?:GE|DE|LT|ME|RS)\d{20}|IL\d{21}|(?:AD|CZ|ES|MD|SA)\d{22}|PT\d{23}|(?:BE|IS)\d{24}|(?:FR|MR|MC)\d{25}|(?:AL|DO|LB|PL)\d{26}|(?:AZ|HU)\d{27}|(?:GR|MU)\d{28})$/i

    if(!iban.val().match(letters)){
         $(".error-statement").html("IBAN non valido");
         iban.focus();
         return false;
    }else {
        // verifica iban già esistente;
        var esito;
        $.ajax({
            url: "validation",
            method: "POST",
            data: { data: iban.val(), action: "iban" },
            success: function(response) {
                console.log(response);
                esito = true;
            },
            error: function() {
                $(".error-statement").html("IBAN gia' esistente");
                iban.focus();
                esito = false;
            },
            async: false // Imposta la chiamata AJAX in modo sincrono
        });
        
       return esito;
    }
}

//VALIDAZIONE INDIRIZZO (VIA-CIVICO-CAP)
function add_validation(via,civico,cap){
   
    // verifica indirizzo già esistente;
        var esito;
        $.ajax({
            url: "validation",
            method: "POST",
            data: { data: via.val()+" "+civico.val()+" "+cap.val(), action: "address" },
            success: function(response) {
                console.log(response);
                esito = true;
            },
            error: function() {
                $(".error-statement").html("indirizzo gia' esistente");
                via.focus();
                esito = false;
            },
            async: false // Imposta la chiamata AJAX in modo sincrono
        });
       
     if(!via_validation(via)) return false;
     else if(!civico_validation(civico)) return false;
     else if(!cap_validation(cap)) return false;
     else  return esito;
}



//VALIDAZIONE VIA
function via_validation(via){
	
	var letters = /^[A-Za-z\s]+$/;
    if(!via.val().match(letters)){
         $(".error-statement").html("Via non valida");
         via.focus();
         return false;
    }
    
    return true;
}

//VALIDAZIONE CIVICO
function civico_validation(civico){
	
	var letters = /^\d{1,3}$/ ;
    if(!civico.val().match(letters)){
         $(".error-statement").html("Civico non valido");
         civico.focus();
         return false;
    }
    
    return true;
}

//VALIDAZIONE CAP
function cap_validation(cap){
	
	var letters = /^\d{5}$/;
    if(!cap.val().match(letters)){
         $(".error-statement").html("CAP non valido");
         cap.focus();
         return false;
    }
    
    return true;
}

//VALIDAZIONE CITTA'
function citta_validation(citta){
	
	var letters = /^[A-Za-z\s]+$/;
    if(!citta.val().match(letters)){
         $(".error-statement").html("Citta' non valida");
         citta.focus();
         return false;
    }
    
    return true;        
}

//VALIDAZIONE NUMERO
function checkPhonenumber(inputtxt) {
	var phoneno = /^([0-9]{10})$/;
	if(!inputtxt.val().match(phoneno)){ 
         $(".error-statement").html("numero non valido");
         inputtxt.focus();
         return false;
    }else{
		// verifica indirizzo già esistente;
        var esito;
        $.ajax({
            url: "validation",
            method: "POST",
            data: { data: inputtxt.val(), action: "phone" },
            success: function(response) {
                console.log(response);
                esito = true;
            },
            error: function() {
                $(".error-statement").html("telefono gia' usato");
                inputtxt.focus();
                esito = false;
            },
            async: false // Imposta la chiamata AJAX in modo sincrono
        });	
     }
     
    return esito;
}
