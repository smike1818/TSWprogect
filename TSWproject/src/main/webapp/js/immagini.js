/**
 * 
 */
//questo file è stato creato per gestire lo scorrimento delle immagini

$(document).ready(function() {
  $('.cycle-slideshow').cycle({
    fx: 'scrollHorz',
    swipe: true,
    prev: '.prev',
    next: '.next',
    timeout: 0, 
  });
  
  $('.cycle-slideshow').hover(
    function() {
      $(this).find('.prev, .next').show(); // Aggiungi la classe 'show' alle frecce
    },
    function() {
      $(this).find('.prev, .next').hide(); // Rimuovi la classe 'show' dalle frecce
    }
  );
});

