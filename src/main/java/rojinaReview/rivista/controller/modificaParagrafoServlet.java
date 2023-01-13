package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Paragrafo;
import rojinaReview.model.utilities.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

@WebServlet(name = "modificaParagrafoServlet", value = "/modificaParagrafoServlet")
@MultipartConfig(maxFileSize = 1024*1024*10)
public class modificaParagrafoServlet extends HttpServlet {

    private String path = "/Rojina_Review_war/formInsertReview";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("paragrafi") != null) {
            ArrayList<Paragrafo> paragrafi = (ArrayList<Paragrafo>) session.getAttribute("paragrafi");
            System.out.println(paragrafi.get(0).toString());
            System.out.println();
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
