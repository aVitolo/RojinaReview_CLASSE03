package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Paragrafo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

@WebServlet(name = "modificaParagrafoServlet", value = "/modificaParagrafoServlet")
public class modificaParagrafoServlet extends HttpServlet {

    private String path = "/Rojina_Review_war/formInsertReview";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("paragrafi") != null) {
            ArrayList<Paragrafo> paragrafi = (ArrayList<Paragrafo>) session.getAttribute("paragrafi");
            System.out.println(paragrafi.get(0).toString());
            System.out.println();
            Paragrafo paragrafo = paragrafi.get(Integer.parseInt(request.getParameter("id")));
            paragrafo.setTesto(request.getParameter("testo"));
            paragrafo.setTitolo(request.getParameter("titolo"));
            /*
             se non ho specificato la foto non la modifico
             altrimenti faccio lo stesso procedimento dell'insert
             */
            paragrafi.remove(Integer.parseInt(request.getParameter("id")));
            paragrafi.add(paragrafo);
            session.setAttribute("paragrafi", paragrafi);
            response.sendRedirect(path);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
