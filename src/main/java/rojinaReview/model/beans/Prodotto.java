package rojinaReview.model.beans;

import rojinaReview.model.utilities.Contenuto;

import java.util.ArrayList;

public class Prodotto extends Contenuto {
    static public String[] fieldsName = {"Id","Nome","Descrizione","Disponibilità","Prezzo","Media Voto","Numero Voti"};


    private ArrayList<Categoria> categorie;
    private ArrayList<Parere> voti;

    private int disponibilità;
    private int id;
    private int numeroVoti;
    private float mediaVoto;
    private float prezzo;


    /* Construct */


    public Prodotto(ArrayList<Commento> commenti, ArrayList<Categoria> categorie,ArrayList<Parere> voti,String immagine, int disponibilità, int id, int numeroVoti, float mediaVoto, float prezzo, String nome, String descrizione) {
        super(nome,descrizione,immagine,commenti);
        this.categorie = categorie;
        this.voti=voti;
        this.disponibilità = disponibilità;
        this.id = id;
        this.numeroVoti = numeroVoti;
        this.mediaVoto = mediaVoto;
        this.prezzo = prezzo;
    }

    /* Getter & Setter*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return super.getNome();
    }

    public void setNome(String nome) {
        super.setNome(nome);
    }

    public String getDescrizione() {
        return super.getTesto();
    }

    public void setDescrizione(String descrizione) {
        super.setTesto(descrizione);
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public int getDisponibilità() {
        return disponibilità;
    }

    public void setDisponibilità(int disponibilità) {
        this.disponibilità = disponibilità;
    }

    public String getImmagine() {
        return super.getImmagine();
    }

    public void setImmagine(String immagine) {
        super.setImmagine(immagine);
    }

    public ArrayList<Categoria> getCategorie() {
        return categorie;
    }

    public void setCategorie(ArrayList<Categoria> categorie) {
        this.categorie = categorie;
    }

    public int getNumeroVoti() {
        return numeroVoti;
    }

    public void setNumeroVoti(int numeroVoti) {
        this.numeroVoti = numeroVoti;
    }

    public float getMediaVoto() {
        return mediaVoto;
    }

    public void setMediaVoto(float mediaVoto) {
        this.mediaVoto = mediaVoto;
    }

    public ArrayList<Commento> getCommenti() {
        return super.getCommenti();
    }

    public void setCommenti(ArrayList<Commento> commenti) {
        super.setCommenti(commenti);
    }
}
