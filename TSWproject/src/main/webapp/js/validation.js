/**
 * 
 */
$(document).ready(function() {
	
  // VALIDAZIONE DEI LOGIN
  $("#login-form").submit(function(event) {
    event.preventDefault();
    validateLogin($(this)); // Effettua la validazione e memorizza il risultato
  });
  
  //VALIDAZIONE REGISTRAZIONE
  $("#registration-form").submit(function(event) {
    event.preventDefault();
    validateReg($(this)); // Effettua la validazione e memorizza il risultato
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
  if(!allLetter(nome) || !allLetter(cognome)){
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
    form.get(0).submit(); // Sottometti il modulo
  }
}

//VALIDAZIONE NOME E COGNOME
function allLetter(uname){     
	var letters = /^[A-Za-z]+$/;
    if(uname.match(letters)){
        return true;
    }else{
       alert('name/surname must have alphabet characters only');
       uname.focus();
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
        alert("You have entered an invalid email address!");
        return false;
     }
}

//VALIDAZIONE PASSWORD
function passid_validation(passid,mx,my)
{
   var passid_len = passid.length;
   if (passid_len == 0 ||passid_len >= my || passid_len < mx)
   {
        alert("Password should not be empty / length be between "+mx+" to "+my);
        return false;
   }
   return true;
}

// VALIDAZIONE CONFERMA PASSWORD 
function pass_confirm(pass1, pass2)
{
   if(pass1!=pass2){
	   alert("password non corrispondono");
	   return false;
   }
   return true;
}

//VALIDAZIONE USERNAME
function userid_validation(uid,mx,my)
{
    var uid_len = uid.length;
    if (uid_len == 0 || uid_len >= my || uid_len < mx){
        alert("User Id should not be empty / length be between "+mx+" to "+my);
        uid.focus();
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
         alert('User address must have alphanumeric characters only');
         uadd.focus();
         return false;
    }
}

//VALIDAZIONE CODICE FISCALE
function cf_validation(ucf){
	var letters = /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/;
    if(ucf.match(letters)){
         return true;
    }else{
         alert('not valid CF');
         ucf.focus();
         return false;
    }
}

