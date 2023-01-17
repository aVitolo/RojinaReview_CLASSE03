package rojinaReview.model.beans;

public class Manager extends Utente {
    private boolean verificato; //eventualmente da modificare

    public Manager() {
        super();
    }

    public Manager(int id, String nome, String cognome, String email, String password, String immagine, boolean verificato) {
        super(id, nome, cognome, email, password, immagine);
        this.verificato = verificato;
    }

    public boolean isVerificato(){return  verificato;}

    public void setVerificato(boolean verificato){this.verificato=verificato;}
}
