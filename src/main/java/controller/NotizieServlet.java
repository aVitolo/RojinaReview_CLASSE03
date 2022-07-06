package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.NotiziaDAO;
import model.beans.Notizia;

import org.json.JSONArray;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "NotizieServlet", value = "/NotizieServlet")
public class NotizieServlet extends HttpServlet {
    private String path ="/WEB-INF/results/notizie.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            int lastID = -1;
            if(request.getParameter("lastID")!=null)
                lastID=Integer.parseInt(request.getParameter("lastID"));
            String reset = request.getParameter("reset");
            String piattafomra = request.getParameter("piattaforma");
            String tipologia = request.getParameter("tipologia");
            String ordine =request.getParameter("ordine");
            notizie = nDAO.updateContent(lastID,reset, piattafomra, tipologia, ordine);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        if(notizie != null){
            JSONArray json = new JSONArray(notizie);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json.toString());
        }
        response.getWriter().flush(); //Calling flush() on the PrintWriter commits the response.
    }
}
