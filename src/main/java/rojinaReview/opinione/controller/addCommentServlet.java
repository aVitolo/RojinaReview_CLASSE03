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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            os = new OpinioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String result = "/Rojina_Review_war/getResource?type="+request.getParameter("contenuto")+"&"+"id="+request.getParameter("idContenuto");
        HttpSession session = request.getSession();
        Videogiocatore videogiocatore = (Videogiocatore) session.getAttribute("videogiocatore");

        Commento c = new Commento();
        c.setIdVideogiocatore(videogiocatore.getId());
        c.setTesto(request.getParameter("commentText"));
        c.setDataScrittura(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        c.setIdContenuto(Integer.parseInt(request.getParameter("idContenuto")));
        c.setTipo(Integer.parseInt(request.getParameter("type")));

        try {
            os.inserisciCommento(c, videogiocatore);
        } catch (InsertOpinionException e) {
            e.printStackTrace();
        }

        response.sendRedirect(result);
    }
}
