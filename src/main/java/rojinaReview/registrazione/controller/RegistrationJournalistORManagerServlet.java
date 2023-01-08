package rojinaReview.registrazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.*;
import rojinaReview.model.exception.EmailNotAvailableException;
import rojinaReview.model.exception.InvalidTextException;
import rojinaReview.model.utilities.Utils;
import rojinaReview.registrazione.service.RegistrazioneService;
import rojinaReview.registrazione.service.RegistrazioneServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegistrationJournalistORManagerServlet", value = "/RegistrationJournalistORManagerServlet")
public class RegistrationJournalistORManagerServlet extends HttpServlet {
    private RegistrazioneService rs;
    private String registrationErrata;
    private String homePage;

    public RegistrationJournalistORManagerServlet() throws SQLException {
        rs = new RegistrazioneServiceImpl();
        registrationErrata = "./registerStaff.jsp";
        homePage = "./home";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String message = null;
        RequestDispatcher dispatcherErrato = request.getRequestDispatcher(registrationErrata);
        Utente utente = null;

        if (session.getAttribute("giornalista") != null || session.getAttribute("manager") != null)
            response.sendRedirect(homePage); //se si è già loggati si viene reindirizzati alla homepage
        else {
            //Preleva dalla request l'email
            String email = request.getParameter("email");
            if (email != null)
                email = email.trim();
            String password = request.getParameter("password");
            if (password != null)
                password = password.trim();
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            int tipo = Integer.parseInt(request.getParameter("userType"));

            if(tipo == 0)
                utente = new Giornalista();
            else if (tipo == 1)
                utente = new Manager();

            utente.setEmail(email);
            utente.setPassword(Utils.calcolaHash(password));
            utente.setNome(nome);
            utente.setCognome(cognome);

            try {
                if(tipo == 0)
                    rs.registraGiornalista((Giornalista) utente);
                else if(tipo == 1)
                    rs.registraManager((Manager) utente);

            } catch (EmailNotAvailableException e) {
                message = "email già in uso";
                request.setAttribute("message", message);
                dispatcherErrato.forward(request, response);
                return;
            } catch (InvalidTextException e) {
                if (e.getMessage().equals("email"))
                    message = "email non valida";
                if (e.getMessage().equals("password"))
                    message = "password non valida";
                request.setAttribute("message", message);
                dispatcherErrato.forward(request, response);
                return;
            }

            //Giornalista o manager ha passato tutti i controlli e può essere registrato nel DB
            if(tipo == 0)
                session.setAttribute("giornalista", utente);
            else if(tipo == 1)
                session.setAttribute("manager", utente);

            response.sendRedirect(homePage);
        }

    }

}
