package rojinaReview.startConfigurations;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.*;
import rojinaReview.model.dao.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ContextSetUpingServlet", value = "/ContextSetUpingServlet")
public class ContextSetUpingServlet extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {





    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
