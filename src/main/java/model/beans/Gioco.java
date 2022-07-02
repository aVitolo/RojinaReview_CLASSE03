package model.beans;

import java.sql.Date;
import java.util.ArrayList;

public class Gioco {

    /* Attributes */

    private ArrayList<Piattaforma> piattaforme;
    private ArrayList<Tipologia> tipologie;
    private java.sql.Date dataDiRilascio;
    private float mediaVoto;
    private int numeroVoti;
    private String titolo;
    private String casaDiSviluppo;
    private String copertina;

    /* Constructor */

    public Gioco() {
    }

    public Gioco(String titolo,
                 String casaDiSviluppo,
                 int numeroVoti,
                 float mediaVoto,
                 java.sql.Date dataDiRilascio,
                 String copertina,
                 ArrayList<Piattaforma> piattaforme,
                 ArrayList<Tipologia> tipologie) {
        this.titolo = titolo;
        this.dataDiRilascio = dataDiRilascio;
        this.casaDiSviluppo = casaDiSviluppo;
        this.mediaVoto = mediaVoto;
        this.numeroVoti = numeroVoti;
        this.copertina = copertina;
        this.piattaforme = piattaforme;
        this.tipologie = tipologie;
    }

    /* Getter and Setter */

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public java.sql.Date getDataDiRilascio() {
        return dataDiRilascio;
    }

    public void setDataDiRilascio(java.sql.Date dataDiRilascio) {
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

    public void setNumeroVoti(int numeroVoti) {
        this.numeroVoti = numeroVoti;
    }

    public String getCopertina() {
        return copertina;
    }

    public void setCopertina(String copertina) {
        this.copertina = copertina;
    }

    public ArrayList<Piattaforma> getPiattaforme() {
        return piattaforme;
    }

    public void setPiattaforme(ArrayList<Piattaforma> piattaforme) {
        this.piattaforme = piattaforme;
    }

    public ArrayList<Tipologia> getTipologie() {
        return tipologie;
    }

    public void setTipologie(ArrayList<Tipologia> tipologie) {
        this.tipologie = tipologie;
    }


}
