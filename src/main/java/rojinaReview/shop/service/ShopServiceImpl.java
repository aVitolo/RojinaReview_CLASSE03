package rojinaReview.shop.service;

import rojinaReview.model.beans.Carrello;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.*;
import rojinaReview.model.exception.*;
import rojinaReview.model.beans.Ordine;
import rojinaReview.model.beans.Prodotto;
import rojinaReview.utilities.ConPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopServiceImpl implements  ShopService{

    private Connection connection;
    private ProdottoDAO pDAO;
    private OrdineDAO oDAO;
    private CategoriaDAO cDAO;
    private CommentoDAO commentoDAO;

    public ShopServiceImpl() throws SQLException {
        this.connection = ConPool.getConnection();
        pDAO = new ProdottoDAO(this.connection);
        oDAO = new OrdineDAO(this.connection);
        cDAO = new CategoriaDAO(this.connection);
        commentoDAO = new CommentoDAO(this.connection);
    }

    /*
    Servelet:
    -shop.ShopServlet
    -shop.ShopManagment (visualizzazione Shop lato manager, da sviluppare)
 */
    @Override
    public List<Prodotto> visualizzaShop(String categoria, String ordine) throws LoadingShopException {
        List<Prodotto> prodotti = null;
        try {
            prodotti = pDAO.updateContent(categoria,ordine);
            return prodotti;
        } catch (SQLException e) {
            throw new LoadingShopException("Errore nel caricamento dei prodotti");
        }
    }

    public List<Prodotto> visualizzaShop() {
        List<Prodotto> prodotti = null;
        try {
            prodotti = pDAO.doRetriveAll();
        } catch (SQLException e) {
           /*
               Possibile Eccezione?
            */
        }
        return prodotti;
    }

    /*
        Servlet
        -utilities.getSingleResourceServlet
     */
    @Override
    public Prodotto visualizzaProdotto(int id) throws ProductIDMissingException {
        Prodotto prodotto = null;
        try {
            prodotto = pDAO.doRetrieveById(id);
            prodotto.setCommenti(commentoDAO.getCommentByContentId(prodotto.getId(), 0));
            return prodotto;
        } catch (SQLException e) {
            throw new ProductIDMissingException("ID non presente nel DB");
        }
    }

    /*
        -shop.addProduct (aggiunta prodotto allo Shop lato manager, da sviluppare)
     */
    @Override
    public void inserisciProdotto(Prodotto prodotto) throws ProductIDMissingException {
        try {
            pDAO.doSave(prodotto);
        } catch (SQLException e) {
            throw new ProductIDMissingException("ID non presente nel DB");
        }
    }

    /*
         -shop.updateProduct (aggiunta prodotto allo Shop lato manager, da sviluppare)
      */
    @Override
    public void modificaProdotto(Prodotto prodotto) throws ProductIDMissingException {
        try {
            pDAO.doUpdate(prodotto);
        } catch (SQLException e) {
            throw new ProductIDMissingException("ID non presente nel DB");
        }
    }

    /*
         -shop.removeProduct (aggiunta prodotto allo Shop lato manager, da sviluppare)
      */
    @Override
    public void cancellaProdotto(int id) throws ProductIDMissingException {
        try {
           pDAO.doRemoveById(id);
        } catch (SQLException e) {
            throw new ProductIDMissingException("ID non presente nel DB");
        }
    }

    /*
       Servlet:
       -shop.addProductCartServlet
     */
    @Override
    public void aggiungiProdottoAlCarrello(Prodotto prodotto, Carrello carrello) {
        int quantitàAggiunta = prodotto.getQuantità();
        boolean trovato = false;
        for (int i = 0; i < carrello.getProdotti().size() && !trovato; i++)
            if (carrello.getProdotti().get(i).getId() == prodotto.getId()) {
                trovato = true;
                carrello.getProdotti().get(i).setQuantità(carrello.getProdotti().get(i).getQuantità() + quantitàAggiunta);
            }
        if (!trovato)
            carrello.getProdotti().add(prodotto);

        carrello.setTotale(carrello.getTotale() + prodotto.getPrezzo() * quantitàAggiunta);
    }

    /*
     Servlet:
     -shop.removeFromCartServlet
     */
    @Override
    public void rimuoviProdottoDalCarrello(Prodotto prodotto, Carrello carrello) {
        ArrayList<Prodotto> prodotti = carrello.getProdotti();
        for (Prodotto p : prodotti)
            if (p.getId() == prodotto.getId()) {
                prodotto = p;
                break;
            }

        prodotti.remove(prodotto);
        carrello.setTotale(carrello.getTotale()-(prodotto.getPrezzo()*prodotto.getQuantità()));
    }
    /*
    Servlet:
     -shop.proceedToOrderServlet
         Modifico il paramentro Carello carello in
         Ordine ordine,
         int idVideogiocatore,
         ArrayList<Prodotto> prodottiContext
     */
    @Override
    public void checkout(Ordine ordine, Videogiocatore videogiocatore) throws CheckoutException {
        try {
            oDAO.confirmOrder(ordine,videogiocatore.getId());
            videogiocatore.getOrdini().add(ordine);
        } catch (SQLException e) {
            throw new CheckoutException("Errore in fase di checkout");
        }
    }

    /*
       Servlet
       autenticazione.userOrderServlet
     */
    @Override
    public ArrayList<Ordine> visualizzaOrdiniEffettuati(Videogiocatore videogiocatore) throws LoadingOrderException {
        try {
            return oDAO.doRetrieveByUserId(videogiocatore.getId());
        } catch (SQLException e) {
            throw new LoadingOrderException("Errore nel caricamento degli ordini");
        }
    }

    @Override
    public ArrayList<String> visualizzaCategorie() throws LoadingCategoriesException {
        try {
            return cDAO.doRetrieveAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new LoadingCategoriesException();
        }
    }
}