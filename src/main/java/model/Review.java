package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Review {
    private String giornalista;
    private String titolo;
    private String sottotitolo;
    private float voto;
    private Date dataCaricamento;
    private Game gioco;
    private ArrayList<Commento> commenti;

    /*
    Possibili altri attributi:
        -immagine di copertina
        -link video recensione
     */

    /* Constructor */

    public Review() {
    }

    /* Getter and Setter */

    public String getGiornalista() {
        return giornalista;
    }

    public String getSottotitolo() { return sottotitolo; }

    public void setSottotitolo(String sottotitolo) { this.sottotitolo = sottotitolo; }

    public Game getGioco() { return gioco; }

    public ArrayList<Commento> getCommenti() { return commenti; }

    public void setCommenti(ArrayList<Commento> commenti) { this.commenti = commenti; }

    public void setGiornalista(String giornalista) {
        this.giornalista = giornalista;
    }

    public Game getGiocGo() {
        return gioco;
    }

    public void setGioco(Game gioco) {
        this.gioco = gioco;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public float getVoto() {
        return voto;
    }

    public void setVoto(float voto) {
        this.voto = voto;
    }

    public Date getDataCaricamento() {
        return dataCaricamento;
    }

    public void setDataCaricamento(Date dataCaricamento) {
        this.dataCaricamento = dataCaricamento;
    }
}
