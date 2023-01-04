package rojinaReview.model.beans;

import java.util.ArrayList;

public class Prodotto extends Contenuto {
    static public String[] fieldsName = {"Id","Nome","DescrizionesetQuantità","Prezzo","Media Voto","Numero Voti"};

    private String categoria;
    private ArrayList<Parere> pareri;

    private int quantità;

    private int numeroVoti;
    private float mediaVoto;
    private float prezzo;


    /* Construct */

    public Prodotto(){}

    public Prodotto(int id, String nome, String testo, String immagine, ArrayList<Commento> commenti, String categoria, ArrayList<Parere> pareri, int quantità, int numeroVoti, float mediaVoto, float prezzo) {
        super(id, nome, testo, immagine, commenti);
        this.categoria = categoria;
        this.pareri = pareri;
        this.quantità = quantità;
        this.numeroVoti = numeroVoti;
        this.mediaVoto = mediaVoto;
        this.prezzo = prezzo;
    }

    /* Getter & Setter*/



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

    public String getImmagine() {
        return super.getImmagine();
    }

    public void setImmagine(String immagine) {
        super.setImmagine(immagine);
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public ArrayList<Parere> getPareri() {
        return pareri;
    }

    public void setPareri(ArrayList<Parere> pareri) {
        this.pareri = pareri;
    }

    public int getQuantità() {
        return quantità;
    }

    public int setQuantità(int quantità) {
        this.quantità = quantità;
    }
}
