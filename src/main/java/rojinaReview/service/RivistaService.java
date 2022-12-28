package rojinaReview.service;

import rojinaReview.model.utilities.Articolo;

import java.util.ArrayList;

/**
 * @author Carmine Iemmino
 * Interfaccia per i metodi del sottosistema Rivista.
 */
public interface RivistaService {
    /**
     * Implementa la funzionalità che permette di
     * visualizzare tutti gli articoli
     * @return la lista degli articoli
     */
    ArrayList<Articolo> visualizzaArticoli();

    /**
     * Implementa la funzionalita che permette di
     * visualizzare un articolo in base al suo ID
     * @param id l'id dell'articolo
     * @param tipo il tipo dell'articolo (0 notizia, 1 recensione)
     * @return l'articolo recuperato
     */
    Articolo visualizzaArticoloByID(int id, int tipo);

    /**
     * Implementa la funzionalita che permette di
     * visualizzare tutti i videoghiochi
     * @return la lista dei videogiochi
     */
    ArrayList<Videogioco> visualizzaVideogiochi();

    /**
     * Implementa la funzioanlità che permette di
     * visualizzare un videogioco in base al suo ID
     * @param id
     * @return il videogioco recuperato
     */
    Videogioco visualizzaVideogiocoByID(int id);

    /**
     * Implementa la funzionalità che permette di
     * inserire un articolo
     * @param articolo l'articolo da inserire
     */

    void inserisciArticolo(Articolo articolo);

    /**
     * Implementa la funzionalità che permette di
     * modificare un articolo
     * @param articolo l'articolo modificato
     */
    void modificaArticolo(Articolo articolo);

    /**
     * Implementa la funzionalità che permette di
     * visualizzare gli articoli scritti da un giornalista nella sua area utente
     * @return la lista degli articoli scritti da un giornalista (il giornalista loggato)
     */

    ArrayList<Articolo> visualizzaArticoliScritti();

    /**
     * Implementa la funzionalità che permette di
     * inserire un videogioco
     * @param videogioco il videogioco da inserire
     */
    void inserisciVideogioco(Videogioco videogioco);

    /**
     * Implementa la funzionalità che permette di
     * modificare un videogioco
     * @param videogioco il videogioco modificato
     */
    void modificaVideogioco(Videogioco videogioco);
}
