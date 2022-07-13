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
                alert("An error occurrent in your request")
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
    let element = prompt("Aggiungi Ora");
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
                    alert("This table have a protection, the system don't allow this action")
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
