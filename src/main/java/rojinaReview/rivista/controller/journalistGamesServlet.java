package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.dao.VideogiocoDAO;
import rojinaReview.model.exception.LoadingVideogamesException;
import rojinaReview.model.exception.ServiceNotAvailableException;
import rojinaReview.rivista.service.RivistaService;
import rojinaReview.rivista.service.RivistaServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "journalistGamesServlet", value = "/journalistGamesServlet")
public class journalistGamesServlet extends HttpServlet {
    private RivistaService rs;
    private String path;

    public journalistGamesServlet() {
        path = "/WEB-INF/results/giornalista/journalistGames.jsp";;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            rs = new RivistaServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            request.setAttribute("giochiGiornalista", rs.visualizzaVideogiochi());
        } catch (LoadingVideogamesException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
