package rojinaReview.autenticazione.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rojinaReview.model.beans.Manager;
import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.utilities.Persona;
import rojinaReview.model.dao.AmministratoreDAO;
import rojinaReview.model.dao.GiornalistaDAO;
import rojinaReview.model.utilities.GenericStaffDAO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginStaff extends HttpServlet {
    private Videogiocatore tmp;
    private String loginErrato = "./staffLogin.jsp";
    private String homePage = "/Rojina_Review_war/home";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Preleva dalla request i parametri -email -password -userType
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //userType = 0 Per i Giornalisti, userType = 1 Per gli Admin
        String userType = request.getParameter("userType");
        //Calcola Hash della password utente
        password = Persona.calcolaHash(password);

        int type = -1;
        Persona tmp = null;
        GenericStaffDAO staffDAO = null;
        try {
            //Verifica se è stato richiesto un giornalista
            if (userType.equals("0")) {
                staffDAO = new GiornalistaDAO();
                type = 0;
            } else if (userType.equals("1")) {
                staffDAO = new AmministratoreDAO();
                type = 1;
            }

            if (staffDAO == null || ((tmp = (Persona) staffDAO.doRetriveByEmail(email)) == null)) {
                String message = "Invalid email";
                request.setAttribute("message", message);
                RequestDispatcher dispatcher = request.getRequestDispatcher(loginErrato);
                dispatcher.forward(request, response);
            } else {
                String dbPass = tmp.getPassword();

                //confronta le password
                if (password.equals(dbPass)) {
                    HttpSession session = request.getSession();
                    if (type == 0) {
                        Giornalista g = (Giornalista) tmp;
                        session.setAttribute("giornalista", g);
                    } else if (type == 1) {
                        Manager a = (Manager) tmp;
                        session.setAttribute("admin", (Manager) tmp);
                    }

                    session.removeAttribute("ospite");
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

