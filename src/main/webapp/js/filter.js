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

function filter(){
    var p = document.getElementById('pButton').innerHTML.toString();
    var t = document.getElementById('tButton').innerHTML.toString();
    var o = document.getElementById('sButton').innerHTML.toString();
    var r ="yes";
    $(document).ready(function () {
        $.getJSON({
            url: "/Rojina_Review_war/news",
            type: "post",
            data: {"reset":r,"piattaforma" : p,"tipologia": t,"ordine" : o},
            error: function (xhr, status, error) {
                //alert("error");
            },
            success: function (data) {

                var articoli = document.getElementsByClassName('articoli')[0];
                var newA ="";
                for (d in data) {
                    var articolo = data[d];
                    var titolo = articolo.titolo;
                    var id = articolo["id"];
                    var immagine= articolo.immagine;
                    var testo = articolo.testo;
                    var a =
                        "<div class = \"articolo\" id="+id+">" +
                        "<a href='/Rojina_Review_war/getResource?type=notizia&id='"+ id +"'>"+
                        "<img src = '" +immagine +"' alt =\"copertina\" decoding=\"async\">" +
                        "<div class = \"articolo-content\">" +
                        "<h2>" + titolo + "</h2>" +
                        "<p>" + testo + "</p>" +
                        "</div>" +
                        "</div>" +
                        "</a>";
                    newA += a;
                }
                articoli.innerHTML = newA;
                triggered = false; // reset onscroll triggere after filtered
            }
        });
    });
}