package model.beans;

import java.sql.Date;
import java.util.ArrayList;


public class Notizia {

    /* Attributes */

    private byte[] immagine;
    private ArrayList<Commento> commenti;
    private ArrayList<Gioco> giochi;
    private Date dataCaricamento;
    private int id;
    private String testo;
    private String titolo;
    private String giornalista;


    /* Constructor */

    public Notizia() {
    }

    public Notizia(ArrayList<Commento> commenti,
                   ArrayList<Gioco> giochi,
                   byte[] immagine,
                   Date dataCaricamento,
                   int id,
                   String testo,
                   String titolo,
                   String giornalista) {
        this.immagine = immagine;
        this.commenti = commenti;
        this.giochi = giochi;
        this.dataCaricamento = dataCaricamento;
        this.id = id;
        this.testo = testo;
        this.titolo = titolo;
        this.giornalista = giornalista;
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

    public Date getDataCaricamento() {
        return dataCaricamento;
    }

    public void setDataCaricamento(Date dataCaricamento) {
        this.dataCaricamento = dataCaricamento;
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

    public ArrayList<Gioco> getGiochi() {
        return giochi;
    }

    public void setGiochi(ArrayList<Gioco> giochi) {
        this.giochi = giochi;
    }

    public byte[] getImmagine() {
        return immagine;
    }

    public void setImmagine(byte[] immagine) {
        this.immagine = immagine;
    }
}
