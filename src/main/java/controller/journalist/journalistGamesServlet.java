package controller.journalist;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Giornalista;
import model.dao.GiocoDAO;
import model.dao.NotiziaDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "journalistGamesServlet", value = "/journalistGamesServlet")
public class journalistGamesServlet extends HttpServlet {
    private String path = "/WEB-INF/results/giornalista/journalistGames.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("giochiGiornalista", new GiocoDAO().doRetrieveAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
