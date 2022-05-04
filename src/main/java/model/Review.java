package model;

import java.sql.Date;
import java.util.GregorianCalendar;

public class Review {
    private String giornalista;
    private String gioco;
    private String titolo;
    private float voto;
    private Date dataCaricamento;

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

    public void setGiornalista(String giornalista) {
        this.giornalista = giornalista;
    }

    public String getGioco() {
        return gioco;
    }

    public void setGioco(String gioco) {
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
