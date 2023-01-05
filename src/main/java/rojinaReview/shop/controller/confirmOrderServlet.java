package rojinaReview.shop.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.exception.CheckoutException;
import rojinaReview.model.exception.VideogiocatoreIDMissingException;
import rojinaReview.model.beans.*;
import rojinaReview.shop.service.ShopServiceImpl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@WebServlet(name = "confirmOrderServlet", value = "/confirmOrderServlet")
public class confirmOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        Prendo il carello dalla sessione
        */
            HttpSession session = request.getSession();
            Videogiocatore u = (Videogiocatore) session.getAttribute("utente");
            Carrello carrello = u.getCarrello();
        /*
            Constriuto l'entita ordine
         */
            Ordine ordine = new Ordine();
            /*
                Constriuto l'entita indirizzo
             */
                Indirizzo indirizzo = new Indirizzo();
                indirizzo.setVia(request.getParameter("via"));
                indirizzo.setNumeroCivico(Integer.parseInt(request.getParameter("numeroCivico")));
                indirizzo.setCittà(request.getParameter("citta"));
                indirizzo.setCap(request.getParameter("cap"));
                /*
                    Se il videogiocatore ha inserito un nuovo indirizzo
                    -Procedo al salvataggio nel DB -> new autenticazioneServiceImpl().inserisciIndirizzo(Indirizzo indirizzo, int id)
                        -Se id del videogiocatore non e' presente lancio eccezione personalizzata, videogiocatoretIDMissingException;
                 */
                    boolean newAddress = Boolean.parseBoolean(request.getParameter("address"));
                    if(newAddress) {
                        try {
                            new AutenticazioneServiceImpl().inserisciIndrizzo(u.getId(),indirizzo);
                            u.getIndirizzi().add(indirizzo);
                        } catch (VideogiocatoreIDMissingException e) {
                            e.printStackTrace();
                        }
                    }
            /*
                Constriuto l'entita pagamento
             */
                Pagamento pagamento = new Pagamento();
                pagamento.setNome(request.getParameter("nome"));
                pagamento.setCognome(request.getParameter("cognome"));
                pagamento.setNumeroCarta(request.getParameter("numeroCarta"));
                try {
                    String data = request.getParameter("dataScadenza");
                    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date dataParsata = parser.parse(data);
                    java.sql.Date dataSQLParsata = new java.sql.Date(dataParsata.getTime());
                    pagamento.setDataScadenza(dataSQLParsata);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                /*
                    Se il videogiocatore ha inserito un nuovo pagemento
                    -Procedo al salvataggio nel DB -> new autenticazioneServiceImpl()inserisciMetodoDiPagamento(Pagamento pagamento, int id)
                        -Se id del videogiocatore non e' presente lancio eccezione personalizzata, videogiocatoretIDMissingException;
                 */
                    boolean newPayment = Boolean.parseBoolean(request.getParameter("payment"));
                    if(newPayment){
                        try {
                            new AutenticazioneServiceImpl().inserisciIndrizzo(u.getId(),indirizzo);
                            u.getPagamenti().add(pagamento);
                        } catch (VideogiocatoreIDMissingException e) {
                            e.printStackTrace();
                        }
                        }
            /*
                Setto tutti i campi dell'entita carello
            */
                ordine.setDataOrdine(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
                ordine.setTotale(carrello.getTotale());
                ordine.setIndirizzo(indirizzo);
                ordine.setPagamento(pagamento);
                ordine.setStato("Preso in carico");
                ordine.setProdotti(carrello.getProdotti());
         /*
            Procedo alla conferma dell'ordine-> shopServiceImpl().checkout();
         */
            try {
                new ShopServiceImpl().checkout(ordine,u.getId(),(ArrayList<Prodotto>) session.getAttribute("prodotti"));
                /*
                    svuotamento carrello sessione
                */
                    carrello.setProdotti(new ArrayList<>());
                    carrello.setTotale(0);
            } catch (CheckoutException e) {
                e.printStackTrace();
            }

        /*
            Insierisco il nuovo orinde nella lista degli oridini
            Eliminabile?
         */
            u.getOrdini().add(0, ordine);
        /*
            reindirizzo il videogiocatore alla home
         */
            String home = "/Rojina_Review_war/home";
            response.sendRedirect(home);
    }
}
