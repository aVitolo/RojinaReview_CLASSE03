package rojinaReview.model.beans;

import rojinaReview.model.utilities.Persona;

import java.io.UnsupportedEncodingException;

public class Giornalista extends Persona {
    static public String[] fieldsName = {"Id","Nome","Cognome","Email","Password Hash"};

    private int id;

    private boolean verificato;

    public Giornalista() {
        super();
    }

    public Giornalista(String nome, String cognome, String email, String password, String immagine, int id) throws UnsupportedEncodingException {
        super(nome, cognome, email, password, immagine);
        this.id = id;
    }

    public Giornalista(String nome, String cognome, String email, String password, String immagine, int id, boolean verificato) throws UnsupportedEncodingException {
        super(nome, cognome, email, password, immagine);
        this.id = id;
        this.verificato=verificato;
    }

    public String getImmagine() {
        return super.getImmagine();
    }

    public int getId() {
        return id;
    }

    public void setImmagine(String immagine) {
        super.setImmagine(immagine);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVerificato(boolean verificato){this.verificato=verificato;}

    public boolean isVerificato() {
        return verificato;
    }
}
