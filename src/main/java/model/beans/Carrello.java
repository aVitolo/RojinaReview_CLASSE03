package model.beans;

import java.util.ArrayList;

public class Carrello {

    /* Attributes */

    private ArrayList<Prodotto> prodotti;
    private double totale;

    /* Constructors */

    public Carrello() {
    }

    public Carrello(ArrayList<Prodotto> prodotti,
                    double totale) {
        this.prodotti = prodotti;
        this.totale = totale;
    }

    /* Getter & Setter */

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(float aFloat) {
        this.totale = totale;
    }
}
