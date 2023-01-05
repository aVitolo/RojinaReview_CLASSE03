package rojinaReview.autenticazione.service;

import rojinaReview.model.exception.VideogiocatoreIDMissingException;
import rojinaReview.model.beans.*;
import rojinaReview.model.dao.IndirizzoDAO;
import rojinaReview.model.dao.PagamentoDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AutenticazioneServiceImpl implements AutenticazioneService{
    @Override
    public Videogiocatore login(String email, String password) {
        return null;
    }

    @Override
    public boolean isVideogiocatore(Videogiocatore videogiocatore) {
        return false;
    }

    @Override
    public boolean isGiornalista(Videogiocatore videogiocatore) {
        return false;
    }

    @Override
    public boolean isManager(Videogiocatore videogiocatore) {
        return false;
    }

    @Override
    public void modificaVideogiocatore(Videogiocatore videogiocatore) {

    }

    @Override
    public void modificaGiornalista(Giornalista giornalista) {

    }

    @Override
    public void modificaManager(Manager manager) {

    }

    @Override
    public void inserisciNumeroTelefonico(String telefono) {

    }

    @Override
    public ArrayList<String> visualizzaNumeriTelefonici(int id) {
        return null;
    }

    @Override
    public void inserisciMetodoDiPagamento(Pagamento pagamento) throws VideogiocatoreIDMissingException {

    }

    /*
        inserisciMetodoDiPagamento(Pagamento pagamento) permette di inserire un nuovo metodo di pagamento nella tabella Pagamento
        ma senza id non e' possibile salvare il "collegamento" pagamento-videogiocatore quindi modifico momentanemente il metodo
     */
    @Override
    public void inserisciMetodoDiPagamento(int id, Pagamento pagamento) throws VideogiocatoreIDMissingException {
        try {
            new PagamentoDAO().doSave(pagamento,id);
        } catch (SQLException e) {
            throw new VideogiocatoreIDMissingException("ID non presente nel DB");
        }
    }

    @Override
    public ArrayList<Pagamento> visualizzaMetodiDiPagamento(int id) {
        return null;
    }

    @Override
    public void inserisciIndrizzo(Indirizzo indirizzo) {

    }
    @Override
    public void inserisciIndrizzo(int id, Indirizzo indirizzo) throws VideogiocatoreIDMissingException {
        try {
            new IndirizzoDAO().doSave(id,indirizzo);
        } catch (SQLException e) {
            throw new VideogiocatoreIDMissingException("ID non presente nel DB");
        }
    }
    @Override
    public ArrayList<Indirizzo> visualizzaIndirizzi(int id) {
        return null;
    }

    @Override
    public void autorizzaRegistrazioneGiornalista(int id) {

    }

    @Override
    public void autorizzaRegistrazioneManager(int id) {

    }

    @Override
    public void negaRegistrazioneGiornalista(int id) {

    }

    @Override
    public void negaRegistrazioneManager(int id) {

    }
}
