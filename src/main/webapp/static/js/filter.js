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
/*
var semaforo = 2;
var articolAtLastRequest;
var articolAtRequest;
var articolAfterRequest;
var moreArticol = true;
//!table in inglese
function yHandler(){
    var wrap = document.getElementById('wrap');
    var contentHeight = wrap.offsetHeight;
    var yOffset = window.pageYOffset;
    var y = yOffset + window.innerHeight;
    var reset = "no";
    //se ci sono ancora altri contenuti e y > contentHeight lancio la richiesta
    if(moreArticol && y > contentHeight) {
        //faccio presente che voglio fare una richiesta
        semaforo += -1;
        //farò la richiesta solo se semaforo è pari ad 1
        if (semaforo === 1) {
            //verifico gli articoli al momento della richiesta
            articolAtRequest = document.getElementsByClassName('articolo').length;
            //se al momento della richiesta il numero di articoli è pari a quello dell'ultima richiesta
            //allora la mia richiesta è un duplicato quinid non la eseguo
            if(articolAtLastRequest != articolAtRequest) {
                //conto il numero di articoli esattamente prima della richiesta
                articolAtLastRequest = document.getElementsByClassName('articolo').length;
                //eseguo la rihciesta
                filterOrUpdate(reset, table);
                }
            }
    }
}
window.onscroll = yHandler;

*/
function filter(reset,table) {

    var categoria,piattaforma,genere,ordine,servlet;

    //se sto filatrato reset === yes quindi il limit è 0 altrimenti il numero di articoli
    if (reset === "yes")
        offset = 0;
    else
        offset = document.getElementsByClassName('articolo').length;
    //se sono in shop devo passare la categoria altrimenti categoria e piattaforma
    if(table === "shop")
        categoria = document.getElementById('cButton').innerHTML.toString();
    else
    {
        piattaforma = document.getElementById('pButton').innerHTML.toString();
        genere = document.getElementById('tButton').innerHTML.toString();
    }
    //sicuramente passerò un criterio di ordinamento
    ordine = document.getElementById('sButton').innerHTML.toString();

    servlet = "/Rojina_Review_war/"+table;
    console.log(servlet);
        $(document).ready(function () {
        $.getJSON({
            url: servlet,//la path dipende dalla table passata
            type: "post",
            data: {"piattaforma": piattaforma, "genere": genere, "ordine": ordine, "categoria":categoria},
            error: function (xhr, status, error) {
                //alert("error");
            },
            success: function (data) {

                var articoli = document.getElementsByClassName('articoli')[0];
                var newA = "";
                for (d in data) {
                    var a,immagine,voto,nome,prezzo,articolo,id,titolo,testo;
                    articolo = data[d];
                    id = articolo["id"];
                    immagine = articolo.immagine;
                    if(table != "shop") {
                        titolo = articolo.nome;
                        testo = articolo.testo;
                        if (table == "reviews")  //recensione e prodotto hanno un voto
                            voto = articolo.votoGiornalista;
                    }
                    else {
                            voto = articolo.mediaVoto;
                            nome = articolo.nome;
                            prezzo = articolo.prezzo;
                    }
                    a =
                        "<div class = \"articolo\" id=\"" + id + "\">" +
                        "<a href=\"/Rojina_Review_war/getResource?type=" + table + "&id=" + id + "\">" +
                        "<img src = '" + immagine + "' alt =\"copertina\" decoding=\"async\">" +
                        "<div class = \"articolo-content\">" +
                        (table != "shop" ?
                            "<h2>" + titolo + "</h2>" + "<p>" + testo + "</p>" :
                            "<h2>" + nome + "</h2>" + "<p>" + prezzo + " $</p>" ) +
                        (table != "news" ?
                            "<p class=\"voto\">" + voto + "</p>" :
                            "") +
                        "</div>" +
                        "</a>" +
                        "</div>" ;

                    newA += a;
                }
                articoli.innerHTML = newA;

                /*
                //conto il numero di articoli esattamente dopo la richiesta
                articolAfterRequest = document.getElementsByClassName('articolo').length;
                console.log(articolAtLastRequest +" "+ articolAfterRequest +" "+moreArticol);
                //se dopo la richiesta il numero di articoli non è cambiato allora non ci sono contenuti
                if(articolAtLastRequest === articolAfterRequest)
                    moreArticol = false;
                //permetto agli altri di fare richieste
                semaforo = 2;
                */
            }
        });
    });
}