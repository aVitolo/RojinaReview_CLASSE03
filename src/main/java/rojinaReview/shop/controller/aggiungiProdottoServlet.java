package rojinaReview.shop.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Prodotto;
import rojinaReview.model.dao.ProdottoDAO;
import rojinaReview.model.exception.ProductIDMissingException;
import rojinaReview.model.utilities.Utils;
import rojinaReview.shop.service.ShopService;
import rojinaReview.shop.service.ShopServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "aggiungioProdottoServlet", value = "/aggiungioProdottoServlet")
@MultipartConfig
public class aggiungiProdottoServlet extends HttpServlet {
    private ShopService ss;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ss = new ShopServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //prodotto da inserire preso dal form
        Prodotto prodotto = new Prodotto();

        prodotto.setNome(request.getParameter("nome"));
        prodotto.setTesto(request.getParameter("descrizione"));
        prodotto.setCategoria(request.getParameter("productType"));
        prodotto.setPrezzo(Float.parseFloat(request.getParameter("prezzo")));
        prodotto.setQuantità(Integer.parseInt(request.getParameter("quantita")));

        Part filePart = request.getPart("foto");
        String imageType = "products";
        String fileName = prodotto.getNome() + ".jpg";
        prodotto.setImmagine(Utils.saveImageWar(imageType, fileName, filePart));
        Utils.saveImageFileSystem(imageType, fileName, filePart);

        try {
            ss.inserisciProdotto(prodotto);
        } catch (ProductIDMissingException e) {
            e.printStackTrace();
        }
        String result = "/Rojina_Review_war/visualizzaShop";
        response.sendRedirect(result);
    }
}
