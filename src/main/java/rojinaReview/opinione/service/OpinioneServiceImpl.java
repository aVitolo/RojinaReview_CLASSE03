package rojinaReview.opinione.service;

import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.VideogiocatoreDAO;
import rojinaReview.model.exception.InsertOpinionException;
import rojinaReview.model.exception.LoadingCommentException;
import rojinaReview.model.beans.Commento;
import rojinaReview.model.beans.Parere;
import rojinaReview.model.beans.Segnalazione;
import rojinaReview.model.dao.CommentoDAO;
import rojinaReview.model.dao.ParereDAO;
import rojinaReview.model.dao.SegnalazioneDAO;
import rojinaReview.model.utilities.ConPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OpinioneServiceImpl implements OpinioneService{
    CommentoDAO cDAO;
    ParereDAO pDAO;
    SegnalazioneDAO sDAO;
    VideogiocatoreDAO vDAO;
    Connection connection;

    public OpinioneServiceImpl() throws SQLException {
        this.connection = ConPool.getConnection();
        cDAO=new CommentoDAO();
        pDAO=new ParereDAO();
        sDAO=new SegnalazioneDAO();
        vDAO=new VideogiocatoreDAO();
    }

    @Override
    public List<Commento> visualizzaCommenti(int id, int flag) throws LoadingCommentException {
        try {
            List<Commento> commenti = null;
            commenti = cDAO.getCommentById(id, flag);
            return commenti;
        }catch (SQLException e){
            throw new LoadingCommentException("Errore nel caricamento dei commenti");
        }
    }

    @Override
    public List<Commento> visualizzaCommentiUtente(int id) throws LoadingCommentException {
        try {
            List<Commento> commenti = null;
            commenti = cDAO.getCommentsByUser(id);
            return commenti;
        }catch (SQLException e){
            throw new LoadingCommentException("Errore nel caricamento dei commenti");
        }
    }

    @Override
    public void inserisciCommento(Commento commento, int flag) throws InsertOpinionException {
        try {
            cDAO.doSave(commento, flag);
        }catch (SQLException e){
            throw new InsertOpinionException("Errore inserimento parere");
        }
    }

    @Override
    public void inserisciVoto(Parere parere, int flag) throws InsertOpinionException {
        try {
            pDAO.doSave(parere, flag);
        }catch (SQLException e){
            throw new InsertOpinionException("Errore inserimento parere");
        }
    }

    @Override
    public List<Parere> visualizzaVoti(int id) throws LoadingCommentException {
        try {
            List<Parere> voti = null;
            voti =pDAO.doRetrieveByUser(id);
            return voti;
        }catch (SQLException e){
            throw new LoadingCommentException("Errore nel caricamento dei voti");
        }
    }

    @Override
    public void inviaSegnalazione(Segnalazione segnalazione) throws InsertOpinionException {
        try {
            sDAO.doSave(segnalazione);
        }catch (SQLException e){
            throw new InsertOpinionException("Errore nel caricamento dei voti");
        }
    }

    @Override
    public List<Commento> visualizzaCommentiSegnalati() throws LoadingCommentException {
        List<Commento> commenti=null;
        try{
            commenti=sDAO.doRetrieveReportedComments();
            return commenti;
        }catch (SQLException e){
            throw new LoadingCommentException("Errore selezione segnalazioni");
        }
    }

    @Override
    public List<Segnalazione> visualizzaDettagliCommentoSegnalato(int id) throws LoadingCommentException {
        List<Segnalazione> segnalazioni=null;
        try{
            segnalazioni=sDAO.doRetrieveReportsForComment(id);
            return segnalazioni;
        } catch (SQLException e){
            throw new LoadingCommentException("Errore selezione segnalazione");
        }
    }

    @Override
    public void gestisciSegnalazione(Segnalazione segnalazione, int flag, Commento commento) throws SQLException {
        // eliminazione segnalazioni
        if(flag==0){
            sDAO.deleteReports(segnalazione);
        }
        //ban utente
        if(flag==1){
            sDAO.deleteReports(segnalazione);
            vDAO.banUser(commento.getIdVideogiocatore());
        }
        //rimozione commento
        if(flag==2){
            sDAO.deleteReports(segnalazione);
            cDAO.deleteComment(commento.getId());
        }
    }
}
