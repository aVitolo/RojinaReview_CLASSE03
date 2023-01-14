package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.beans.Notizia;
import rojinaReview.model.beans.Paragrafo;
import rojinaReview.model.dao.NotiziaDAO;
import rojinaReview.model.dao.VideogiocoDAO;
import rojinaReview.model.exception.InsertCommentException;
import rojinaReview.model.exception.InsertNewException;
import rojinaReview.model.exception.InsertParagraphException;
import rojinaReview.model.utilities.Utils;
import rojinaReview.rivista.service.RivistaService;
import rojinaReview.rivista.service.RivistaServiceImpl;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "insertNewServlet", value = "/insertNew")
@MultipartConfig(maxFileSize = 1024*1024*10)
public class insertNewServlet extends HttpServlet {
    private RivistaService rs;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            rs = new RivistaServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        String result = "/Rojina_Review_war/journalistNews";
        Giornalista giornalista = (Giornalista) request.getSession().getAttribute("giornalista");
        ArrayList<Paragrafo> paragrafi;
        if(session.getAttribute("paragrafi") != null)
            paragrafi = (ArrayList<Paragrafo>) session.getAttribute("paragrafi");
        else
            paragrafi = new ArrayList<>();

        ArrayList<String> allGames = (ArrayList<String>) request.getServletContext().getAttribute("nomiGiochi");


        //notizia da inserire presa dal form
        Notizia notizia = new Notizia();

        notizia.setNome(request.getParameter("titolo"));
        notizia.setTesto(request.getParameter("testo"));
        notizia.setDataScrittura(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        notizia.setCommenti(new ArrayList<>());
        notizia.setParagrafi(paragrafi);
        for(Paragrafo p : notizia.getParagrafi())
            System.out.println(p.getTitolo()+" "+p.getTesto());

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
            for(Map.Entry<Integer, String> set : notizia.getGiochi().entrySet())
                System.out.println(set.getKey()+" "+set.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            rs.inserisciNotizia(notizia, giornalista);
        } catch (InsertCommentException e) {
            e.printStackTrace();
        } catch (InsertNewException e) {
            e.printStackTrace();
        } catch (InsertParagraphException e) {
            e.printStackTrace();
        }

        session.removeAttribute("paragrafi");

        response.sendRedirect(result);

    }



}
