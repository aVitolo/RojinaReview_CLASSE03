package model.beans;

import java.util.Date;

public class Commento {

    /* Attributes */

    private int id;
    private java.sql.Timestamp data;
    private String testo;
    private String utente;
    private String resource; //oggetto a cui fa riferimento: notizia, recensione, prodotto



    /* Constructor */

    public Commento() {

    }

    public Commento(String testo, String utente, java.sql.Timestamp data, int id, String resource) {
        this.testo = testo;
        this.utente = utente;
        this.data = data;
        this.id = id;
        this.resource = resource;
    }

    /* Getter & Setter */

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public java.sql.Timestamp getData() {
        return data;
    }

    public void setData(java.sql.Timestamp data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
