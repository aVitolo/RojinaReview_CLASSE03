package rojinaReview.autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Videogiocatore;
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
        Videogiocatore u = (Videogiocatore) request.getSession().getAttribute("utente");
        String t = request.getParameter("telefono");
        try {
            new TelefonoDAO().doSave(u.getId(), t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        u.getTelefoni().add(t);

        response.sendRedirect(result);
    }
}
