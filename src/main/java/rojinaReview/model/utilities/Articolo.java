package rojinaReview.model.utilities;

import rojinaReview.model.beans.Commento;
import rojinaReview.model.beans.Gioco;

import java.util.ArrayList;

public abstract class Articolo {

    /* Attributes */


    private ArrayList<Commento> commenti;
    private java.sql.Date dataCaricamento;
    private Gioco gioco;
    private int id;
    private String testo;
    private String titolo;
    private String giornalista;
    private String immagine;
    private String immagineGiornalista;

    /* Constructor */

    public Articolo() {
    }

    public Articolo(ArrayList<Commento> commenti,
                    String immagine,
                    java.sql.Date dataCaricamento,
                    Gioco gioco,
                    int id,
                    String testo,
                    String titolo,
                    String giornalista,
                    String immagineGiornalista) {
        this.immagine = immagine;
        this.commenti = commenti;
        this.dataCaricamento = dataCaricamento;
        this.gioco = gioco;
        this.id = id;
        this.testo = testo;
        this.titolo = titolo;
        this.giornalista = giornalista;
        this.immagineGiornalista = immagineGiornalista;
    }

    /* Getter and Setter */

    public String getGiornalista() {
        return giornalista;
    }

    public void setGiornalista(String giornalista) {
        this.giornalista = giornalista;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public java.sql.Date getDataCaricamento() {
        return dataCaricamento;
    }

    public void setDataCaricamento(java.sql.Date dataCaricamento) {
        this.dataCaricamento = dataCaricamento;
    }

    public Gioco getGioco() {
        return gioco;
    }

    public void setGioco(Gioco gioco) {
        this.gioco = gioco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public void setCommenti(ArrayList<Commento> commenti) {
        this.commenti = commenti;
    }

    public ArrayList<Commento> getCommenti() {
        return commenti;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public String  getImmagineGiornalista() {
        return immagineGiornalista;
    }

    public void setImmagineGiornalista(String immagineGiornalista) {
        this.immagineGiornalista = immagineGiornalista;
    }
}

