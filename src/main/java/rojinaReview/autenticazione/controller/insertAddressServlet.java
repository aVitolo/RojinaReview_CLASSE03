package rojinaReview.autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.autenticazione.service.AutenticazioneService;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.beans.Indirizzo;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.IndirizzoDAO;
import rojinaReview.model.exception.InsertAddressException;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "insertAddressServlet", value = "/insertAddressServlet")
public class insertAddressServlet extends HttpServlet {
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
        Indirizzo i = new Indirizzo();

        i.setVia(request.getParameter("via"));
        i.setNumeroCivico(Integer.parseInt(request.getParameter("numeroCivico")));
        i.setCittà(request.getParameter("città"));
        i.setCap(request.getParameter("cap"));

        try {
            as.inserisciIndrizzo(i, u);
        } catch (InsertAddressException e) {
            e.printStackTrace();
        }

        response.sendRedirect(result);
    }
}
