package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {

    public Product doRetriveById(String id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM product WHERE Id=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            Product p = new Product();
            if (rs.next()) {
                p.setId(rs.getString(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setPrezzo(rs.getFloat(4));
                p.setSconto(rs.getFloat(5));
                p.setDisponibilità(rs.getString(6));

                ps = con.prepareStatement("SELECT tipologia FROM categorie WHERE prodotto=?");
                rs = ps.executeQuery();
                while (rs.next())
                    p.getCategorie().add(rs.getString(1));

                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
