package model.beans;

import java.sql.Date;
import java.util.ArrayList;

public class Gioco {

    /* Attributes */

    private byte[] copertina;
    private ArrayList<Piattaforma> piattaforme;
    private ArrayList<Tipologia> tipologie;
    private Date dataDiRilascio;
    private float mediaVoto;
    private int numeroVoti;
    private String titolo;
    private String casaDiSviluppo;

    /* Constructor */

    public Gioco() {
    }

    public Gioco(String titolo,
                 String casaDiSviluppo,
                 int numeroVoti,
                 float mediaVoto,
                 Date dataDiRilascio,
                 byte[] copertina,
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

    public void setNumeroVoti(int numeroVoti) {
        this.numeroVoti = numeroVoti;
    }

    public byte[] getCopertina() {
        return copertina;
    }

    public void setCopertina(byte[] copertina) {
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
