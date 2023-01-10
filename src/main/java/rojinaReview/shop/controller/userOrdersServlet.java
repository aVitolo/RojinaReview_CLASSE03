package rojinaReview.shop.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.exception.LoadingOrderException;
import rojinaReview.shop.service.ShopService;
import rojinaReview.shop.service.ShopServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "userOrdersServlet", value = "/userOrdersServlet")
public class userOrdersServlet extends HttpServlet {
    private ShopService ss;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ss = new ShopServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String result = "/WEB-INF/results/videogiocatorePages/userOrders.jsp";
        HttpSession session = request.getSession();
        Videogiocatore videogiocatore = (Videogiocatore) session.getAttribute("videogiocatore");
        try {
            videogiocatore.setOrdini(ss.visualizzaOrdiniEffettuati(videogiocatore));
        } catch (LoadingOrderException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(result).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
