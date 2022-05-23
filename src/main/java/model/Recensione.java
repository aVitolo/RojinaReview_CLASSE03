package model;

import java.sql.Date;
import java.util.ArrayList;

public class Recensione {

    private int id;
    private String testo;
    private String giornalista;
    private String titolo;
    private float voto;
    private Date dataCaricamento;
    private Gioco gioco;
    private ArrayList<Commento> commenti;

    /*
    Possibili altri attributi:
        -immagine di copertina
        -link video recensione
     */

    /* Constructor */

    public Recensione() {
    }

    /* Getter and Setter */

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getTesto() {return testo;}

    public void setTesto(String testo) {this.testo = testo;}

    public ArrayList<Commento> getCommenti() { return commenti; }

    public void setCommenti(ArrayList<Commento> commenti) { this.commenti = commenti; }

    public String getGiornalista() {
        return giornalista;
    }

    public void setGiornalista(String giornalista) {
        this.giornalista = giornalista;
    }

    public Gioco getGioco() {return gioco;}

    public void setGioco(Gioco gioco) {
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
