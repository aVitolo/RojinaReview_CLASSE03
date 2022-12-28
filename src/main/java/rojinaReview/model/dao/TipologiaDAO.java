package rojinaReview.model.dao;

import rojinaReview.model.beans.Tipologia;
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

    public TipologiaDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<Tipologia> doRetrieveAll() throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT nome FROM tipologia");
        ResultSet rs = ps.executeQuery();
        ArrayList<Tipologia> tipologie = new ArrayList<>();
        while (rs.next())
            tipologie.add(new Tipologia(rs.getString(1)));
        return tipologie;
    }

    public ArrayList<Tipologia> doRetriveByGame(String titolo) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT tipologia FROM gioco_tipologia WHERE gioco=?");
        ps.setString(1, titolo);
        ResultSet rs = ps.executeQuery();
        ArrayList<Tipologia> tipologie = new ArrayList<>();
        while (rs.next())
            tipologie.add(new Tipologia(rs.getString(1)));

        return tipologie;

    }

    public void doSave(String gioco, ArrayList<Tipologia> tipologie) throws SQLException {
        PreparedStatement ps;
        for (Tipologia t : tipologie) {
            ps = con.prepareStatement("INSERT INTO gioco_tipologia VALUES (?,?)");

            ps.setString(1, gioco);
            ps.setString(2, t.getNome());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("Insert error");
        }
    }

}
