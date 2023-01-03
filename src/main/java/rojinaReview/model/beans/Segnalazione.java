package rojinaReview.model.beans;

import java.sql.Date;

public class Segnalazione {
    private String motivo;
    private String commentoAggiuntivo;
    private java.sql.Date dataSegnalazione;
    private String utenteSegnalante;

    public Segnalazione() {
    }

    public Segnalazione(String motivo, String commentoAggiuntivo, Date dataSegnalazione, String utenteSegnalante) {
        this.motivo = motivo;
        this.commentoAggiuntivo = commentoAggiuntivo;
        this.dataSegnalazione = dataSegnalazione;
        this.utenteSegnalante = utenteSegnalante;
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

    public String getUtenteSegnalante() {
        return utenteSegnalante;
    }

    public void setUtenteSegnalante(String utenteSegnalante) {
        this.utenteSegnalante = utenteSegnalante;
    }
}
