package rojinaReview.shop.service;

import java.util.ArrayList;
import java.util.List;

import rojinaReview.model.beans.Videogiocatore;
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
    List<Prodotto> visualizzaShop(String categoria, String ordine) throws LoadingShopException;

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
     * @param ordine per la persistenza
     * @param videogiocatore che sta acquistando
     */
    void checkout(Ordine ordine, Videogiocatore videogiocatore) throws CheckoutException;

    /**
     * Firma del metodo che permette
     * ad un Videogicoatore
     * di visualziare gli ordini effettuati
     * @param videogiocatore di cui si devono visualizzare gli ordini
     */
    ArrayList<Ordine>visualizzaOrdiniEffettuati(Videogiocatore videogiocatore) throws LoadingOrderException;

}
