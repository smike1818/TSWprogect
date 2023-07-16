/**
 * 
 */
$(document).ready(function() {
 
  $(".delete-button").click(function(){
	  var currentImage = $(".cycle-slide-active");
	  
	  if (typeof currentImage.val() === "undefined")   currentImage = $(".only-image");
	  
      var imagePath = currentImage.attr("src");
      var fileName = imagePath.split("/").pop();
      var art = $(".delete-input").val();
      
       $.ajax({
          url: 'imagespage', 
          method: 'POST',
          data: { 
			  action: "delete",
			  nome: fileName,
			  code: art
          }, 
          success: function() {            
             window.location.href = "./imagespage?code="+art;
          }
         


        });
  })
  
});

