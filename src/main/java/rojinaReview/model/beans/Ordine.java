package rojinaReview.model.beans;

import java.util.ArrayList;

public class Ordine {

    /* Attributes */

    private ArrayList<Prodotto> prodotti;
    private java.sql.Date dataOrdine;
    private float totale;
    private int id;
    private Indirizzo indirizzo;
    private Pagamento pagamento;
    private String stato;


    /* Costructor */

    public Ordine() {
        prodotti = new ArrayList<>();
        indirizzo = new Indirizzo();
        pagamento = new Pagamento();
    }

    public Ordine(ArrayList<Prodotto> prodotti,
                  java.sql.Date dataOrdine,
                  Float totale,
                  int id,
                  Indirizzo indirizzo,
                  Pagamento pagamento,
                  String stato) {
        this.prodotti = prodotti;
        this.dataOrdine = dataOrdine;
        this.totale = totale;
        this.id = id;
        this.indirizzo = indirizzo;
        this.pagamento = pagamento;
        this.stato = stato;
    }
    /* Getter & Setter */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public java.sql.Date getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(java.sql.Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public Float getTotale() {
        return totale;
    }

    public void setTotale(Float totale) {
        this.totale = totale;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }




}
