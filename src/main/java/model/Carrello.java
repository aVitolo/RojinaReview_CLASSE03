package model;

import java.util.ArrayList;

public class Carrello {

    private ArrayList<Prodotto> carrello;
    private Float total; //Da valutare

    /* Constructor */

    public Carrello(){}

    /* Getter & Setter */

    public ArrayList<Prodotto> getCarrello() { return carrello; }

    public void setCarrello(ArrayList<Prodotto> carrello) { this.carrello = carrello; }
}
