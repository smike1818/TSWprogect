function addHoverAnimation(divId) {
    var div = document.getElementById(divId);

    div.style.transition = "transform 0.3s, box-shadow 0.3s";

    div.addEventListener("mouseover", function () {
      div.style.transform = "scale(1.05)";
      div.style.boxShadow = "0 2px 4px rgba(0, 0, 0, 0.3)";
      div.style.opacity = "1";
    });

    div.addEventListener("mouseout", function () {
      div.style.transform = "scale(1)";
      div.style.boxShadow = "none";
      div.style.opacity = "0.9";
    });
}