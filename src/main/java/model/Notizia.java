package model;

import java.sql.Date;
import java.util.ArrayList;


public class Notizia {

    private int id;
    private String testo;
    private String titolo;
    private Date dataCaricamento;
    private String giornalista;
    private ArrayList<Commento> commenti;
    private ArrayList<Gioco> giochi; // da valutare
/*
    Possibili altri attributi:
        -immagine di copertina
        -link video notiza
     */

    /* Constructor */

    public Notizia() {
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

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getTesto() {return testo;}

    public void setTesto(String testo) {this.testo = testo;}

    public void setCommenti(ArrayList<Commento> commenti) { this.commenti = commenti; }

    public ArrayList<Commento> getCommenti() { return commenti; }

    public ArrayList<Gioco> getGiochi() { return giochi; }

    public void setGiochi(ArrayList<Gioco> giochi) { this.giochi = giochi; }
}
