package rojinaReview.autenticazione.service;

import rojinaReview.model.dao.*;
import rojinaReview.model.exception.EmailNotExistsException;
import rojinaReview.model.exception.IncorrectPasswordException;
import rojinaReview.model.exception.VideogiocatoreIDMissingException;
import rojinaReview.model.beans.*;
import rojinaReview.model.utilities.ConPool;
import rojinaReview.model.utilities.Utils;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class AutenticazioneServiceImpl implements AutenticazioneService{
    Connection connection;
    private VideogiocatoreDAO vDAO;

    public AutenticazioneServiceImpl() throws SQLException {
        this.connection = ConPool.getConnection();
        vDAO = new VideogiocatoreDAO(this.connection);
    }



    @Override
    public Videogiocatore loginVideogiocatore(String email, String password) throws EmailNotExistsException, IncorrectPasswordException {
        String hashedPassword = null;
        Videogiocatore videogiocatoreDB = null;

        try {
            hashedPassword = Utils.calcolaHash(password);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            videogiocatoreDB = vDAO.doRetriveByEmail(email);
        } catch (SQLException e) {
            if(e.getMessage().equalsIgnoreCase("Invalid email"))
                throw new EmailNotExistsException();
        }

        if(hashedPassword.equals(videogiocatoreDB.getPassword()))
        {
            if (videogiocatoreDB.getImmagine() == null)
                videogiocatoreDB.setImmagine("./static/images/utility/defaultImageUser.png"); //immagine di default utente
            return videogiocatoreDB;
        }
        else
            throw new IncorrectPasswordException();

    }

    public Giornalista loginGiornalista(String email, String password)
    {
        return null;
    }

    public Manager loginManager(String email, String password)
    {
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

    @Override
    public ArrayList<ArrayList<Utente>> visualizzaRichieste() {
        ArrayList<ArrayList<Utente>> inQueeue = new ArrayList<>();
        try {
            inQueeue.add(new GiornalistaDAO().doRetriveInQueeue());
            inQueeue.add(new ManagerDAO().doRetriveInQueeue());
            return inQueeue;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
