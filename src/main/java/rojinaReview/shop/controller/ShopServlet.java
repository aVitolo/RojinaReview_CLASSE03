package rojinaReview.shop.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Notizia;
import rojinaReview.model.beans.Prodotto;
import org.json.JSONArray;
import rojinaReview.model.exception.LoadingNewsException;
import rojinaReview.model.exception.LoadingShopException;
import rojinaReview.shop.service.ShopService;
import rojinaReview.shop.service.ShopServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ShopServlet", value = "/ShopServlet")
public class ShopServlet extends HttpServlet {
    private String path = "/WEB-INF/results/mainPage/shop.jsp";

    private ShopService ssi;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ssi = new ShopServiceImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Prodotto> prodotti  = null;
        prodotti = (ArrayList<Prodotto>) ssi.visualizzaShop();
        request.setAttribute("prodotti",prodotti);
        request.setAttribute("articoli", "shop");
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        /*
            Carico dal DB un sottogruppo dei prodotti da far visualizzare
         */
        ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
        try {
            String offset = request.getParameter("offset");
            String categoria = request.getParameter("categoria");
            String ordine = request.getParameter("ordine");
            prodotti = (ArrayList<Prodotto>) ssi.visualizzaShop(categoria, ordine);
        } catch (LoadingShopException e) {
            e.printStackTrace();
        }

        /*
             Se ci sono altri prodotti da mostrare li inivio al CLient tramite JSON
         */
        if(prodotti != null){
            JSONArray json = new JSONArray(prodotti);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json.toString());
        }
        response.getWriter().flush(); //Calling flush() on the PrintWriter commits the response.
    }
}