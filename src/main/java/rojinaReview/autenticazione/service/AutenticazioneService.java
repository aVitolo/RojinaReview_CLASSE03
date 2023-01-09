package rojinaReview.autenticazione.service;

import rojinaReview.model.exception.*;
import rojinaReview.model.beans.*;

import java.util.ArrayList;

/**
 * @author Carmine Iemmino
 * Interfaccia per i metodi del sottosistema Autenticazione.
 */
public interface AutenticazioneService {

    /**
     * Firma del metodo che permette di
     * effettuare il login come videogiocatore
     * @param email dell'utente da loggare
     * @param password dell'utente da loggare
     * @return utente loggato
     */
    Videogiocatore loginVideogiocatore(String email, String password) throws EmailNotExistsException, IncorrectPasswordException, LoadingCartException;

    /**
     * Firma del metodo che permette di
     * effettuare il login come giornalista
     * @param email dell'utente da loggare
     * @param password dell'utente da loggare
     * @return utente loggato
     */
    Giornalista loginGiornalista(String email, String password) throws EmailNotExistsException, IncorrectPasswordException, NotVerifiedAccountException;

    /**
     * Firma del metodo che permette di
     * effettuare il login come manager
     * @param email dell'utente da loggare
     * @param password dell'utente da loggare
     * @return utente loggato
     */
    Manager loginManager(String email, String password) throws EmailNotExistsException, IncorrectPasswordException, NotVerifiedAccountException;

    /**
     * Firma del metodo che permette di
     * identificare un videogiocatore
     * @param utente registrato salvato nella sessione
     * @return true se l'utente è un videogiocatore altrimenti false
     */
    boolean isVideogiocatore(Utente utente);

    /**
     * Firma del metodo che permette di
     * identificare un giornalista
     * @param utente registrato salvato nella sessione
     * @return true se l'utente è un giornalista altrimenti false
     */
    boolean isGiornalista(Utente utente);

    /**
     * Firma del metodo che permette di
     * identificare un manager
     * @param utente registrato salvato nella sessione
     * @return true se l'utente è un manager altrimenti false
     */
    boolean isManager(Utente utente);

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
     * @param videogiocatore a cui appartiene il numero di telefono
     */
    void inserisciNumeroTelefonico(String telefono, Videogiocatore videogiocatore);

    /**
     * Firma del metodo che permette di
     * visualizzare i numeri telefonici di un videogiocatore
     * @param videogiocatore a cui appartengono i numeri telefonici
     * @return una lista di numeri di telefono
     */
    ArrayList<String> visualizzaNumeriTelefonici(Videogiocatore videogiocatore);

    /**
     * Firma del metodo che permette di
     * inserire un metodo di pagamento ad un videogiocatore
     * @param pagamento da inserire
     * @param videogiocatore a cui appartiene il metodo di pagamento
     */
    void inserisciMetodoDiPagamento(Pagamento pagamento, Videogiocatore videogiocatore) throws VideogiocatoreIDMissingException;

    /*
        inserisciMetodoDiPagamento(Pagamento pagamento) permette di inserire un nuovo metodo di pagamento nella tabella Pagamento
        ma senza id non e' possibile salvare il "collegamento" pagamento-videogiocatore quindi modifico momentanemente il metodo
     */

    /**
     * Firma del metodo che permette di
     * visualizzare i metodi di pagamento di un videogiocatore
     * @param videogiocatore a cui appartengono i metodi di pagamento
     * @return una lista di metodi di pagamento
     */
    ArrayList<Pagamento> visualizzaMetodiDiPagamento(Videogiocatore videogiocatore);

    /**
     * Firma del metodo che permette di
     * inserire un indirizzo ad un videogiocatore
     * @param indirizzo da inserire
     * @param videogiocatore a cui appartiene l'indirizzo
     */
    void inserisciIndrizzo(Indirizzo indirizzo, Videogiocatore videogiocatore);

    /**
     * Firma del metodo che permette di
     * visualizzare gli indirizzi di un videogiocatore
     * @param videogiocatore a cui appartengono gli indirizzi
     * @return una lista di indirizzi
     */
    ArrayList<Indirizzo> visualizzaIndirizzi(Videogiocatore videogiocatore);

    /**
     * Firma del metodo che permette di
     * autorizzare la registrazione di un giornalista
     * @param giornalista da autorizzare
     */
    void autorizzaRegistrazioneGiornalista(Giornalista giornalista);

    /**
     * Firma del metodo che permette di
     * autorizzare la registrazione di un manager
     * @param manager autorizzare
     */
    void autorizzaRegistrazioneManager(Manager manager);

    /**
     * Firma del metodo che permette di
     * negare la registrazione ad una persona che si è finta giornalista
     * @param giornalista da rimuovere dal database
     */
    void negaRegistrazioneGiornalista(Giornalista giornalista);

    /**
     * Firma del metodo che permette di
     * negare la registrazione ad una persona che si è finta manager
     * @param manager da rimuovere dal database
     */
    void negaRegistrazioneManager(Manager manager);

    /**
     * Firma del metodo che permette di
     * visualizzar le richieste di registrazione come manager o giornalista
     */
    ArrayList<ArrayList<Utente>> visualizzaRichieste();
}
