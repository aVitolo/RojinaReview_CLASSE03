package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.*;
import model.dao.GiocoDAO;
import model.dao.PiattaformaDAO;
import model.dao.RecensioneDAO;
import model.dao.TipologiaDAO;

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
        String pathStore = "../../webapp/images/games"; //path in cui salvare l'immagine
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
        g.setCopertina(pathStore);

        g.setTipologie(new ArrayList<>());
        for(Tipologia t : tipologie)
            if(t.getNome().equalsIgnoreCase(request.getParameter(t.getNome())))
                g.getTipologie().add(t);


        g.setPiattaforme(new ArrayList<>());
        for(Piattaforma p : piattaforme)
            if(p.getNome().equalsIgnoreCase(request.getParameter(p.getNome())))
                g.getPiattaforme().add(p);

        try {
            new GiocoDAO().doSave(g);
            new TipologiaDAO().doSave(g.getTitolo(), g.getTipologie());
            new PiattaformaDAO().doSave(g.getTitolo(), g.getPiattaforme());
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

        //aggiornamento lista giochi nel context
        ArrayList<String> allGames = (ArrayList<String>) request.getServletContext().getAttribute("nomiGiochi");
        allGames.add(g.getTitolo());
        request.getServletContext().setAttribute("nomiGiochi", allGames);

        response.sendRedirect(result);

    }
}
