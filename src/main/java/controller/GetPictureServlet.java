package controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Gioco;
import model.Notizia;
import model.Recensione;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/getPicture") //chiamata alla servlet avviene tramite: <img src="/getPicture?action=recensione&key=3
public class GetPictureServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        ServletContext context = req.getServletContext();
        byte[] img;
        ServletOutputStream out = resp.getOutputStream();

        if(action.equals("gioco"))
        {
            String titolo = req.getParameter("key");
            ArrayList<Gioco> giochi = (ArrayList<Gioco>) context.getAttribute("giochi"); //getAttribute o getInitParameter?
            Gioco trovato = null;

            for(Gioco g : giochi)
                if(titolo.equals(g.getTitolo()))
                {
                    trovato = g;
                    break;
                }

            if(trovato == null) //gioco non trovato nel servletContext: inesistente o presente nel DB
            {

            }

            img = trovato.getCopertina();
            out.write(img);
            resp.setContentType("image/jpeg");
        }

        else if(action.equals("recensione"))
        {
            int id = Integer.parseInt(req.getParameter("key"));
            ArrayList<Recensione> recensioni = (ArrayList<Recensione>) context.getAttribute("recensioni");
            Recensione trovata = null;

            for(Recensione r : recensioni)
                if(r.getId() == id)
                {
                    trovata = r;
                    break;
                }

            if(trovata == null) //recensione non trovata nel servletContext: inesistente o presente nel DB
            {

            }

            img = trovata.getImmagine();
            out.write(img);
            resp.setContentType("image/jpeg");
        }

        else if(action.equals("notizia"))
        {
            int id = Integer.parseInt(req.getParameter("key"));
            ArrayList<Notizia> notizie = (ArrayList<Notizia>) context.getAttribute("notizie");
            Notizia trovata = null;

            for(Notizia n : notizie)
                if(n.getId() == id)
                {
                    trovata = n;
                    break;
                }

            if(trovata == null) //notizia non trovata nel servletContext: inesistente o presente nel DB
            {

            }

            img = trovata.getImmagine();
            out.write(img);
            resp.setContentType("image/jpeg");
        }

        else //action non specificata o scritta male
        {
            //pagina di errore
        }


    }
}
