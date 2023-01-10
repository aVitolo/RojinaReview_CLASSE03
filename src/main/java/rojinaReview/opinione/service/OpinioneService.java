package rojinaReview.opinione.service;

import rojinaReview.model.beans.*;
import rojinaReview.model.exception.InsertOpinionException;
import rojinaReview.model.exception.LoadingCommentException;
import rojinaReview.model.exception.LoadingOpinionException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Carmine Pio Nardo
 * Interfaccia per i metodi del sottosistema Opinione.
 */

public interface OpinioneService {
    /**
     * Firma del metodo che permette di
     * verificare se un contenuto è un prodotto
     * @param contenuto da verificare
     * @return true se contenuto è prodotto, false altrimenti
     */
    boolean isProdotto(Contenuto contenuto);

    /**
     * Firma del metodo che permette di
     * verificare se un contenuto è una recensione
     * @param contenuto da verificare
     * @return true se contenuto è recensione, false altrimenti
     */
    boolean isRecensione(Contenuto contenuto);

    /**
     * Firma del metodo che permette di
     * verifiare se un contenuto è una notizia
     * @param contenuto da verificare
     * @return true se contenuto è notizia, false altrimenti
     */
    boolean isNotizia(Contenuto contenuto);

    /**
     * Firma del metodo che permette di
     * restituire tutti i commenti presenti sotto ogni tipo di contenuto(recensione, notizia o prodotto)
     * @param contenuto di cui recuperare i commenti
     * @return la lista di tutti i commenti riguardo quel contenuto
     */
    ArrayList<Commento> visualizzaCommenti(Contenuto contenuto) throws SQLException, LoadingCommentException;

    /**
     * Firma del metodo che permette di
     * visualizzare i commenti di un singolo utente
     * @param videogiocatore della quale visualizzare i commenti
     * @return la lista di tutti i commenti dell'utente
     */
    ArrayList<Commento>visualizzaCommentiUtente(Videogiocatore videogiocatore) throws LoadingCommentException;

    /**
     * Firma del metodo che permette di
     * commentare un qualsiasi contenuto sulla piattaforma
     * @param commento da inserire
     * @param videogiocatore che sta inserendo il commento
     */
    void inserisciCommento(Commento commento, Videogiocatore videogiocatore) throws InsertOpinionException;

    /**
     * Firma del metodo che permette di
     * inserire un parere ad un videogioco o ad un prodotto
     * @param parere da inserire
     * @param videogiocatore che sta inserendo il parere
     */
    void inserisciVoto(Parere parere, Videogiocatore videogiocatore) throws InsertOpinionException;

    /**
     * Firma del metodo che permette di
     * visualizzare i voti di un singolo utente
     * @param videogiocatore della quale visualizzare i pareri
     * @return lista dei pareri dell'utente
     */
    ArrayList<Parere> visualizzaVotiUtente(Videogiocatore videogiocatore) throws LoadingCommentException;

    /**
     * Firma del metodo che permette di
     * visualizzare il voto di un utente espresso ad un prodotto o videogioco
     * @param videogiocatore nella sessione che visualizza il proprio voto
     * @param contenuto a cui si riferisce il voto (recensione quindi a videogioco, oppure prodotto)
     * @return il parere da visualizzare
     */
    Parere visualizzaVotoUtente(Videogiocatore videogiocatore, Contenuto contenuto) throws LoadingOpinionException;

    /**
     * Firma del metodo che permette di
     * segnalare un commento presente sulla piattaforma
     * @param segnalazione informazioni sul commento segnalato
     */
    void inviaSegnalazione(Segnalazione segnalazione) throws InsertOpinionException;

    /**
     * Firma del metodo che permette di
     * vedere i commenti segnalati
     * @return lista dei commenti segnalati
     */
    ArrayList<Commento> visualizzaCommentiSegnalati() throws LoadingCommentException;

    /**
     * Firma del metodo che permette di
     * visualizzare i dettagli di un commento segnalato
     * @param commento di cui visualizzare i dettagli
     * @return lista delle segnalazioni relative al commento
     */
    ArrayList<Segnalazione> visualizzaDettagliCommentoSegnalato(Commento commento) throws LoadingCommentException;

    /**
     * Firma del metodo che permette di
     * gestire una segnalazione bannando l'utente, eliminando il commento o eliminando la segnalazione
     * @param segnalazione  che si vuole gestire
     * @param flag che serve a capire quale operazione effettuare sul commento
     * @param commento segnalato
     */
    void gestisciSegnalazione(Segnalazione segnalazione, int flag, Commento commento) throws SQLException;
}
