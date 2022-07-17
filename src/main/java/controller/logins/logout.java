package controller.logins;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.beans.Utente;
import model.dao.CarrelloDAO;

import java.io.IOException;
import java.sql.SQLException;

public class logout extends HttpServlet {
    private String redirect = "./home";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getSession(false).getAttribute("utente") != null || request.getSession(false).getAttribute("giornalista") != null || request.getSession(false).getAttribute("admin") != null) {
                if(request.getSession(false).getAttribute("utente") != null){
                    Utente u = (Utente) request.getSession().getAttribute("utente");
                    new CarrelloDAO().doSave(u.getCarrello(), u.getEmail());
                }
                request.getSession().invalidate();
            }
            response.sendRedirect(redirect);
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
