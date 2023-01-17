package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Notizia;
import rojinaReview.model.beans.Paragrafo;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "rimuoviParagrafoServlet", value = "/rimuoviParagrafoServlet")
public class rimuoviParagrafoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = null;
        HttpSession session = request.getSession();
        if(session.getAttribute("recensione") != null)
            path = "/Rojina_Review_war/formInsertReview";
        else if(session.getAttribute("notizia") != null)
            if(!(boolean) session.getAttribute("update"))
                path = "/Rojina_Review_war/formInsertNew";
            else
            {
                Notizia notizia = (Notizia) session.getAttribute("notizia");
                path = "/Rojina_Review_war/formModificaNotizia?id=" + notizia.getId();
            }

        if(session.getAttribute("paragrafi")!=null) {
            ArrayList<Paragrafo> paragrafi = (ArrayList<Paragrafo>) session.getAttribute("paragrafi");
            paragrafi.remove(Integer.parseInt(request.getParameter("id")));
            session.setAttribute("paragrafi", paragrafi);
            response.sendRedirect(path);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
