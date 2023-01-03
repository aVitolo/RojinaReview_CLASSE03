package rojinaReview.model.beans;

import rojinaReview.model.utilities.Persona;

public class Manager extends Utente {
    static public String[] fieldsName = {"Id","Nome","Cognome","Email","Password Hash"};

    private boolean verificato;

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
