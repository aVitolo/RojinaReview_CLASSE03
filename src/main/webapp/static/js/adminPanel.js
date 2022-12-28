var oldElement;

function search(){
    if(oldElement != null){
        document.getElementById(oldElement).style.color = "black";
    }
    oldElement = prompt("Cerca Ora con Primary Key (First Field Required)");
    document.getElementById(oldElement).style.color = "red";

}

function modifyEntry(element){
    var nameModify = element.getAttribute("name")
    let userInput = prompt("Modifica ["+nameModify+"] ora!! (Es: nome='Stringa' oppure valore=intero)","valoreEsempio=10");

    let request;
    if(className === "Utente"){
        request = className + " SET " + userInput + "WHERE id='" + nameModify + "'";
    }
    else{
        request = className + " SET " + userInput + "WHERE id=" + nameModify;
    }
    request = "query=" + request;

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if(this.responseText !== "true"){
                alert("Si è verificato un errore con la tua richiesta")
            }
            else{
                location.reload();
            }
        }
    };
    xhttp.open("POST", "./UpdateDb", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(request);

}

function add(){
    let query = prompt("Aggiungi Ora (Riempi i valori)",details);
    query = "query=" + className +" "+ necessary + " VALUES " + "(" + query + ");"

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            if (this.responseText !== "true") {
                alert("E' stato riscontrato un errore durante l' inserimento")
            } else {
                alert("L'entry in "+className+" è stata aggiunta con successo");
                location.reload();
            }
        }
    };
    xhttp.open("POST", "./InsertDbEntry", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(query);
}

function deleteEntry(element){
    var name = element.getAttribute("name")
    let conferma = confirm("Sei sicuro di voler Eliminare l' elemento "+ name + "?");
    if(conferma == true) {
        identifier = "identifier=" + name;
        requestText = identifier + "&" + "className=" + className;

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                if (this.responseText !== "true") {
                    alert("Questa entry ha un vincolo relazionale, non è possibile rimuoverla!!")
                } else {
                    deleteTD(name);
                }
            }
        };
        xhttp.open("POST", "./RemoveDbEntry", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send(requestText);
    }
}

function deleteTD(id){
    document.getElementById(id).remove();
}
