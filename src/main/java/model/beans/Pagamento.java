package model.beans;

import java.util.Date;

public class Pagamento {

    /* Attributes */

    private String nome;
    private String cognome;
    private String numeroCarta;
    private java.sql.Date dataScadenza;

    /* Constructors */

    public Pagamento() {
    }

    public Pagamento(String nome,
                     String cognome,
                     String numeroCarta,
                     java.sql.Date dataScadenza) {
        this.nome = nome;
        this.cognome = cognome;
        this.numeroCarta = numeroCarta;
        this.dataScadenza = dataScadenza;
    }
    /* Getter & Setter */

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public java.sql.Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(java.sql.Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

}
