/**
 * 
 */
$('.menuButton').click(function()
{
        $('.menu').animate({"left":"0px"}, 100); 
});

$('.closeButton').click(function()
{
        $('.menu').animate({"left":"-200px"}, 100);
});