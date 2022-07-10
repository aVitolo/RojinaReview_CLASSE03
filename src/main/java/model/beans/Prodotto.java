package model.beans;

import java.util.ArrayList;

public class Prodotto {
    static public String[] fieldNames = {"Id","Nome","Descrizione","Disponibilità","Prezzo","Immagine","Sconto","Media Voto","Numero Voti"};

    private ArrayList<Commento> commenti;
    private ArrayList<Categoria> categorie;

    private int disponibilità;
    private int id;
    private int numeroVoti;
    private float mediaVoto;
    private float prezzo;
    private String nome;
    private String descrizione;
    private Sconto sconto;
    private String immagine;

    /* Construct */

    public Prodotto() {
        this.sconto = new Sconto();
    }

    public Prodotto(ArrayList<Commento> commenti, ArrayList<Categoria> categorie, String immagine, int disponibilità, int id, int numeroVoti, float mediaVoto, float prezzo, String nome, String descrizione, Sconto sconto) {
        this.commenti = commenti;
        this.categorie = categorie;
        this.immagine = immagine;
        this.disponibilità = disponibilità;
        this.id = id;
        this.numeroVoti = numeroVoti;
        this.mediaVoto = mediaVoto;
        this.prezzo = prezzo;
        this.nome = nome;
        this.descrizione = descrizione;
        this.sconto = sconto;
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
        if(this.sconto.nome != null)
            return prezzo*this.sconto.percentuale;
        else
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

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
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
        return commenti;
    }

    public void setCommenti(ArrayList<Commento> commenti) {
        this.commenti = commenti;
    }

    /* Inner Class */

    public class Sconto {

        private String nome;
        private float percentuale;

        public Sconto() {
        }

        public String getNome() {
            return this.nome;
        }

        public void setNome(String string) {
            this.nome = nome;
        }

        public float getPercentuale() {
            return this.percentuale;
        }

        public void setPercentuale(float aFloat) {
            this.percentuale = percentuale;
        }
    }
}
