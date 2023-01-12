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
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.exception.BannedUserException;
import rojinaReview.model.exception.EmailNotExistsException;
import rojinaReview.model.exception.IncorrectPasswordException;
import rojinaReview.model.exception.LoadingCartException;
import rojinaReview.model.utilities.Utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginUser extends HttpServlet {
    private AutenticazioneService as;
    private String loginErrato;
    private String homePage;

    public LoginUser() throws SQLException {

        loginErrato = "./userLogin.jsp";
        homePage = "./home";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            as = new AutenticazioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(loginErrato); //da aggiungere pagina errore servizio
        }
        HttpSession session = request.getSession();
            RequestDispatcher dispatcherErrato = request.getRequestDispatcher(loginErrato);
            String message = null;

            if (session.getAttribute("videogiocatore") != null)
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
                } catch (BannedUserException e) {
                    message = "Utente Bannato";
                    request.setAttribute("message", message);
                    dispatcherErrato.forward(request, response);
                    return;
                } catch (LoadingCartException e) {
                    e.printStackTrace();
                }



                videogiocatore.setPagamenti(new ArrayList<>());
                videogiocatore.setIndirizzi(new ArrayList<>());
                videogiocatore.setTelefoni(new ArrayList<>());
                videogiocatore.setOrdini(new ArrayList<>());

                //merge dei carrelli
                Carrello ospite = (Carrello) session.getAttribute("ospite");
                videogiocatore.setCarrello(Utils.mergeCarts(videogiocatore.getCarrello(), ospite));
                //rimozione del carrello ospite
                session.removeAttribute("ospite");


                session.setAttribute("videogiocatore", videogiocatore);
                response.sendRedirect(homePage);
            }

    }



}
