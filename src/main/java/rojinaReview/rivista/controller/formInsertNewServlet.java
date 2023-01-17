package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Notizia;
import rojinaReview.model.beans.Recensione;

import java.io.IOException;

@WebServlet(name = "formInsertNewServlet", value = "/formInsertNew")
public class formInsertNewServlet extends HttpServlet {
    private String path = "/WEB-INF/results/giornalistaPages/journalistInsertNews.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("recensione");
        session.removeAttribute("paragrafi");
        if(session.getAttribute("notizia")==null)
            session.setAttribute("notizia", new Notizia());
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(path);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
