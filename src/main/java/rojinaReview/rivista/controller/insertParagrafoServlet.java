package rojinaReview.rivista.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import rojinaReview.model.beans.Notizia;
import rojinaReview.model.beans.Paragrafo;
import rojinaReview.model.utilities.Utils;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "insertParagrafoServlet", value = "/insertParagrafoServlet")
@MultipartConfig(maxFileSize = 1024*1024*10)
public class insertParagrafoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("paragrafi")==null)
            session.setAttribute("paragrafi", new ArrayList<Paragrafo>());
        ArrayList<Paragrafo> paragrafi = ( ArrayList<Paragrafo>) session.getAttribute("paragrafi");
        String path = null;
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

        Paragrafo p = new Paragrafo();
        p.setTitolo(request.getParameter("titolo"));
        p.setTesto(request.getParameter("testo"));

        if(request.getPart("immagine").getSize() <= 0)
            p.setImmagine(null);
        else{
            Part filePart = request.getPart("immagine");
            String imageType = "paragraphs";
            String fileName = p.getTitolo() + p.getTesto().substring(0,3)  + ".jpg"; //per evitare tra notizie e recensioni diverse immagini con stesso nome per via dello stesso titolo paragrafo
            p.setImmagine(Utils.saveImageWar(imageType, fileName, filePart));
            Utils.saveImageFileSystem(imageType, fileName, filePart);
        }

        paragrafi.add(p);
        session.setAttribute("paragrafi", paragrafi);

        response.sendRedirect(path);
    }
}
