package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.*;
import model.dao.GiocoDAO;
import model.dao.RecensioneDAO;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

@WebServlet(name = "insertGameServlet", value = "/insertGameServlet")
public class insertGameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "/Rojina_Review_war/journalistGames";
        String pathStore = "../../webapp/images/games"; //path in cui salvare l'immagine

        //gioco da inserire preso dal form
        Gioco g = new Gioco();
        g.setTitolo(request.getParameter("titolo"));
        g.setCasaDiSviluppo(request.getParameter("casadiSviluppo"));
        g.setDataDiRilascio(new java.sql.Date(2020, 10, 3));
        g.setCopertina(pathStore);

        g.setTipologie(new ArrayList<>());
        String[] tipologieScelte = request.getParameterValues("tipologia");
        for(String s : tipologieScelte)
            g.getTipologie().add(new Tipologia(s));

        g.setPiattaforme(new ArrayList<>());
        String[] piattaformeScelte = request.getParameterValues("piattaforma");
        for(String s : piattaformeScelte)
            g.getPiattaforme().add(new Piattaforma(s));

        try {
            new GiocoDAO().doSave(g);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Part filePart = request.getPart("copertina");
        String fileName = g.getTitolo().replaceAll("\\s+", "").toLowerCase(Locale.ROOT);

        OutputStream out = null;
        InputStream filecontent = null;



        out = new FileOutputStream(new File(fileName));
        filecontent = filePart.getInputStream();

        int read = 0;
        byte[] bytes = new byte[1024];

        while((read = filecontent.read(bytes)) != -1)
            out.write(bytes, 0, read);

        if(out != null)
            out.close();

        if(filecontent != null)
            filecontent.close();

        request.getRequestDispatcher(result).include(request, response);

    }
}
