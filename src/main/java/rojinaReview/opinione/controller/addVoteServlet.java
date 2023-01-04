package rojinaReview.opinione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.beans.Parere;
import rojinaReview.model.dao.ParereDAO;

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
            Parere p = new Parere();
            p.setIdProdottoORVideogioco(Integer.valueOf(request.getParameter("nomeGioco")));
            /*
            aggiungere se ID videogiocatore sara' inserito nella classe
            p.setUtente(u.getId());
             */
            p.setDataVotazione(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            p.setVoto(Float.parseFloat(request.getParameter("toVoto")));

            /*
            0 e' valore momentanio, sostituito con ID videogiocatore se sara' inserito nella classe
            */
            try {
                new ParereDAO().doSave(p, 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(table.equals("prodotto")){
            Parere vp = new Parere();
            vp.setId(Integer.parseInt(request.getParameter("id")));
            /*
                aggiungere se ID videogiocatore sara' inserito nella classe
                 vp.setUtente(u.getId());
             */
            vp.setDataVotazione(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            vp.setVoto(Float.parseFloat(request.getParameter("toVoto")));

            /*
                0 e' valore momentanio, sostituito con ID videogiocatore se sara' inserito nella classe
            */
            try {
                new ParereDAO().doSave(vp, 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        response.sendRedirect(result);
    }
}
