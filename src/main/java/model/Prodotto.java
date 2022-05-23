package model;

import java.util.ArrayList;

public class Prodotto {

    private String id;
    private String nome;
    private String descrizione;
    private float prezzo;
    private float sconto;
    private String disponibilità;
    private ArrayList<String> categorie;

    /* Construct */

    public Prodotto() {}

    /* Getter & Setter*/

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getDescrizione() {return descrizione;}

    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}

    public float getPrezzo() {return prezzo;}

    public void setPrezzo(float prezzo) {this.prezzo = prezzo;}

    public float getSconto() {return sconto;}

    public void setSconto(float sconto) {this.sconto = sconto;}

    public String getDisponibilità() {return disponibilità;}

    public void setDisponibilità(String disponibilità) {this.disponibilità = disponibilità;}

    public ArrayList<String> getCategorie() {return categorie;}

    public void setCategorie(ArrayList<String> categorie) {this.categorie = categorie;}
}
