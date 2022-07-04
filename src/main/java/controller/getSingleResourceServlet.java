package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Notizia;
import model.beans.Prodotto;
import model.beans.Recensione;
import model.dao.CommentoDAO;
import model.dao.NotiziaDAO;
import model.dao.ProdottoDAO;
import model.dao.RecensioneDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "getSingleResourceServlet", value = "/getSingleResourceServlet")
public class getSingleResourceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String result = null;
        int id = Integer.parseInt(request.getParameter("id"));
        int i;
        boolean trovato = false;
        ServletContext context = request.getServletContext();

        if(type.equalsIgnoreCase("notizia"))
        {
            //cerco prima nel context la notizia
            ArrayList<Notizia> notizieContext = (ArrayList<Notizia>) context.getAttribute("notizie");
            for(i = 0; i < notizieContext.size() && !trovato; i++)
            {
                if(notizieContext.get(i).getId() == id)
                {
                    trovato = true;
                    request.setAttribute("notizia", notizieContext.get(i));
                    try {
                        request.setAttribute("commenti", new CommentoDAO().getCommentById(id, "Notizia"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(trovato = false)
            {
                try {
                    Notizia n = new NotiziaDAO().doRetrieveById(id);
                    request.setAttribute("notizia", n);
                    request.setAttribute("commenti", new CommentoDAO().getCommentById(id, "Notizia"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            result = "/WEB-INF/results/notizia.jsp";
        }

        else if(type.equalsIgnoreCase("recensione")){
            //cerco prima nel context la recensione
            ArrayList<Recensione> recensioniContext = (ArrayList<Recensione>) context.getAttribute("recensioni");
            for(i = 0; i < recensioniContext.size() && !trovato; i++)
            {
                if(recensioniContext.get(i).getId() == id)
                {
                    trovato = true;
                    request.setAttribute("recensione", recensioniContext.get(i));
                    try {
                        request.setAttribute("commenti", new CommentoDAO().getCommentById(id, "Recensione"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(trovato = false)
            {
                try {
                    Recensione r = new RecensioneDAO().doRetrieveById(id);
                    request.setAttribute("recensione", r);
                    request.setAttribute("commenti", new CommentoDAO().getCommentById(id, "Recensione"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            result = "/WEB-INF/results/recensione.jsp";
        }

        else if(type.equalsIgnoreCase("prodotto"))
        {
            //cerco prima nel context il prodotto
            ArrayList<Prodotto> prodottiContext = (ArrayList<Prodotto>) context.getAttribute("prodotti");
            for(i = 0; i < prodottiContext.size() && !trovato; i++)
            {
                if(prodottiContext.get(i).getId() == id)
                {
                    trovato = true;
                    request.setAttribute("prodotto", prodottiContext.get(i));
                    try {
                        request.setAttribute("commenti", new CommentoDAO().getCommentById(id, "Prodotto"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(trovato = false)
            {
                try {
                    Prodotto p = new ProdottoDAO().doRetrieveById(id);
                    request.setAttribute("prodotto", p);
                    request.setAttribute("commenti", new CommentoDAO().getCommentById(id, "Prodotto"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            result = "/WEB-INF/results/prodotto.jsp";
        }

        request.getRequestDispatcher(result).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
