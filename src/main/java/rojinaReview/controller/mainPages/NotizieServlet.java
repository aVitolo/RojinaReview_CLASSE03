package rojinaReview.controller.mainPages;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.dao.NotiziaDAO;
import rojinaReview.model.beans.Notizia;

import org.json.JSONArray;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "NotizieServlet", value = "/NotizieServlet")
public class NotizieServlet extends HttpServlet {
    private String path = "/WEB-INF/results/mainPage/notizie.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("articoli","news");
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Notizia> notizie = new ArrayList<Notizia>();
        NotiziaDAO nDAO = null;

        try {
            nDAO = new NotiziaDAO();
            String offset = request.getParameter("offset");
            String piattafomra = request.getParameter("piattaforma");
            String tipologia = request.getParameter("tipologia");
            String ordine = request.getParameter("ordine");
            notizie = nDAO.updateContent(offset, piattafomra, tipologia, ordine);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        if (notizie != null) {
            JSONArray json = new JSONArray(notizie);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json.toString());
        }
        response.getWriter().flush(); //Calling flush() on the PrintWriter commits the response.
    }
}
