package rojinaReview.shop.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.autenticazione.service.AutenticazioneService;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.exception.LoadingAddressesException;
import rojinaReview.model.exception.LoadingPaymentsException;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = " proceedToOrderServlet", value = "/ proceedToOrderServlet")
public class proceedToOrderServlet extends HttpServlet {
    private AutenticazioneService as;

    private String path = "/WEB-INF/results/videogiocatorePages/acquista.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            as = new AutenticazioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        Videogiocatore videogiocatore = (Videogiocatore) session.getAttribute("videogiocatore");
        if(videogiocatore != null)
        {
            try {
                videogiocatore.setIndirizzi(as.visualizzaIndirizzi(videogiocatore));
            } catch (LoadingAddressesException e) {
                e.printStackTrace();
            }
            try {
                videogiocatore.setPagamenti(as.visualizzaMetodiDiPagamento(videogiocatore));
            } catch (LoadingPaymentsException e) {
                e.printStackTrace();
            }
        }

        /*
            Reindirizzo alla pagine per il checkout
         */
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
