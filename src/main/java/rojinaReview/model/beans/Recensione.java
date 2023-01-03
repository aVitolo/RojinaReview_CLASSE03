package rojinaReview.model.beans;

import java.sql.Date;
import java.util.ArrayList;

public class Recensione extends Articolo {
    static public String[] fieldsName = {"Id","Testo","Titolo","Voto","Data Caricamento"};

    /* Attributes */

    public float votoGiornalista;
    public String nomeVideogioco;
    public int idVideogioco;

    /* Constructor */

    public Recensione() {
    }

    public Recensione(int id, String nome, String testo, String immagine, ArrayList<Commento> commenti, ArrayList<Paragrafo> paragrafi, Date dataScrittura, float votoGiornalista, String nomeVideogioco, int idVideogioco) {
        super(id, nome, testo, immagine, commenti, paragrafi, dataScrittura);
        this.votoGiornalista = votoGiornalista;
        this.nomeVideogioco = nomeVideogioco;
        this.idVideogioco = idVideogioco;
    }

    /* Getter and Setter */

    public float getVotoGiornalista() {
        return votoGiornalista;
    }

    public void setVotoGiornalista(float voto) {
        this.votoGiornalista = voto;
    }

    public String getNomeVideogioco() {
        return nomeVideogioco;
    }

    public void setNomeVideogioco(String nomeVideogioco) {
        this.nomeVideogioco = nomeVideogioco;
    }

    public int getIdVideogioco() {
        return idVideogioco;
    }

    public void setIdVideogioco(int idVideogioco) {
        this.idVideogioco = idVideogioco;
    }
}
