package rojinaReview.opinione.service;

import rojinaReview.model.exception.InsertOpinionException;
import rojinaReview.model.exception.LoadingCommentException;
import rojinaReview.model.beans.Commento;
import rojinaReview.model.beans.Segnalazione;
import rojinaReview.model.beans.Parere;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Carmine Pio Nardo
 * Interfaccia per i metodi del sottosistema Opinione.
 */

public interface OpinioneService {
    /**
     * Firma del metodo che permette di
     * restituire tutti i commenti presenti sotto ogni tipo di contenuto(recensione, notizia o prodotto)
     * @param id id del contenuto da ricercare
     * @param flag flag che serve a capire per quale tipo di contenuto si vogliono visualizzare i commenti
     * @return la lista di tutti i commenti riguardo quel contenuto
     */
    List<Commento> visualizzaCommenti(int id, int flag) throws SQLException, LoadingCommentException;

    /**
     * Firma del metodo che permette di
     * visualizzare i commenti di un singolo utente
     * @param id id dell'utente della quale visualizzare i commenti
     * @return la lista di tutti i commenti dell'utente
     */
    List<Commento>visualizzaCommentiUtente(int id) throws LoadingCommentException;

    /**
     * Firma del metodo che permette di
     * commentare un qualsiasi contenuto sulla piattaforma
     * @param commento Il commento da inserire nel database
     * @param flag flag che serve a capire a quale tipo di contenuto è inserito il commento
     */
    void inserisciCommento(Commento commento, int flag) throws InsertOpinionException;

    /**
     * Firma del metodo che permette di
     * inserire un parere ad un videogioco o ad un prodotto
     * @param parere il voto da inserire nel database
     * @param flag flag che serve a capire se il voto si riferisce ad un prodotto o ad un vidogioco
     *
     */
    void inserisciVoto(Parere parere, int flag) throws InsertOpinionException;

    /**
     * Firma del metodo che permette di
     * visualizzare i voti di un singolo utente
     * @param id id dell'utente della quale visualizzare i voti
     * @return lista dei voti dell'utente
     */
    List<Parere> visualizzaVoti(int id) throws LoadingCommentException;

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
    List<Commento> visualizzaCommentiSegnalati() throws LoadingCommentException;

    /**
     * Firma del metodo che permette di
     * visualizzare i dettagli di un commento segnalato
     * @param id id della segnalazione
     * @return
     */
    List<Segnalazione> visualizzaDettagliCommentoSegnalato(int id) throws LoadingCommentException;

    /**
     * Firma del metodo che permette di
     * gestire una segnalazione bannando l'utente, eliminando il commento o eliminando la segnalazione
     * @param id id della segnalazione
     */
    void gestisciSegnalazione(Segnalazione segnalazione, int flag) throws SQLException;
}
