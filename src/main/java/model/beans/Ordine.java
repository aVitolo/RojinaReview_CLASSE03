package model.beans;

import java.util.ArrayList;
import java.util.Date;

public class Ordine {

    /* Attributes */

    private ArrayList<ProdottoOrdine> prodotti;
    private java.sql.Date dataOrdine;
    private Float totale;
    private int id;
    private Indirizzo indirizzo;
    private Pagamento pagamento;
    private String stato;
    private String tracking;

    /* Costructor */

    public Ordine() {
    }

    public Ordine(ArrayList<ProdottoOrdine> prodotti,
                  java.sql.Date dataOrdine,
                  Float totale,
                  int id,
                  Indirizzo indirizzo,
                  Pagamento pagamento,
                  String stato,
                  String tracking) {
        this.prodotti = prodotti;
        this.dataOrdine = dataOrdine;
        this.totale = totale;
        this.id = id;
        this.pagamento = pagamento;
        this.indirizzo = indirizzo;
        this.stato = stato;
        this.tracking = tracking;
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

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
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

    public ArrayList<ProdottoOrdine> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<ProdottoOrdine> prodotti) {
        this.prodotti = prodotti;
    }

    /* Inner Class */

    public static class ProdottoOrdine {
        /*
            Valutare se modificare a class  per riutilizzo in Carrello e Ordine
            o estendere prodotto
        */

        /* Attributes */

        public int quantita;
        public float prezzoAcquisto;
        public Prodotto prodotto;

        /* Constructors */

        public ProdottoOrdine() {
        }



        /* Getter & Setter */

        public void setProdotto(Prodotto prodotto) {
            this.prodotto = prodotto;
        }

        public Prodotto getProdotto() {
            return this.prodotto;
        }

        public float getPrezzoAcquisto() {
            return prezzoAcquisto;
        }

        public void setPrezzoAcquisto(float prezzoAcquisto) {
            this.prezzoAcquisto = prezzoAcquisto;
        }

        public int getQuantita() {
            return quantita;
        }

        public void setQuantita(int quantita) {
            this.quantita = quantita;
        }

    }

}
