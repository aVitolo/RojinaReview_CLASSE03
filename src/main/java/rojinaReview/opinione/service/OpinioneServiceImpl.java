package rojinaReview.opinione.service;

import rojinaReview.exception.InsertOpinionException;
import rojinaReview.exception.LoadingCommentException;
import rojinaReview.model.beans.Commento;
import rojinaReview.model.beans.Parere;
import rojinaReview.model.beans.Segnalazione;
import rojinaReview.model.dao.CommentoDAO;
import rojinaReview.model.dao.ParereDAO;
import rojinaReview.model.dao.SegnalazioneDAO;

import java.sql.SQLException;
import java.util.List;

public class OpinioneServiceImpl implements OpinioneService{
    @Override
    public List<Commento> visualizzaCommenti(int id, int flag) throws LoadingCommentException {
        try {
            List<Commento> commenti = null;
            commenti = new CommentoDAO().getCommentById(id, flag);
            return commenti;
        }catch (SQLException e){
            throw new LoadingCommentException("Errore nel caricamento dei commenti");
        }
    }

    @Override
    public List<Commento> visualizzaCommentiUtente(int id) throws LoadingCommentException {
        try {
            List<Commento> commenti = null;
            commenti = new CommentoDAO().getCommentByUser(id);
            return commenti;
        }catch (SQLException e){
            throw new LoadingCommentException("Errore nel caricamento dei commenti");
        }
    }

    @Override
    public void inserisciCommento(Commento commento, int flag) throws InsertOpinionException {
        try {
            new CommentoDAO().doSave(commento, flag);
        }catch (SQLException e){
            throw new InsertOpinionException("Errore inserimento parere");
        }
    }

    @Override
    public void inserisciVoto(Parere parere, int flag) throws InsertOpinionException {
        try {
            new ParereDAO().doSave(parere, flag);
        }catch (SQLException e){
            throw new InsertOpinionException("Errore inserimento parere");
        }
    }

    @Override
    public List<Parere> visualizzaVoti(int id) throws LoadingCommentException {
        try {
            List<Parere> voti = null;
            voti = new ParereDAO().doRetrieveByUser(id);
            return voti;
        }catch (SQLException e){
            throw new LoadingCommentException("Errore nel caricamento dei voti");
        }
    }

    @Override
    public void inviaSegnalazione(Segnalazione segnalazione) throws InsertOpinionException {
        try {
            new SegnalazioneDAO().doSave(segnalazione);
        }catch (SQLException e){
            throw new InsertOpinionException("Errore nel caricamento dei voti");
        }
    }

    @Override
    public List<Commento> visualizzaCommentiSegnalati() throws LoadingCommentException {
        List<Commento> commenti=null;
        try{
            commenti=new SegnalazioneDAO().doRetrieveReportedComments();
            return commenti;
        }catch (SQLException e){
            throw new LoadingCommentException("Errore selezione segnalazioni");
        }
    }

    @Override
    public List<Segnalazione> visualizzaDettagliCommentoSegnalato(int id) throws LoadingCommentException {
        List<Segnalazione> segnalazioni=null;
        try{
            segnalazioni=new SegnalazioneDAO().doRetrieveReportsForComment(id);
            return segnalazioni;
        } catch (SQLException e){
            throw new LoadingCommentException("Errore selezione segnalazione");
        }
    }

    @Override
    public void gestisciSegnalazione(Segnalazione segnalazione, int flag) throws SQLException {
        // eliminazione segnalazioni
        if(flag==0){
            new SegnalazioneDAO().deleteReports(segnalazione);
        }
        //ban utente
        if(flag==1){

        }
        //rimozione commento
        if(flag==2){

        }
    }
}
