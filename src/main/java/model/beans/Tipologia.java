package model.beans;

public class Tipologia {

    /* Attributes */

    private String nome;

    /* Constructor */

    public Tipologia() {
    }

    public Tipologia(String nome) {
        this.nome = nome;
    }

    /* Getter & Setter */

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
