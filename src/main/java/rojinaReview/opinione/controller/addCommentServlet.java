package rojinaReview.opinione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Commento;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.CommentoDAO;
import rojinaReview.model.exception.InsertOpinionException;
import rojinaReview.opinione.service.OpinioneService;
import rojinaReview.opinione.service.OpinioneServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

@WebServlet(name = "addCommentServlet", value = "/addCommentServlet")
public class addCommentServlet extends HttpServlet {
    private OpinioneService os;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            os = new OpinioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String result = "/Rojina_Review_war/getResource?type=" + request.getParameter("contenuto") + "&" + "id=" + request.getParameter("idContenuto");
        HttpSession session = request.getSession();
        Videogiocatore videogiocatore = (Videogiocatore) session.getAttribute("videogiocatore");

        Commento c = new Commento();
        String commento = request.getParameter("commentText");
        c.setIdVideogiocatore(videogiocatore.getId());
        c.setTesto(request.getParameter("commentText"));
        c.setDataScrittura(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        c.setIdContenuto(Integer.parseInt(request.getParameter("idContenuto")));
        c.setTipo(Integer.parseInt(request.getParameter("type")));

        if (commento.equals("")) {
            System.out.println("A");
            request.getSession(false).setAttribute("error", "commEmpty");
            response.getWriter().write("commEmpty");
            response.sendRedirect(result);
        }
        else if(commento.length()>256){
            request.getSession(false).setAttribute("error", "commLong");
            response.getWriter().write("commLong");
            response.sendRedirect(result);
        }
        else {
            try {
                os.inserisciCommento(c, videogiocatore);
            } catch (InsertOpinionException e) {
                e.printStackTrace();
            }
            response.getWriter().write("commentoOK");
            response.sendRedirect(result);
        }
    }
}
