package model.beans;

public class Telefono {

    /* Attributes */

    private String numero;

    /* Constructors */

    public Telefono() {
    }

    public Telefono(String numero) {
        this.numero = numero;
    }

    /* Getter & Setter */

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
