package rojinaReview.controller.autenticazione;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Telefono;
import rojinaReview.model.beans.Utente;
import rojinaReview.model.dao.TelefonoDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "insertNumberServlet", value = "/insertNumberServlet")
public class insertNumberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "/Rojina_Review_war/userInformations";
        Utente u = (Utente) request.getSession().getAttribute("utente");
        Telefono t = new Telefono(request.getParameter("telefono"));
        try {
            new TelefonoDAO().doSave(u.getEmail(), t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        u.getTelefoni().add(t);

        response.sendRedirect(result);
    }
}
