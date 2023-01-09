package rojinaReview.autenticazione.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rojinaReview.autenticazione.service.AutenticazioneService;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.beans.Manager;
import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.beans.Utente;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.ManagerDAO;
import rojinaReview.model.dao.GiornalistaDAO;
import rojinaReview.model.exception.EmailNotExistsException;
import rojinaReview.model.exception.IncorrectPasswordException;
import rojinaReview.model.exception.NotVerifiedAccountException;
import rojinaReview.model.exception.ServiceNotAvailableException;
import rojinaReview.model.utilities.GenericStaffDAO;
import rojinaReview.model.utilities.Utils;

import java.io.IOException;
import java.sql.SQLException;

public class LoginStaff extends HttpServlet {
    private AutenticazioneService as;
    private String loginErrato;
    private String homePage;

    public LoginStaff() {
        loginErrato = "./staffLogin.jsp";
        homePage = "/Rojina_Review_war/home";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            as = new AutenticazioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(loginErrato); //da aggiungere pagina errore di servizio non disponibile
        }
        HttpSession session = request.getSession();
        RequestDispatcher dispatcherErrato = request.getRequestDispatcher(loginErrato);
        String message;

        if(session.getAttribute("giornalista") != null || session.getAttribute("manager") != null)
            response.sendRedirect(homePage); //reindirizzo alla home se si è già loggati
        else
        {
            Utente utente = null;
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int userType = Integer.parseInt(request.getParameter("userType")); //userType = 0 Per i Giornalisti, userType = 1 Per i Manager

            if(userType == 0)
            {
                try {
                    utente = as.loginGiornalista(email, password);
                } catch (EmailNotExistsException e) {
                    message = "Email inesistente";
                    request.setAttribute("message", message);
                    dispatcherErrato.forward(request, response);
                    return;
                } catch (IncorrectPasswordException e) {
                    message = "Password sbagliata";
                    request.setAttribute("message", message);
                    dispatcherErrato.forward(request, response);
                    return;
                } catch (NotVerifiedAccountException e) {
                    message = "Account non verificato";
                    request.setAttribute("message", message);
                    dispatcherErrato.forward(request, response);
                    return;
                }
            }
            else if(userType == 1)
            {
                try {
                    utente = as.loginManager(email, password);
                } catch (EmailNotExistsException e) {
                    message = "Email inesistente";
                    request.setAttribute("message", message);
                    dispatcherErrato.forward(request, response);
                    return;
                } catch (IncorrectPasswordException e) {
                    message = "Password sbagliata";
                    request.setAttribute("message", message);
                    dispatcherErrato.forward(request, response);
                    return;
                } catch (NotVerifiedAccountException e) {
                    message = "Account non verificato";
                    request.setAttribute("message", message);
                    dispatcherErrato.forward(request, response);
                    return;
                }
            }

            if(userType == 0)
                session.setAttribute("giornalista", utente);
            else if (userType == 1)
                session.setAttribute("manager", utente);

            response.sendRedirect(homePage);
        }


    }
}

