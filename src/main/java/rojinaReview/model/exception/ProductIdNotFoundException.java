package rojinaReview.model.exception;

public class ProductIdNotFoundException extends Exception{
    public ProductIdNotFoundException(String id){
        super("Prodotto con id: "+id+" non trovato");
    }
}
