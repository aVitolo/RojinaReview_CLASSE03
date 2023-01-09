package rojinaReview.autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.autenticazione.service.AutenticazioneService;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.TelefonoDAO;
import rojinaReview.model.exception.InsertNumberException;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "insertNumberServlet", value = "/insertNumberServlet")
public class insertNumberServlet extends HttpServlet {
    private AutenticazioneService as;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            as = new AutenticazioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String result = "/Rojina_Review_war/userInformations";
        Videogiocatore u = (Videogiocatore) request.getSession().getAttribute("videogiocatore");
        String t = request.getParameter("telefono");

        try {
            as.inserisciNumeroTelefonico(t, u);
        } catch (InsertNumberException e) {
            e.printStackTrace();
        }

        response.sendRedirect(result);
    }
}
