package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.dao.RecensioneDAO;
import rojinaReview.rivista.service.RivistaService;
import rojinaReview.rivista.service.RivistaServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "journalistReviewsServlet", value = "/journalistReviewsServlet")
public class journalistReviewsServlet extends HttpServlet {
    private String path = "/WEB-INF/results/giornalista/journalistReviews.jsp";
    private RivistaService rs = new RivistaServiceImpl();

    public journalistReviewsServlet() throws SQLException {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Giornalista giornalista = (Giornalista) session.getAttribute("giornalista");
        request.setAttribute("recensioniGiornalista", rs.visualizzaRecensioniScritte(giornalista));
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
