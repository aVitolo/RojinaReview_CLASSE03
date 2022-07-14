package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.utilities.ConPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "InsertDbEntry", value = "/InsertDbEntry")
public class InsertDbEntry extends HttpServlet {
    private String homePage = "./home";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("admin") == null)
                response.sendRedirect(homePage);
            else {
                String query = request.getParameter("query");
                Connection con = ConPool.getConnection();
                Statement stmt = con.createStatement();

                query = "INSERT INTO " + query;
                if (stmt.executeUpdate(query) >= 1)
                    response.getWriter().print("true");
                else
                    response.getWriter().print("false");

                con.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
