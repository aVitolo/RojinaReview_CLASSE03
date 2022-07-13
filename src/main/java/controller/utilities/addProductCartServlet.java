package controller.utilities;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Carrello;
import model.beans.Prodotto;
import model.beans.Utente;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "addProductCartServlet", value = "/addProductCartServlet")
public class addProductCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //prendo i dati dal form
        int prodottoID = Integer.parseInt(request.getParameter("prodottoID"));
        int quantità = Integer.parseInt(request.getParameter("quantità"));
        int i;
        boolean trovato = false;

        String result = "/Rojina_Review_war/getResource?type=shop&id="+prodottoID;
        HttpSession session = request.getSession();
        Carrello carrello;
        if(session.getAttribute("utente") != null){
            Utente u = (Utente) session.getAttribute("utente");
            carrello = u.getCarrello();
        }
        else
            carrello = (Carrello) session.getAttribute("ospite"); //sono loggato come ospite






        Prodotto p = null;
        Carrello.ProdottoCarrello prodottoCarrello= null;

        //momentaneo, da mandare direttamente prodotto nel form invece che cercarlo nel database
        try {
            p = new ProdottoDAO().doRetrieveById(prodottoID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //vedo se il prodotto-carrello è gia presente nel carrello, se sì aggiorno semplicemente la quantità
        for(i = 0; i < carrello.getProdotti().size() && !trovato; i++)
            if(carrello.getProdotti().get(i).getProdotto().getId() == prodottoID)
            {
                trovato = true;
                prodottoCarrello = carrello.getProdotti().get(i);
                prodottoCarrello.setQuantità(prodottoCarrello.getQuantità() + quantità);
            }

        if(!trovato){
            prodottoCarrello = new Carrello.ProdottoCarrello();
            prodottoCarrello.setProdotto(p);
            prodottoCarrello.setQuantità(quantità);
            prodottoCarrello.setPrezzoAttuale(p.getPrezzo());
            carrello.getProdotti().add(prodottoCarrello);
        }


        carrello.setTotale(carrello.getTotale() + p.getPrezzo()*quantità);

        response.sendRedirect(result);
    }
}
