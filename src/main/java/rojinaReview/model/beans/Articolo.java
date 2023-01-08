package rojinaReview.model.beans;

import java.sql.Date;
import java.util.ArrayList;

public abstract class Articolo extends Contenuto {

    /* Attributes */

    private ArrayList<Paragrafo> paragrafi;
    private java.sql.Date dataScrittura;

    private int id_Giornalista;

    /* Constructor */
    public Articolo(){
        super();
    }

    public Articolo(int id, String nome, String testo, String immagine, ArrayList<Commento> commenti, ArrayList<Paragrafo> paragrafi, Date dataScrittura) {
        super(id, nome, testo, immagine, commenti);
        this.paragrafi = paragrafi;
        this.dataScrittura = dataScrittura;
    }

    public ArrayList<Paragrafo> getParagrafi() {
        return paragrafi;
    }

    public void setParagrafi(ArrayList<Paragrafo> paragrafi) {
        this.paragrafi = paragrafi;
    }

    public Date getDataScrittura() {
        return dataScrittura;
    }

    public void setDataScrittura(Date dataScrittura) {
        this.dataScrittura = dataScrittura;
    }

    public int getId_Giornalista() {
        return id_Giornalista;
    }

    public void setId_Giornalista(int id_Giornalista) {
        this.id_Giornalista = id_Giornalista;
    }
}

