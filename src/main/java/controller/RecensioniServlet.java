package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Recensione;
import model.dao.NotiziaDAO;
import model.dao.RecensioneDAO;
import org.json.JSONArray;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "RecensioneServlet", value = "/RecensioneServlet")
public class RecensioniServlet extends HttpServlet {
    private String path ="/WEB-INF/results/recensioni.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("articoli","recensione");
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
            int lastID = -1;
            if(request.getParameter("lastID")!=null)
                lastID=Integer.parseInt(request.getParameter("lastID"));
            String reset = request.getParameter("reset");
            String piattafomra = request.getParameter("piattaforma");
            String tipologia = request.getParameter("tipologia");
            String ordine =request.getParameter("ordine");
            recensioni = rDAO.updateContent(lastID,reset, piattafomra, tipologia, ordine);
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
