package rojinaReview.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.utilities.WrapperDaoAdminPanel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RemoveDbEntry", value = "/RemoveDbEntry")
public class RemoveDbEntry extends HttpServlet {
    private String homePage = "./home";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("admin") == null)
                response.sendRedirect(homePage);
            else {
                String className = request.getParameter("className");
                String identifier = request.getParameter("identifier");

                WrapperDaoAdminPanel.doRemoveEntry(className, identifier);
                response.getWriter().print(true);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

