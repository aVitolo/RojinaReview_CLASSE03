package controller.utilities;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Carrello;
import model.beans.Utente;

import java.io.IOException;
import java.util.ArrayList;

public class removeFromCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resp="-1";
        int id=-1;
        if(request.getParameter("id") != null) {
            Carrello cart;
            id = Integer.parseInt(request.getParameter("id"));
            Utente u = (Utente) request.getSession().getAttribute("utente");
            if (u != null)
                cart = u.getCarrello();
            else
                cart = (Carrello) request.getSession().getAttribute("ospite");
            ArrayList<Carrello.ProdottoCarrello> prodotti = cart.getProdotti();
            Carrello.ProdottoCarrello r = null;
            for (Carrello.ProdottoCarrello p : prodotti)
                if (p.getProdotto().getId() == id) {
                    r = p;
                    break;
                }
            if(r!= null) {
                prodotti.remove(r);
                cart.setTotale(cart.getTotale()-(r.getPrezzoAttuale()*r.getQuantità()));
                resp = String.valueOf(cart.getTotale());
                System.out.println(resp);
            }
        }
        response.getWriter().print(resp);
    }
}
