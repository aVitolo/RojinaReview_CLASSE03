package model;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Game {
    private String titolo;
    private Date DataDiRilascio;
    private String casaDiSviluppo;
    private float mediaVoto;
    private int numeroVoti;
    private ArrayList<String> piattaforme;
    private ArrayList<String> categorie;
    /*
    Possibili altri attributi:
        -copertina del gioco
     */

    /*
    Considerazioni:
       Al posto di creare due beans, Piattaforme e Categorie, definiamo due array di stringhe.
       Credo sia giusto creare due array, Piattaform e Categorie, nel servlet context da utilizzare per i filtri di ricerca.
     */

    /* Constructor */

    public Game() {
    }

    /* Getter and Setter */

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Date getDataDiRilascio() {
        return DataDiRilascio;
    }

    public void setDataDiRilascio(Date dataDiRilascio) {
        DataDiRilascio = dataDiRilascio;
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

    public ArrayList<String> getPiattaforme() { return piattaforme; }

    public void setPiattaforme(ArrayList<String> piattaforme) { this.piattaforme = piattaforme; }

    public ArrayList<String> getCategorie() { return categorie; }

    public void setCategorie(ArrayList<String> categorie) { this.categorie = categorie; }
}
