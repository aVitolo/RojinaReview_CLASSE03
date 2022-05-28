package model.beans;

public class Piattaforma {

    /* Attributes */

    private String nome;

    /* Constructorsr */

    public Piattaforma() {
    }

    public Piattaforma(String nome) {
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
