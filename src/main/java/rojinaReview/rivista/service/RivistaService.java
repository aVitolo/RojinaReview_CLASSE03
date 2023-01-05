package rojinaReview.rivista.service;

import rojinaReview.model.beans.*;

import java.util.ArrayList;

/**
 * @author Carmine Iemmino
 * Interfaccia per i metodi del sottosistema Rivista.
 */
public interface RivistaService {
    //non serve?
    /**
     * Firma del metodo che permette di
     * visualizzare tutti gli articoli
     * @return la lista degli articoli
     */
    ArrayList<Articolo> visualizzaArticoli();



    /**
     * Firma del metodo che permette di
     * visualizzare una recensione in base al suo ID
     * @param id della recensione
     *
     * @return la recensione recuperata
     */
    Recensione visualizzaRecensioneByID(int id);

    /**
     * Firma del metodo che permette di
     * visualizzare una notizia in base al suo ID
     * @param id della notizia
     *
     * @return la notizia recuperata
     */
    Notizia visualizzaNotiziaByID(int id);

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
     * inserire una recensione
     * @param recensione da inserire
     */

    void inserisciRecensione(Recensione recensione);

    /**
     * Firma del metodo che permette di
     * inserire una notizia
     * @param notizia da inserire
     */

    void inserisciNotizia(Notizia notizia);

    /**
     * Firma del metodo che permette di
     * modificare un articolo
     * @param articolo modificato
     */
    void modificaArticolo(Articolo articolo);

    /**
     * Firma del metodo che permette di
     * visualizzare le recensioni scritte da un giornalista nella sua area utente
     * @param giornalista di cui si vogliono visualizzare le recensioni
     * @return la lista delle recensioni scritte da un giornalista (il giornalista loggato)
     */
    ArrayList<Recensione> visualizzaRecensioniScritte(Giornalista giornalista);

    /**
     * Firma del metodo che permette di
     * visualizzare le notizie scritte da un giornalista nella sua area utente
     * @param giornalista di cui si vogliono visualizzare le notizie
     * @return la lista delle notizie scritte da un giornalista (il giornalista loggato)
     */
    ArrayList<Notizia> visualizzaNotizieScritte(Giornalista giornalista);

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
