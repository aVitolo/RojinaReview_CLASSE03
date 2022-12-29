package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Recensione;
import rojinaReview.model.dao.RecensioneDAO;
import org.json.JSONArray;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "RecensioneServlet", value = "/RecensioneServlet")
public class RecensioniServlet extends HttpServlet {
    private String path = "/WEB-INF/results/mainPage/recensioni.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("articoli","reviews");
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Recensione> recensioni = new ArrayList<Recensione>();
        RecensioneDAO rDAO = null;

        try {
            rDAO = new RecensioneDAO();
            String offset = request.getParameter("offset");
            String piattafomra = request.getParameter("piattaforma");
            String tipologia = request.getParameter("tipologia");
            String ordine = request.getParameter("ordine");
            recensioni = rDAO.updateContent(offset, piattafomra, tipologia, ordine);
                   } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        if(recensioni != null){
            JSONArray json = new JSONArray(recensioni);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json.toString());
        }
        response.getWriter().flush(); //Calling flush() on the PrintWriter commits the response.
    }
}
