package model.dao;

import model.beans.Carrello;
import model.beans.Categoria;
import model.beans.Prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO {

    public ArrayList<Categoria> doRetrieveByProductId(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                  con.prepareStatement("SELECT categoria FROM prodotto_categoria WHERE prodotto=?");
            ResultSet rs =  ps.executeQuery();
            ArrayList<Categoria> categorie = new ArrayList<>();
            while (rs.next())
                categorie.add(new Categoria(rs.getString(1)));
            return categorie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
