package rojinaReview.registrazione.service;

import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.beans.Manager;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.VideogiocatoreDAO;
import rojinaReview.model.exception.EmailNotAvailableException;
import rojinaReview.model.exception.InvalidTextException;
import rojinaReview.model.exception.NicknameNotAvailableException;
import rojinaReview.model.utilities.ConPool;
import rojinaReview.model.utilities.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class RegistrazioneServiceImpl implements RegistrazioneService{
    private Connection connection;
    private VideogiocatoreDAO vDAO;

    public RegistrazioneServiceImpl() throws SQLException {
        this.connection = ConPool.getConnection();
        vDAO = new VideogiocatoreDAO(this.connection);
    }

    public void registraVideogiocatore(Videogiocatore videogiocatore) throws EmailNotAvailableException, NicknameNotAvailableException, InvalidTextException {
        //Info costanti per validazione input
        final String[] badCharacters = {"#", "-", " ", "\'", "\""};

        final int passwordMinLenght = 6;
        final int passwordMaxLenght = 20;

        final int nickNameMinLenght = 5;
        final int nickNameMaxLenght = 30;

        final int emailMinLenght = 5;
        final int emailMaxLenght = 30;

        try {
            Utils.textCheck(videogiocatore.getEmail(), emailMinLenght, emailMaxLenght, badCharacters);
        } catch (InvalidTextException e) {
            throw new InvalidTextException("email invalida");
        }

        try{
            Utils.textCheck(videogiocatore.getNickname(), nickNameMinLenght, nickNameMaxLenght, badCharacters);
        } catch (InvalidTextException e){
            throw new InvalidTextException("nickname invalido");
        }

        try{
            Utils.textCheck(videogiocatore.getPassword(), passwordMinLenght, passwordMaxLenght, badCharacters);
        } catch (InvalidTextException e){
            throw new InvalidTextException("password invalida");
        }

        try {
            vDAO.doInsertUser(videogiocatore);
        } catch (SQLException e) {
            if(e.getMessage().equals("email"))
                throw new EmailNotAvailableException();
            else if (e.getMessage().equals("nickname"))
                throw new NicknameNotAvailableException();
        }
    }

    public void registraGiornalista(Giornalista giornalista)
    {

    }

    public void registraManager(Manager manager)
    {

    }

}
