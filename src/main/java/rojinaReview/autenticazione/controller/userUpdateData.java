package rojinaReview.autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.autenticazione.service.AutenticazioneService;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.exception.UpdateDataException;
import rojinaReview.model.utilities.ConPool;
import rojinaReview.model.utilities.Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "userUpdateData", value = "/userUpdateData")
public class userUpdateData extends HttpServlet {
    private AutenticazioneService as;
    private String path = "/WEB-INF/results/videogiocatorePages/utenteModificaDati.jsp";
    private  String homePage = "./home";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            as = new AutenticazioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();

        if (session.getAttribute("videogiocatore") == null)
            response.sendRedirect(homePage);
        else
        {
            Videogiocatore videogiocatore = (Videogiocatore) session.getAttribute("videogiocatore");

            videogiocatore.setEmail(request.getParameter("email"));
            videogiocatore.setPassword(Utils.calcolaHash(request.getParameter("password")));
            videogiocatore.setNickname(request.getParameter("nickname"));
            videogiocatore.setNome(request.getParameter("nome"));
            videogiocatore.setCognome(request.getParameter("cognome"));

            try {
                as.modificaVideogiocatore(videogiocatore);
            } catch (UpdateDataException e) {
                e.printStackTrace();
            }


            request.getRequestDispatcher(path).forward(request, response);



        }
    }
}

