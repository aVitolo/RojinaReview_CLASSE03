/* Responsive Funtion */

function expandMenu() {
    var x = document.getElementById("nav");
    if (x.className === "navigazione") {
        x.className += " responsive";
    } else {
        x.className = "navigazione";
    }

    x = document.getElementById("us");
    if (x.className === "user") {
        x.className += " responsive";
    } else {
        x.className = "user";
    }
}

