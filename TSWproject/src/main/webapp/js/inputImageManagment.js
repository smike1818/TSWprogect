/**
 * 
 */
$(document).ready(function() {
  $("#myFileInput").change(function() {
    var fileName = $(this).val().split("\\").pop();
    $("#fileNameLabel").text(fileName);
  });
});