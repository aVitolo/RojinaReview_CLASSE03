function canComment(canDo){
    if(canDo == 0){
        alert("Devi essere registrato per commentare!");
        return false;
    }
    if(canDo == 2)
    {
        alert("Giornalisti e amministratori non possono commentare!")
        return false;
    }
    if(canDo == 1)
        return true;
}

function canVote(canDo){
    if(canDo == 0){
        alert("Devi essere registrato per votare!");
        return false;
    }
    if(canDo == 2)
    {
        alert("Giornalisti e amministratori non possono votare!")
        return false;
    }
    if(canDo == 1)
        return true;
}

function canBuy(canDo){
    if(canDo == 2)
    {
        alert("Giornalisti e amministratori non possono acquistare prodotti!")
        return false;
    }
    else
        return true;
}