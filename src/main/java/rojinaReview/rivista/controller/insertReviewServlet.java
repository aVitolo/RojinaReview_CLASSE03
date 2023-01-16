package rojinaReview.rivista.controller;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.beans.Paragrafo;
import rojinaReview.model.beans.Recensione;
import rojinaReview.model.dao.RecensioneDAO;
import rojinaReview.model.dao.VideogiocoDAO;
import rojinaReview.model.exception.InsertCommentException;
import rojinaReview.model.exception.InsertParagraphException;
import rojinaReview.model.exception.InsertReviewException;
import rojinaReview.model.utilities.Utils;
import rojinaReview.rivista.service.RivistaService;
import rojinaReview.rivista.service.RivistaServiceImpl;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

@WebServlet(name = "insertReviewServlet", value = "/insertReviewServlet")
@MultipartConfig(maxFileSize = 1024*1024*10)
public class insertReviewServlet extends HttpServlet {
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
        String result = "/Rojina_Review_war/journalistReviews";
        Giornalista giornalista = (Giornalista) request.getSession().getAttribute("giornalista");
        ArrayList<Paragrafo> paragrafi;
        if(session.getAttribute("paragrafi") != null)
            paragrafi = (ArrayList<Paragrafo>) session.getAttribute("paragrafi");
        else
            paragrafi = new ArrayList<>();

        //recensione da inserire presa dal form
        Recensione recensione = new Recensione();

        recensione.setTesto(request.getParameter("testo"));
        recensione.setNome(request.getParameter("titolo"));
        recensione.setVotoGiornalista(Float.parseFloat(request.getParameter("parere")));
        recensione.setDataScrittura(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        recensione.setNomeVideogioco(request.getParameter("videogioco"));
        recensione.setCommenti(new ArrayList<>());
        recensione.setParagrafi(paragrafi);

        if(request.getPart("immagine").getSize() <= 0)
            recensione.setImmagine(null);
        else
        {
            Part filePart = request.getPart("immagine");
            String imageType = "reviews";
            String fileName = "review-" + recensione.getNomeVideogioco() + ".jpg";
            recensione.setImmagine(Utils.saveImageWar(imageType, fileName, filePart));
            Utils.saveImageFileSystem(imageType, fileName, filePart);
        }


        try {
            rs.inserisciRecensione(recensione, giornalista);
        } catch (InsertReviewException e) {
            e.printStackTrace();
        } catch (InsertCommentException e) {
            e.printStackTrace();
        } catch (InsertParagraphException e) {
            e.printStackTrace();
        }

        session.removeAttribute("paragrafi");

        response.sendRedirect(result);
    }

}
