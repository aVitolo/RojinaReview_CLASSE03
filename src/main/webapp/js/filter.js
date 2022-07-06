/* When the user clicks on the button,
 toggle between hiding and showing the dropdown content */
function expandFilter(drop) {
    document.getElementById(drop).classList.toggle("show");
}

function filterFunction(input, drop) {
    var inp, filter, a, i, div, txtValue;
    inp = document.getElementById(input);
    filter = inp.value.toUpperCase();
    div = document.getElementById(drop);
    p = div.getElementsByTagName("p");
    for (i = 0; i < p.length; i++) {
        txtValue = p[i].textContent || p[i].innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            p[i].style.display = "";
        } else {
            p[i].style.display = "none";
        }
    }
}

function setFilter(filterID, buttonID, dropID,closeID){
    var b = document.getElementById(buttonID);
    var f = document.getElementById(filterID);
    b.innerHTML = f.innerHTML;
    expandFilter(dropID);
    if(!document.getElementById(closeID).classList.contains("show"))
        document.getElementById(closeID).classList.toggle("show");
}

function resetFilter(text,buttonID,closeID){
    var b = document.getElementById(buttonID);
    b.innerHTML = text;
    document.getElementById(closeID).classList.toggle("show");
}