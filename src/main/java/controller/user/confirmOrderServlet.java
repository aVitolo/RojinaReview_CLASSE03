package controller.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Carrello;
import model.beans.Indirizzo;
import model.beans.Pagamento;
import model.beans.Utente;
import model.dao.IndirizzoDAO;
import model.dao.OrdineDAO;
import model.dao.PagamentoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

        try {
            new OrdineDAO().confirmOrder(carrello, u.getEmail(), indirizzo, pagamento);
        } catch (SQLException e) {
            //prodotto non disponibile
        }

        if(newAddress) {
            try {
                new IndirizzoDAO().doSave(u.getEmail(), indirizzo);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(newPayment){
            try {
                new PagamentoDAO().doSave(pagamento, u.getEmail());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect(home);
    }
}
