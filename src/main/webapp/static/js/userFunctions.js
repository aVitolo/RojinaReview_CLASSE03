function addToUserCart(canDo) {
    if(canDo == 1 || canDo == 0) {
        let id = $("#prodottoID").val();
        let quantita = $("#quantita").val();
        let query = "prodottoID=" + id + "&quantita=" + quantita;
        let carrelloElement = document.getElementById("countCarrello");
        let quantitaIniziale = document.getElementById("disponibilità").innerHTML;
        let nuovaQuantita = quantitaIniziale - quantita;

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                if (this.responseText === "0") {
                    alert("E' stato riscontrato un errore durante l' inserimento")
                } else {
                    carrelloElement.innerHTML = this.responseText;
                    document.getElementById("disponibilità").innerHTML = nuovaQuantita;
                }
            }
        };
        xhttp.open("POST", "./addProductCart", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send(query);
    }
    else{
        alert("Errore, non puoi aggiungere al carrello \n se sei un Admin o un Giornalista");
    }
}

function canComment(canDo) {
    if (canDo == 0) {
        alert("Devi essere registrato per commentare!");
        return false;
    }
    if (canDo == 2) {
        alert("Giornalisti e amministratori non possono commentare!")
        return false;
    }
    if (canDo == 1)
        return true;
}

function canVote(canDo) {
    if (canDo == 0) {
        alert("Devi essere registrato per votare!");
        return false;
    }
    if (canDo == 2) {
        alert("Giornalisti e amministratori non possono votare!")
        return false;
    }
    if (canDo == 1)
        return true;
}

function canBuy(canDo) {
    if (canDo == 2) {
        alert("Giornalisti e amministratori non possono acquistare prodotti!")
        return false;
    } else
        return true;
}