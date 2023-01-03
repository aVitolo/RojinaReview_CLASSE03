package rojinaReview.shop.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import rojinaReview.model.beans.Carrello;
import rojinaReview.model.beans.Prodotto;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.ProdottoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class addProductCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String out = "0";
        if(request.getParameter("prodottoID") == null || request.getParameter("quantita") == null)
            out = "0";
        else {
            //prendo i dati dal form
            int prodottoID = Integer.parseInt(request.getParameter("prodottoID"));
            int quantità = Integer.parseInt(request.getParameter("quantita"));
            int i;
            boolean trovato = false;

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
            Prodotto p = null;
            Carrello.ProdottoCarrello prodottoCarrello = null;

            //momentaneo, da mandare direttamente prodotto nel form invece che cercarlo nel database
            try {
                p = new ProdottoDAO().doRetrieveById(prodottoID);
                if (p == null) {
                    out = "false";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //vedo se il prodotto-carrello è gia presente nel carrello, se sì aggiorno semplicemente la quantità
            for (i = 0; i < carrello.getProdotti().size() && !trovato; i++)
                if (carrello.getProdotti().get(i).getProdotto().getId() == prodottoID) {
                    trovato = true;
                    prodottoCarrello = carrello.getProdotti().get(i);
                    prodottoCarrello.setQuantità(prodottoCarrello.getQuantità() + quantità);
                }

            if (!trovato) {
                prodottoCarrello = new Carrello.ProdottoCarrello();
                prodottoCarrello.setProdotto(p);
                prodottoCarrello.setQuantità(quantità);
                prodottoCarrello.setPrezzoAttuale(p.getPrezzo());
                carrello.getProdotti().add(prodottoCarrello);
            }


            carrello.setTotale(carrello.getTotale() + p.getPrezzo() * quantità);
            out = String.valueOf(carrello.getProdotti().size());
        }
        response.getWriter().print(out);
    }
}
