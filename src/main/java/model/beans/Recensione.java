package model.beans;

import model.utilities.Articolo;

import java.sql.Date;
import java.util.ArrayList;

public class Recensione extends Articolo {

    /* Attributes */

    public float voto;

    /* Constructor */

    public Recensione() {
    }

    public Recensione(ArrayList<Commento> commenti,
                      String immagine,
                      java.sql.Date dataCaricamento,
                      float voto,
                      Gioco gioco,
                      int id,
                      String testo,
                      String giornalista,
                      String titolo) {

        super(commenti,
                immagine,
                dataCaricamento,
                gioco,
                id,
                testo,
                titolo,
                giornalista);
        this.voto = voto;
    }

    /* Getter and Setter */

    public float getVoto() {
        return voto;
    }

    public void setVoto(float voto) {
        this.voto = voto;
    }
}
