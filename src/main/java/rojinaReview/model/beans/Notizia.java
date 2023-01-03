package rojinaReview.model.beans;

import rojinaReview.model.utilities.Articolo;

import java.util.ArrayList;
import java.util.HashMap;


public class Notizia extends Articolo {
    static public String[] fieldsName = {"Id","Testo","Titolo","Data Caricamento"};

    /* Attributes */

    private HashMap<Integer,String> giochi;
    /* Constructor */
    public Notizia(){
        super();
    }
    public Notizia(ArrayList<Commento> commenti,
                   HashMap<Integer,String> giochi,
                   String immagine,
                   java.sql.Date dataCaricamento,
                   String nomeVideogioco,
                   int idVideogioco,
                   int id,
                   String testo,
                   String titolo,
                   String giornalista,
                   String immagineGiornalista,
                    ArrayList<Paragrafo> paragrafi) {
        super(commenti, immagine, dataCaricamento, nomeVideogioco,idVideogioco, id, testo, titolo, giornalista, immagineGiornalista,paragrafi);
        this.giochi = giochi;
    }

    /* Getter and Setter */

    public HashMap<Integer,String> getGiochi() {
        return giochi;
    }

    public void setGiochi(HashMap<Integer,String> giochi) {
        this.giochi = giochi;
    }
}
