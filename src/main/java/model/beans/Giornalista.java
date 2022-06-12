package model.beans;

import java.io.UnsupportedEncodingException;

public class Giornalista extends Persona{

    private byte[] immagine;
    private int id;

    public Giornalista(){
        super();
    }

    public Giornalista(String nome, String cognome, String email, String password, byte[] immagine, int id) throws UnsupportedEncodingException {
        super(nome, cognome, email, password);
        this.immagine = immagine;
        this.id = id;
    }

    public byte[] getImmagine() { return immagine; }

    public int getId() { return id; }

    public void setImmagine(byte[] immagine) { this.immagine = immagine; }

    public void setId(int id) { this.id = id; }
}
