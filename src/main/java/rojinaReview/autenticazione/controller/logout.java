package rojinaReview.autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import rojinaReview.autenticazione.service.AutenticazioneService;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.exception.SavingCartException;

import java.io.IOException;
import java.sql.SQLException;

public class logout extends HttpServlet {
    private AutenticazioneService as;
    private String redirect = "./home";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            as = new AutenticazioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (request.getSession(false).getAttribute("videogiocatore") != null || request.getSession(false).getAttribute("giornalista") != null || request.getSession(false).getAttribute("manager") != null) {
            if(request.getSession(false).getAttribute("videogiocatore") != null){
                Videogiocatore u = (Videogiocatore) request.getSession().getAttribute("videogiocatore");
                try {
                    as.salvaCarrello(u);
                } catch (SavingCartException e) {
                    e.printStackTrace();
                }
            }

            request.getSession().invalidate();
        }

        response.sendRedirect(redirect);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
