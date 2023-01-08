package rojinaReview.utilities;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.*;
import rojinaReview.model.dao.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "getSingleResourceServlet", value = "/getSingleResourceServlet")
public class getSingleResourceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String result = null;
        int id = Integer.parseInt(request.getParameter("id"));
        int i, j;
        boolean searchDB = Boolean.parseBoolean(request.getParameter("searchDB"));
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        request.setAttribute("votoUtente", null);


        if (type.equalsIgnoreCase("news") || type.equalsIgnoreCase("notizia")) {
            try {
                Notizia n = new NotiziaDAO().doRetrieveById(id);
                request.setAttribute("notizia", n);
                request.setAttribute("commenti", new CommentoDAO().getCommentById(id, 2));
                request.setAttribute("giornalista", new GiornalistaDAO().doRetrieveById(n.getId_Giornalista()));
                //1 dovrebbe specificare la colonna idRecensione, guardare DB per verificare la correttezza
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            result = "/WEB-INF/results/mainPage/notizia.jsp";
        }
        else if (type.equalsIgnoreCase("reviews") || type.equalsIgnoreCase("recensione")) {
            try {
                Recensione r = new RecensioneDAO().doRetrieveById(id);
                request.setAttribute("recensione", r);
                request.setAttribute("commenti", new CommentoDAO().getCommentById(id, 1));
                request.setAttribute("videogioco",new VideogiocoDAO().doRetrieveById(r.getIdVideogioco()));
                request.setAttribute("giornalista",new GiornalistaDAO().doRetrieveById(r.getId_Giornalista()));
                if(session.getAttribute("utente") != null)
                {
                    Videogiocatore u = (Videogiocatore) session.getAttribute("utente");
                    request.setAttribute("votoUtente", new ParereDAO().
                            doRetrieveUserOpinion(u.getId(), r.getIdVideogioco(), 4));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            result = "/WEB-INF/results/mainPage/recensione.jsp";
        }
        else if (type.equalsIgnoreCase("shop") || type.equalsIgnoreCase("prodotto")) {
            Videogiocatore u = null;
            Carrello carrello = null;
            int quantitàCarrello = 0;
            if(session.getAttribute("utente") != null){
                u = (Videogiocatore) session.getAttribute("utente");
                carrello = u.getCarrello();
            }
            else if(session.getAttribute("ospite") != null)
                carrello = (Carrello) session.getAttribute("ospite");
            try {
                Prodotto p = new ProdottoDAO().doRetrieveById(id);
                request.setAttribute("prodotto", p);
                request.setAttribute("commenti", new CommentoDAO().getCommentById(id, 0));
                if(u != null)
                    request.setAttribute("votoUtente", new ParereDAO().
                            doRetrieveByUserAndIDTable(u.getId(),Integer.toString(p.getId()), false));
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            //per aggiornare in real-time la quantità disponibile
            if(carrello != null){
                for(j = 0; j < carrello.getProdotti().size(); j++)
                    if(carrello.getProdotti().get(j).getId() == id)
                        quantitàCarrello = carrello.getProdotti().get(j).getQuantità();
            }
            request.setAttribute("quantitàCarrello", quantitàCarrello);

            result = "/WEB-INF/results/mainPage/prodotto.jsp";
        }
       request.getRequestDispatcher(result).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
