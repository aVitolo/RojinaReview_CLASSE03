package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.*;
import rojinaReview.model.dao.VideogiocatoreDAO;
import rojinaReview.model.dao.PiattaformaDAO;
import rojinaReview.model.dao.TipologiaDAO;
import rojinaReview.model.dao.VideogiocoDAO;
import rojinaReview.model.exception.InsertVideogameException;
import rojinaReview.model.exception.ServiceNotAvailableException;
import rojinaReview.model.utilities.Utils;
import rojinaReview.rivista.service.RivistaService;
import rojinaReview.rivista.service.RivistaServiceImpl;

import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet(name = "insertGameServlet", value = "/insertGameServlet")
@MultipartConfig
public class insertGameServlet extends HttpServlet {
    private RivistaService rs;
    private String path;

    public insertGameServlet() throws ServiceNotAvailableException {
        try {
            rs = new RivistaServiceImpl();
        } catch (SQLException e) {
            throw new ServiceNotAvailableException("Errore nel servizio rivista");
        }
        path = "/Rojina_Review_war/journalistGames";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        ArrayList<String> generi = (ArrayList<String>) context.getAttribute("generi");
        ArrayList<String> piattaforme = (ArrayList<String>) context.getAttribute("piattaforme");

        //gioco da inserire preso dal form
        Videogioco videogioco = new Videogioco();
        videogioco.setTitolo(request.getParameter("titolo"));
        videogioco.setCasaDiSviluppo(request.getParameter("casaDiSviluppo"));
        try {
            String data = request.getParameter("dataDiRilascio");
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dataParsata = parser.parse(data);
            java.sql.Date dataSQLParsata = new java.sql.Date(dataParsata.getTime());
            videogioco.setDataDiRilascio(dataSQLParsata);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        videogioco.setGeneri(new ArrayList<String>());
        for (String g : generi)
            if (g.equalsIgnoreCase(request.getParameter(g)))
                videogioco.getGeneri().add(g);


        videogioco.setPiattaforme(new ArrayList<String>());
        for (String p : piattaforme)
            if (p.equalsIgnoreCase(request.getParameter(p)))
                videogioco.getPiattaforme().add(p);

        //immagine videogioco
        Part filePart = request.getPart("copertina");
        String imageType = "games";
        String fileName = videogioco.getTitolo() + ".jpg";
        videogioco.setCopertina(Utils.saveImageWar(imageType, fileName, filePart));
        Utils.saveImageFileSystem(imageType, fileName, filePart);

        try {
            rs.inserisciVideogioco(videogioco);
        } catch (InsertVideogameException e) {
            e.printStackTrace();
        }



        response.sendRedirect(path);

    }
}
