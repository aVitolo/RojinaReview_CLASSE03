package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Giornalista;
import model.beans.Notizia;
import model.beans.Recensione;
import model.dao.GiocoDAO;
import model.dao.NotiziaDAO;
import model.dao.RecensioneDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

@WebServlet(name = "insertNewServlet", value = "/insertNewServlet")
@MultipartConfig
public class insertNewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "/Rojina_Review_war/journalistNews";
        String pathStore = "../../webapp/images/news"; //path in cui salvare l'immagine
        ArrayList<String> allGames = (ArrayList<String>) request.getServletContext().getAttribute("nomiGiochi");
        Giornalista g = (Giornalista) request.getSession().getAttribute("giornalista");
        String nomeG = g.getNome()+" "+g.getCognome();

        //recensione da inserire presa dal form
        Notizia n = new Notizia();
        n.setTitolo(request.getParameter("titolo"));
        n.setTesto(request.getParameter("testo"));
        n.setDataCaricamento(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        n.setImmagine(pathStore);
        n.setGiochi(parseGames(request.getParameter("giochi"), allGames));
        try {
            new NotiziaDAO().doSave(n, g.getId());
            new NotiziaDAO().doSaveMentioned(n.getGiochi(), g.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Part filePart = request.getPart("immagine");
        String fileName = "new-"+n.getTitolo().toLowerCase(Locale.ROOT)+".jpg";

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

        response.sendRedirect(result);

    }

    public ArrayList<String> parseGames(String toParse, ArrayList<String> allGames)
    {
        ArrayList<String> parsedGames = new ArrayList<>();

        for(String s : allGames)
            if(toParse.contains(s))
                parsedGames.add(s);



        return parsedGames;
    }

}
