package rojinaReview.opinione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.ParereGioco;
import rojinaReview.model.beans.ParereProdotto;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.VotoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

@WebServlet(name = "addVoteServlet", value = "/addVoteServlet")
public class addVoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "/Rojina_Review_war/getResource?type="+request.getParameter("type")+"&"+"id="+
                request.getParameter("id")+"&"+"searchDB=true";
        HttpSession session = request.getSession();
        Videogiocatore u = (Videogiocatore) session.getAttribute("utente");
        String table = request.getParameter("table");

        if(table.equals("gioco")){
            ParereGioco vg = new ParereGioco();
            vg.setGioco(request.getParameter("nomeGioco"));
            vg.setUtente(u.getEmail());
            vg.setDataVotazione(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            vg.setVoto(Float.parseFloat(request.getParameter("toVoto")));

            try {
                new VotoDAO().doSave(vg, table);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(table.equals("prodotto")){
            ParereProdotto vp = new ParereProdotto();
            vp.setId(Integer.parseInt(request.getParameter("id")));
            vp.setUtente(u.getEmail());
            vp.setDataVotazione(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            vp.setVoto(Float.parseFloat(request.getParameter("toVoto")));

            try {
                new VotoDAO().doSave(vp, table);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        response.sendRedirect(result);
    }
}
