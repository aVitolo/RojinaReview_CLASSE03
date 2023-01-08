package rojinaReview.opinione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rojinaReview.model.exception.LoadingCommentException;
import rojinaReview.opinione.service.OpinioneServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "visualizzaCommentiSegnalazioniServlet", value = "/visualizzaCommentiSegnalazioniServlet")
public class visualizzaDettagliCommentoSegnalatoServlet extends HttpServlet {

    private String path = "/WEB-INF/results/managerPages/managerDettagliSegnalazione.jsp";
    private OpinioneServiceImpl osi;

    {
        try {
            osi = new OpinioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
