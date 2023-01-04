package rojinaReview.rivista.controller;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.beans.Recensione;
import rojinaReview.model.dao.RecensioneDAO;
import rojinaReview.model.dao.VideogiocoDAO;
import rojinaReview.model.utilities.Utils;

import java.io.*;
import java.sql.SQLException;
import java.util.Calendar;

@WebServlet(name = "insertReviewServlet", value = "/insertReviewServlet")
@MultipartConfig
public class insertReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "/Rojina_Review_war/journalistReviews";
        Giornalista g = (Giornalista) request.getSession().getAttribute("giornalista");
        String nomeG = g.getNome() + " " + g.getCognome();

        //recensione da inserire presa dal form
        Recensione r = new Recensione();
        r.setTesto(request.getParameter("testo"));

        try {
            r.setIdVideogioco(new VideogiocoDAO().doRetrieveIdByTitle(request.getParameter("gioco")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        r.setNome(request.getParameter("titolo"));
        r.setVotoGiornalista(Float.parseFloat(request.getParameter("voto")));
        r.setDataScrittura(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        Part filePart = request.getPart("immagine");
        String imageType = "reviews";
        String fileName = "review-" + "gioco" + ".jpg";
        r.setImmagine(Utils.saveImageWar(imageType, fileName, filePart));
        Utils.saveImageFileSystem(imageType, fileName, filePart);

        try {
            new RecensioneDAO().doSave(r, g.getId(),r.getIdVideogioco());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(result);
    }

}
