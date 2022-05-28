package model;

import java.util.ArrayList;
import java.util.Date;

public class Ordine {

    private int id;
    private String stato;
    private String tracking;
    private Date dataOrdine;
    private Float totale;
    private String pagamento;
    private String indirizzo;
    private ArrayList<ProdottoOrdine> prodotti;

    /* Costructor */

    public Ordine(){}

    /* Getter & Setter */

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getStato() {return stato;}

    public void setStato(String stato) {this.stato = stato;}

    public String getTracking() {return tracking;}

    public void setTracking(String tracking) {this.tracking = tracking;}

    public Date getDataOrdine() {return dataOrdine;}

    public void setDataOrdine(Date dataOrdine) {this.dataOrdine = dataOrdine;}

    public Float getTotale() {return totale;}

    public void setTotale(Float totale) {this.totale = totale;}

    public String getPagamento() {return pagamento;}

    public void setPagamento(String pagamento) {this.pagamento = pagamento;}

    public String getIndirizzo() {return indirizzo;}

    public void setIndirizzo(String indirizzo) {this.indirizzo = indirizzo;}

    public ArrayList<ProdottoOrdine> getProdotti() {return prodotti;}

    public void setProdotti(ArrayList<ProdottoOrdine> prodotti) {this.prodotti = prodotti;}

    public class ProdottoOrdine{

        public Prodotto prodotto;
        public float prezzo;
        public int quantita;

        public ProdottoOrdine(){}

        public void setProdotto(Prodotto prodotto) {this.prodotto=prodotto;}

        public Prodotto getProdotto() {return this.prodotto;}

        public float getPrezzo() {return prezzo;}

        public void setPrezzo(float prezzo) {this.prezzo = prezzo;}

        public int getQuantita() {return quantita;}

        public void setQuantita(int quantita) {this.quantita = quantita;}

    }

}
