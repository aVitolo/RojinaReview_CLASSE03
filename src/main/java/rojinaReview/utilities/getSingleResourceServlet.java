package rojinaReview.utilities;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.*;
import rojinaReview.model.dao.*;
import rojinaReview.model.exception.*;
import rojinaReview.opinione.service.OpinioneService;
import rojinaReview.opinione.service.OpinioneServiceImpl;
import rojinaReview.rivista.service.RivistaService;
import rojinaReview.rivista.service.RivistaServiceImpl;
import rojinaReview.shop.service.ShopService;
import rojinaReview.shop.service.ShopServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "getSingleResourceServlet", value = "/getSingleResourceServlet")
public class getSingleResourceServlet extends HttpServlet {
    private RivistaService rs;
    private ShopService ss;
    private OpinioneService os;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            rs = new RivistaServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ss = new ShopServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            os = new OpinioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String type = request.getParameter("type");
        String result = null;
        int id = Integer.parseInt(request.getParameter("id"));
        int i, j;
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        request.setAttribute("votoUtente", null);


        if (type.equalsIgnoreCase("news") || type.equalsIgnoreCase("notizia")) {
            try {
                Notizia notizia = rs.visualizzaNotiziaByID(id);
                request.setAttribute("notizia", notizia);
                request.setAttribute("giornalistaArticolo", rs.visualizzaGiornalista(notizia));
            } catch (LoadingNewsException e) {
                e.printStackTrace();
            } catch (LoadingJournalistException e) {
                e.printStackTrace();
            }
            result = "/WEB-INF/results/mainPage/notizia.jsp";
        }
        else if (type.equalsIgnoreCase("reviews") || type.equalsIgnoreCase("recensione") || type.equalsIgnoreCase("game")) {
            try {
                Recensione recensione;
                if(type.equalsIgnoreCase("game"))
                    recensione = rs.visualizzaRecensioneByIDVideogioco(id);
                else
                    recensione = rs.visualizzaRecensioneByID(id);

                request.setAttribute("recensione", recensione);
                request.setAttribute("videogioco", rs.visualizzaVideogioco(recensione));
                request.setAttribute("giornalistaArticolo",rs.visualizzaGiornalista(recensione));
                if(session.getAttribute("videogiocatore") != null)
                {
                    Videogiocatore videogiocatore = (Videogiocatore) session.getAttribute("videogiocatore");
                    request.setAttribute("votoUtente", os.visualizzaVotoUtente(videogiocatore, recensione));
                }
            } catch (LoadingReviewsException e) {
                e.printStackTrace();
            } catch (LoadingVideogamesException e) {
                e.printStackTrace();
            } catch (LoadingJournalistException e) {
                e.printStackTrace();
            } catch (LoadingOpinionException e) {
                e.printStackTrace();
            }
            result = "/WEB-INF/results/mainPage/recensione.jsp";
        }
        else if (type.equalsIgnoreCase("shop") || type.equalsIgnoreCase("prodotto")) {
            Videogiocatore videogiocatore = null;
            Carrello carrello = null;
            int quantitàCarrello = 0;
            if(session.getAttribute("videogiocatore") != null){
                videogiocatore = (Videogiocatore) session.getAttribute("videogiocatore");
                carrello = videogiocatore.getCarrello();
            }
            else if(session.getAttribute("ospite") != null)
                carrello = (Carrello) session.getAttribute("ospite");
            try {
                Prodotto prodotto = ss.visualizzaProdotto(id);
                request.setAttribute("prodotto", prodotto);
                if(videogiocatore != null)
                    request.setAttribute("votoUtente", os.visualizzaVotoUtente(videogiocatore, prodotto));
            } catch (ProductIDMissingException e) {
                e.printStackTrace();
            } catch (LoadingOpinionException e) {
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
