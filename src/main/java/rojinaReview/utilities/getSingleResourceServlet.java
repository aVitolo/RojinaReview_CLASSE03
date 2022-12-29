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
        boolean trovato = false;
        boolean searchDB = Boolean.parseBoolean(request.getParameter("searchDB"));
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        request.setAttribute("votoUtente", null);


        if (type.equalsIgnoreCase("news") || type.equalsIgnoreCase("notizia")) {
            //cerco prima nel context la notizia
            ArrayList<Notizia> notizieContext = (ArrayList<Notizia>) context.getAttribute("notizie");
            for (i = 0; i < notizieContext.size() && !trovato; i++) {
                if (notizieContext.get(i).getId() == id) {
                    trovato = true;
                    request.setAttribute("notizia", notizieContext.get(i));
                    try {
                        request.setAttribute("commenti", new CommentoDAO().getCommentById(id, "Notizia"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (trovato == false) {
                try {
                    Notizia n = new NotiziaDAO().doRetrieveById(id);
                    request.setAttribute("notizia", n);
                    request.setAttribute("commenti", new CommentoDAO().getCommentById(id, "Notizia"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            result = "/WEB-INF/results/mainPage/notizia.jsp";

        } else if (type.equalsIgnoreCase("reviews") || type.equalsIgnoreCase("recensione")) {
            //cerco prima nel context la recensione
            ArrayList<Recensione> recensioniContext = (ArrayList<Recensione>) context.getAttribute("recensioni");
            for (i = 0; i < recensioniContext.size() && !trovato && !searchDB; i++) {
                if (recensioniContext.get(i).getId() == id) {
                    trovato = true;
                    request.setAttribute("recensione", recensioniContext.get(i));
                    try {
                        request.setAttribute("commenti", new CommentoDAO().getCommentById(id, "Recensione"));
                        if(session.getAttribute("utente") != null)
                        {
                            Utente u = (Utente) session.getAttribute("utente");
                            request.setAttribute("votoUtente", new VotoDAO().doRetrieveByUserAndIDTable(u.getEmail(),
                                    recensioniContext.get(i).getGioco().getTitolo(), "gioco"));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (trovato == false || searchDB) {
                try {
                    Recensione r = new RecensioneDAO().doRetrieveById(id);
                    request.setAttribute("recensione", r);
                    request.setAttribute("commenti", new CommentoDAO().getCommentById(id, "Recensione"));
                    if(session.getAttribute("utente") != null)
                    {
                        Utente u = (Utente) session.getAttribute("utente");
                        request.setAttribute("votoUtente", new VotoDAO().doRetrieveByUserAndIDTable(u.getEmail(),
                                r.getGioco().getTitolo(), "gioco"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            result = "/WEB-INF/results/mainPage/recensione.jsp";

        } else if (type.equalsIgnoreCase("shop") || type.equalsIgnoreCase("prodotto")) {
            Utente u = null;
            Carrello carrello = null;
            Integer quantitàCarrello = new Integer(0);
            if(session.getAttribute("utente") != null){
                u = (Utente) session.getAttribute("utente");
                carrello = u.getCarrello();
            }
            else if(session.getAttribute("ospite") != null)
                carrello = (Carrello) session.getAttribute("ospite");


            //cerco prima nel context il prodotto
            ArrayList<Prodotto> prodottiContext = (ArrayList<Prodotto>) context.getAttribute("prodotti");
            for (i = 0; i < prodottiContext.size() && !trovato && !searchDB; i++) {
                if (prodottiContext.get(i).getId() == id) {
                    trovato = true;
                    request.setAttribute("prodotto", prodottiContext.get(i));
                    try {
                        request.setAttribute("commenti", new CommentoDAO().getCommentById(id, "Prodotto"));
                        if(u != null)
                            request.setAttribute("votoUtente", new VotoDAO().doRetrieveByUserAndIDTable(u.getEmail(),
                                    Integer.toString(prodottiContext.get(i).getId()), "prodotto"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            //cerco nel database
            if (trovato == false || searchDB) {
                try {
                    Prodotto p = new ProdottoDAO().doRetrieveById(id);
                    request.setAttribute("prodotto", p);
                    request.setAttribute("commenti", new CommentoDAO().getCommentById(id, "Prodotto"));
                    if(u != null)
                        request.setAttribute("votoUtente", new VotoDAO().doRetrieveByUserAndIDTable(u.getEmail(),
                                Integer.toString(p.getId()), "prodotto"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            //per aggiornare in real-time la quantità disponibile
            if(carrello != null){
                for(j = 0; j < carrello.getProdotti().size(); j++)
                    if(carrello.getProdotti().get(j).getProdotto().getId() == id)
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
