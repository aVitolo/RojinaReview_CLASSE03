package rojinaReview.autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.autenticazione.service.AutenticazioneService;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.beans.Pagamento;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.PagamentoDAO;
import rojinaReview.model.exception.InsertPaymentException;
import rojinaReview.model.exception.VideogiocatoreIDMissingException;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "insertPaymentServlet", value = "/insertPaymentServlet")
public class insertPaymentServlet extends HttpServlet {
    private AutenticazioneService as;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            as = new AutenticazioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String result = "/Rojina_Review_war/userInformations";
        Videogiocatore u = (Videogiocatore) request.getSession().getAttribute("videogiocatore");
        Pagamento p = new Pagamento();

        String data = request.getParameter("dataScadenza");
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataParsata = null;
        try {
            dataParsata = parser.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date dataSQLParsata = new java.sql.Date(dataParsata.getTime());

        p.setDataScadenza(dataSQLParsata);
        p.setNome(request.getParameter("nome"));
        p.setCognome(request.getParameter("cognome"));
        p.setNumeroCarta(request.getParameter("numeroCarta"));

        try {
            as.inserisciMetodoDiPagamento(p, u);
        }
         catch (InsertPaymentException e) {
            e.printStackTrace();
        }

        response.sendRedirect(result);
    }
}
