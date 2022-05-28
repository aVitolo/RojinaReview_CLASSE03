package model.beans;

public class Indirizzo {
    String via;
    int numeroCivico;
    String città;
    String cap;

    public Indirizzo() {
    }

    public Indirizzo(String via, int numeroCivico, String città, String cap) {
        this.via = via;
        this.numeroCivico = numeroCivico;
        this.città = città;
        this.cap = cap;
    }

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
