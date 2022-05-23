package model;

import java.util.ArrayList;
import java.util.Date;

public class Ordine {

    private ArrayList<Prodotto> products;
    private String state;
    private Date data;
    private String id;

    /* Costructor */

    public Ordine(){}

    /* Getter & Setter */

    public ArrayList<Prodotto> getProducts() { return products; }

    public void setProducts(ArrayList<Prodotto> products) { this.products = products; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public Date getData() { return data; }

    public void setData(Date data) { this.data = data; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }
}
