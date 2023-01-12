package rojinaReview.opinione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.opinione.service.OpinioneServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "gestisicSegnalazionServlet", value = "/gestisicSegnalazionServlet")
public class gestisicSegnalazionServlet extends HttpServlet {
    private OpinioneServiceImpl osi;
    private String address = "/Rojina_Review_war/visualizzaCommentiSegnalati";
    {
        try {
            osi = new OpinioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            osi.gestisciSegnalazione(
                    Integer.parseInt(request.getParameter("flag")),
                    Integer.parseInt(request.getParameter("id")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect(address);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
