package rojinaReview.autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.utilities.ConPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "journalistUpdateData", value = "/journalistUpdateData")
public class journalistUpdateData extends HttpServlet {
    private String homePage = "./home";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("giornalista") == null)
                response.sendRedirect(homePage);
            else {
                String email = request.getParameter("email");
                String nome = request.getParameter("nome");
                String cognome = request.getParameter("cognome");
                String id = request.getParameter("id");

                Connection con = ConPool.getConnection();
                Statement stmt = con.createStatement();

                String query = "UPDATE Giornalista SET id = " + id +
                        ", nome = \"" + nome +
                        "\", cognome = \"" + cognome +
                        "\", email = \"" + email +
                        "\" WHERE Giornalista.id = " + id + ";";

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
