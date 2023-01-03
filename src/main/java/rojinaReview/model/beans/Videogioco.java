package rojinaReview.model.beans;

import java.util.ArrayList;
import java.util.HashMap;

public class Videogioco {

    /* Attributes */

    private ArrayList<Piattaforma> piattaforme;
    private ArrayList<Tipologia> tipologie;
    private java.sql.Date dataDiRilascio;
    private float mediaVoto;
    private int numeroVoti;
    private String titolo;
    private String casaDiSviluppo;
    private String copertina;
    private HashMap<Integer, String> listaNotizie;
    private ArrayList<Parere> pareri;

    /* Constructor */

    public Videogioco() {
    }

    public Videogioco(String titolo,
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

    public Videogioco(String titolo,
                      String casaDiSviluppo,
                      int numeroVoti,
                      float mediaVoto,
                      java.sql.Date dataDiRilascio,
                      String copertina,
                      ArrayList<Piattaforma> piattaforme,
                      ArrayList<Tipologia> tipologie,
                      HashMap<Integer,String> notizie,
                      ArrayList<Parere> voti) {
        this.titolo = titolo;
        this.dataDiRilascio = dataDiRilascio;
        this.casaDiSviluppo = casaDiSviluppo;
        this.mediaVoto = mediaVoto;
        this.numeroVoti = numeroVoti;
        this.copertina = copertina;
        this.piattaforme = piattaforme;
        this.tipologie = tipologie;
        this.listaNotizie=notizie;
        this.pareri=voti;
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
        this.dataDiRilascio = dataDiRilascio;
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

    public HashMap<Integer, String> getListaNotizie() {
        return listaNotizie;
    }

    public void setListaNotizie(HashMap<Integer, String> listaNotizie) {
        this.listaNotizie = listaNotizie;
    }

    public ArrayList<Parere> getPareri() {
        return pareri;
    }

    public void setPareri(ArrayList<Parere> pareri) {
        this.pareri = pareri;
    }
}
