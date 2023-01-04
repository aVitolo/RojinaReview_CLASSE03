package rojinaReview.registrazione.service;

import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.beans.Manager;
import rojinaReview.model.beans.Videogiocatore;

/**
 * @author Carmine Iemmino
 * Interfaccia per i metodi del sottosistema Registrazione.
 */
public interface RegistrazioneService {
    /**
     * Firma del metodo che permette di
     * registrare un videogiocatore nel sistema
     * @param videogiocatore da registrare
     */
    void registraVideogiocatore(Videogiocatore videogiocatore);

    /**
     * Firma del metodo che permette di
     * registrare un giornalista nel sistema
     * @param giornalista da registare
     */
    void registraGiornalista(Giornalista giornalista);

    /**
     * Firma del metodo che permette di
     * registrare un manager nel sistema
     * @param manager da registrare
     */
    void registraManager(Manager manager);


}
