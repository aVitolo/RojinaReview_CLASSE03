package rojinaReview.model.beans;

import java.util.ArrayList;

public class Carrello {

    /* Attributes */

    private ArrayList<Prodotto> prodotti;
    private float totale;

    /* Constructors */

    public Carrello() {
        this.prodotti = new ArrayList<>();
    }

    public Carrello(ArrayList<Prodotto> prodotti,
                    float totale) {
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

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }

}
