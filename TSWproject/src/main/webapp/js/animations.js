$(document).ready(function() {
  function applyHoverEffect(className) {
    var $divs = $('.' + className);

    $divs.css({
      transition: "transform 0.3s, box-shadow 0.3s",
      opacity: "0.9"
    });

    $divs.each(function() {
      var $div = $(this);

      $div.on("mouseover", function(event) {
        $div.css({
          transform: "scale(1.05)",
          boxShadow: "0 2px 4px rgba(0, 0, 0, 0.3)",
          opacity: "1"
        });
      });

      $div.on("mouseout", function(event) {
        $div.css({
          transform: "scale(1)",
          boxShadow: "none",
          opacity: "0.9"
        });
      });
    });
  }

  // Call the function for elements with a specific class
  applyHoverEffect('catalogo-items');
});
