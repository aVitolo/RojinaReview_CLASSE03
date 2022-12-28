package rojinaReview.controller.autenticazione;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "journalistAreaServlet", value = "/journalistAreaServlet")
public class journalistAreaServlet extends HttpServlet {
    private String path = "/WEB-INF/results/giornalista/journalistProfile.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
