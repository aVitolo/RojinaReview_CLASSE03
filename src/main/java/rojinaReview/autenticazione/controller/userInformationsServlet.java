package rojinaReview.autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.autenticazione.service.AutenticazioneService;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.exception.LoadingAddressesException;
import rojinaReview.model.exception.LoadingNumbersException;
import rojinaReview.model.exception.LoadingPaymentsException;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "userInformationsServlet", value = "/userInformationsServlet")
public class userInformationsServlet extends HttpServlet {
    private AutenticazioneService as;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            as = new AutenticazioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String result = "/WEB-INF/results/videogiocatorePages/userInformations.jsp";
        HttpSession session = request.getSession();
        Videogiocatore videogiocatore = (Videogiocatore) session.getAttribute("videogiocatore");

        try {
            videogiocatore.setIndirizzi(as.visualizzaIndirizzi(videogiocatore));
        } catch (LoadingAddressesException e) {
            e.printStackTrace();
        }
        try {
            videogiocatore.setTelefoni(as.visualizzaNumeriTelefonici(videogiocatore));
        } catch (LoadingNumbersException e) {
            e.printStackTrace();
        }
        try {
            videogiocatore.setPagamenti(as.visualizzaMetodiDiPagamento(videogiocatore));
        } catch (LoadingPaymentsException e) {
            e.printStackTrace();
        }


        request.getRequestDispatcher(result).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
