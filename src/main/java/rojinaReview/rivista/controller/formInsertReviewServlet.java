package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Paragrafo;
import rojinaReview.model.beans.Recensione;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "formInsertReviewServlet", value = "/formInsertReviewServlet")
public class formInsertReviewServlet extends HttpServlet {
    private String path = "/WEB-INF/results/giornalistaPages/journalistInsertReviews.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("notizia");
        session.removeAttribute("paragrafi");
        if(session.getAttribute("recensione")==null)
            session.setAttribute("recensione", new Recensione());
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
