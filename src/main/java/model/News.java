package model;

import java.sql.Date;
import java.util.GregorianCalendar;

public class News {
    private String giornalista;
    private String titolo;
    private Date dataCaricamento;

    /*
    Possibili altri attributi:
        -immagine di copertina
        -link video notiza
     */

    /*
    Considerazioni:
        Una notizia riferendosi a piu giochi a livello di DB porta alla rieficazione
        e quindi alla interrogazione di una seconda tabella e di conseguenza all'utilizzo di una lista.
        Per evitare di creare un'ulteriore beans e DAO potremmo aggiungere la lista tra gli attributi di questo beans
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
}
