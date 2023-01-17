package rojinaReview.autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.autenticazione.service.AutenticazioneService;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.exception.InvalidTextException;
import rojinaReview.model.exception.UpdateDataException;
import rojinaReview.utilities.Utils;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "journalistUpdateData", value = "/journalistUpdateData")
public class journalistUpdateData extends HttpServlet {
    private AutenticazioneService as;
    private String homePage = "./home";
    private String path = "/WEB-INF/results/giornalistaPages/giornalistaModificaDati.jsp";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            as = new AutenticazioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("giornalista") == null)
            response.sendRedirect(homePage);
        else
        {
            Giornalista giornalista = (Giornalista) session.getAttribute("giornalista");

            giornalista.setEmail(request.getParameter("email"));
            try {
                Utils.textCheckPassword(request.getParameter("password"));
                giornalista.setPassword(Utils.calcolaHash(request.getParameter("password")));
            } catch (InvalidTextException e) {
                e.printStackTrace(); //da aggiungere pagina errore "nuova password non valida"
            }
            giornalista.setNome(request.getParameter("nome"));
            giornalista.setCognome(request.getParameter("cognome"));

            try {
                as.modificaGiornalista(giornalista);
            } catch (UpdateDataException e) {
                e.printStackTrace();
            }

            request.getRequestDispatcher(path).forward(request, response);
        }

    }
}
