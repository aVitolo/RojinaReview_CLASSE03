package rojinaReview.rivista.service;

import rojinaReview.model.beans.Articolo;
import rojinaReview.model.beans.Videogioco;

import java.util.ArrayList;

/**
 * @author Carmine Iemmino
 * Interfaccia per i metodi del sottosistema Rivista.
 */
public interface RivistaService {
    /**
     * Firma del metodo che permette di
     * visualizzare tutti gli articoli
     * @return la lista degli articoli
     */
    ArrayList<Articolo> visualizzaArticoli();

    /**
     * Firma del metodo che permette di
     * visualizzare un articolo in base al suo ID
     * @param id dell'articolo
     * @param tipo dell'articolo (0 notizia, 1 recensione)
     * @return l'articolo recuperato
     */
    Articolo visualizzaArticoloByID(int id, int tipo);

    /**
     * Firma del metodo che permette di
     * visualizzare tutti i videoghiochi
     * @return la lista dei videogiochi
     */
    ArrayList<Videogioco> visualizzaVideogiochi();

    /**
     * Firma del metodo che permette di
     * visualizzare un videogioco in base al suo ID
     * @param id del videogioco da recuperare
     * @return il videogioco recuperato
     */
    Videogioco visualizzaVideogiocoByID(int id);

    /**
     * Firma del metodo che permette di
     * inserire un articolo
     * @param articolo da inserire
     */

    void inserisciArticolo(Articolo articolo);

    /**
     * Firma del metodo che permette di
     * modificare un articolo
     * @param articolo modificato
     */
    void modificaArticolo(Articolo articolo);

    /**
     * Firma del metodo che permette di
     * visualizzare gli articoli scritti da un giornalista nella sua area utente
     * @return la lista degli articoli scritti da un giornalista (il giornalista loggato)
     */

    ArrayList<Articolo> visualizzaArticoliScritti();

    /**
     * Firma del metodo che permette di
     * inserire un videogioco
     * @param videogioco da inserire
     */
    void inserisciVideogioco(Videogioco videogioco);

    /**
     * Firma del metodo che permette di
     * modificare un videogioco
     * @param videogioco modificato
     */
    void modificaVideogioco(Videogioco videogioco);
}
