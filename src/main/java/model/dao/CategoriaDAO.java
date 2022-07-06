package model.dao;

import model.beans.Categoria;
import model.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO {
    private final Connection con;

    public CategoriaDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public CategoriaDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<Categoria> doRetrieveByProductId(int id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT categoria FROM prodotto_categoria WHERE prodotto=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        ArrayList<Categoria> categorie = new ArrayList<>();
        while (rs.next())
            categorie.add(new Categoria(rs.getString(1)));
        return categorie;
    }

    public ArrayList<Categoria> doRetrieveAll() throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT DISTINCT categoria FROM prodotto_categoria");
        ResultSet rs = ps.executeQuery();
        ArrayList<Categoria> categorie = new ArrayList<>();
        while (rs.next())
            categorie.add(new Categoria(rs.getString(1)));
        return categorie;
    }


}
