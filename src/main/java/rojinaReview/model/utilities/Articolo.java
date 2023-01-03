package rojinaReview.model.utilities;

import rojinaReview.model.beans.Commento;
import rojinaReview.model.beans.Paragrafo;
import rojinaReview.model.beans.Videogioco;

import java.util.ArrayList;

public abstract class Articolo extends Contenuto {

    /* Attributes */

    private ArrayList<Paragrafo> paragrafi;
    private java.sql.Date dataCaricamento;
    private String nomeVideogioco;
    private int idVideogioco;
    private int id;
    private String giornalista;
    private String immagineGiornalista;


    /* Constructor */
    public Articolo(){
        super();
    }

    public Articolo(ArrayList<Commento> commenti,
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
        super(testo,titolo,immagine,commenti);
        this.dataCaricamento = dataCaricamento;
        this.nomeVideogioco = nomeVideogioco;
        this.idVideogioco=idVideogioco;
        this.id = id;
        this.giornalista = giornalista;
        this.immagineGiornalista = immagineGiornalista;
        this.paragrafi=paragrafi;
    }



    /* Getter and Setter */

    public String getGiornalista() {
        return giornalista;
    }

    public void setGiornalista(String giornalista) {
        this.giornalista = giornalista;
    }

    public String getTitolo() {return (super.getNome());}

    public void setTitolo(String titolo) {
        super.setNome(titolo);
    }

    public java.sql.Date getDataCaricamento() {
        return dataCaricamento;
    }

    public void setDataCaricamento(java.sql.Date dataCaricamento) {
        this.dataCaricamento = dataCaricamento;
    }

    public ArrayList<Paragrafo> getParagrafi() {
        return paragrafi;
    }

    public void setParagrafi(ArrayList<Paragrafo> paragrafi) {
        this.paragrafi = paragrafi;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTesto() {
        return super.getTesto();
    }

    public void setTesto(String testo) {
        super.setTesto(testo);
    }

    public void setCommenti(ArrayList<Commento> commenti) {
        super.setCommenti(commenti);
    }

    public ArrayList<Commento> getCommenti() {
        return super.getCommenti();
    }

    public String getImmagine() {
        return super.getImmagine();
    }

    public void setImmagine(String immagine) {
        super.setImmagine(immagine);
    }

    public String  getImmagineGiornalista() {
        return immagineGiornalista;
    }

    public void setImmagineGiornalista(String immagineGiornalista) {
        this.immagineGiornalista = immagineGiornalista;
    }
}

