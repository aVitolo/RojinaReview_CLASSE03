package rojinaReview.model.utilities;

import java.sql.Date;

public abstract class Voto {
    private String utente;
    private float voto;
    private java.sql.Date dataVotazione;

    public Voto(){

    }

    public Voto(String utente, int voto, Date dataVotazione) {
        this.utente = utente;
        this.voto = voto;
        this.dataVotazione = dataVotazione;
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
}
