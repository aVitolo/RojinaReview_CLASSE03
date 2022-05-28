package model.beans;

import java.util.ArrayList;

public class Prodotto {

    private ArrayList<Categoria> categorie;
    private int disponibilità;
    private int id;
    private float prezzo;
    private String nome;
    private String descrizione;;
    private Sconto sconto;

    /* Construct */

    public Prodotto() {

    }

    /* Getter & Setter*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public Sconto getSconto() {
        return sconto;
    }

    public void setSconto(Sconto sconto) {
        this.sconto = sconto;
    }

    public int getDisponibilità() {
        return disponibilità;
    }

    public void setDisponibilità(int disponibilità) {
        this.disponibilità = disponibilità;
    }

    public ArrayList<Categoria> getCategorie() {
        return categorie;
    }

    public void setCategorie(ArrayList<Categoria> categorie) {
        this.categorie = categorie;
    }

    /* Inner Class */

    public class Sconto{

        private String nome;
        private float percentuale;

        public Sconto(){};

        public String getNome() { return this.nome; }

        public void setNome(String string) {this.nome=nome;}

        public float getPercentuale() {return this.percentuale;}

        public void setPercentuale(float aFloat) {this.percentuale=percentuale;}
    }
}
