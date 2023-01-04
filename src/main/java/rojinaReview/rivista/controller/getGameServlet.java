package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "getGameServlet", value = "/getGameServlet")
public class getGameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "/WEB-INF/results/recensione.jsp";
        HttpSession session = request.getSession();
        Videogiocatore u = (Videogiocatore) session.getAttribute("utente");
        try {
            int id = new RecensioneDAO().doRetrieveIDByGameTitle(request.getParameter("name"));
            //request.setAttribute("gioco", new GiocoDAO().doRetrieveByTitle(request.getParameter("name")));
            request.setAttribute("recensione", new RecensioneDAO().doRetrieveById(id));
            request.setAttribute("commenti", new CommentoDAO().getCommentById(id, 0));
            request.setAttribute("votoUtente", new ParereDAO().doRetrieveUserOpinion(u.getId(), id, true));
            //request.setAttribute("notizieGioco", new NotiziaDAO().doRetrieveByGameMentioned(request.getParameter("name")));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(result).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
