package controller.logins;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

public class logout extends HttpServlet {
    private String redirect = "./home";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession(false).getAttribute("utente") != null || request.getSession(false).getAttribute("giornalista") != null || request.getSession(false).getAttribute("admin") != null) {
            request.getSession().invalidate();
        }
        response.sendRedirect(redirect);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
