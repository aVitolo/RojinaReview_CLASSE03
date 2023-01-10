package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.dao.NotiziaDAO;
import rojinaReview.model.exception.LoadingNewsException;
import rojinaReview.model.exception.ServiceNotAvailableException;
import rojinaReview.rivista.service.RivistaService;
import rojinaReview.rivista.service.RivistaServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "journalistNewsServlet", value = "/journalistNewsServlet")
public class journalistNewsServlet extends HttpServlet {
    private RivistaService rs;
    private String path;

    public journalistNewsServlet()  {

        path = "/WEB-INF/results/giornalistaPages/journalistNews.jsp";
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
            request.setAttribute("notizieGiornalista",  rs.visualizzaNotizieScritte(giornalista));
        } catch (LoadingNewsException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
