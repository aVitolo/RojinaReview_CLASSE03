package rojinaReview.registrazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Carrello;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.exception.EmailNotAvailableException;
import rojinaReview.model.exception.InvalidTextException;
import rojinaReview.model.exception.NicknameNotAvailableException;
import rojinaReview.model.utilities.Utils;
import rojinaReview.registrazione.service.RegistrazioneService;
import rojinaReview.registrazione.service.RegistrazioneServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "userCreationCheckServlet", value = "/userCreationCheckServlet")
public class RegistrationVideogamerServlet extends HttpServlet {
    private RegistrazioneService rs;
    private String registrationErrata;
    private String homePage;

    public RegistrationVideogamerServlet() {
        registrationErrata = "./registerUser.jsp";
        homePage = "./home";
    }



    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            rs = new RegistrazioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        String message = null;
        RequestDispatcher dispatcherErrato = request.getRequestDispatcher(registrationErrata);

        if (session.getAttribute("videogiocatore") != null)
            response.sendRedirect(homePage); //se si è già loggati si viene reindirizzati alla homepage
        else {
            //Preleva dalla request l'email
            String email = request.getParameter("email");
            if(email != null)
                email = email.trim();
            String nickname = request.getParameter("nickname");
            if(nickname != null)
                nickname = nickname.trim();
            String password = request.getParameter("password");
            if(password != null)
                password = password.trim();

            Videogiocatore videogiocatore = new Videogiocatore();

            videogiocatore.setEmail(email);
            videogiocatore.setNickname(nickname);
            videogiocatore.setPassword(password);

            try {
                videogiocatore.setId(rs.registraVideogiocatore(videogiocatore));
            } catch (EmailNotAvailableException e) {
                message = "email già in uso";
                request.setAttribute("message", message);
                response.getWriter().write("EmailUsata");
                dispatcherErrato.forward(request, response);
                return;
            } catch (NicknameNotAvailableException e) {
                message = "nickname già in uso";
                request.setAttribute("message", message);
                response.getWriter().write("NicknameUsato");
                dispatcherErrato.forward(request, response);
                return;
            } catch (InvalidTextException e) {
                if(e.getMessage().equals("email"))
                    message = "email non valida";
                if(e.getMessage().equals("nickname"))
                    message = "nickname non valido";
                if(e.getMessage().equals("password"))
                    message = "password non valida";
                request.setAttribute("message", message);
                dispatcherErrato.forward(request, response);
                return;
            }

            //Videogiocatore ha passato tutti i controlli e può essere registrato nel DB

            if(session.getAttribute("ospite") != null)
                videogiocatore.setCarrello((Carrello) session.getAttribute("ospite"));
            else
                videogiocatore.setCarrello(new Carrello());
            response.getWriter().write("registrazioneOK");
            session.setAttribute("videogiocatore", videogiocatore);
            response.sendRedirect(homePage);
        }

    }

}




