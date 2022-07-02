package model.beans;

import model.utilities.Persona;

import java.io.UnsupportedEncodingException;

public class Giornalista extends Persona {


    private int id;
    private String immagine;

    public Giornalista(){
        super();
    }

    public Giornalista(String nome, String cognome, String email, String password, String immagine, int id) throws UnsupportedEncodingException {
        super(nome, cognome, email, password);
        this.immagine = immagine;
        this.id = id;
    }

    public String getImmagine() { return immagine; }

    public int getId() { return id; }

    public void setImmagine(String immagine) { this.immagine = immagine; }

    public void setId(int id) { this.id = id; }
}
