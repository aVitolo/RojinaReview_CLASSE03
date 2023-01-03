package rojinaReview.model.beans;

import rojinaReview.model.utilities.Articolo;

import java.util.ArrayList;

public class Recensione extends Articolo {
    static public String[] fieldsName = {"Id","Testo","Titolo","Voto","Data Caricamento"};

    /* Attributes */

    public float voto;
    public String nomeVideogioco;
    public int idVideogioco;

    /* Constructor */

    public Recensione() {
    }

    public Recensione(ArrayList<Commento> commenti,
                      String immagine,
                      java.sql.Date dataCaricamento,
                      float voto,
                      String nomeVideogioco,
                      int idVideogioco,
                      int id,
                      String testo,
                      String giornalista,
                      String titolo,
                      String immagineGiornalista,
                      ArrayList<Paragrafo> paragrafi) {

        super(  commenti,
                immagine,
                dataCaricamento,
                nomeVideogioco,
                idVideogioco,
                id,
                testo,
                titolo,
                giornalista,
                immagineGiornalista,
                paragrafi);
        this.voto = voto;
        this.nomeVideogioco=nomeVideogioco;
        this.idVideogioco=idVideogioco;
    }

    /* Getter and Setter */

    public float getVoto() {
        return voto;
    }

    public void setVoto(float voto) {
        this.voto = voto;
    }
}
