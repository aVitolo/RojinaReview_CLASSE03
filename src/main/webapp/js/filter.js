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
    a = div.getElementsByTagName("a");
    for (i = 0; i < a.length; i++) {
        txtValue = a[i].textContent || a[i].innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            a[i].style.display = "";
        } else {
            a[i].style.display = "none";
        }
    }
}