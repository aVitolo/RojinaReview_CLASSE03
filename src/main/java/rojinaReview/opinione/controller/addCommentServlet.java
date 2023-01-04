package rojinaReview.opinione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Commento;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.CommentoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

@WebServlet(name = "addCommentServlet", value = "/addCommentServlet")
public class addCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "/Rojina_Review_war/getResource?type="+request.getParameter("type")+"&"+"id="+request.getParameter("id");
        HttpSession session = request.getSession();
        Videogiocatore u = (Videogiocatore) session.getAttribute("utente");

        Commento c = new Commento();
        c.setIdVideogiocatore(u.getId());
        c.setTesto(request.getParameter("commentText"));
        c.setDataScrittura(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        c.setId(Integer.parseInt(request.getParameter("id")));
        /*
            Integer.parseInt(request.getParameter("type") da modificare con idContenuto
         */
        c.setIdContenuto(Integer.parseInt(request.getParameter("idContenuto")));

        /*
             Ordini dei check provvisorio
         */
        int type = -1;
        if(request.getParameter("idContenuto").equals("prodotto"))
            type = 0;
        else if(request.getParameter("idContenuto").equals("recensione"))
            type = 1;
        else if(request.getParameter("idContenuto").equals("notizia"))
            type = 2;
        else
                throw new RuntimeException();// modificare con Eccezione Personalizzata
        try {
            new CommentoDAO().doSave(c,type);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(result);
    }
}
