package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Notizia;
import rojinaReview.model.beans.Paragrafo;
import rojinaReview.model.exception.LoadingNewsException;
import rojinaReview.rivista.service.RivistaService;
import rojinaReview.rivista.service.RivistaServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "formModificaNotiziaServlet", value = "/formModificaNotizia")
public class formModificaNotiziaServlet extends HttpServlet {
    private String path;
    private RivistaService rs;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            rs = new RivistaServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        path = "/WEB-INF/results/giornalistaPages/journalistUpdateNews.jsp";
        HttpSession session = request.getSession();

        Notizia notiziaSession = (Notizia) session.getAttribute("notizia");





        Notizia notizia = null;
        if(notiziaSession == null)
        {
            int idNotizia = Integer.parseInt(request.getParameter("id"));
            try {
                notizia = rs.visualizzaNotiziaByID(idNotizia);
            } catch (LoadingNewsException e) {
                e.printStackTrace();
            }

            session.setAttribute("notizia", notizia);
            session.setAttribute("paragrafi", notizia.getParagrafi());
            notiziaSession = notizia;
        }


        session.setAttribute("idArticle", notiziaSession.getId());
        session.setAttribute("type", 2);
        session.setAttribute("update", true);

        request.getRequestDispatcher(path).forward(request, response);
    }


}
