package rojinaReview.model.beans;

import rojinaReview.model.utilities.Voto;

import java.sql.Date;

public class VotoGioco extends Voto {
    private String gioco;


    public VotoGioco() {

    }

    public VotoGioco(String utente, int voto, Date dataVotazione, String gioco) {
        super(utente, voto, dataVotazione);
        this.gioco = gioco;
    }

    public String getGioco() {
        return gioco;
    }

    public void setGioco(String gioco) {
        this.gioco = gioco;
    }
}
