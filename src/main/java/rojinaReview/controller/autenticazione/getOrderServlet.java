package rojinaReview.controller.autenticazione;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Ordine;
import rojinaReview.model.beans.Utente;

import java.io.IOException;

@WebServlet(name = "getOrderServlet", value = "/getOrderServlet")
public class getOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "/WEB-INF/results/ordine.jsp";
        String home = "./home";
        int id = Integer.parseInt(request.getParameter("id"));
        Utente u = (Utente) request.getSession().getAttribute("utente");
        if(u == null)
            response.sendRedirect(home);

        for (Ordine o : u.getOrdini())
        {
            if(id == o.getId())
            {
                request.setAttribute("ordine", o);
                break;
            }
        }

        if(request.getAttribute("ordine") == null)
            response.sendRedirect(home);
        else
            request.getRequestDispatcher(result).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
