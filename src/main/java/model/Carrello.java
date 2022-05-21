package model;

import java.util.ArrayList;

public class Carrello {

    private ArrayList<Product> carrello;
    private Float total; //Da valutare

    /* Constructor */

    public Carrello(){}

    /* Getter & Setter */

    public ArrayList<Product> getCarrello() { return carrello; }

    public void setCarrello(ArrayList<Product> carrello) { this.carrello = carrello; }
}
