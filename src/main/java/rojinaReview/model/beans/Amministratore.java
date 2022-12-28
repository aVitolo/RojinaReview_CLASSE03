package rojinaReview.model.beans;

import rojinaReview.model.utilities.Persona;

import java.io.UnsupportedEncodingException;

public class Amministratore extends Persona {
    static public String[] fieldsName = {"Id","Nome","Cognome","Email","Password Hash"};

    private int id;

    public Amministratore() {
        super();
    }

    public Amministratore(String nome, String cognome, String email, String password, int id) throws UnsupportedEncodingException {
        super(nome, cognome, email, password);
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
