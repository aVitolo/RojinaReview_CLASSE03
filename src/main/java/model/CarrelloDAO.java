package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarrelloDAO {

    public ArrayList<Prodotto> getProductByOrder(String id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM carrello WHERE Order=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> products = new ArrayList<>();
            ProdottoDAO prodottoDAO = new ProdottoDAO();
            while(rs.next()) {
                Prodotto p = new Prodotto();
                p = prodottoDAO.doRetriveById(rs.getString(1)); /*definrie indice colonna di prodotto */
                p.setSconto(rs.getFloat(2)); /* definire indice colonna sconto */
                p.setPrezzo(rs.getFloat(3)); /* definire indice colonna prezzo */
                products.add(p);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Prodotto> getUserCart(String user) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM carrello WHERE Order=null and User=?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> products = new ArrayList<>();
            ProdottoDAO prodottoDAO = new ProdottoDAO();
            while(rs.next()) {
                Prodotto p = new Prodotto();
                p = prodottoDAO.doRetriveById(rs.getString(1)); /*definrie indice colonna di prodotto */
                products.add(p);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
