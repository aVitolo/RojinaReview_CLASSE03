package controller.utilities;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.GiocoDAO;
import model.dao.NotiziaDAO;
import model.dao.RecensioneDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "getGameServlet", value = "/getGameServlet")
public class getGameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "/WEB-INF/results/gioco.jsp";
        try {
            request.setAttribute("gioco", new GiocoDAO().doRetrieveByTitle(request.getParameter("name")));
            request.setAttribute("recensione", new RecensioneDAO().doRetrieveIDByGameTitle(request.getParameter("name")));
            request.setAttribute("notizieGioco", new NotiziaDAO().doRetrieveByGameMentioned(request.getParameter("name")));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(result).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
