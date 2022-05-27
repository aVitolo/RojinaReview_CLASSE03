package model;

import java.util.ArrayList;

public class Carrello {

    private ArrayList<Prodotto> prodotti;
    private Float totale;

    /* Constructor */

    public Carrello(){}

    /* Getter & Setter */

    public ArrayList<Prodotto> getCarrello() { return prodotti; }

    public void setCarrello(ArrayList<Prodotto> prodotti) { this.prodotti = prodotti; }

    public Float getTotale() {return totale;}

    public void setTotale(float aFloat) {this.totale=totale;}
}
