package rojinaReview.model.beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Commento {

    /* Attributes */

    private int id;
    private java.sql.Timestamp dataScrittura;
    private String testo;
    private int idVideogiocatore;
    private String nicknameVideogiocatore;
    private int idContenuto;
    private String nomeContenuto;
    private int tipo; //0 prodotto, 1 recensione, 2 notizia
    private ArrayList<Segnalazione> segnalazioni;
    private int numeroSegnalazioni;

    /* Constructor */

    public Commento() {

    }

    public Commento(int id, Timestamp dataScrittura, String testo, int idVideogiocatore, String nicknameVideogiocatore, int idContenuto, String nomeContenuto) {
        this.id = id;
        this.dataScrittura = dataScrittura;
        this.testo = testo;
        this.idVideogiocatore = idVideogiocatore;
        this.nicknameVideogiocatore = nicknameVideogiocatore;
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

    public Timestamp getDataScrittura() {
        return dataScrittura;
    }

    public void setDataScrittura(Timestamp dataScrittura) {
        this.dataScrittura = dataScrittura;
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

    public String getNicknameVideogiocatore() {
        return nicknameVideogiocatore;
    }

    public void setNicknameVideogiocatore(String nicknameVideogiocatore) {
        this.nicknameVideogiocatore = nicknameVideogiocatore;
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

    public ArrayList<Segnalazione> getSegnalazioni() {
        return segnalazioni;
    }

    public void setSegnalazioni(ArrayList<Segnalazione> segnalazioni) {
        this.segnalazioni = segnalazioni;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getNumeroSegnalazioni() {
        return numeroSegnalazioni;
    }

    public void setNumeroSegnalazioni(int numeroSegnalazioni) {
        this.numeroSegnalazioni= numeroSegnalazioni;
    }

    public String getResource()
    {
        if(tipo == 0)
            return "shop";
        else if (tipo == 1)
            return "reviews";
        else if (tipo == 2)
            return "news";

        return null;
    }
}
