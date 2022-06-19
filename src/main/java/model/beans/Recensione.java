package model.beans;

import java.sql.Date;
import java.util.ArrayList;

public class Recensione {

    /* Attributes */

    private ArrayList<Commento> commenti;
    private byte[] immagine;
    private byte[] imgGiornalista;
    private Date dataCaricamento;
    private float voto;
    private Gioco gioco;
    private int id;
    private String testo;
    private String giornalista;
    private String titolo;

    /* Constructor */

    public Recensione() {
    }

    public Recensione(ArrayList<Commento> commenti,
                      byte[] immagine,
                      byte[] imgGiornalista,
                      Date dataCaricamento,
                      float voto,
                      Gioco gioco,
                      int id,
                      String testo,
                      String giornalista,
                      String titolo) {
        this.commenti = commenti;
        this.immagine = immagine;
        this.imgGiornalista = imgGiornalista;
        this.dataCaricamento = dataCaricamento;
        this.voto = voto;
        this.gioco = gioco;
        this.id = id;
        this.testo = testo;
        this.giornalista = giornalista;
        this.titolo = titolo;
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

    public byte[] getImmagine() { return immagine; }

    public void setImmagine(byte[] immagine) { this.immagine = immagine; }

    public byte[] getImgGiornalista() {
        return imgGiornalista;
    }

    public void setImgGiornalista(byte[] imgGiornalista) {
        this.imgGiornalista = imgGiornalista;
    }
}
