package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.*;
import rojinaReview.model.dao.VideogiocatoreDAO;
import rojinaReview.model.dao.PiattaformaDAO;
import rojinaReview.model.dao.TipologiaDAO;
import rojinaReview.model.utilities.Utils;

import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet(name = "insertGameServlet", value = "/insertGameServlet")
@MultipartConfig
public class insertGameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "/Rojina_Review_war/journalistGames";
        ServletContext context = request.getServletContext();
        ArrayList<String> tipologie = (ArrayList<String>) context.getAttribute("tipologie");
        ArrayList<String> piattaforme = (ArrayList<String>) context.getAttribute("piattaforme");

        //gioco da inserire preso dal form
        Videogioco g = new Videogioco();
        g.setTitolo(request.getParameter("titolo"));
        g.setCasaDiSviluppo(request.getParameter("casaDiSviluppo"));
        try {
            String data = request.getParameter("dataDiRilascio");
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dataParsata = parser.parse(data);
            java.sql.Date dataSQLParsata = new java.sql.Date(dataParsata.getTime());
            g.setDataDiRilascio(dataSQLParsata);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        g.setGeneri(new ArrayList<String>());
        for (String t : tipologie)
            if (t.equalsIgnoreCase(request.getParameter(t)))
                g.getGeneri().add(t);


        g.setPiattaforme(new ArrayList<>());
        for (String p : piattaforme)
            if (p.equalsIgnoreCase(request.getParameter(p)))
                g.getPiattaforme().add(p);

        Part filePart = request.getPart("copertina");
        String imageType = "games";
        String fileName = g.getTitolo() + ".jpg";
        g.setCopertina(Utils.saveImageWar(imageType, fileName, filePart));
        Utils.saveImageFileSystem(imageType, fileName, filePart);

        try {
            new VideogiocatoreDAO().doSave(g);
            new TipologiaDAO().doSave(g.getId(), g.getGeneri());
            new PiattaformaDAO().doSave(g.getId(), g.getPiattaforme());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //aggiornamento lista giochi nel context
        ArrayList<String> allGames = (ArrayList<String>) request.getServletContext().getAttribute("nomiGiochi");
        allGames.add(g.getTitolo());
        request.getServletContext().setAttribute("nomiGiochi", allGames);

        response.sendRedirect(result);

    }
}
