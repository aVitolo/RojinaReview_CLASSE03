package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.beans.Notizia;
import rojinaReview.model.dao.NotiziaDAO;
import rojinaReview.model.dao.VideogiocoDAO;
import rojinaReview.model.utilities.Utils;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

@WebServlet(name = "insertNewServlet", value = "/insertNewServlet")
@MultipartConfig
public class insertNewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "/Rojina_Review_war/journalistNews";
        ArrayList<String> allGames = (ArrayList<String>) request.getServletContext().getAttribute("nomiGiochi");
        Giornalista g = (Giornalista) request.getSession().getAttribute("giornalista");
        String nomeG = g.getNome() + " " + g.getCognome();

        //notizia da inserire presa dal form
        Notizia n = new Notizia();
        n.setNome(request.getParameter("titolo"));
        n.setTesto(request.getParameter("testo"));
        n.setDataScrittura(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        n.setGiochi(parseGames(allGames));

        Part filePart = request.getPart("immagine");
        String imageType = "news";
        String fileName = "new-" + n.getNome() + ".jpg";
        n.setImmagine(Utils.saveImageWar(imageType, fileName, filePart));
        Utils.saveImageFileSystem(imageType, fileName, filePart);

        try {
            new NotiziaDAO().doSave(n, g.getId());
            new NotiziaDAO().doSaveMentioned(n.getGiochi());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(result);

    }

    public HashMap<Integer, String> parseGames(ArrayList<String> allGames) {
        HashMap<Integer, String> parsedGames = new HashMap<>();

        for (String g : allGames) {
            try {
                parsedGames.put(new VideogiocoDAO().retrieveIdByName(g),"g");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return parsedGames;
    }

}
