package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.*;
import model.dao.GiocoDAO;
import model.dao.PiattaformaDAO;
import model.dao.RecensioneDAO;
import model.dao.TipologiaDAO;
import model.utilities.Utils;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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
        ArrayList<Tipologia> tipologie = (ArrayList<Tipologia>) context.getAttribute("tipologie");
        ArrayList<Piattaforma> piattaforme = (ArrayList<Piattaforma>) context.getAttribute("piattaforme");

        //gioco da inserire preso dal form
        Gioco g = new Gioco();
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

        g.setTipologie(new ArrayList<>());
        for(Tipologia t : tipologie)
            if(t.getNome().equalsIgnoreCase(request.getParameter(t.getNome())))
                g.getTipologie().add(t);


        g.setPiattaforme(new ArrayList<>());
        for(Piattaforma p : piattaforme)
            if(p.getNome().equalsIgnoreCase(request.getParameter(p.getNome())))
                g.getPiattaforme().add(p);

        Part filePart = request.getPart("copertina");
        String imageType = "games";
        String fileName = g.getTitolo()+".jpg";
        g.setCopertina(Utils.saveImageWar(imageType, fileName, filePart));
        Utils.saveImageFileSystem(imageType, fileName, filePart);

        try {
            new GiocoDAO().doSave(g);
            new TipologiaDAO().doSave(g.getTitolo(), g.getTipologie());
            new PiattaformaDAO().doSave(g.getTitolo(), g.getPiattaforme());
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
