package model;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.ArrayList;

public class News {
    private String titolo;
    private Date dataCaricamento;
    private String giornalista;
    private String corpo;
    private String sottotitolo;
    private ArrayList<Commento> commenti;
    private ArrayList<Game> giochi;
/*
    Possibili altri attributi:
        -immagine di copertina
        -link video notiza
     */

    /*
    Considerazioni:
        -
     */

    /* Constructor */

    public News() {
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

    public void setCorpo(String corpo) { this.corpo = corpo; }

    public void setSottotitolo(String sottotitolo) { this.sottotitolo = sottotitolo; }

    public void setCommenti(ArrayList<Commento> commenti) { this.commenti = commenti; }

    public String getCorpo() { return corpo; }

    public String getSottotitolo() { return sottotitolo; }

    public ArrayList<Commento> getCommenti() { return commenti; }

    public ArrayList<Game> getGiochi() { return giochi; }

    public void setGiochi(ArrayList<Game> giochi) { this.giochi = giochi; }
}
