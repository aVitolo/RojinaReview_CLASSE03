package rojinaReview.controller.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.utilities.ConPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "userUpdateData", value = "/userUpdateData")
public class userUpdateData extends HttpServlet {
    private String homePage = "./home";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("utente") == null)
                response.sendRedirect(homePage);
            else {
                String email = request.getParameter("email");
                String nickname = request.getParameter("nickname");
                String nome = request.getParameter("nome");
                String cognome = request.getParameter("cognome");
                String eta = request.getParameter("eta");

                Connection con = ConPool.getConnection();
                Statement stmt = con.createStatement();

                String query = "UPDATE Utente SET email = '" + email +
                        "', nickname = '" + nickname +
                        "', nome = '" + nome +
                        "', cognome = '" + cognome +
                        "', età = " + eta +
                        " WHERE Utente.email = '" + email + "';";

                if (stmt.executeUpdate(query) >= 1) {
                    response.sendRedirect("./logout");
                } else {
                    response.sendRedirect("./home");
                }

                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

