package controller.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.*;
import model.dao.IndirizzoDAO;
import model.dao.OrdineDAO;
import model.dao.PagamentoDAO;

import java.io.IOException;
import java.sql.SQLException;
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
        HttpSession session = request.getSession();
        String home = "/Rojina_Review_war/home";
        Utente u = (Utente) session.getAttribute("utente");
        Carrello carrello = u.getCarrello();
        boolean newAddress = Boolean.parseBoolean(request.getParameter("address"));
        boolean newPayment = Boolean.parseBoolean(request.getParameter("payment"));
        ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getServletContext().getAttribute("prodotti");
        Ordine ordine = new Ordine();
        ArrayList<Ordine.ProdottoOrdine> prodottoOrdines = new ArrayList<>();

        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setVia(request.getParameter("via"));
        indirizzo.setNumeroCivico(Integer.parseInt(request.getParameter("numeroCivico")));
        indirizzo.setCittà(request.getParameter("citta"));
        indirizzo.setCap(request.getParameter("cap"));

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

        if(newAddress) {
            try {
                new IndirizzoDAO().doSave(u.getEmail(), indirizzo);
                u.getIndirizzi().add(indirizzo);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(newPayment){
            try {
                new PagamentoDAO().doSave(pagamento, u.getEmail());
                u.getPagamenti().add(pagamento);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        ordine.setDataOrdine(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        ordine.setTotale(carrello.getTotale());
        ordine.setIndirizzo(indirizzo);
        ordine.setPagamento(pagamento);
        ordine.setStato("Preso in carico");
        ordine.setTracking("");
        for(Carrello.ProdottoCarrello p : carrello.getProdotti()){
            Ordine.ProdottoOrdine pOrdine = new Ordine.ProdottoOrdine();
            pOrdine.setProdotto(p.getProdotto());
            pOrdine.setPrezzoAcquisto(p.getPrezzoAttuale());
            pOrdine.setQuantita(p.getQuantità());

            prodottoOrdines.add(pOrdine);
        }
        ordine.setProdotti(prodottoOrdines);

        try {
            new OrdineDAO().confirmOrder(ordine, u.getEmail(), prodotti);
            //svuotamento carrello sessione
            carrello.setProdotti(new ArrayList<>());
            carrello.setTotale(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        u.getOrdini().add(ordine);

        response.sendRedirect(home);
    }
}
