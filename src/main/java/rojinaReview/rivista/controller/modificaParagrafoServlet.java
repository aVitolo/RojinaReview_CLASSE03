package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Notizia;
import rojinaReview.model.beans.Paragrafo;
import rojinaReview.utilities.Utils;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "modificaParagrafoServlet", value = "/modificaParagrafoServlet")
@MultipartConfig(maxFileSize = 1024*1024*10)
public class modificaParagrafoServlet extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
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

        if (session.getAttribute("paragrafi") != null) {
            ArrayList<Paragrafo> paragrafi = (ArrayList<Paragrafo>) session.getAttribute("paragrafi");
            Paragrafo paragrafo = paragrafi.get(Integer.parseInt(request.getParameter("id")));
            paragrafo.setTesto(request.getParameter("testo"));
            paragrafo.setTitolo(request.getParameter("titolo"));
            if(request.getPart("immagine").getSize() <= 0)
                paragrafo.setImmagine(null);
            else{
                Part filePart = request.getPart("immagine");
                String imageType = "paragraphs";
                String fileName = paragrafo.getTitolo() + paragrafo.getTesto().substring(0,3)  + ".jpg"; //per evitare tra notizie e recensioni diverse immagini con stesso nome per via dello stesso titolo paragrafo
                paragrafo.setImmagine(Utils.saveImageWar(imageType, fileName, filePart));
                Utils.saveImageFileSystem(imageType, fileName, filePart);
            }

            session.setAttribute("paragrafi", paragrafi);
            response.sendRedirect(path);
        }
    }
}
