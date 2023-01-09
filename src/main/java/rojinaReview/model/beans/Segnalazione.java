package rojinaReview.model.beans;

import java.sql.Date;

public class Segnalazione {
    private int id_commento;
    private int id_videogiocatoreSegnalante;
    private String motivo;
    private String commentoAggiuntivo;
    private java.sql.Date dataSegnalazione;
    private String videogiocatoreSegnalante;


    public Segnalazione() {
    }

    public Segnalazione(String motivo, String commentoAggiuntivo, Date dataSegnalazione, String utenteSegnalante) {
        this.motivo = motivo;
        this.commentoAggiuntivo = commentoAggiuntivo;
        this.dataSegnalazione = dataSegnalazione;
        this.videogiocatoreSegnalante = utenteSegnalante;
    }

    public int getId_commento() {
        return id_commento;
    }

    public void setId_commento(int id_commento) {
        this.id_commento = id_commento;
    }

    public int getId_videogiocatoreSegnalante() {
        return id_videogiocatoreSegnalante;
    }

    public void setId_videogiocatoreSegnalante(int id_videogiocatore) {
        this.id_videogiocatoreSegnalante = id_videogiocatore;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getCommentoAggiuntivo() {
        return commentoAggiuntivo;
    }

    public void setCommentoAggiuntivo(String commentoAggiuntivo) {
        this.commentoAggiuntivo = commentoAggiuntivo;
    }

    public Date getDataSegnalazione() {
        return dataSegnalazione;
    }

    public void setDataSegnalazione(Date dataSegnalazione) {
        this.dataSegnalazione = dataSegnalazione;
    }

    public String getVideogiocatoreSegnalante() {
        return videogiocatoreSegnalante;
    }

    public void setVideogiocatoreSegnalante(String videogiocatoreSegnalante) {
        this.videogiocatoreSegnalante = videogiocatoreSegnalante;
    }
}
