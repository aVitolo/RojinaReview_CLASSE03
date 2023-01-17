package rojinaReview.autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.autenticazione.service.AutenticazioneService;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.beans.Manager;
import rojinaReview.model.exception.InvalidTextException;
import rojinaReview.model.exception.UpdateDataException;
import rojinaReview.utilities.Utils;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "managerUpdateDataServlet", value = "/managerUpdateDataServlet")
public class managerUpdateDataServlet extends HttpServlet {
    private AutenticazioneService as;
    private String path = "/WEB-INF/results/managerPages/managerModificaDati.jsp";
    private String homePage = "./home";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            as = new AutenticazioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();

        if (session.getAttribute("manager") == null)
            response.sendRedirect(homePage);
        else {
            Manager manager = (Manager) session.getAttribute("manager");

            manager.setEmail(request.getParameter("email"));
            try {
                Utils.textCheckPassword(request.getParameter("password"));
                manager.setPassword(Utils.calcolaHash(request.getParameter("password")));
            } catch (InvalidTextException e) {
                e.printStackTrace(); //da aggiungere pagina errore "nuova password non valida"
            }
            manager.setNome(request.getParameter("nome"));
            manager.setCognome(request.getParameter("cognome"));

            try {
                as.modificaManager(manager);
            } catch (UpdateDataException e) {
                e.printStackTrace();
            }


            request.getRequestDispatcher(path).forward(request, response);

        }
    }
}
