package controller.logins;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.utilities.Persona;
import model.beans.Utente;
import model.dao.UtenteDAO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginUser extends HttpServlet {
    private Utente tmp;
    private String loginErrato = "./userLogin.jsp";
    private String homePage =  "./home";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if(session.getAttribute("utente") != null)
                response.sendRedirect(homePage);
            else {
                //Preleva dalla request l'email
                String email = request.getParameter("email");

                //Inizializza la connessione al DB tramite DAO
                UtenteDAO uDAO = new UtenteDAO();
                this.tmp = uDAO.doRetriveByEmail(email);

                //Verifica se la ricerca dell' utente è andata male, e assegna a tmp il risultato
                if (this.tmp == null) {
                    //Il flag a false indica che non è necessario continuare i controlli, l' utente non c' è nel db
                    String message = "Invalid email";
                    request.setAttribute("message", message);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(loginErrato);
                    dispatcher.forward(request, response);
                }

                //Preleva dalla request la password
                String password = request.getParameter("password");

                //Calcola Hash della password utente
                password = Persona.calcolaHash(password);

                //ottengo la password dell' user nel db
                String dbPass = this.tmp.getPassword();
                //confronta le password
                if (password.equals(dbPass)) {
                    session.setAttribute("utente", this.tmp);
                    response.sendRedirect(homePage);
                } else {
                    String message = "Invalid password";
                    request.setAttribute("message", message);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(loginErrato);
                    dispatcher.forward(request, response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
