package rojinaReview.rivista.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Giornalista;
import rojinaReview.model.dao.NotiziaDAO;
import rojinaReview.rivista.service.RivistaService;
import rojinaReview.rivista.service.RivistaServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "journalistNewsServlet", value = "/journalistNewsServlet")
public class journalistNewsServlet extends HttpServlet {
    private String path = "/WEB-INF/results/giornalista/journalistNews.jsp";
    RivistaService rs = new RivistaServiceImpl();

    public journalistNewsServlet() throws SQLException {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Giornalista giornalista = (Giornalista) session.getAttribute("giornalista");
        request.setAttribute("notizieGiornalista",  rs.visualizzaNotizieScritte(giornalista));
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
