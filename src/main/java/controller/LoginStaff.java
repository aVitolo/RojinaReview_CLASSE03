package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.beans.Amministratore;
import model.beans.Giornalista;
import model.utilities.Persona;
import model.beans.Utente;
import model.dao.AmministratoreDAO;
import model.dao.GiornalistaDAO;
import model.utilities.GenericStaffDAO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginStaff extends HttpServlet {
    private Utente tmp;
    private String loginErrato = "Da Aggiungere" ;
    private String homePage = "/Rojina_Review_war/home";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Preleva dalla request i parametri -email -password -userType
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //userType = 0 Per i Giornalisti, userType = 1 Per gli Admin
        String userType = request.getParameter("userType");
        //Calcola Hash della password utente
        password= Persona.calcolaHash(password);

        int type = -1;
        Object tmp = null;
        GenericStaffDAO staffDAO = null;
        try {
            //Verifica se è stato richiesto un giornalista
            if(userType.equals("0")){
                staffDAO = new GiornalistaDAO();
                type = 0;
            }
            else if (userType.equals("1")){
                staffDAO = new AmministratoreDAO();
                type = 1;
            }

            if( staffDAO == null || (( tmp = staffDAO.doRetriveByEmail(email) ) == null)) {
                response.sendRedirect(loginErrato);
            }
            else {
                    String dbPass = ((Persona) tmp).getPassword();

                    //confronta le password
                    if (password.equals(dbPass)) {
                        HttpSession session = request.getSession();
                        if (type == 0)
                            session.setAttribute("utente",(Giornalista)tmp);
                        else if(type == 1)
                            session.setAttribute("utente",(Amministratore)tmp);

                        response.sendRedirect(homePage);
                    } else {
                        response.sendRedirect(loginErrato);
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

