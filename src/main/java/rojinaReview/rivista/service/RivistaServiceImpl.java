package rojinaReview.rivista.service;

import rojinaReview.model.exception.*;
import rojinaReview.model.beans.*;
import rojinaReview.model.dao.*;
import rojinaReview.utilities.ConPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
    private GiornalistaDAO giornalistaDAO;

    public RivistaServiceImpl() throws SQLException {
        this.connection = ConPool.getConnection();
        this.rDAO = new RecensioneDAO(this.connection);
        this.nDAO = new NotiziaDAO(this.connection);
        this.vDAO = new VideogiocoDAO(this.connection);
        this.cDAO = new CommentoDAO(this.connection);
        this.pDAO = new ParagrafoDAO(this.connection);
        this.gDAO = new GenereDAO(this.connection);
        this.piattaformaDAO = new PiattaformaDAO(this.connection);
        this.giornalistaDAO = new GiornalistaDAO(this.connection);
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
        Recensione recensione;
        try {
            recensione = rDAO.doRetrieveById(id);
            recensione.setCommenti(cDAO.getCommentByContentId(recensione.getId(), 1));
            recensione.setParagrafi(pDAO.doRetrieveAllByArticle(recensione.getId(), true));
            return recensione;
        } catch (SQLException e) {
            throw new LoadingReviewsException("Errore nel caricamento della recensione");
        }
    }

    public Notizia visualizzaNotiziaByID(int id) throws LoadingNewsException //da aggiungere in questo metodo il setting dell'hashmap
    {
        Notizia notizia;
        try {
            notizia = nDAO.doRetrieveById(id);
            notizia.setCommenti(cDAO.getCommentByContentId(notizia.getId(), 2));
            notizia.setParagrafi(pDAO.doRetrieveAllByArticle(notizia.getId(), false));
            notizia.setGiochi(nDAO.doRetrieveMentionedGames(id));
            return notizia;
        } catch (SQLException e) {
            e.printStackTrace();
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
            rDAO.doSave(recensione, giornalista.getId(), recensione.getNomeVideogioco());
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            throw new InsertReviewException("Videogioco non presente");
        } catch (SQLException e) {
            e.printStackTrace();
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
                pDAO.doSave(p, rDAO.doRetrieveLastId(), 1);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new InsertParagraphException("Errore nell'inserimento dei paragrafi");
            }
        }

    }

    public void inserisciNotizia(Notizia notizia, Giornalista giornalista) throws InsertCommentException, InsertNewException, InsertParagraphException {
        try {
            nDAO.doSave(notizia, giornalista.getId());
        } catch (SQLException e) {
            e.printStackTrace();
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
                pDAO.doSave(p, nDAO.doRetrieveLastId(), 2);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new InsertParagraphException("Errore nell'inserimento dei paragrafi");
            }
        }

        try {
            nDAO.doSaveMentioned(notizia.getGiochi(), nDAO.doRetrieveLastId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InsertNewException("Errore nell'inserimento dei giochi menzionati");
        }


    }

    //da implementare in seguito con una vera modifica
    public void modificaRecensione(Recensione recensione, Giornalista giornalista) throws RemovingParagraphsException, RemovingCommentsException, RemovingReviewException, InsertParagraphException, InsertReviewException, InsertCommentException {
        cancellaRecensione(recensione);
        inserisciRecensione(recensione, giornalista);
    }

    public void modificaNotizia(Notizia notizia, Giornalista giornalista) throws RemovingNewException, RemovingParagraphsException, RemovingCommentsException, InsertParagraphException, InsertNewException, InsertCommentException, UpdateDataException {
        try {
            nDAO.doUpdate(notizia);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UpdateDataException();
        }

        for(Paragrafo p : notizia.getParagrafi())
        {
            try {
                if(p.getId() != 0) //il paragrafo deve essere aggiornato e non aggiunto
                    pDAO.doUpdate(p, notizia.getId());
                else
                    pDAO.doSave(p, notizia.getId(), 2);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new UpdateDataException();
            }
        }

        try {
            nDAO.deleteMentioned(notizia.getId());
            nDAO.doSaveMentioned(notizia.getGiochi(), notizia.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UpdateDataException();
        }
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
            e.printStackTrace();
            throw new LoadingNewsException("Errore nel caricamento delle notizie del giornalista");
        }
    }

    @Override
    public void inserisciVideogioco(Videogioco videogioco) throws InsertVideogameException {
        try {
            videogioco.setId(vDAO.doSave(videogioco));
        } catch (SQLException e) {
            e.printStackTrace();
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

    public Giornalista visualizzaGiornalista(Articolo articolo) throws LoadingJournalistException {
        try {
            return giornalistaDAO.doRetrieveById(articolo.getId_Giornalista());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new LoadingJournalistException();
        }
    }

    public Videogioco visualizzaVideogioco(Recensione recensione) throws LoadingVideogamesException {
        try {
            return vDAO.doRetrieveById(recensione.getIdVideogioco());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new LoadingVideogamesException();
        }
    }

    public Recensione visualizzaRecensioneByIDVideogioco(int id) throws LoadingReviewsException {
        Recensione recensione;
        try {
            recensione = rDAO.doRetrieveByIdVideogioco(id);
            recensione.setCommenti(cDAO.getCommentByContentId(recensione.getId(), 1));
            recensione.setParagrafi(pDAO.doRetrieveAllByArticle(recensione.getId(), true));
            return recensione;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new LoadingReviewsException();
        }
    }

    @Override
    public ArrayList<String> visualizzaPiattaforme() throws LoadingPlatformsException {
        try {
            return piattaformaDAO.doRetrieveAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new LoadingPlatformsException();
        }
    }

    @Override
    public ArrayList<String> visualizzaGeneri() throws LoadingGenresException {
        try {
            return gDAO.doRetrieveAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new LoadingGenresException();
        }
    }

    @Override
    public ArrayList<String> visualizzaNomiVideogiochi() throws LoadingVideogamesException {
        try {
            return vDAO.doRetrieveAllNames();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new LoadingVideogamesException();
        }
    }


}
