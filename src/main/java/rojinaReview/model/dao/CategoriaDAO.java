package rojinaReview.model.dao;

import rojinaReview.model.utilities.ConPool;

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

    public String doRetrieveByProductId(int id) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT nome_categoria FROM prodotto WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return rs.getString(1);
        return null;
    }

    public ArrayList<String> doRetrieveAll() throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT nome FROM categoria");
        ResultSet rs = ps.executeQuery();
        ArrayList<String> categorie = new ArrayList<>();
        while (rs.next())
            categorie.add(rs.getString(1));
        return categorie;
    }


}
