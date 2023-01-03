package rojinaReview.model.beans;

import java.sql.Date;

public class Parere {
    private String utente;
    private float voto;
    private java.sql.Date dataVotazione;
    private Integer idProdotto;
    private Integer idNotizia;
    private Integer idRecensione;

    public Parere(){

    }

    public Parere(String utente, int voto, Date dataVotazione) {
        this.utente = utente;
        this.voto = voto;
        this.dataVotazione = dataVotazione;
    }

    public Parere(String utente, int voto, Date dataVotazione,int idProdotto,int idRecensione, int idNotizia, int flag) {
        this.utente = utente;
        this.voto = voto;
        this.dataVotazione = dataVotazione;
        if(flag==1) {
            this.idProdotto = idProdotto;
            this.idNotizia = null;
            this.idRecensione = null;
        }
        if(flag==2) {
            this.idProdotto = null;
            this.idNotizia = idNotizia;
            this.idRecensione = null;
        }
        if(flag==3) {
            this.idProdotto = null;
            this.idNotizia = null;
            this.idRecensione = idRecensione;
        }
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public float getVoto() {
        return voto;
    }

    public void setVoto(float voto) {
        this.voto = voto;
    }

    public Date getDataVotazione() {
        return dataVotazione;
    }

    public void setDataVotazione(Date dataVotazione) {
        this.dataVotazione = dataVotazione;
    }

    public Integer getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(Integer idProdotto) {
        this.idProdotto = idProdotto;
    }

    public Integer getIdNotizia() {
        return idNotizia;
    }

    public void setIdNotizia(Integer idNotizia) {
        this.idNotizia = idNotizia;
    }

    public Integer getIdRecensione() {
        return idRecensione;
    }

    public void setIdRecensione(Integer idRecensione) {
        this.idRecensione = idRecensione;
    }
}
