package rojinaReview.model.beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class Videogioco {

    /* Attributes */

    private int id;
    private ArrayList<String> piattaforme;
    private ArrayList<String> generi;
    private java.sql.Date dataDiRilascio;
    private float mediaVoto;
    private int numeroVoti;
    private String titolo;
    private String casaDiSviluppo;
    private String copertina;
    private HashMap<Integer, String> listaNotizie;
    private int idRecensione;
    private String nomeRecensione;
    private ArrayList<Parere> pareri;

    /* Constructor */

    public Videogioco() {
    }

    public Videogioco(int id, ArrayList<String> piattaforme, ArrayList<String> generi, Date dataDiRilascio, float mediaVoto, int numeroVoti, String titolo, String casaDiSviluppo, String copertina, HashMap<Integer, String> listaNotizie, ArrayList<Parere> pareri) {
        this.id = id;
        this.piattaforme = piattaforme;
        this.generi = generi;
        this.dataDiRilascio = dataDiRilascio;
        this.mediaVoto = mediaVoto;
        this.numeroVoti = numeroVoti;
        this.titolo = titolo;
        this.casaDiSviluppo = casaDiSviluppo;
        this.copertina = copertina;
        this.listaNotizie = listaNotizie;
        this.pareri = pareri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getPiattaforme() {
        return piattaforme;
    }

    public void setPiattaforme(ArrayList<String> piattaforme) {
        this.piattaforme = piattaforme;
    }

    public ArrayList<String> getGeneri() {
        return generi;
    }

    public void setGeneri(ArrayList<String> generi) {
        this.generi = generi;
    }

    public Date getDataDiRilascio() {
        return dataDiRilascio;
    }

    public void setDataDiRilascio(Date dataDiRilascio) {
        this.dataDiRilascio = dataDiRilascio;
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

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCasaDiSviluppo() {
        return casaDiSviluppo;
    }

    public void setCasaDiSviluppo(String casaDiSviluppo) {
        this.casaDiSviluppo = casaDiSviluppo;
    }

    public String getCopertina() {
        return copertina;
    }

    public void setCopertina(String copertina) {
        this.copertina = copertina;
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
