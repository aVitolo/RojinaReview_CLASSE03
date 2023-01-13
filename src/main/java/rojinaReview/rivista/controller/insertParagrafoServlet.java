package rojinaReview.rivista.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import rojinaReview.model.beans.Paragrafo;
import rojinaReview.model.utilities.Utils;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "insertParagrafoServlet", value = "/insertParagrafoServlet")
@MultipartConfig
public class insertParagrafoServlet extends HttpServlet {
    private String path = "/Rojina_Review_war/formInsertReview";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if(session.getAttribute("paragrafi")==null)
            session.setAttribute("paragrafi", new ArrayList<Paragrafo>());
        ArrayList<Paragrafo> paragrafi = ( ArrayList<Paragrafo>) session.getAttribute("paragrafi");

        Paragrafo p = new Paragrafo();
        p.setTitolo(request.getParameter("titolo"));
        p.setTesto(request.getParameter("testo"));
        /*
        Part filePart = request.getPart("foto");
        String imageType = "products";
        String fileName = p.getTitolo() + ".jpg";
        p.setImmagine(Utils.saveImageWar(imageType, fileName, filePart));
         */
        paragrafi.add(p);
        session.setAttribute("paragrafi", paragrafi);
        response.sendRedirect(path);
    }
}
