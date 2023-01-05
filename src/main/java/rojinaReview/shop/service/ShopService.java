package rojinaReview.shop.service;

import java.util.ArrayList;
import java.util.List;

import rojinaReview.model.exception.CheckoutException;
import rojinaReview.model.exception.LoadingOrderException;
import rojinaReview.model.exception.LoadingShopException;
import rojinaReview.model.exception.ProductIDMissingException;
import rojinaReview.model.beans.Ordine;
import rojinaReview.model.beans.Prodotto;

/**
 * Implementa l'interfaccia service
 * per il sottosistema ClubDelLibro.
 * @author Andrea Vitolo
 */
public interface ShopService
{
    /*
    Servelet:
    -shop.ShopServlet
    -autenticazione.ShopManagment (visualizzazione Shop lato manager, da sviluppare)

 */
    List<Prodotto> visualizzaShop(String offset, String categoria, String ordine) throws LoadingShopException;

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
    Prodotto visualizzaProdotto(int id) throws ProductIDMissingException;

    /**
     * Firma del metodo che permette
     * ad un Giornalista
     * di aggiungere un prodotto allo shop
     * @param prodotto info
     */
    void inserisciProdotto(Prodotto prodotto) throws ProductIDMissingException;

    /**
     * Firma del metodo che permette
     * ad un Giornalista
     * di aggiungere un prodotto allo shop
     * @param prodotto
     */
    void modificaProdotto(Prodotto prodotto) throws ProductIDMissingException;

    /**
     * Firma del metodo che permette
     * ad un Giornalista
     * di rimuovere un prodotto dallo shop
     * @param id del prodotto
     */
    void cancellaProdotto(int id) throws ProductIDMissingException;

    /**
     * Firma del metodo che permette
     * ad un Videogicoatore
     * di aggiungere un prodotto al carello
     * @param prodotto da aggiungere al carello
     */
    void aggiungiProdottoAlCarrello(Prodotto prodotto);

    /**
     * Firma del metodo che permette
     * ad un Videogicoatore
     * di rimuovere un prodotto al carello
     * @param prodotto da rimuovere dal carello
     */
    void rimuoviProdottoDalCarrello(Prodotto prodotto);

    /**
     * Firma del metodo che permette
     * ad un Videogicoatore
     * di rimuovere un prodotto al carello
     * @param ordin per la persistenza
     * @param id del videogiocatore
     * @param prodottiContext , lista prodotti nella sessione da aggiornare
     */
    void checkout(Ordine ordine, int id, ArrayList<Prodotto>prodottiContext) throws CheckoutException;

    /**
     * Firma del metodo che permette
     * ad un Videogicoatore
     * di visualziare gli ordini effettuati
     * @param id del videogiocatore
     */
    List<Ordine>visualizzaOrdiniEffettuati(int id) throws LoadingOrderException;

}
