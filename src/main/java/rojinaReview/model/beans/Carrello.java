package rojinaReview.model.beans;

import java.util.ArrayList;

public class Carrello {

    /* Attributes */

    private ArrayList<ProdottoCarrello> prodotti;
    private float totale;

    /* Constructors */

    public Carrello() {
        this.prodotti = new ArrayList<>();
    }

    public Carrello(ArrayList<ProdottoCarrello> prodotti,
                    float totale) {
        this.prodotti = prodotti;
        this.totale = totale;
    }

    /* Getter & Setter */

    public ArrayList<ProdottoCarrello> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<ProdottoCarrello> prodotti) {
        this.prodotti = prodotti;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }

    public static class ProdottoCarrello{
        private int quantità;
        private float prezzoAttuale;
        private Prodotto prodotto;

        public ProdottoCarrello(){

        }

        public int getQuantità() {
            return quantità;
        }

        public void setQuantità(int quantità) {
            this.quantità = quantità;
        }

        public float getPrezzoAttuale() {
            return prezzoAttuale;
        }

        public void setPrezzoAttuale(float prezzoAttuale) {
            this.prezzoAttuale = prezzoAttuale;
        }

        public Prodotto getProdotto() {
            return prodotto;
        }

        public void setProdotto(Prodotto prodotto) {
            this.prodotto = prodotto;
        }
    }
}
