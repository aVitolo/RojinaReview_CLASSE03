package controller.mainPages;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Prodotto;
import model.beans.Recensione;
import model.dao.ProdottoDAO;
import model.dao.RecensioneDAO;
import org.json.JSONArray;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ShopServlet", value = "/ShopServlet")
public class ShopServlet extends HttpServlet {
    private String path = "/WEB-INF/results/mainPage/shop.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("articoli","shop");
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
        ProdottoDAO pDAO = null;

        try {
            pDAO = new ProdottoDAO();
            String offset = request.getParameter("offset");
            String categoria = request.getParameter("categoria");
            String ordine = request.getParameter("ordine");
            prodotti = pDAO.updateContent(offset, categoria, ordine);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

        if(prodotti != null){
            JSONArray json = new JSONArray(prodotti);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json.toString());
        }
        response.getWriter().flush(); //Calling flush() on the PrintWriter commits the response.
    }
}