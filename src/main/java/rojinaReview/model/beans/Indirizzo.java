package rojinaReview.model.beans;

public class Indirizzo {

    /* Attributes */

    int numeroCivico;
    String via;
    String città;
    String cap;

    /* Costructor */

    public Indirizzo() {
    }

    public Indirizzo(String via,
                     String città,
                     String cap,
                     int numeroCivico) {
        this.via = via;
        this.numeroCivico = numeroCivico;
        this.città = città;
        this.cap = cap;
    }

    /* Getter & Setter */

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(int numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getCittà() {
        return città;
    }

    public void setCittà(String città) {
        this.città = città;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }
}
