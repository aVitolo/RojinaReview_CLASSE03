package rojinaReview.startConfigurations;

import com.oracle.wls.shaded.org.apache.xml.dtm.ref.sax2dtm.SAX2RTFDTM;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.*;
import rojinaReview.model.dao.*;
import rojinaReview.model.exception.LoadingCategoriesException;
import rojinaReview.model.exception.LoadingGenresException;
import rojinaReview.model.exception.LoadingPlatformsException;
import rojinaReview.rivista.service.RivistaService;
import rojinaReview.rivista.service.RivistaServiceImpl;
import rojinaReview.shop.service.ShopService;
import rojinaReview.shop.service.ShopServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "ContextSetUpingServlet", value = "/ContextSetUpingServlet")
public class ContextSetUpingServlet extends HttpServlet {
    private RivistaService rs;
    private ShopService ss;

    public void init(ServletConfig config) throws ServletException {
        try {
            rs = new RivistaServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ss = new ShopServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ArrayList<String>  piattaforme = rs.visualizzaPiattaforme();
            ArrayList<String>  generi = rs.visualizzaGeneri();
            ArrayList<String>  categorie = ss.visualizzaCategorie();
            config.getServletContext().setAttribute("piattaforme", piattaforme);
            config.getServletContext().setAttribute("generi", generi);
            config.getServletContext().setAttribute("categorie", categorie);
        } catch (LoadingPlatformsException e) {
            e.printStackTrace();
        } catch (LoadingGenresException e) {
            e.printStackTrace();
        } catch (LoadingCategoriesException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
