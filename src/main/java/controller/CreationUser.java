package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Utente;
import model.dao.UtenteDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "userCreationCheckServlet", value = "/userCreationCheckServlet")
public class CreationUser extends HttpServlet {

    private Utente tmp;
    private String loginErrato = "./userCreation.jsp";
    private String homePage =  "./home";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(request.getSession().getAttribute("utente") != null)
                response.sendRedirect(homePage);
            else {
                //Preleva dalla request l'email
                String email = request.getParameter("email");

                //Inizializza la connessione al DB tramite DAO
                UtenteDAO uDAO = new UtenteDAO();
                this.tmp = uDAO.doRetriveByEmail(email);

                //Verifica se la ricerca dell' utente è andata male, e assegna a tmp il risultato
                if (this.tmp != null) {
                //Il flag a false indica che non è necessario continuare i controlli, l'email esiste gia
                    String message = "Invalid email";
                    request.setAttribute("message", message);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(loginErrato);
                    dispatcher.forward(request, response);
                }

                String nickname = request.getParameter("nickname");
                this.tmp = uDAO.doRetriveByNickname(nickname);

                //Verifica se la ricerca dell' utente è andata male, e assegna a tmp il risultato
                if (this.tmp != null) {
                    //Il flag a false indica che non è necessario continuare i controlli, il nickname  esiste già
                    String message = "Invalid nickname";
                    request.setAttribute("message", message);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(loginErrato);
                    dispatcher.forward(request, response);
                }

                //Prelevo dalla request la password
                String password = request.getParameter("password");

                /*
                verificare la correttazza della password
                 */

                /*
                aggiungere utente al db  e alla sessione
                 */
            }
    } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}