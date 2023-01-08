package rojinaReview.shop.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import rojinaReview.model.beans.Prodotto;
import rojinaReview.model.exception.ProductIDMissingException;
import rojinaReview.model.utilities.Utils;
import rojinaReview.shop.service.ShopServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "modificaProdottoServlet", value = "/modificaProdottoServlet")
public class modificaProdottoServlet extends HttpServlet {
    ShopServiceImpl ssi;

    {
        try {
            ssi = new ShopServiceImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(request.getParameter("nome"));
        prodotto.setTesto(request.getParameter("descizione"));
        prodotto.setCategoria(request.getParameter("productType"));
        prodotto.setPrezzo(Float.parseFloat(request.getParameter("prezzo")));
        prodotto.setQuantità(Integer.parseInt(request.getParameter("quantita")));
        prodotto.setId(Integer.parseInt(request.getParameter("id")));
        if(request.getPart("foto")==null){
            prodotto.setImmagine("null");
        }
        else{
        Part filePart = request.getPart("foto");
        String imageType = "products";
        String fileName = prodotto.getNome() + ".jpg";
        prodotto.setImmagine(Utils.saveImageWar(imageType, fileName, filePart));
        Utils.saveImageFileSystem(imageType, fileName, filePart);
        }

        try {
            ssi.modificaProdotto(prodotto);
        } catch (ProductIDMissingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
