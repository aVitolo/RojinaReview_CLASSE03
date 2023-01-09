package rojinaReview.autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.autenticazione.service.AutenticazioneService;
import rojinaReview.autenticazione.service.AutenticazioneServiceImpl;
import rojinaReview.model.beans.Utente;
import rojinaReview.model.exception.LoadingRegistrationRequestsException;
import rojinaReview.shop.service.ShopServiceImpl;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "visualizzaRichiesteServlet", value = "/visualizzaRichiesteServlet")
public class visualizzaRichiesteServlet extends HttpServlet {

    private String path = "/WEB-INF/results/managerPages/managerRichieste.jsp";
    private AutenticazioneServiceImpl asi;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            asi = new AutenticazioneServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<ArrayList<Utente>> inQueeue = null;
        try {
            inQueeue = asi.visualizzaRichieste();
        } catch (LoadingRegistrationRequestsException e) {
            e.printStackTrace();
        }
        request.setAttribute("giornalisti",inQueeue.get(0));
        request.setAttribute("managers",inQueeue.get(1));

        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
