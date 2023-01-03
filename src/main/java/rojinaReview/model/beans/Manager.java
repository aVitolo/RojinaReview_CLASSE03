package rojinaReview.model.beans;

import rojinaReview.model.utilities.Persona;

import java.io.UnsupportedEncodingException;

public class Manager extends Persona {
    static public String[] fieldsName = {"Id","Nome","Cognome","Email","Password Hash"};

    private int id;
    private boolean verificato;

    public Manager() {
        super();
    }

    public Manager(String nome, String cognome, String email, String password, int id) throws UnsupportedEncodingException {
        super(nome, cognome, email, password);
        this.id = id;
    }

    public Manager(String nome, String cognome, String email, String password, int id, boolean verificato) throws UnsupportedEncodingException {
        super(nome, cognome, email, password);
        this.id = id;
        this.verificato=verificato;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isVerificato(){return  verificato;}
    public void setVerificato(boolean verificato){this.verificato=verificato;}
}
