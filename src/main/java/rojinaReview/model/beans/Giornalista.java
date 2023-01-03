package rojinaReview.model.beans;

import rojinaReview.model.utilities.Persona;

import java.util.ArrayList;

public class Giornalista extends Utente {
    static public String[] fieldsName = {"Id","Nome","Cognome","Email","Password Hash"};

    private boolean verificato;
    private ArrayList<Articolo> articoli;

    public Giornalista() {
        super();
    }

    public Giornalista(int id, String nome, String cognome, String email, String password, String immagine, boolean verificato, ArrayList<Articolo> articoli) {
        super(id, nome, cognome, email, password, immagine);
        this.verificato = verificato;
        this.articoli = articoli;
    }


    public ArrayList<Articolo> getArticoli() {
        return articoli;
    }

    public void setArticoli(ArrayList<Articolo> articoli) {
        this.articoli = articoli;
    }

    public void setVerificato(boolean verificato){this.verificato=verificato;}

    public boolean isVerificato() {
        return verificato;
    }
}
