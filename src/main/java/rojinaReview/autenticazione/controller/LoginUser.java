package rojinaReview.autenticazione.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rojinaReview.autenticazione.service.AutenticazioneService;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.beans.Carrello;
import rojinaReview.model.beans.Prodotto;
import rojinaReview.model.beans.Utente;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.dao.VideogiocatoreDAO;
import rojinaReview.model.exception.EmailNotExistsException;
import rojinaReview.model.exception.IncorrectPasswordException;
import rojinaReview.model.utilities.Utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginUser extends HttpServlet {
    AutenticazioneService as;
    private String loginErrato;
    private String homePage;

    public LoginUser() throws SQLException {
        as = new AutenticazioneServiceImpl();
        loginErrato = "./userLogin.jsp";
        homePage = "./home";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            RequestDispatcher dispatcherErrato = request.getRequestDispatcher(loginErrato);
            String message = null;

            if (session.getAttribute("utente") != null)
                response.sendRedirect(homePage); //se si è già loggati si viene reindirizzati alla homepage
            else
            {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                Videogiocatore videogiocatore = null;
                try {
                    videogiocatore = as.loginVideogiocatore(email, password);
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
                }


                //merge dei carrelli
                Carrello ospite = (Carrello) session.getAttribute("ospite");
                videogiocatore.setCarrello(Utils.mergeCarts(videogiocatore.getCarrello(), ospite));
                //rimozione del carrello ospite
                session.removeAttribute("ospite");


                session.setAttribute("utente", videogiocatore);
                response.sendRedirect(homePage);
            }

    }



}
