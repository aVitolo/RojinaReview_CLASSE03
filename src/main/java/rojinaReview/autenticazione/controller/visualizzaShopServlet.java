package rojinaReview.autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.dao.VideogiocoDAO;
import rojinaReview.shop.service.ShopServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "visualizzaShopServlet", value = "/visualizzaShopServlet")
public class visualizzaShopServlet extends HttpServlet {


    private String path = "/WEB-INF/results/managerPages/managerShop.jsp";
    private ShopServiceImpl ssi = new ShopServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("prodotti", ssi.visualizzaShop());
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
