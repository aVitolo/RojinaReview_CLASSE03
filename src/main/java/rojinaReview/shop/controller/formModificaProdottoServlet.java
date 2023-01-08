package rojinaReview.shop.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Prodotto;
import rojinaReview.model.exception.ProductIDMissingException;
import rojinaReview.shop.service.ShopServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "formModificaProdottoServlet", value = "/formModificaProdottoServlet")
public class formModificaProdottoServlet extends HttpServlet {
    private String path = "/WEB-INF/results/managerPages/managerModificaProdotto.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Prodotto prodotto = null;
        try {
            prodotto = new ShopServiceImpl().visualizzaProdotto(Integer.parseInt(request.getParameter("prodotto")));
        } catch (ProductIDMissingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("prodotto",prodotto);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
