package controller.logins;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Utente;
import model.dao.CarrelloDAO;

import java.io.IOException;
import java.sql.SQLException;

public class logout extends HttpServlet {
    private String redirect = "./home";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession(false).getAttribute("utente") != null || request.getSession(false).getAttribute("giornalista") != null || request.getSession(false).getAttribute("admin") != null) {
            if(request.getSession(false).getAttribute("utente") != null){
                Utente u = (Utente) request.getSession().getAttribute("utente");
                try {
                    new CarrelloDAO().doSave(u.getCarrello(), u.getEmail());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            request.getSession().invalidate();
        }
        response.sendRedirect(redirect);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
