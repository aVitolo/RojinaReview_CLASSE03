package rojinaReview.autenticazione.service;

import rojinaReview.model.dao.*;
import rojinaReview.model.exception.*;
import rojinaReview.model.beans.*;
import rojinaReview.model.utilities.ConPool;
import rojinaReview.model.utilities.Utils;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class AutenticazioneServiceImpl implements AutenticazioneService{
    private Connection connection;
    private VideogiocatoreDAO vDAO;
    private GiornalistaDAO gDAO;
    private ManagerDAO mDAO;
    private CarrelloDAO cDAO;
    private TelefonoDAO tDAO;
    private PagamentoDAO pDAO;
    private IndirizzoDAO iDAO;

    public AutenticazioneServiceImpl() throws SQLException {
        this.connection = ConPool.getConnection();
        vDAO = new VideogiocatoreDAO(this.connection);
        gDAO = new GiornalistaDAO(this.connection);
        mDAO = new ManagerDAO(this.connection);
        cDAO = new CarrelloDAO(this.connection);
        tDAO = new TelefonoDAO(this.connection);
        pDAO = new PagamentoDAO(this.connection);
        iDAO = new IndirizzoDAO(this.connection);
    }



    @Override
    public Videogiocatore loginVideogiocatore(String email, String password) throws EmailNotExistsException, IncorrectPasswordException, LoadingCartException {
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

            try {
                videogiocatoreDB.setCarrello(cDAO.doRetrieveByUser(videogiocatoreDB.getId()));
            } catch (SQLException e) {
                throw new LoadingCartException();
            }

            return videogiocatoreDB;
        }
        else
            throw new IncorrectPasswordException();

    }
    @Override
    public Giornalista loginGiornalista(String email, String password) throws EmailNotExistsException, IncorrectPasswordException, NotVerifiedAccountException {
        String hashedPassword = null;
        Giornalista giornalistaDB = null;

        try {
            hashedPassword = Utils.calcolaHash(password);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            giornalistaDB = gDAO.doRetriveByEmail(email);
        } catch (SQLException e) {
            if(e.getMessage().equalsIgnoreCase("Invalid email"))
                throw new EmailNotExistsException();
        }

        if(!giornalistaDB.isVerificato())
            throw new NotVerifiedAccountException();

        if(hashedPassword.equals(giornalistaDB.getPassword()))
        {
            if(giornalistaDB.getImmagine() == null)
                giornalistaDB.setImmagine("./static/images/utility/defaultImageUser.png"); //immagine di default utente

            return giornalistaDB;
        }
        else
            throw new IncorrectPasswordException();

    }
    @Override
    public Manager loginManager(String email, String password) throws EmailNotExistsException, IncorrectPasswordException, NotVerifiedAccountException {
        String hashedPassword = null;
        Manager managerDB = null;

        try {
            hashedPassword = Utils.calcolaHash(password);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            managerDB = mDAO.doRetriveByEmail(email);
        } catch (SQLException e) {
            throw new EmailNotExistsException();
        }

        if(!managerDB.isVerificato())
            throw new NotVerifiedAccountException();


        if(hashedPassword.equals(managerDB.getPassword()))
        {
            if(managerDB.getImmagine() == null)
                managerDB.setImmagine("./static/images/utility/defaultImageUser.png"); //immagine di default utente
            return managerDB;
        }
        else
            throw new IncorrectPasswordException();
    }

    public void salvaCarrello(Videogiocatore videogiocatore) throws SavingCartException {
        try {
            cDAO.doSave(videogiocatore.getCarrello(), videogiocatore.getId());
        } catch (SQLException e) {
            throw new SavingCartException();
        }
    }


    @Override
    public boolean isVideogiocatore(Utente utente) {
        return "Videogiocatore".equals(utente.getClass().getSimpleName());
    }

    @Override
    public boolean isGiornalista(Utente utente) {
        return "Giornalista".equals(utente.getClass().getSimpleName());
    }

    @Override
    public boolean isManager(Utente utente) {
        return "Manager".equals(utente.getClass().getSimpleName());
    }

    @Override
    public void modificaVideogiocatore(Videogiocatore videogiocatore) throws UpdateDataException {
        try {
            vDAO.update(videogiocatore);
        } catch (SQLException e) {
            throw new UpdateDataException();
        }
    }

    @Override
    public void modificaGiornalista(Giornalista giornalista) throws UpdateDataException {
        try {
            gDAO.update(giornalista);
        } catch (SQLException e) {
            throw new UpdateDataException();
        }

    }

    @Override
    public void modificaManager(Manager manager) throws UpdateDataException {
        try {
            mDAO.update(manager);
        } catch (SQLException e) {
            throw new UpdateDataException();
        }

    }

    @Override
    public void inserisciNumeroTelefonico(String telefono, Videogiocatore videogiocatore) throws InsertNumberException {
        try {
            tDAO.doSave(videogiocatore.getId(), telefono);
            videogiocatore.getTelefoni().add(telefono); //aggiungo il numero di telefono all'oggetto nella sessione
        } catch (SQLException e) {
            throw new InsertNumberException();
        }
    }

    @Override
    public ArrayList<String> visualizzaNumeriTelefonici(Videogiocatore videogiocatore) throws LoadingNumbersException {
        if(videogiocatore.getTelefoni().size() != 0)
            return videogiocatore.getTelefoni();
        try {
            return tDAO.doRetriveByUser(videogiocatore.getId());
        } catch (SQLException e) {
            throw new LoadingNumbersException();
        }
    }

    @Override
    public void inserisciMetodoDiPagamento(Pagamento pagamento, Videogiocatore videogiocatore) throws InsertPaymentException {
        try {
            pDAO.doSave(pagamento, videogiocatore.getId());
            videogiocatore.getPagamenti().add(pagamento); //aggiungo il pagamento all'oggetto nella sessione
        } catch (SQLException e) {
            throw new InsertPaymentException();
        }
    }

    @Override
    public ArrayList<Pagamento> visualizzaMetodiDiPagamento(Videogiocatore videogiocatore) throws LoadingPaymentsException {
        if(videogiocatore.getPagamenti().size() != 0)
            return videogiocatore.getPagamenti();
        try {
            return pDAO.doRetrieveByUser(videogiocatore.getId());
        } catch (SQLException e) {
            throw new LoadingPaymentsException();
        }
    }

    @Override
    public void inserisciIndrizzo(Indirizzo indirizzo, Videogiocatore videogiocatore) throws InsertAddressException {
        try {
            iDAO.doSave(videogiocatore.getId(), indirizzo);
            videogiocatore.getIndirizzi().add(indirizzo); //aggiungo l'indirizzo all'oggetto nella sessione
        } catch (SQLException e) {
            throw new InsertAddressException();
        }

    }

    @Override
    public ArrayList<Indirizzo> visualizzaIndirizzi(Videogiocatore videogiocatore) throws LoadingAddressesException {
        if(videogiocatore.getIndirizzi().size() != 0)
            return videogiocatore.getIndirizzi();
        try {
            return iDAO.doRetriveByUser(videogiocatore.getId());
        } catch (SQLException e) {
            throw new LoadingAddressesException();
        }
    }

    @Override
    public void autorizzaRegistrazioneGiornalista(Giornalista giornalista) throws AuthorizeException {
        try {
            gDAO.doVerificaGiornalista(giornalista.getId());
        } catch (SQLException e) {
        }

    }

    @Override
    public void autorizzaRegistrazioneManager(Manager manager) throws AuthorizeException {
        try {
            mDAO.doVerificaManager(manager.getId());
        } catch (SQLException e) {
            throw new AuthorizeException();
        }

    }

    @Override
    public void negaRegistrazioneGiornalista(Giornalista giornalista) throws NotAuthorizeException {
        try {
            gDAO.doRemoveFromQueeue(giornalista.getId());
        } catch (SQLException e) {
            throw new NotAuthorizeException();
        }

    }

    @Override
    public void negaRegistrazioneManager(Manager manager) throws NotAuthorizeException {
        try {
            mDAO.doRemoveFromQueeue(manager.getId());
        } catch (SQLException e) {
            throw new NotAuthorizeException();
        }

    }

    @Override
    public ArrayList<ArrayList<Utente>> visualizzaRichieste() throws RuntimeException, LoadingRegistrationRequestsException {
        ArrayList<ArrayList<Utente>> inQueeue = new ArrayList<>();
        try {
            inQueeue.add(gDAO.doRetriveInQueeue());
            inQueeue.add(mDAO.doRetriveInQueeue());
            return inQueeue;
        } catch (SQLException e) {
            throw new LoadingRegistrationRequestsException();
        }
    }
}
