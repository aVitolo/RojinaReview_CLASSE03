package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.beans.Utente;
import model.dao.UtenteDAO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginUtente extends HttpServlet {
    private Utente tmp;
    private String loginErrato = "Da aggiungere" ;
    private String homePage = "Da aggiungere";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean flag = true;
        //Preleva dalla request i parametri -email -password
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //Calcola Hash della password utente
        password= Utente.calcolaHash(password);

        try {
            //Inizializza la connessione al DB tramite DAO
            UtenteDAO uDAO = new UtenteDAO();
            //Verifica se la ricerca dell' utente è andata male, e assegna a tmp il risultato
            if( ( tmp = uDAO.doRetriveByEmail(email) ) == null) {
                //Il flag a false indica che non è necessario continuare i controlli, l' utente non c' è nel db
                flag = false;
                response.sendRedirect(loginErrato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Il flag a true indica l' effettiva presenza dell' utente nell' db
        //Se non avessi il flag, e l' user TMP avesse valore null (non trovato), la successiva seconda istruzione darebbe errore
        if(flag == true) {
            //ottengo la password dell' user nel db
            String dbPass = tmp.getPassword();

            //confronta le password
            if (password.equals(dbPass)) {
                HttpSession session = request.getSession();
                session.setAttribute("Utente", tmp);
                response.sendRedirect(homePage);
            } else {
                response.sendRedirect(loginErrato);
            }
        }
    }
}
