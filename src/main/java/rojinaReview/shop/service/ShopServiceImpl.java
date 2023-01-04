package rojinaReview.shop.service;

import rojinaReview.exception.CheckoutException;
import rojinaReview.exception.LoadingOrderException;
import rojinaReview.exception.LoadingShopException;
import rojinaReview.exception.ProductIDMissingException;
import rojinaReview.model.beans.Ordine;
import rojinaReview.model.beans.Prodotto;
import rojinaReview.model.dao.OrdineDAO;
import rojinaReview.model.dao.ProdottoDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopServiceImpl implements  ShopService{

    /*
    Servelet:
    -shop.ShopServlet
    -autenticazione.ShopManagment (visualizzazione Shop lato manager, da sviluppare)
 */
    @Override
    public List<Prodotto> visualizzaShop(String offset, String categoria, String ordine) throws LoadingShopException {
        List<Prodotto> prodotti = null;
        try {
            prodotti = new ProdottoDAO().doRetriveAll();
            return prodotti;
        } catch (SQLException e) {
            throw new LoadingShopException("Errore nel caricamento dei prodotti");
        }
    }

    public List<Prodotto> visualizzaShop() {
        List<Prodotto> prodotti = null;
        try {
            prodotti = new ProdottoDAO().doRetriveAll();
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
            prodotto = new ProdottoDAO().doRetrieveById(id);
            return prodotto;
        } catch (SQLException e) {
            throw new ProductIDMissingException("ID non presente nel DB");
        }
    }

    /*
        -autenticazione.addProduct (aggiunta prodotto allo Shop lato manager, da sviluppare)
     */
    @Override
    public void inserisciProdotto(Prodotto prodotto) throws ProductIDMissingException {
        try {
          new ProdottoDAO().doSave(prodotto);
        } catch (SQLException e) {
            throw new ProductIDMissingException("ID non presente nel DB");
        }
    }

    /*
         -autenticazione.updateProduct (aggiunta prodotto allo Shop lato manager, da sviluppare)
      */
    @Override
    public void modificaProdotto(Prodotto prodotto) throws ProductIDMissingException {
        try {
            new ProdottoDAO().doUpdate(prodotto);
        } catch (SQLException e) {
            throw new ProductIDMissingException("ID non presente nel DB");
        }
    }

    /*
         -autenticazione.removeProduct (aggiunta prodotto allo Shop lato manager, da sviluppare)
      */
    @Override
    public void cancellaProdotto(int id) throws ProductIDMissingException {
        try {
            new ProdottoDAO().doRemoveById(id);
        } catch (SQLException e) {
            throw new ProductIDMissingException("ID non presente nel DB");
        }
    }

    /*
       Servlet:
       -shop.addProductCartServlet
     */
    @Override
    public void aggiungiProdottoAlCarrello(Prodotto prodotto) {

    }

    /*
     Servlet:
     -shop.removeFromCartServlet
     */
    @Override
    public void rimuoviProdottoDalCarrello(Prodotto prodotto) {

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
    public void checkout(Ordine ordine,int idVideogiocatore, ArrayList<Prodotto> prodottiContext) throws CheckoutException {
        try {
            new OrdineDAO().confirmOrder(ordine,idVideogiocatore,prodottiContext);
        } catch (SQLException e) {
            throw new CheckoutException("Errore in fase di checkout");
        }
    }

    /*
       Servlet
       autenticazione.userOrderServlet
     */
    @Override
    public List<Ordine> visualizzaOrdiniEffettuati(int id) throws LoadingOrderException {
        List<Ordine> ordini = null;
        try {
            ordini = new OrdineDAO().doRetrieveUserById(id);
            return ordini;
        } catch (SQLException e) {
            throw new LoadingOrderException("Errore nel caricamento degli ordini");
        }
    }
}