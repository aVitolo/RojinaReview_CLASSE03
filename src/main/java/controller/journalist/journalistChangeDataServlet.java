package controller.journalist;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "journalistChangeDataServlet", value = "/journalistChangeDataServlet")
public class journalistChangeDataServlet extends HttpServlet {
    private String path = "/WEB-INF/results/giornalista/giornalistaModificaDati.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
