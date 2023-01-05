package rojinaReview.model.beans;

import java.sql.Date;

public class Parere {
    //private String utente; non serve sapere chi ha dato il voto
    private int id;
    private float voto;
    private java.sql.Date dataVotazione;
    private int idProdottoORVideogioco; //id del prodotto o videogioco
    private int type; //0 prodotto, 3 videogioco

    public Parere(){

    }

    public Parere(int id, float voto, Date dataVotazione, Integer idProdottoORVideogioco, boolean type) {
        this.id = id;
        this.voto = voto;
        this.dataVotazione = dataVotazione;
        this.idProdottoORVideogioco = idProdottoORVideogioco;
        this.type = type;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdProdottoORVideogioco() {
        return idProdottoORVideogioco;
    }

    public void setIdProdottoORVideogioco(Integer idProdottoORVideogioco) {
        this.idProdottoORVideogioco = idProdottoORVideogioco;
    }

    public int isType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
