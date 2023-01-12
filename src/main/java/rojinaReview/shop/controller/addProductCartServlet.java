package rojinaReview.shop.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.exception.ProductIDMissingException;
import rojinaReview.model.beans.Carrello;
import rojinaReview.model.beans.Prodotto;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.shop.service.ShopService;
import rojinaReview.shop.service.ShopServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class addProductCartServlet extends HttpServlet {

    private ShopService ssi;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ssi = new ShopServiceImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*
            String out indice il numero di prodotti nel carello?
         */
        String out = "0";
        /*
            Se i parametri della richiesta "prodottoID" == null o "quantita" == null  la richiesta non è valida
         */
        if(request.getParameter("prodottoID") == null || request.getParameter("quantita") == null)
            out = "0";
        else
        {
            /*
                -Verifico se il carello appertiene ad un utente loggato o ad un ospite
                -L'attributo ospite della sessione corrisponde ad un entita Carello
             */
            HttpSession session = request.getSession();
            Carrello carrello;
            if (session.getAttribute("videogiocatore") != null) {
                Videogiocatore u = (Videogiocatore) session.getAttribute("videogiocatore");
                carrello = u.getCarrello();
            } else {
                if (session.getAttribute("ospite") == null) {
                    session.setAttribute("ospite", new Carrello(new ArrayList<>(), 0));
                }
                carrello = (Carrello) session.getAttribute("ospite"); //sono loggato come ospite
            }

            /*
                -Cerco se il prodotto è presente nel DataBase -> shopServiceImpl.visualizzaProdotto(int id);
                -Se il prodotto non e' presente lancio eccezione personalizzata, productIDMissingException;
             */
            int prodottoID = Integer.parseInt(request.getParameter("prodottoID"));
            Prodotto p = null;
            try {
                p = ssi.visualizzaProdotto(prodottoID);
                if (p == null) {
                    out = "false";
                }
            } catch (ProductIDMissingException e) {
                /*
                    Segnalare errore all'utente
                 */
                e.printStackTrace();
            }

            /*
                Controllo se il prodotto è gia presente nel carrello
                    se sì aggiorno semplicemente la quantità
                    altrimenti lo aggiungo al carelllo e setto la quantità
                Da documentazione questa operazione dovrebbe svolgerla shopServicea.ggiungiProdottoAlCarrello(Prodotto prodotto)
             */
            int quantità = Integer.parseInt(request.getParameter("quantita"));
            p.setQuantità(quantità);
            ssi.aggiungiProdottoAlCarrello(p, carrello);


            out = String.valueOf(carrello.getProdotti().size());
        }

        /*
        Invio la nuova dimnesione del carello alla jps per modificare la pagina HTML?
         */
        response.getWriter().print(out);
    }

}
