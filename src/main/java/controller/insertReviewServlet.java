package controller;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Giornalista;
import model.beans.Recensione;
import model.dao.RecensioneDAO;
import model.dao.GiocoDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Locale;

@WebServlet(name = "insertReviewServlet", value = "/insertReviewServlet")
@MultipartConfig
public class insertReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "/Rojina_Review_war/journalistReviews";
        String pathStore = "../../webapp/images/reviews"; //path in cui salvare l'immagine
        Giornalista g = (Giornalista) request.getSession().getAttribute("giornalista");
        String nomeG = g.getNome()+" "+g.getCognome();

        //recensione da inserire presa dal form
        Recensione r = new Recensione();
        r.setTesto(request.getParameter("testo"));
        try {
            r.setGioco(new GiocoDAO().doRetrieveByTitle(request.getParameter("gioco")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        r.setTitolo(request.getParameter("titolo"));
        r.setVoto(Float.parseFloat(request.getParameter("voto")));
        r.setDataCaricamento(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        r.setImmagine(pathStore);

        try {
            new RecensioneDAO().doSave(r, g.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Part filePart = request.getPart("immagine");
        String fileName = "review-"+r.getGioco().getTitolo().toLowerCase(Locale.ROOT)+".jpg";

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

}
