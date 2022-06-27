package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.utilities.Articolo;
import model.beans.Notizia;
import model.beans.Recensione;
import model.dao.NotiziaDAO;
import model.dao.RecensioneDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {
    private String jspPath ="/WEB-INF/results/home.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Notizia> notizie;
        ArrayList<Recensione> recensioni;

        NotiziaDAO nDAO;
        RecensioneDAO rDAO;

        try {
            nDAO = new NotiziaDAO();
            notizie = nDAO.doRetrieveLast();
        } catch (SQLException e) {
            System.out.println("News Error");
            throw new RuntimeException(e);
        }


        try {
            rDAO = new RecensioneDAO();
            recensioni = rDAO.doRetrieveLast();
        } catch (SQLException e) {
            System.out.println("Reviews ERROR");
            throw new RuntimeException(e);
        }

        Notizia copertina = notizie.remove(0);
        ArrayList<Articolo> articoli = new ArrayList<>();
        articoli.addAll(notizie);
        articoli.addAll(recensioni);
        articoli.sort(Comparator.comparing(a -> a.getDataCaricamento()));

        request.setAttribute("copertina",copertina);
        request.setAttribute("articoli",articoli);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(jspPath);
        dispatcher.forward(request, response);
    }
}
