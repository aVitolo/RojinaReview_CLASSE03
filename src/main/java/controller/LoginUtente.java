package controller;

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

public class LoginUtente extends HttpServlet {
    private Utente tmp;
    private String loginErrato = "Da aggiungere";
    private String homePage = "/Rojina_Review_war/home";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(!(request.getSession(false) == null))
                response.sendRedirect(homePage);
            else {
                boolean flag = true;
                //Preleva dalla request i parametri -email -password
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                //Calcola Hash della password utente
                password = Persona.calcolaHash(password);

                //Inizializza la connessione al DB tramite DAO
                UtenteDAO uDAO = new UtenteDAO();
                this.tmp = uDAO.doRetriveByEmail(email);

                //Verifica se la ricerca dell' utente è andata male, e assegna a tmp il risultato
                if (this.tmp == null) {
                    //Il flag a false indica che non è necessario continuare i controlli, l' utente non c' è nel db
                    flag = false;
                    response.sendRedirect(loginErrato);
                }

                //Il flag a true indica l' effettiva presenza dell' utente nell' db
                //Se non avessi il flag, e l' user TMP avesse valore null (non trovato), la successiva seconda istruzione darebbe errore
                if (flag == true) {
                    //ottengo la password dell' user nel db
                    String dbPass = this.tmp.getPassword();

                    //confronta le password
                    if (password.equals(dbPass)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("Utente", this.tmp);
                        response.sendRedirect(homePage);
                    } else {
                        response.sendRedirect(loginErrato);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
