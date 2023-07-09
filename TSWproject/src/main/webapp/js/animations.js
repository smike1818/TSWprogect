$(document).ready(function() {
  function applyHoverEffect(className, IDname) {
	  if(IDname === null){
		var $divs = $('.' + className);  
	  }
	  else{
		  var $divs = $('#' + IDname); 
	  }

    $divs.css({
      transition: "transform 0.2s"
    });

    $divs.each(function() {
      var $div = $(this);

      $div.on("mouseover", function() {
        $div.css({
          transform: "scale(1.05)"
        });
      });

      $div.on("mouseout", function() {
        $div.css({
          transform: "scale(1)"
        });
      });
    });
  }

  // Call the function for elements with a specific class
  applyHoverEffect('product-container', null);
  applyHoverEffect('table-cell', 'cat1');
  applyHoverEffect('table-cell', 'cat2');
});
