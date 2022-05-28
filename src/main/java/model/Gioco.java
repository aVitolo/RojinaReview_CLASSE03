package model;

import java.sql.Date;
import java.util.ArrayList;

public class Gioco {

    private String titolo;
    private Date dataDiRilascio;
    private String casaDiSviluppo;
    private float mediaVoto;
    private int numeroVoti;
    private byte[] copertina;
    private ArrayList<String> piattaforme;
    private ArrayList<String> categorie;

    /*
    Possibili altri attributi:
        -copertina del gioco
     */

    /* Constructor */

    public Gioco() {
    }

    /* Getter and Setter */

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Date getDataDiRilascio() {
        return dataDiRilascio;
    }

    public void setDataDiRilascio(Date dataDiRilascio) {
        dataDiRilascio = dataDiRilascio;
    }

    public String getCasaDiSviluppo() {
        return casaDiSviluppo;
    }

    public void setCasaDiSviluppo(String casaDiSviluppo) {
        this.casaDiSviluppo = casaDiSviluppo;
    }

    public float getMediaVoto() {
        return mediaVoto;
    }

    public void setMediaVoto(float mediaVoto) {
        this.mediaVoto = mediaVoto;
    }

    public int getNumeroVoti() {
        return numeroVoti;
    }

    public void setNumeroVoti(int numeroVoti) { this.numeroVoti = numeroVoti; }

    public byte[] getCopertina() { return copertina; }

    public void setCopertina(byte[] copertina) { this.copertina = copertina; }

    public ArrayList<String> getPiattaforme() { return piattaforme; }

    public void setPiattaforme(ArrayList<String> piattaforme) { this.piattaforme = piattaforme; }

    public ArrayList<String> getCategorie() { return categorie; }

    public void setCategorie(ArrayList<String> categorie) { this.categorie = categorie; }


}
