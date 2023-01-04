package rojinaReview.autenticazione.service;

import rojinaReview.exception.VideogiocatoreIDMissingException;
import rojinaReview.model.beans.*;

import java.util.ArrayList;

/**
 * @author Carmine Iemmino
 * Interfaccia per i metodi del sottosistema Autenticazione.
 */
public interface AutenticazioneService {

    /**
     * Firma del metodo che permette di
     * effettuare il login come videogiocatore, giornalista o manager
     * @param email dell'utente da loggare
     * @param password dell'utente da loggare
     * @return utente loggato
     */
    Videogiocatore login(String email, String password);

    /**
     * Firma del metodo che permette di
     * identificare un videogiocatore
     * @param videogiocatore registrato salvato nella sessione
     * @return true se l'utente è un videogiocatore altrimenti false
     */
    boolean isVideogiocatore(Videogiocatore videogiocatore);

    /**
     * Firma del metodo che permette di
     * identificare un giornalista
     * @param videogiocatore registrato salvato nella sessione
     * @return true se l'utente è un giornalista altrimenti false
     */
    boolean isGiornalista(Videogiocatore videogiocatore);

    /**
     * Firma del metodo che permette di
     * identificare un manager
     * @param videogiocatore registrato salvato nella sessione
     * @return true se l'utente è un manager altrimenti false
     */
    boolean isManager(Videogiocatore videogiocatore);

    /**
     * Firma del metodo che permette di
     * modificare i dati di un videogiocatore
     * @param videogiocatore modificato
     */
    void modificaVideogiocatore(Videogiocatore videogiocatore);

    /**
     * Firma del metodo che permette di
     * modificare i dati di un giornalista
     * @param giornalista modificato
     */
    void modificaGiornalista(Giornalista giornalista);

    /**
     * Firma del metodo che permette di
     * modificare i dati di un manager
     * @param manager modificato
     */
    void modificaManager(Manager manager);

    /**
     * Firma del metodo che permette di
     * inserire un numero telefonico ad un videogiocatore
     * @param telefono da inserire
     */
    void inserisciNumeroTelefonico(Telefono telefono);

    /**
     * Firma del metodo che permette di
     * visualizzare i numeri telefonici di un videogiocatore
     * @param id del videogiocatore
     * @return una lista di numeri di telefono
     */
    ArrayList<Telefono> visualizzaNumeriTelefonici(int id);

    /**
     * Firma del metodo che permette di
     * inserire un metodo di pagamento ad un videogiocatore
     * @param pagamento da inserire
     */
    void inserisciMetodoDiPagamento(Pagamento pagamento) throws VideogiocatoreIDMissingException;

    /*
        inserisciMetodoDiPagamento(Pagamento pagamento) permette di inserire un nuovo metodo di pagamento nella tabella Pagamento
        ma senza id non e' possibile salvare il "collegamento" pagamento-videogiocatore quindi modifico momentanemente il metodo
     */
    void inserisciMetodoDiPagamento(int id, Pagamento pagamento) throws VideogiocatoreIDMissingException;

    /**
     * Firma del metodo che permette di
     * visualizzare i metodi di pagamento di un videogiocatore
     * @param id del videogiocatore
     * @return una lista di metodi di pagamento
     */
    ArrayList<Pagamento> visualizzaMetodiDiPagamento(int id);

    /**
     * Firma del metodo che permette di
     * inserire un indirizzo ad un videogiocatore
     * @param indirizzo da inserire
     */
    void inserisciIndrizzo(Indirizzo indirizzo);

    void inserisciIndrizzo(int id, Indirizzo indirizzo) throws VideogiocatoreIDMissingException;

    /**
     * Firma del metodo che permette di
     * visualizzare gli indirizzi di un videogiocatore
     * @param id del videogiocatore
     * @return una lista di indirizzi
     */
    ArrayList<Indirizzo> visualizzaIndirizzi(int id);

    /**
     * Firma del metodo che permette di
     * autorizzare la registrazione di un giornalista
     * @param id del giornalista da autorizzare
     */
    void autorizzaRegistrazioneGiornalista(int id);

    /**
     * Firma del metodo che permette di
     * autorizzare la registrazione di un manager
     * @param id del manager da autorizzare
     */
    void autorizzaRegistrazioneManager(int id);

    /**
     * Firma del metodo che permette di
     * negare la registrazione ad una persona che si è finta giornalista
     * @param id del "giornalista" da rimuovere dal database
     */
    void negaRegistrazioneGiornalista(int id);

    /**
     * Firma del metodo che permette di
     * negare la registrazione ad una persona che si è finta manager
     * @param id del "manager" da rimuovere dal database
     */
    void negaRegistrazioneManager(int id);

}
