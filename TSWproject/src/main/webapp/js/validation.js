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
  
});


// FUNZIONE DI VALIDAZIONE LOGIN 


function validateLogin(form) {
  var valid = true;
  
  var username = form.find("input[name='un']").val();
  var password = form.find("input[name='pw']").val();
  
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
  
  var username = form.find("input[name='us']").val();
  var password = form.find("input[name='pws']").val();
  var nome = form.find("input[name='name']").val();
  var cognome = form.find("input[name='cognome']").val();
  var email = form.find("input[name='email']").val();
  var cf = form.find("input[name='cf']").val();
  var conferma = form.find("input[name='pws1']").val();
  
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
  
  var numero = form.find("input[name='number-card']").val();
  var cvv = form.find("input[name='cvv']").val();
  var iban = form.find("input[name='IBAN']").val();
  
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


function validateAddress(form){
  var valid = true;
  
  var via = form.find("input[name='via']").val();
  var civico = form.find("input[name='civico']").val();
  var citta = form.find("input[name='citta']").val();
  var cap = form.find("input[name='CAP']").val();
  
  if (!via_validation(via)) {
    valid = false;
  }
  if (!civico_validation(civico)) {
    valid = false;
  }
  if(!citta_validation(citta)){
	  valid=false;
  }
  if(!cap_validation(cap)){
	  valid=false;
  }
  
  if (valid) {
	$(".error-statement").empty();
    form.get(0).submit(); // Sottometti il modulo
  }	
}







//VALIDAZIONE NOME E COGNOME
function allLetter(uname){     
	var letters = /^[A-Za-z]+$/;
    if(uname.match(letters)){		
        return true;
    }else{
       $(".error-statement").html("il nome e il cognome devono avere solo caratteri alfabetici");
       return false;
    }
}

//VAIDAZIONE EMAIL
function ValidateEmail(uemail)
{
     var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
     if(uemail.match(mailformat)){
           return true;
     }else{
        $(".error-statement").html("email non valida");
        return false;
     }
}

//VALIDAZIONE PASSWORD
function passid_validation(passid,mx,my)
{
   var passid_len = passid.length;
   if (passid_len == 0 ||passid_len >= my || passid_len < mx)
   {
        $(".error-statement").html("la password deve avere dai "+mx+" ai "+my+" caratteri");       
        return false;
   }
   return true;
}

// VALIDAZIONE CONFERMA PASSWORD 
function pass_confirm(pass1, pass2)
{
   if(pass1!=pass2){
	   $(".error-statement").html("hai inserito 2 password diverse");
	   return false;
   }
   return true;
}

//VALIDAZIONE USERNAME
function userid_validation(uid,mx,my)
{
    var uid_len = uid.length;
    if (uid_len == 0 || uid_len >= my || uid_len < mx){
        $(".error-statement").html("lo username deve avere dai "+mx+" ai "+my+" caratteri");
        return false;
    }
    
    return alphanumeric(uid);
}

//CONTINUO USERNAME
function alphanumeric(uadd)   //VERIFICA SIA ALPHANUMERICO 
{ 
    var letters = /^[0-9a-zA-Z]+$/;
    if(uadd.match(letters)){
         return true;
    }else{
         $(".error-statement").html("lo username deve avere solo caratteri alfanumerici");
         return false;
    }
}

//VALIDAZIONE CODICE FISCALE
function cf_validation(ucf){
	var letters = /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/;
    if(ucf.match(letters)){
         return true;
    }else{
         $(".error-statement").html("Codice Fiscale non valido");
         return false;
    }
}

//VALIDAZIONE NUMERO DI CARTA
function n_card_validation(unum){
	var letters = /^\d{16}$/;

    if(unum.match(letters)){
         return true;
    }else{
         $(".error-statement").html("numero di carta non valido");
         return false;
    }
}

//VALIDAZIONE CVV
function cvv_validation(cvv){
	var letters = /^\d{3}$/;

    if(cvv.match(letters)){
         return true;
    }else{
         $(".error-statement").html("cvv non valido");
         return false;
    }
}

//VALIDAZIONE IBAN
function iban_validation(iban){
	//è così lunga perchè valida tutte le carte
	var letters = /^(?:(?:IT|SM)\d{2}[A-Z]\d{10}[0-9A-Z]{12}|CY\d{2}[A-Z]\d{23}|NL\d{2}[A-Z]{4}\d{10}|LV\d{2}[A-Z]{4}\d{13}|(?:BG|BH|GB|IE)\d{2}[A-Z]{4}\d{14}|GI\d{2}[A-Z]{4}\d{15}|RO\d{2}[A-Z]{4}\d{16}|KW\d{2}[A-Z]{4}\d{22}|MT\d{2}[A-Z]{4}\d{23}|NO\d{13}|(?:DK|FI|GL|FO)\d{16}|MK\d{17}|(?:AT|EE|KZ|LU|XK)\d{18}|(?:BA|HR|LI|CH|CR)\d{19}|(?:GE|DE|LT|ME|RS)\d{20}|IL\d{21}|(?:AD|CZ|ES|MD|SA)\d{22}|PT\d{23}|(?:BE|IS)\d{24}|(?:FR|MR|MC)\d{25}|(?:AL|DO|LB|PL)\d{26}|(?:AZ|HU)\d{27}|(?:GR|MU)\d{28})$/i

    if(iban.match(letters)){
         return true;
    }else{
         $(".error-statement").html("IBAN non valido");
         return false;
    }
}

//VALIDAZIONE VIA
function via_validation(via){
	
	var letters = /^[A-Za-z\s]+$/;
    if(via.match(letters)){
         return true;
    }else{
         $(".error-statement").html("Via non valida");
         return false;
    }
}

//VALIDAZIONE CIVICO
function civico_validation(civico){
	
	var letters = /^\d{1,3}$/ ;
    if(civico.match(letters)){
         return true;
    }else{
         $(".error-statement").html("Civico non valido");
         return false;
    }
}

//VALIDAZIONE CAP
function cap_validation(cap){
	
	var letters = /^\d{5}$/;
    if(cap.match(letters)){
         return true;
    }else{
         $(".error-statement").html("CAP non valido");
         return false;
    }
}

//VALIDAZIONE CITTA'
function citta_validation(citta){
	
	var letters = /^[A-Za-z\s]+$/;
    if(citta.match(letters)){
         return true;
    }else{
         $(".error-statement").html("Citta' non valida");
         return false;
    }
}

