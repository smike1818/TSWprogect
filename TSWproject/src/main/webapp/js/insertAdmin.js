function showCategoriesForm(){
    var formHTML = 
        '<br><h3> inserimento della categoria </h3><br>' +
        '<form action="insertadmin" method="post">' +
        '<input type="hidden" name="action" value="cat">' +
        '<input name="name" type="text" maxlength="50" required placeholder="Enter name"><br>' +
        '<textarea name="descrizione" maxlength="1000" rows="5" required placeholder="Enter description"></textarea><br>' +
        '<input type="submit" value="Add">' +
        '</form>';
    
    var categoriesElements = document.getElementsByClassName("categorie");
    for (var i = 0; i < categoriesElements.length; i++) {
        categoriesElements[i].innerHTML = formHTML;
    }
    
}

function showImageForm(code){
	 var formHTML = 
        '<br><h3> inserimento delle immagini </h3><br>' +
        '<form action="imagespage?code='+code+'" method="post" enctype="multipart/form-data">' +
        '<input type="hidden" name="action" value="addImage">' +
        '<label for="images">immagini</label><br>' +
        '<input name="images" type="file" multiple required accept="image/*"><br>' +
        '<input type="submit" value="Add">' +
        '</form>';
    
    var categoriesElements = document.getElementsByClassName("image-form");
    for (var i = 0; i < categoriesElements.length; i++) {
        categoriesElements[i].innerHTML = formHTML;
    }
}

