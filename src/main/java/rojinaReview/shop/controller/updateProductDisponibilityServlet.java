package rojinaReview.shop.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Carrello;
import rojinaReview.model.beans.Prodotto;
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
        /*
            Verifico la presenza dei paremetri id e quantita
            Necessari per aggiornare i dati del carello
         */
        int id=-1;
        int newQuantity=-1;
        if(request.getParameter("id") != null)
            id = Integer.parseInt(request.getParameter("id"));
        if(request.getParameter("quantita") != null)
            newQuantity = Integer.parseInt(request.getParameter("quantita"));
        /*
            Se i paremetri sono presenti proseguo con le operazioni di aggiornamento
         */
        if(id!=-1 && newQuantity!=-1) {
            /*
                -Verifico se il carello appertiene ad un utente loggato o ad un ospite
                -L'attributo ospite della sessione corrisponde ad un entita Carello
             */
            Carrello cart;
            Videogiocatore u = (Videogiocatore) request.getSession().getAttribute("utente");
            if (u != null)
                cart = u.getCarrello();
            else
                cart = (Carrello) request.getSession().getAttribute("ospite");
            /*
                Controllo se il prodotto è presente nel carello
                -Se presente procedo con l'operazioni di aggiornamento
                -Altrimenti dovrei far visualizzare un errore -> lanciare ProductIDMissingException?
                Operazione che dovrebbe svolgerla shopService.modificaProdottoCarello(Prodotto prodotto) non presente nell'interfaccia
             */
            ArrayList<Prodotto> prodotti = cart.getProdotti();
            Prodotto prodottoDaAggiornare = null;
            for (Prodotto p : prodotti)
                if (p.getId() == id) {
                    prodottoDaAggiornare = p;
                    break;
                }
            if (prodottoDaAggiornare != null) {
                cart.setTotale(cart.getTotale()-(prodottoDaAggiornare.getPrezzo()*prodottoDaAggiornare.getQuantità()));
                prodottoDaAggiornare.setQuantità(newQuantity);
                cart.setTotale(cart.getTotale()+(prodottoDaAggiornare.getPrezzo()*prodottoDaAggiornare.getQuantità()));
                resp = String.valueOf(cart.getTotale());
            }
        }
        response.getWriter().print(resp);
    }
}
