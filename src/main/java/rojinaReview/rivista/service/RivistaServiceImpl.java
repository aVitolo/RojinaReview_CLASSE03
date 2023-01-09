package rojinaReview.rivista.service;

import rojinaReview.model.exception.*;
import rojinaReview.model.beans.*;
import rojinaReview.model.dao.*;
import rojinaReview.model.utilities.ConPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class RivistaServiceImpl implements RivistaService{
    private Connection connection;
    private RecensioneDAO rDAO;
    private NotiziaDAO nDAO;
    private VideogiocoDAO vDAO;
    private CommentoDAO cDAO;
    private ParagrafoDAO pDAO;
    private GenereDAO gDAO;
    private PiattaformaDAO piattaformaDAO;

    public RivistaServiceImpl() throws SQLException {
        this.connection = ConPool.getConnection();
        this.rDAO = new RecensioneDAO(this.connection);
        this.nDAO = new NotiziaDAO(this.connection);
        this.vDAO = new VideogiocoDAO(this.connection);
        this.cDAO = new CommentoDAO(this.connection);
        this.pDAO = new ParagrafoDAO(this.connection);
        this.gDAO = new GenereDAO(this.connection);
        this.piattaformaDAO = new PiattaformaDAO(this.connection);
    }

    @Override
    public ArrayList<Articolo> visualizzaArticoli() throws LoadingArticlesException {
        ArrayList<Articolo> articoli = new ArrayList<>();
        try {
            articoli.addAll(nDAO.doRetriveAll());
            articoli.addAll(rDAO.doRetriveAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        articoli.sort(Comparator.comparing(a -> a.getId()));
        return articoli;
    }

    @Override
    public ArrayList<Notizia> visualizzaNotizie(String piattaforma, String genere, String ordine) throws LoadingNewsException {
        try {
            return nDAO.updateContent(piattaforma,genere,ordine);
        } catch (SQLException e) {
            throw new LoadingNewsException("Errore nel caricamento delle notizie");
        }
    }

    @Override
    public ArrayList<Notizia> visualizzaNotizie() throws LoadingNewsException {
        try {
            return nDAO.doRetriveAll();
        } catch (SQLException e) {
            throw new LoadingNewsException("Errore nel caricamento delle notizie");
        }
    }

    @Override
    public ArrayList<Recensione> visualizzaRecensioni(String piattaforma, String genere, String ordine) throws LoadingReviewsException {
        try {
            return rDAO.updateContent(piattaforma,genere,ordine);
        } catch (SQLException e) {
            throw new LoadingReviewsException("Errore nel caricamento delle recensioni");
        }
    }
    @Override
    public ArrayList<Recensione> visualizzaRecensioni() throws LoadingReviewsException {
        try {
            return rDAO.doRetriveAll();
        } catch (SQLException e) {
            throw new LoadingReviewsException("Errore nel caricamento delle recensioni");
        }
    }

    public Recensione visualizzaRecensioneByID(int id) throws LoadingReviewsException {
        try {
            return rDAO.doRetrieveById(id);
        } catch (SQLException e) {
            throw new LoadingReviewsException("Errore nel caricamento della recensione");
        }
    }

    public Notizia visualizzaNotiziaByID(int id) throws LoadingNewsException //da aggiungere in questo metodo il setting dell'hashmap
    {
        try {
            return nDAO.doRetrieveById(id);
        } catch (SQLException e) {
            throw new LoadingNewsException("Errore nel caricamento notizia");
        }
    }

    public ArrayList<Videogioco> visualizzaVideogiochi() throws LoadingVideogamesException {
        try {
            return vDAO.doRetrieveAll();
        } catch (SQLException e) {
            throw new LoadingVideogamesException("Errore nel caricamento dei videogiochi");
        }
    }

    public Videogioco visualizzaVideogiocoByID(int id) throws LoadingVideogamesException {
        try {
            return vDAO.doRetrieveById(id);
        } catch (SQLException e) {
            throw new LoadingVideogamesException("Errore nel caricamento del videogioco");
        }
    }


    public void inserisciRecensione(Recensione recensione, Giornalista giornalista) throws InsertReviewException, InsertCommentException, InsertParagraphException {
        try {
            rDAO.doSave(recensione, giornalista.getId(), recensione.idVideogioco);
        } catch (SQLException e) {
            throw new InsertReviewException("Errore nell'inserimento della recensione");
        }

        for(Commento c : recensione.getCommenti()) { //inutile perché all'inserimento dell'articolo non ci saranno commenti
            try {
                cDAO.doSave(c, 1);
            } catch (SQLException e) {
                throw new InsertCommentException("Errore nell'inserimento dei commenti");
            }
        }

        for(Paragrafo p : recensione.getParagrafi()){
            try {
                pDAO.doSave(p, rDAO.doRetrieveLastId(), true);
            } catch (SQLException e) {
                throw new InsertParagraphException("Errore nell'inserimento dei paragrafi");
            }
        }

    }

    public void inserisciNotizia(Notizia notizia, Giornalista giornalista) throws InsertCommentException, InsertNewException, InsertParagraphException {
        try {
            nDAO.doSave(notizia, giornalista.getId());
        } catch (SQLException e) {
           throw new InsertNewException("Errore nell'inserimento della notizia");
        }

        for(Commento c : notizia.getCommenti()) { //inutile perché all'inserimento dell'articolo non ci saranno commenti
            try {
                cDAO.doSave(c, 2);
            } catch (SQLException e) {
                throw new InsertCommentException("Errore nell'inserimento dei commenti");
            }
        }

        for(Paragrafo p : notizia.getParagrafi()){
            try {
                pDAO.doSave(p, rDAO.doRetrieveLastId(), false);
            } catch (SQLException e) {
                throw new InsertParagraphException("Errore nell'inserimento dei paragrafi");
            }
        }

        try {
            nDAO.doSaveMentioned(notizia.getGiochi());
        } catch (SQLException e) {
            throw new InsertNewException("Errore nell'inserimento dei giochi menzionati");
        }


    }

    //da modificare in seguito con una vera modifica
    public void modificaRecensione(Recensione recensione, Giornalista giornalista) throws RemovingParagraphsException, RemovingCommentsException, RemovingReviewException, InsertParagraphException, InsertReviewException, InsertCommentException {
        cancellaRecensione(recensione);
        inserisciRecensione(recensione, giornalista);
    }

    public void modificaNotizia(Notizia notizia, Giornalista giornalista) throws RemovingNewException, RemovingParagraphsException, RemovingCommentsException, InsertParagraphException, InsertNewException, InsertCommentException {
        cancellaNotizia(notizia);
        inserisciNotizia(notizia, giornalista);
    }

    public void cancellaRecensione(Recensione recensione) throws RemovingReviewException, RemovingParagraphsException, RemovingCommentsException {
        try {
            rDAO.doRemoveById(recensione.getId());
        } catch (SQLException e) {
            throw new RemovingReviewException("Errore nella rimozione recensione");
        }
        try {
            pDAO.doRemoveAll(recensione.getId(), 1);
        } catch (SQLException e) {
            throw new RemovingParagraphsException("Errore nella rimozione paragrafi");
        }
        try {
            cDAO.deleteAll(recensione.getId(), 1);
        } catch (SQLException e) {
            throw new RemovingCommentsException("Errore nella rimozione commenti");
        }

    }

    public void cancellaNotizia(Notizia notizia) throws RemovingParagraphsException, RemovingNewException, RemovingCommentsException {
        try {
            nDAO.doRemoveById(notizia.getId());
        } catch (SQLException e) {
            throw new RemovingNewException("Errore nella rimozione notizia");
        }
        try {
            pDAO.doRemoveAll(notizia.getId(), 2);
        } catch (SQLException e) {
            throw new RemovingParagraphsException("Errore nella rimozione paragrafi");
        }
        try {
            cDAO.deleteAll(notizia.getId(), 2);
        } catch (SQLException e) {
            throw new RemovingCommentsException("Errore nella rimozione commenti");
        }
    }

    public ArrayList<Recensione> visualizzaRecensioniScritte(Giornalista giornalista) throws LoadingReviewsException {
        try {
            return rDAO.doRetrieveByIdJournalist(giornalista.getId());
        } catch (SQLException e) {
            throw new LoadingReviewsException("Errore nel caricamento delle recensioni del giornalista");
        }
    }

    public ArrayList<Notizia> visualizzaNotizieScritte(Giornalista giornalista) throws LoadingNewsException {
        try {
            return nDAO.doRetrieveByIdJournalist(giornalista.getId());
        } catch (SQLException e) {
            throw new LoadingNewsException("Errore nel caricamento delle notizie del giornalista");
        }
    }

    @Override
    public void inserisciVideogioco(Videogioco videogioco) throws InsertVideogameException {
        try {
            videogioco.setId(vDAO.doSave(videogioco));
        } catch (SQLException e) {
            throw new InsertVideogameException("Errore nell'inserimento videogioco");
        }
        try {
            gDAO.doSave(videogioco.getId(), videogioco.getGeneri());
        } catch (SQLException e) {
            throw new InsertVideogameException("Errore nell'inserimento generi videogioco");
        }
        try {
            piattaformaDAO.doSave(videogioco.getId(), videogioco.getPiattaforme());
        } catch (SQLException e) {
            throw new InsertVideogameException("Errore nell'inserimento piattaforme videogioco");
        }
    }

    @Override
    //da implementare in una futura release
    public void modificaVideogioco(Videogioco videogioco) {

    }
}
