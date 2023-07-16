/**
 * 
 */
$(document).ready(function() {
	$('#change-view').click(function() {
		$('.table-row').toggleClass('change-view');
		$('.image-block').toggleClass('change-imageBlock');
	});
});