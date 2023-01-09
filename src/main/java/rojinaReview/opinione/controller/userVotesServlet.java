package rojinaReview.opinione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.CommentoDAO;
import rojinaReview.model.dao.ParereDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "userVotesServlet", value = "/userVotesServlet")
public class userVotesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "/WEB-INF/results/utente/userVotes.jsp";
        HttpSession session = request.getSession();
        Videogiocatore u = (Videogiocatore) session.getAttribute("videogiocatore");
        try {
            request.setAttribute("commenti", new CommentoDAO().getCommentByUser(u.getId()));
            request.setAttribute("voti", new ParereDAO().doRetrieveByUser(u.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(result).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
