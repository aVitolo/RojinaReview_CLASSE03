package rojinaReview.rivista.service;

import rojinaReview.model.exception.*;
import rojinaReview.model.beans.*;

import java.util.ArrayList;

/**
 * @author Carmine Iemmino
 * Interfaccia per i metodi del sottosistema Rivista.
 */
public interface RivistaService {

    /**
     * Firma del metodo che permette di
     * visualizzare gli articoli nella homepage
     * @return la lista degli articoli
     */
    ArrayList<Articolo> visualizzaArticoli() throws LoadingArticlesException;


    /**
     * Firma del metodo che permette di
     * visualizzare le notizie nella sezione notizie
     * @return la lista delle notizie
     */
    ArrayList<Notizia> visualizzaNotizie(String piattaforma, String tipologia, String ordine) throws LoadingNewsException;

    /**
     * Firma del metodo che permette di
     * visualizzare le recensioni nella sezione recensioni
     * @return la lista delle recensioni
     */
    ArrayList<Recensione> visualizzaRecensioni(String piattaforma, String tipologia, String ordine) throws LoadingReviewsException;

    /**
     * Firma del metodo che permette di
     * visualizzare una recensione in base al suo ID
     * @param id della recensione
     *
     * @return la recensione recuperata
     */
    Recensione visualizzaRecensioneByID(int id) throws LoadingVideogamesException, LoadingReviewsException;

    /**
     * Firma del metodo che permette di
     * visualizzare una notizia in base al suo ID
     * @param id della notizia
     *
     * @return la notizia recuperata
     */
    Notizia visualizzaNotiziaByID(int id) throws LoadingNewsException;

    /**
     * Firma del metodo che permette di
     * visualizzare tutti i videoghiochi
     * @return la lista dei videogiochi
     */
    ArrayList<Videogioco> visualizzaVideogiochi() throws LoadingVideogamesException;

    /**
     * Firma del metodo che permette di
     * visualizzare un videogioco in base al suo ID
     * @param id del videogioco da recuperare
     * @return il videogioco recuperato
     */
    Videogioco visualizzaVideogiocoByID(int id) throws LoadingVideogamesException;

    /**
     * Firma del metodo che permette di
     * inserire una recensione
     * @param recensione da inserire
     * @param giornalista che ha scritto la recensione
     */
    void inserisciRecensione(Recensione recensione, Giornalista giornalista) throws InsertReviewException, InsertCommentException, InsertParagraphException;

    /**
     * Firma del metodo che permette di
     * inserire una notizia
     * @param notizia da inserire
     * @param giornalista che ha scritto la notizia
     */
    void inserisciNotizia(Notizia notizia, Giornalista giornalista) throws InsertCommentException, InsertNewException, InsertParagraphException;

    /**
     * Firma del metodo che permette di
     * modificare una recensione
     * @param recensione modificata
     * @param giornalista che ha modificato la recensione
     */
    void modificaRecensione(Recensione recensione, Giornalista giornalista) throws RemovingParagraphsException, RemovingCommentsException, RemovingReviewException, InsertParagraphException, InsertReviewException, InsertCommentException;

    /**
     * Firma del metodo che permette di
     * modificare un articolo
     * @param notizia modificata
     * @param giornalista che ha modificato la notizia
     */
    void modificaNotizia(Notizia notizia, Giornalista giornalista) throws RemovingNewException, RemovingParagraphsException, RemovingCommentsException, InsertParagraphException, InsertNewException, InsertCommentException;

    /**
     * Firma del metodo che permette di
     * cancellare una recensione
     * @param recensione da cancellare
     */
    void cancellaRecensione(Recensione recensione) throws RemovingReviewException, RemovingParagraphsException, RemovingCommentsException;

    /**
     * Firma del metodo che permette di
     * cancellare una notizia
     * @param notizia da cancellare
     */
    void cancellaNotizia(Notizia notizia) throws RemovingParagraphsException, RemovingNewException, RemovingCommentsException;

    /**
     * Firma del metodo che permette di
     * visualizzare le recensioni scritte da un giornalista nella sua area utente
     * @param giornalista di cui si vogliono visualizzare le recensioni
     * @return la lista delle recensioni scritte da un giornalista (il giornalista loggato)
     */
    ArrayList<Recensione> visualizzaRecensioniScritte(Giornalista giornalista) throws LoadingReviewsException;

    /**
     * Firma del metodo che permette di
     * visualizzare le notizie scritte da un giornalista nella sua area utente
     * @param giornalista di cui si vogliono visualizzare le notizie
     * @return la lista delle notizie scritte da un giornalista (il giornalista loggato)
     */
    ArrayList<Notizia> visualizzaNotizieScritte(Giornalista giornalista) throws LoadingNewsException;

    /**
     * Firma del metodo che permette di
     * inserire un videogioco
     * @param videogioco da inserire
     */
    void inserisciVideogioco(Videogioco videogioco) throws InsertVideogameException;

    /**
     * Firma del metodo che permette di
     * modificare un videogioco
     * @param videogioco modificato
     */
    void modificaVideogioco(Videogioco videogioco);

    ArrayList<Notizia> visualizzaNotizie() throws LoadingNewsException;

    ArrayList<Recensione> visualizzaRecensioni() throws LoadingReviewsException;

    Giornalista visualizzaGiornalista(Articolo articolo) throws LoadingJournalistException;

    Videogioco visualizzaVideogioco(Recensione recensione) throws LoadingVideogamesException;

    Recensione visualizzaRecensioneByIDVideogioco(int id) throws LoadingReviewsException;

    /**
     * Firma del metodo che permette di
     * visualizzare tutte le piattaforme presenti
     * @return la lista di piattaforme
     */
    ArrayList<String> visualizzaPiattaforme() throws LoadingPlatformsException;

    /**
     * Firma del metodo che permette di
     * visualizzare tutti i generi presenti
     * @return la lista di generi
     */
    ArrayList<String> visualizzaGeneri() throws LoadingGenresException;

    ArrayList<String> visualizzaNomiVideogiochi() throws LoadingVideogamesException;

}
