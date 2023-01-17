package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.beans.Notizia;
import rojinaReview.model.beans.Paragrafo;
import rojinaReview.model.exception.*;
import rojinaReview.utilities.Utils;
import rojinaReview.rivista.service.RivistaService;
import rojinaReview.rivista.service.RivistaServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

@WebServlet(name = "updateNewServlet", value = "/updateNew")
@MultipartConfig(maxFileSize = 1024*1024*10)
public class updateNewServlet extends HttpServlet {
    private RivistaService rs;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            rs = new RivistaServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        String result = "/Rojina_Review_war/journalistNews";
        Giornalista giornalista = (Giornalista) session.getAttribute("giornalista");
        ArrayList<Paragrafo> paragrafi;
        if(session.getAttribute("paragrafi") != null)
            paragrafi = (ArrayList<Paragrafo>) session.getAttribute("paragrafi");
        else
            paragrafi = new ArrayList<>();

        ArrayList<String> allGames = (ArrayList<String>) request.getServletContext().getAttribute("nomiGiochi");


        //notizia da modificare presa dal form
        Notizia notizia = new Notizia();

        notizia.setId(Integer.parseInt(request.getParameter("idNotizia")));
        notizia.setNome(request.getParameter("titolo"));
        notizia.setTesto(request.getParameter("testo"));
        notizia.setDataScrittura(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        notizia.setParagrafi(paragrafi);


        if(request.getPart("immagine").getSize() <= 0)
            notizia.setImmagine(null);
        else
        {
            Part filePart = request.getPart("immagine");
            String imageType = "news";
            String fileName = "new-" + notizia.getNome() + ".jpg";
            notizia.setImmagine(Utils.saveImageWar(imageType, fileName, filePart));
            Utils.saveImageFileSystem(imageType, fileName, filePart);
        }

        String toParse = request.getParameter("videogiochi");
        try {
            notizia.setGiochi(Utils.parseGames(toParse, allGames));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            rs.modificaNotizia(notizia, giornalista);
        } catch (InsertCommentException e) {
            e.printStackTrace();
        } catch (InsertNewException e) {
            e.printStackTrace();
        } catch (InsertParagraphException e) {
            e.printStackTrace();
        } catch (RemovingNewException e) {
            e.printStackTrace();
        } catch (RemovingParagraphsException e) {
            e.printStackTrace();
        } catch (UpdateDataException e) {
            e.printStackTrace();
        } catch (RemovingCommentsException e) {
            e.printStackTrace();
        }

        session.removeAttribute("paragrafi");

        response.sendRedirect(result);

    }
}
