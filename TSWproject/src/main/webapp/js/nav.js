/**
 * 
 */
$('#menuButton').click(function()
{
        $('.menu').animate({"left":"0px"}, 300); 
});


$('.closeButton').click(function()
{
        $('.menu').animate({"left":"-200px"}, 300);
});
