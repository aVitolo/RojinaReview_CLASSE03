package controller;

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

public class userLoginCheck extends HttpServlet {
    private Utente tmp;
    private String loginErrato = "/WEB-INF/results/userLoginErrato.jsp";
    private String homePage =  "./home";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(request.getSession().getAttribute("utente") != null)
                response.sendRedirect(homePage);
            else {
                boolean flag = true;
                //Preleva dalla request l'email
                String email = request.getParameter("email");

                //Inizializza la connessione al DB tramite DAO
                UtenteDAO uDAO = new UtenteDAO();
                this.tmp = uDAO.doRetriveByEmail(email);

                //Verifica se la ricerca dell' utente è andata male, e assegna a tmp il risultato
                if (this.tmp == null) {
                    //Il flag a false indica che non è necessario continuare i controlli, l' utente non c' è nel db
                    flag = false;
                    System.out.println("Utente non presente");

                    RequestDispatcher dispatcher = request.getRequestDispatcher(loginErrato);
                    dispatcher.forward(request, response);
                }

                //Il flag a true indica l' effettiva presenza dell' utente nell' db
                //Se non avessi il flag, e l' user TMP avesse valore null (non trovato), la successiva seconda istruzione darebbe errore
                if (flag == true) {

                    //Preleva dalla request la password
                    String password = request.getParameter("password");

                    //Calcola Hash della password utente
                    password = Persona.calcolaHash(password);

                    //ottengo la password dell' user nel db
                    String dbPass = this.tmp.getPassword();
                    //confronta le password
                    if (password.equals(dbPass)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("utente", this.tmp);
                        response.sendRedirect(homePage);
                    } else {
                        System.out.println("password errata");
                        RequestDispatcher dispatcher = request.getRequestDispatcher(loginErrato);
                        dispatcher.forward(request, response);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
