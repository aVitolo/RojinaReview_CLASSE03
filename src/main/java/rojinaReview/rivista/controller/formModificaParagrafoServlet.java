package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Paragrafo;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "formModificaParagrafoServlet", value = "/formModificaParagrafoServlet")
public class formModificaParagrafoServlet extends HttpServlet {

    private String path = "/WEB-INF/results/giornalistaPages/journalistModificaParagrafo.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("paragrafi")!=null) {
            ArrayList<Paragrafo> paragrafi = (ArrayList<Paragrafo>) session.getAttribute("paragrafi");
            Paragrafo paragrafo = paragrafi.get(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("paragrafo",paragrafo);
            request.setAttribute("id",Integer.parseInt(request.getParameter("id")));
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
