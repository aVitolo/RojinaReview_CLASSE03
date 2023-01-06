package rojinaReview.autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.autenticazione.service.AutenticazioneService;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.shop.service.ShopServiceImpl;

import java.io.IOException;

@WebServlet(name = "visualizzaRichiesteServlet", value = "/visualizzaRichiesteServlet")
public class visualizzaRichiesteServlet extends HttpServlet {

    private String path = "/WEB-INF/results/managerPages/managerRichieste.jsp";
    private AutenticazioneServiceImpl asi = new AutenticazioneServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("richieste", asi.visualizzaRichieste());
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
