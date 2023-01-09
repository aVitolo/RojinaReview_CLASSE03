package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.dao.RecensioneDAO;
import rojinaReview.model.exception.LoadingReviewsException;
import rojinaReview.model.exception.ServiceNotAvailableException;
import rojinaReview.rivista.service.RivistaService;
import rojinaReview.rivista.service.RivistaServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "journalistReviewsServlet", value = "/journalistReviewsServlet")
public class journalistReviewsServlet extends HttpServlet {
    private RivistaService rs;
    private String path;

    public journalistReviewsServlet() throws ServiceNotAvailableException {
        path = "/WEB-INF/results/giornalista/journalistReviews.jsp";
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            rs = new RivistaServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        Giornalista giornalista = (Giornalista) session.getAttribute("giornalista");
        try {
            request.setAttribute("recensioniGiornalista", rs.visualizzaRecensioniScritte(giornalista));
        } catch (LoadingReviewsException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
