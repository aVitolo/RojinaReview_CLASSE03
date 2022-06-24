package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Notizia;
import model.beans.Recensione;
import model.dao.NotiziaDAO;
import model.dao.RecensioneDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Clock;
import java.util.ArrayList;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {
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

        request.setAttribute("copertina",copertina);
        request.setAttribute("notizie", notizie);
        request.setAttribute("recensioni", recensioni);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/results/home.jsp");
        dispatcher.forward(request, response);

    }
}
