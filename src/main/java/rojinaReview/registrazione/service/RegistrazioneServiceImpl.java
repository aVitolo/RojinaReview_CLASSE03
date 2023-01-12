package rojinaReview.registrazione.service;

import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.beans.Manager;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.GiornalistaDAO;
import rojinaReview.model.dao.ManagerDAO;
import rojinaReview.model.dao.VideogiocatoreDAO;
import rojinaReview.model.exception.EmailNotAvailableException;
import rojinaReview.model.exception.InvalidTextException;
import rojinaReview.model.exception.NicknameNotAvailableException;
import rojinaReview.model.utilities.ConPool;
import rojinaReview.model.utilities.Utils;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;

public class RegistrazioneServiceImpl implements RegistrazioneService{
    private Connection connection;
    private VideogiocatoreDAO vDAO;
    private GiornalistaDAO gDAO;
    private ManagerDAO mDAO;

    public RegistrazioneServiceImpl() throws SQLException {
        this.connection = ConPool.getConnection();
        vDAO = new VideogiocatoreDAO(this.connection);
        gDAO = new GiornalistaDAO(this.connection);
        mDAO = new ManagerDAO(this.connection);
    }

    public int registraVideogiocatore(Videogiocatore videogiocatore) throws EmailNotAvailableException, NicknameNotAvailableException, InvalidTextException {
        try {
            Utils.textCheckEmail(videogiocatore.getEmail());
        } catch (InvalidTextException e) {
            throw new InvalidTextException("email invalida");
        }

        try{
            Utils.textCheckNickname(videogiocatore.getNickname());
        } catch (InvalidTextException e){
            throw new InvalidTextException("nickname invalido");
        }

        try{
            Utils.textCheckPassword(videogiocatore.getPassword());
        } catch (InvalidTextException e){
            throw new InvalidTextException("password invalida");
        }

        String unhashedPassword = videogiocatore.getPassword();
        String hashedPassword = null;
        try {
            hashedPassword = Utils.calcolaHash(unhashedPassword);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        videogiocatore.setPassword(hashedPassword);

        try {
            return vDAO.doInsertUser(videogiocatore);
        } catch (SQLException e) {
            if(e.getMessage().equals("email"))
                throw new EmailNotAvailableException();
            else if (e.getMessage().equals("nickname"))
                throw new NicknameNotAvailableException();
        }

        return 0;
    }

    public int registraGiornalista(Giornalista giornalista) throws InvalidTextException, EmailNotAvailableException {
        try {
            Utils.textCheckEmail(giornalista.getEmail());
        } catch (InvalidTextException e) {
            throw new InvalidTextException("email invalida");
        }

        try{
            Utils.textCheckPassword(giornalista.getPassword());
        } catch (InvalidTextException e){
            throw new InvalidTextException("password invalida");
        }

        String unhashedPassword = giornalista.getPassword();
        String hashedPassword = null;
        try {
            hashedPassword = Utils.calcolaHash(unhashedPassword);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        giornalista.setPassword(hashedPassword);

        try {
            gDAO.doSendRequestJournalist(giornalista);
        } catch (SQLException e) {
            if(e.getMessage().equals("email"))
                throw new EmailNotAvailableException();
        }

        return 0;

    }

    public int registraManager(Manager manager) throws InvalidTextException, EmailNotAvailableException {
        try {
            Utils.textCheckEmail(manager.getEmail());
        } catch (InvalidTextException e) {
            throw new InvalidTextException("email invalida");
        }

        try{
            Utils.textCheckPassword(manager.getPassword());
        } catch (InvalidTextException e){
            throw new InvalidTextException("password invalida");
        }

        String unhashedPassword = manager.getPassword();
        String hashedPassword = null;
        try {
            hashedPassword = Utils.calcolaHash(unhashedPassword);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        manager.setPassword(hashedPassword);

        try {
            mDAO.doSendRequestManager(manager);
        } catch (SQLException e) {
            if(e.getMessage().equals("email"))
                throw new EmailNotAvailableException();
        }

        return 0;
    }

}
