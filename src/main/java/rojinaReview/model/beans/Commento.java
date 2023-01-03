package rojinaReview.model.beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Commento {

    /* Attributes */

    private int id;
    private java.sql.Timestamp data;
    private String testo;
    private int idVideogiocatore;
    private String videogiocatore;
    private int idContenuto;
    private String nomeContenuto;
    private List<Segnalazione> segnalazioni;



    /* Constructor */

    public Commento() {

    }

    public Commento(int id, Timestamp data, String testo, int idVideogiocatore, String videogiocatore, int idContenuto, String nomeContenuto) {
        this.id = id;
        this.data = data;
        this.testo = testo;
        this.idVideogiocatore = idVideogiocatore;
        this.videogiocatore = videogiocatore;
        this.idContenuto = idContenuto;
        this.nomeContenuto = nomeContenuto;
        this.segnalazioni = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public int getIdVideogiocatore() {
        return idVideogiocatore;
    }

    public void setIdVideogiocatore(int idVideogiocatore) {
        this.idVideogiocatore = idVideogiocatore;
    }

    public String getVideogiocatore() {
        return videogiocatore;
    }

    public void setVideogiocatore(String videogiocatore) {
        this.videogiocatore = videogiocatore;
    }

    public int getIdContenuto() {
        return idContenuto;
    }

    public void setIdContenuto(int idContenuto) {
        this.idContenuto = idContenuto;
    }

    public String getNomeContenuto() {
        return nomeContenuto;
    }

    public void setNomeContenuto(String nomeContenuto) {
        this.nomeContenuto = nomeContenuto;
    }

    public List<Segnalazione> getSegnalazioni() {
        return segnalazioni;
    }

    public void setSegnalazioni(List<Segnalazione> segnalazioni) {
        this.segnalazioni = segnalazioni;
    }
}
