package model.utilities;

import model.beans.Commento;
import model.beans.Gioco;

import java.sql.Date;
import java.util.ArrayList;

public abstract class Articolo {

    /* Attributes */

    private byte[] immagine;
    private ArrayList<Commento> commenti;
    private Date dataCaricamento;
    private Gioco gioco;
    private int id;
    private String testo;
    private String titolo;
    private String giornalista;


    /* Constructor */

    public Articolo() {
    }

    public Articolo(ArrayList<Commento> commenti,
                    byte[] immagine,
                    Date dataCaricamento,
                    Gioco gioco,
                    int id,
                    String testo,
                    String titolo,
                    String giornalista) {
        this.immagine = immagine;
        this.commenti = commenti;
        this.dataCaricamento = dataCaricamento;
        this.gioco = gioco;
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

    public byte[] getImmagine() {
        return immagine;
    }

    public void setImmagine(byte[] immagine) {
        this.immagine = immagine;
    }
}

