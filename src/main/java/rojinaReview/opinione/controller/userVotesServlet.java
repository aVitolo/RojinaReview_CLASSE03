package rojinaReview.opinione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.exception.LoadingCommentException;
import rojinaReview.opinione.service.OpinioneService;
import rojinaReview.opinione.service.OpinioneServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "userVotesServlet", value = "/userVotesServlet")
public class userVotesServlet extends HttpServlet {
    private OpinioneService os;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            os = new OpinioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String result = "/WEB-INF/results/videogiocatorePages/userVotes.jsp";
        HttpSession session = request.getSession();
        Videogiocatore videogiocatore = (Videogiocatore) session.getAttribute("videogiocatore");

        try {
            videogiocatore.setCommenti(os.visualizzaCommentiUtente(videogiocatore));
        } catch (LoadingCommentException e) {
            e.printStackTrace();
        }
        try {
            videogiocatore.setPareri(os.visualizzaVotiUtente(videogiocatore));
        } catch (LoadingCommentException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(result).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
