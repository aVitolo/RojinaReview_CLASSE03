package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Notizia;
import rojinaReview.model.beans.Recensione;
import rojinaReview.model.dao.RecensioneDAO;
import org.json.JSONArray;
import rojinaReview.model.exception.LoadingNewsException;
import rojinaReview.model.exception.LoadingReviewsException;
import rojinaReview.model.exception.ServiceNotAvailableException;
import rojinaReview.rivista.service.RivistaService;
import rojinaReview.rivista.service.RivistaServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "RecensioneServlet", value = "/RecensioneServlet")
public class RecensioniServlet extends HttpServlet {
    private RivistaServiceImpl rs;
    private String path;

    public RecensioniServlet() {

        path = "/WEB-INF/results/mainPage/recensioni.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            rs = new RivistaServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Recensione> recensioni  = null;
        try {
            recensioni = rs.visualizzaRecensioni();
        } catch (LoadingReviewsException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("recensioni",recensioni);
        request.setAttribute("articoli", "reviews");
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            rs = new RivistaServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Recensione> recensioni = null;
        String offset = request.getParameter("offset");
        String piattafomra = request.getParameter("piattaforma");
        String tipologia = request.getParameter("tipologia");
        String ordine = request.getParameter("ordine");

        try {
            recensioni = rs.visualizzaRecensioni(piattafomra, tipologia, ordine);
        } catch (LoadingReviewsException e) {
            e.printStackTrace();
        }

        if(recensioni != null){
            JSONArray json = new JSONArray(recensioni);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json.toString());
        }
        response.getWriter().flush(); //Calling flush() on the PrintWriter commits the response.
    }
}
