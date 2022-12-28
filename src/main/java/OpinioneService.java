import model.beans.Commento;
import model.beans.Segnalazione;
import model.utilities.Voto;

import java.util.List;

public interface OpinioneService {
    /**
     * Implementa la funzionalità di restituire tutti i commenti presenti sotto ogni tipo di contenuto(recensione, notizia o prodotto)
     * @param id id del contenuto da ricercare
     * @param flag flag che serve a capire per quale tipo di contenuto si vogliono visualizzare i commenti
     * @return la lista di tutti i commenti riguardo quel contenuto
     */
    List<String>visualizzaCommenti(int id, int flag);

    /**
     * Implementa la funzionalità di visualizzare i commenti di un singolo utente
     * @param id id dell'utente della quale visualizzare i commenti
     * @return la lista di tutti i commenti dell'utente
     */
    List<Commento>visualizzaCommentiUtente(int id);

    /**
     * Implementa la funzionalità di commentare un qualsiasi contenuto sulla piattaforma
     * @param commento Il commento da inserire nel database
     * @param flag flag che serve a capire a quale tipo di contenuto è inserito il commento
     */
    void inserisciCommento(Commento commento, int flag);

    /**
     * Implementa la funzionalità di inserire un parere ad un videogioco o ad un prodotto
     * @param voto il voto da inserire nel database
     * @param flag flag che serve a capire se il voto si riferisce ad un prodotto o ad un vidogioco
     *
     */
    void inserisciVoto(Voto voto, int flag);

    /**
     * Implementa la funzionalità di visualizzare i voti di un singolo utente
     * @param id id dell'utente della quale visualizzare i voti
     * @return lista dei voti dell'utente
     */
    List<Voto> visualizzaVoti(int id);

    /**
     * Implementa la funzionalità di segnalare un commento presente sulla piattaforma
     * @param segnalazione informazioni sul commento segnalato
     * @param flag flag che serve a capire il contenuto nella quale è presente il commento da segnalare
     */
    void inviaSegnalazione(Segnalazione segnalazione, int flag);

    /**
     * Implementa la funzionalità di vedere i commenti segnalati
     * @return lista dei commenti segnalati
     */
    List<Segnalazione> visualizzaCommentiSegnalati();

    /**
     * Implementa la funzionalità di visualizzare i dettagli di un commento segnalato
     * @param id id della segnalazione
     */
    Segnalazione visualizzaDettagliCommentoSegnalato(int id);

    /**
     * Implementa la funzionalità di gestire una segnalazione bannando l'utente, eliminando il commento o eliminando la segnalazione
     * @param id id della segnalazione
     */
    void gestisciSegnalazione(int id);
}
