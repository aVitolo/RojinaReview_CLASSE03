package model;

import java.util.Date;

public class PaymentMethod {

    private Date dataScadenza;
    private String numeroCarta;

    /* Constructor */

    public PaymentMethod() {}

    /* Getter & Setter */

    public Date getDataScadenza() { return dataScadenza; }

    public void setDataScadenza(Date dataScadenza) { this.dataScadenza = dataScadenza; }

    public String getNumeroCarta() { return numeroCarta; }

    public void setNumeroCarta(String numeroCarta) { this.numeroCarta = numeroCarta; }

}
