package rojinaReview.registrazione.service;

import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.beans.Manager;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.exception.EmailNotAvailableException;
import rojinaReview.model.exception.InvalidTextException;
import rojinaReview.model.exception.NicknameNotAvailableException;

/**
 * @author Carmine Iemmino
 * Interfaccia per i metodi del sottosistema Registrazione.
 */
public interface RegistrazioneService {
    /**
     * Firma del metodo che permette di
     * registrare un videogiocatore nel sistema
     * @param videogiocatore da registrare
     * @return l'id del videogiocatore appena registrato
     */
    int registraVideogiocatore(Videogiocatore videogiocatore) throws EmailNotAvailableException, NicknameNotAvailableException, InvalidTextException;

    /**
     * Firma del metodo che permette di
     * registrare un giornalista nel sistema
     * @param giornalista da registare
     * @return l'id del giornalista appena registrato
     */
    int registraGiornalista(Giornalista giornalista) throws InvalidTextException, EmailNotAvailableException;

    /**
     * Firma del metodo che permette di
     * registrare un manager nel sistema
     * @param manager da registrare
     * @return l'id del manager appena registrato
     */
    int registraManager(Manager manager) throws InvalidTextException, EmailNotAvailableException;


}
