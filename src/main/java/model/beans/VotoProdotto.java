package model.beans;

import model.utilities.Voto;

import java.sql.Date;

public class VotoProdotto extends Voto {
    private int id;

    public VotoProdotto(){

    }

    public VotoProdotto(String utente, int voto, Date dataVotazione, int id) {
        super(utente, voto, dataVotazione);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
