package rojinaReview.startConfigurations;

import com.oracle.wls.shaded.org.apache.xml.dtm.ref.sax2dtm.SAX2RTFDTM;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.*;
import rojinaReview.model.dao.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ContextSetUpingServlet", value = "/ContextSetUpingServlet")
public class ContextSetUpingServlet extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {

        try {
            ArrayList<String>  piattaforme = new PiattaformaDAO().doRetrieveAll();
            ArrayList<String>  generi = new GenereDAO().doRetrieveAll();
            ArrayList<String>  categorie = new CategoriaDAO().doRetrieveAll();
            config.getServletContext().setAttribute("piattaforme", piattaforme);
            config.getServletContext().setAttribute("generi", generi);
            config.getServletContext().setAttribute("categorie", categorie);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
