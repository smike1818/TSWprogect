$(document).ready(function() {
  function applyHoverEffect(divId) {
    var $div = $('#' + divId);

    $div.css({
      transition: "transform 0.3s, box-shadow 0.3s",
      opacity: "0.9"
    });

    $div.on("mouseover", function(event) {
      $(this).css({
        transform: "scale(1.05)",
        boxShadow: "0 2px 4px rgba(0, 0, 0, 0.3)",
        opacity: "1"
      });
    });

    $div.on("mouseout", function(event) {
      $(this).css({
        transform: "scale(1)",
        boxShadow: "none",
        opacity: "0.9"
      });
    });
  }

  // Call the function for elements with IDs from 1 to 5
  for (var i = 1; i <= 5; i++) {
    applyHoverEffect(i);
  }
});
