package rojinaReview.rivista.service;

import rojinaReview.exception.LoadingNewsException;
import rojinaReview.exception.LoadingReviewsException;
import rojinaReview.model.beans.*;
import rojinaReview.model.dao.*;
import rojinaReview.model.utilities.ConPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RivistaServiceImpl implements RivistaService{
    private Connection connection;
    private RecensioneDAO rDAO;
    private NotiziaDAO nDAO;
    private VideogiocoDAO vDAO;
    private CommentoDAO cDAO;
    private ParagrafoDAO pDAO;

    public RivistaServiceImpl() throws SQLException {
        this.connection = ConPool.getConnection();
        this.rDAO = new RecensioneDAO(this.connection);
        this.nDAO = new NotiziaDAO(this.connection);
        this.vDAO = new VideogiocoDAO(this.connection);
        this.cDAO = new CommentoDAO(this.connection);
        this.pDAO = new ParagrafoDAO(this.connection);
    }

    @Override
    public ArrayList<Articolo> visualizzaArticoli() {
        return null;
    }

    @Override
    public ArrayList<Notizia> visualizzaNotizie(String offset, String piattaforma, String genere, String ordine) throws LoadingNewsException {
        ArrayList<Notizia> notizie = null;
        try {
            notizie = new NotiziaDAO().updateContent(offset,piattaforma,genere,ordine);
            return notizie;
        } catch (SQLException e) {
            throw new LoadingNewsException("Errore nel caricamento delle notizie");
        }
    }
    @Override
    public ArrayList<Recensione> visualizzaRecensioni(String offset, String piattaforma, String genere, String ordine) throws LoadingReviewsException {
        ArrayList<Recensione> recensioni = null;
        try {
            recensioni = new RecensioneDAO().updateContent(offset,piattaforma,genere,ordine);
            return recensioni;
        } catch (SQLException e) {
            throw new LoadingReviewsException("Errore nel caricamento delle recensioni");
        }
    }

    public Recensione visualizzaRecensioneByID(int id)
    {
        try {
            return rDAO.doRetrieveById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Notizia visualizzaNotiziaByID(int id) //da aggiungere in questo metodo il setting dell'hashmap
    {
        try {
            return nDAO.doRetrieveById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Videogioco> visualizzaVideogiochi()
    {
        try {
            return vDAO.doRetrieveAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Videogioco visualizzaVideogiocoByID(int id)
    {
        try {
            return vDAO.doRetrieveById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void inserisciRecensione(Recensione recensione) {

    }

    @Override
    public void inserisciNotizia(Notizia notizia) {

    }

    @Override
    public void modificaArticolo(Articolo articolo) {

    }

    public void inserisciRecensione(Recensione recensione, Giornalista giornalista)
    {
        try {
            rDAO.doSave(recensione, giornalista.getId(), recensione.idVideogioco);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(Commento c : recensione.getCommenti()) { //inutile perché all'inserimento dell'articolo non ci saranno commenti
            try {
                cDAO.doSave(c, 1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        for(Paragrafo p : recensione.getParagrafi()){
            try {
                pDAO.doSave(p, rDAO.doRetrieveLastId(), true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void inserisciNotizia(Notizia notizia, Giornalista giornalista)
    {
        try {
            nDAO.doSave(notizia, giornalista.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(Commento c : notizia.getCommenti()) { //inutile perché all'inserimento dell'articolo non ci saranno commenti
            try {
                cDAO.doSave(c, 2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        for(Paragrafo p : notizia.getParagrafi()){
            try {
                pDAO.doSave(p, rDAO.doRetrieveLastId(), false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            nDAO.doSaveMentioned(notizia.getGiochi());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public ArrayList<Recensione> visualizzaRecensioniScritte(Giornalista giornalista) {
        try {
            return rDAO.doRetrieveByIdJournalist(giornalista.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Notizia> visualizzaNotizieScritte(Giornalista giornalista) {
        try {
            return nDAO.doRetrieveByIdJournalist(giornalista.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void inserisciVideogioco(Videogioco videogioco) {

    }

    @Override
    public void modificaVideogioco(Videogioco videogioco) {

    }
}
