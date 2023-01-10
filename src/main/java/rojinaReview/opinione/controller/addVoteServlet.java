package rojinaReview.opinione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.beans.Parere;
import rojinaReview.model.dao.ParereDAO;
import rojinaReview.model.exception.InsertOpinionException;
import rojinaReview.opinione.service.OpinioneService;
import rojinaReview.opinione.service.OpinioneServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

@WebServlet(name = "addVoteServlet", value = "/addVoteServlet")
public class addVoteServlet extends HttpServlet {
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
        String result = "/Rojina_Review_war/getResource?type="+request.getParameter("contenuto")+"&"+"id="+
                request.getParameter("idContenuto");
        HttpSession session = request.getSession();
        Videogiocatore videogiocatore = (Videogiocatore) session.getAttribute("videogiocatore");

        Parere parere = new Parere();

        parere.setVoto(Float.parseFloat(request.getParameter("toVoto")));
        parere.setDataVotazione(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        parere.setType(Integer.parseInt(request.getParameter("type")));
        parere.setIdProdottoORVideogioco(Integer.valueOf(request.getParameter("idProdottoORVideogioco")));

        try {
            os.inserisciVoto(parere, videogiocatore);
        } catch (InsertOpinionException e) {
            e.printStackTrace();
        }


        response.sendRedirect(result);
    }
}
