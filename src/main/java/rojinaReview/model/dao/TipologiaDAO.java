package rojinaReview.model.dao;

import rojinaReview.model.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipologiaDAO {

    private final Connection con;

    public TipologiaDAO() throws SQLException {
        con = ConPool.getConnection();
    }
    public ArrayList<String> doRetrieveAll() throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT nome FROM Tipologie");
        ResultSet rs = ps.executeQuery();
        ArrayList<String> tipologie = new ArrayList<>();
        while (rs.next())
            tipologie.add(rs.getString(1));
        return tipologie;
    }

    public void doSave(int id, ArrayList<String> generi) {
    }
}
