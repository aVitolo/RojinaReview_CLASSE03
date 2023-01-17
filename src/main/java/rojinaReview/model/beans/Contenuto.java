package rojinaReview.model.beans;

import rojinaReview.model.beans.Commento;

import java.util.ArrayList;

public abstract class Contenuto {
    private int id;
    private String nome;
    private String testo;
    private String immagine;
    private ArrayList<Commento> commenti;

    public Contenuto(){
        commenti = new ArrayList<>();
    }

    public Contenuto(int id, String nome, String testo, String immagine, ArrayList<Commento> commenti) {
        this.id = id;
        this.nome = nome;
        this.testo = testo;
        this.immagine = immagine;
        this.commenti = commenti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public ArrayList<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(ArrayList<Commento> commenti) {
        this.commenti = commenti;
    }
}


