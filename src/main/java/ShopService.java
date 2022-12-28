import java.util.List;

import model.beans.Carrello;
import model.beans.Ordine;
import model.beans.Prodotto;

/**
 * Implementa l'interfaccia service
 * per il sottosistema ClubDelLibro.
 * @author Andrea Vitolo
 */
public interface ShopService
{
    /**
     * Implementa la funzionalità che permette
     * ad Giornalista o un Videogiocatore
     * di visualizzare tutti i prodotti.
     * @return la lista dei prodotti
     */
    List<Prodotto> visualizzaShop();

    /**
     * Implementa la funzionalità che permette
     * ad un Giornalista o un Videogiocatore
     * di visualizzare i dati di un prodotto.
     * @param id del prodotto
     * @return dati del prodotto
     */
    Prodotto visualizzaProdotto(int id);

    /**
     * Implementa la funzionalità che permette
     * ad un Giornalista
     * di aggiungere un prodotto allo shop
     * @param prodotto info
     */
    void inserisciProdotto(Prodotto prodotto);

    /**
     * Implementa la funzionalità che permette
     * ad un Giornalista
     * di aggiungere un prodotto allo shop
     * @param prodotto
     */
    void modificaProdotto(Prodotto prodotto);

    /**
     * Implementa la funzionalità che permette
     * ad un Giornalista
     * di rimuovere un prodotto dallo shop
     * @param id del prodotto
     */
    void cancellaProdotto(int id);

    /**
     * Implementa la funzionalità che permette
     * ad un Videogicoatore
     * di aggiungere un prodotto al carello
     * @param prodotto da aggiungere al carello
     */
    void aggiungiProdottoAlCarrello(Carrello.ProdottoCarrello prodotto);

    /**
     * Implementa la funzionalità che permette
     * ad un Videogicoatore
     * di rimuovere un prodotto al carello
     * @param prodotto da rimuovere dal carello
     */
    void rimuoviProdottoDalCarrello(Carrello.ProdottoCarrello prodotto);

    /**
     * Implementa la funzionalità che permette
     * ad un Videogicoatore
     * di rimuovere un prodotto al carello
     * @param carrello da svuotare
     */
    void checkout(Carrello carrello);

    /**
     * Implementa la funzionalità che permette
     * ad un Videogicoatore
     * di visualziare gli ordini effettuati
     * @param id del videogiocatore
     */
    List<Ordine>visualizzaOrdiniEffettuati(int id);

}
