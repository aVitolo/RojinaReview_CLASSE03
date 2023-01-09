package rojinaReview.autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Ordine;
import rojinaReview.model.beans.Videogiocatore;

import java.io.IOException;

@WebServlet(name = "getOrderServlet", value = "/getOrderServlet")
public class getOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "/WEB-INF/results/ordine.jsp";
        String home = "./home";
        int id = Integer.parseInt(request.getParameter("id"));
        Videogiocatore u = (Videogiocatore) request.getSession().getAttribute("videogiocatore");
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
