package model;

import java.sql.Date;
import java.util.GregorianCalendar;

public class Game {
    private String titolo;
    private Date DataDiRilascio;
    private String casaDiSviluppo;
    private float mediaVoto;
    private int numeroVoti;

    /*
    Possibili altri attributi:
        -copertina del gioco

     */

    /*
    Considerazioni:
        Un gioco appartendo potendo appartenere a piu categorie e giocabile su piu piattaforme
        a livello di DB porta a delle rieficazione e quindi alla interrogazione di altre tabelle
        e di conseguenza all'utilizzo di due liste.
        Per evitare di creare un'ulteriore beans a DAO potremmo aggiungere le lista tra gli attributi di questo beans
     */

    /* Constructor */

    public Game() {
    }

    /* Getter and Setter */

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Date getDataDiRilascio() {
        return DataDiRilascio;
    }

    public void setDataDiRilascio(Date dataDiRilascio) {
        DataDiRilascio = dataDiRilascio;
    }

    public String getCasaDiSviluppo() {
        return casaDiSviluppo;
    }

    public void setCasaDiSviluppo(String casaDiSviluppo) {
        this.casaDiSviluppo = casaDiSviluppo;
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
}
