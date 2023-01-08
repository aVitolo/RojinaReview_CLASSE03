package rojinaReview.shop.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.exception.ProductIDMissingException;
import rojinaReview.model.beans.Carrello;
import rojinaReview.model.beans.Prodotto;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.shop.service.ShopServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class addProductCartServlet extends HttpServlet {

    private ShopServiceImpl ssi;
    {
        try {
            ssi = new ShopServiceImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
            String out indice il numero di prodotti nel carello?
         */
        String out = "0";
        /*
            Se i parametri della richiesta "prodottoID" == null o "quantita" == null  la richiesta non è valida
         */
        if(request.getParameter("prodottoID") == null || request.getParameter("quantita") == null)
            out = "0";
        else {
            /*
                -Verifico se il carello appertiene ad un utente loggato o ad un ospite
                -L'attributo ospite della sessione corrisponde ad un entita Carello
             */
            HttpSession session = request.getSession();
            Carrello carrello;
            if (session.getAttribute("utente") != null) {
                Videogiocatore u = (Videogiocatore) session.getAttribute("utente");
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
                p = ssi.visualizzaProdotto (prodottoID);
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
            boolean trovato = false;
            int quantità = Integer.parseInt(request.getParameter("quantita"));
            for (int i = 0; i < carrello.getProdotti().size() && !trovato; i++)
                if (carrello.getProdotti().get(i).getId() == prodottoID) {
                    trovato = true;
                    carrello.getProdotti().get(i).setQuantità(carrello.getProdotti().get(i).getQuantità() + quantità);
                }

            if (!trovato) {
                p.setQuantità(quantità);
                carrello.getProdotti().add(p);
            }

            /*
                Aggiorno i dati del carello
             */
            carrello.setTotale(carrello.getTotale() + p.getPrezzo() * quantità);
            out = String.valueOf(carrello.getProdotti().size());
        }
        /*
        Invio la nuova dimnesione del carello alla jps per modificare la pagina HTML?
         */
        response.getWriter().print(out);
    }
}
