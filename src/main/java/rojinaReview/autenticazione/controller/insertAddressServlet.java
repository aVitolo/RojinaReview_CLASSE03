package rojinaReview.autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Indirizzo;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.IndirizzoDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "insertAddressServlet", value = "/insertAddressServlet")
public class insertAddressServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "/Rojina_Review_war/userInformations";
        Videogiocatore u = (Videogiocatore) request.getSession().getAttribute("utente");
        Indirizzo i = new Indirizzo();

        i.setVia(request.getParameter("via"));
        i.setNumeroCivico(Integer.parseInt(request.getParameter("numeroCivico")));
        i.setCittà(request.getParameter("città"));
        i.setCap(request.getParameter("cap"));

        try {
            new IndirizzoDAO().doSave(u.getId(), i);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        u.getIndirizzi().add(i);

        response.sendRedirect(result);
    }
}
