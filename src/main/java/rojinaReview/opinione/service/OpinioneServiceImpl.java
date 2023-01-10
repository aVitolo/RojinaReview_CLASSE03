package rojinaReview.opinione.service;

import rojinaReview.model.beans.*;
import rojinaReview.model.dao.VideogiocatoreDAO;
import rojinaReview.model.exception.InsertOpinionException;
import rojinaReview.model.exception.LoadingCommentException;
import rojinaReview.model.dao.CommentoDAO;
import rojinaReview.model.dao.ParereDAO;
import rojinaReview.model.dao.SegnalazioneDAO;
import rojinaReview.model.exception.LoadingOpinionException;
import rojinaReview.model.utilities.ConPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OpinioneServiceImpl implements OpinioneService{
    private Connection connection;
    private CommentoDAO cDAO;
    private ParereDAO pDAO;
    private SegnalazioneDAO sDAO;
    private VideogiocatoreDAO vDAO;

    public OpinioneServiceImpl() throws SQLException {
        this.connection = ConPool.getConnection();
        cDAO=new CommentoDAO(this.connection);
        pDAO=new ParereDAO(this.connection);
        sDAO=new SegnalazioneDAO(this.connection);
        vDAO=new VideogiocatoreDAO(this.connection);
    }

    @Override
    public boolean isProdotto(Contenuto contenuto)
    {
        return "Prodotto".equals(contenuto.getClass().getSimpleName());
    }

    @Override
    public boolean isRecensione(Contenuto contenuto)
    {
        return "Recensione".equals(contenuto.getClass().getSimpleName());
    }

    @Override
    public boolean isNotizia(Contenuto contenuto)
    {
        return "Notizia".equals(contenuto.getClass().getSimpleName());
    }

    @Override
    public ArrayList<Commento> visualizzaCommenti(Contenuto contenuto) throws LoadingCommentException {
        try {
            if(isProdotto(contenuto))
                return cDAO.getCommentById(contenuto.getId(), 0);
            if(isRecensione(contenuto))
                return cDAO.getCommentById(contenuto.getId(), 1);
            if(isNotizia(contenuto))
                return cDAO.getCommentById(contenuto.getId(), 2);
        }catch (SQLException e){
            throw new LoadingCommentException("Errore nel caricamento dei commenti");
        }

        return null;
    }

    @Override
    public ArrayList<Commento> visualizzaCommentiUtente(Videogiocatore videogiocatore) throws LoadingCommentException {
        if(videogiocatore.getCommenti() != null)
            return videogiocatore.getCommenti();
        try {
            return cDAO.getCommentsByUser(videogiocatore.getId());
        }catch (SQLException e){
            throw new LoadingCommentException("Errore nel caricamento dei commenti");
        }
    }

    @Override
    public void inserisciCommento(Commento commento, Videogiocatore videogiocatore) throws InsertOpinionException {
        try {
            cDAO.doSave(commento, videogiocatore.getId());
            if(videogiocatore.getCommenti() != null)
                videogiocatore.getCommenti().add(commento);
        }catch (SQLException e){
            e.printStackTrace();
            throw new InsertOpinionException("Errore inserimento parere");
        }
    }

    @Override
    public void inserisciVoto(Parere parere, Videogiocatore videogiocatore) throws InsertOpinionException {
        try {
            pDAO.doSave(parere, videogiocatore.getId());
            if(videogiocatore.getPareri() != null)
                videogiocatore.getPareri().add(parere);
        }catch (SQLException e){
            e.printStackTrace();
            throw new InsertOpinionException("Errore inserimento parere");
        }
    }

    @Override
    public ArrayList<Parere> visualizzaVotiUtente(Videogiocatore videogiocatore) throws LoadingCommentException {
        try {
            return pDAO.doRetrieveByUser(videogiocatore.getId());
        }catch (SQLException e){
            throw new LoadingCommentException("Errore nel caricamento dei voti");
        }
    }

    @Override
    public Parere visualizzaVotoUtente(Videogiocatore videogiocatore, Contenuto contenuto) throws LoadingOpinionException {
        if(isRecensione(contenuto))
        {
            Recensione recensione = (Recensione) contenuto;
            try {
                return pDAO.doRetrieveUserOpinion(videogiocatore.getId(), recensione.getIdVideogioco(), 3);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new LoadingOpinionException();
            }
        }
        else if(isProdotto(contenuto))
        {
            Prodotto prodotto = (Prodotto) contenuto;
            try {
                return pDAO.doRetrieveUserOpinion(videogiocatore.getId(), prodotto.getId(), 0);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new LoadingOpinionException();
            }
        }

        return null;
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
    public ArrayList<Commento> visualizzaCommentiSegnalati() throws LoadingCommentException {
        try{
            return sDAO.doRetrieveReportedComments();
        }catch (SQLException e){
            e.printStackTrace();
            throw new LoadingCommentException("Errore selezione segnalazioni");
        }
    }

    @Override
    public ArrayList<Segnalazione> visualizzaDettagliCommentoSegnalato(Commento commento) throws LoadingCommentException {
        try{
            return sDAO.doRetrieveReportsForComment(commento.getId());
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
