package rojinaReview.shop.service;

import java.util.List;

import rojinaReview.model.beans.Carrello;
import rojinaReview.model.beans.Ordine;
import rojinaReview.model.beans.Prodotto;

/**
 * Implementa l'interfaccia service
 * per il sottosistema ClubDelLibro.
 * @author Andrea Vitolo
 */
public interface ShopService
{
    /**
     * Firma del metodo che permette
     * ad Giornalista o un Videogiocatore
     * di visualizzare tutti i prodotti.
     * @return la lista dei prodotti
     */
    List<Prodotto> visualizzaShop();

    /**
     * Firma del metodo che permette
     * ad un Giornalista o un Videogiocatore
     * di visualizzare i dati di un prodotto.
     * @param id del prodotto
     * @return dati del prodotto
     */
    Prodotto visualizzaProdotto(int id);

    /**
     * Firma del metodo che permette
     * ad un Giornalista
     * di aggiungere un prodotto allo shop
     * @param prodotto info
     */
    void inserisciProdotto(Prodotto prodotto);

    /**
     * Firma del metodo che permette
     * ad un Giornalista
     * di aggiungere un prodotto allo shop
     * @param prodotto
     */
    void modificaProdotto(Prodotto prodotto);

    /**
     * Firma del metodo che permette
     * ad un Giornalista
     * di rimuovere un prodotto dallo shop
     * @param id del prodotto
     */
    void cancellaProdotto(int id);

    /**
     * Firma del metodo che permette
     * ad un Videogicoatore
     * di aggiungere un prodotto al carello
     * @param prodotto da aggiungere al carello
     */
    void aggiungiProdottoAlCarrello(Carrello.ProdottoCarrello prodotto);

    /**
     * Firma del metodo che permette
     * ad un Videogicoatore
     * di rimuovere un prodotto al carello
     * @param prodotto da rimuovere dal carello
     */
    void rimuoviProdottoDalCarrello(Carrello.ProdottoCarrello prodotto);

    /**
     * Firma del metodo che permette
     * ad un Videogicoatore
     * di rimuovere un prodotto al carello
     * @param carrello da svuotare
     */
    void checkout(Carrello carrello);

    /**
     * Firma del metodo che permette
     * ad un Videogicoatore
     * di visualziare gli ordini effettuati
     * @param id del videogiocatore
     */
    List<Ordine>visualizzaOrdiniEffettuati(int id);

}
