package model.beans;

import model.utilities.Articolo;

import java.sql.Date;
import java.util.ArrayList;


public class Notizia extends Articolo {
    static public String[] fieldsName = {"Id","Testo","ID Giornalista","Titolo","Data Caricamento"};

    /* Attributes */

    private ArrayList<String> giochi;
    /* Constructor */

    public Notizia() {
    }

    public Notizia(ArrayList<Commento> commenti,
                   ArrayList<String> giochi,
                   String immagine,
                   java.sql.Date dataCaricamento,
                   Gioco gioco,
                   int id,
                   String testo,
                   String titolo,
                   String giornalista,
                   String immagineGiornalista
                   ) {
        super(commenti,
                immagine,
                dataCaricamento,
                gioco,
                id,
                testo,
                titolo,
                giornalista,
                immagineGiornalista);
        this.giochi = giochi;
    }

    /* Getter and Setter */

    public ArrayList<String> getGiochi() {
        return giochi;
    }

    public void setGiochi(ArrayList<String> giochi) {
        this.giochi = giochi;
    }
}
