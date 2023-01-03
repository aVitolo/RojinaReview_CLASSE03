package rojinaReview.shop.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Carrello;
import rojinaReview.model.beans.Videogiocatore;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "updateProductDisponibilityServlet", value = "/updateProductDisponibilityServlet")
public class updateProductDisponibilityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resp="-1";
        int id=-1;
        int newQuantity=-1;
        if(request.getParameter("id") != null)
            id = Integer.parseInt(request.getParameter("id"));
        if(request.getParameter("quantita") != null)
            newQuantity = Integer.parseInt(request.getParameter("quantita"));
        if(id!=-1 && newQuantity!=-1) {
            Carrello cart;
            Videogiocatore u = (Videogiocatore) request.getSession().getAttribute("utente");
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
            if (r != null) {
                cart.setTotale(cart.getTotale()-(r.getPrezzoAttuale()*r.getQuantità()));
                r.setQuantità(newQuantity);
                cart.setTotale(cart.getTotale()+(r.getPrezzoAttuale()*r.getQuantità()));
                resp = String.valueOf(cart.getTotale());
            }
        }
        response.getWriter().print(resp);
    }
}
