package rojinaReview.model.dao;

import rojinaReview.model.beans.Tipologia;
import rojinaReview.model.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenereDAO {
    private final Connection con;

    public GenereDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public GenereDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<String> doRetrieveAll() throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT nome FROM genere");
        ResultSet rs = ps.executeQuery();
        ArrayList<String> generi = new ArrayList<>();
        while (rs.next())
            generi.add(rs.getString(1));

        return generi;
    }

    public ArrayList<String> doRetriveByGame(int game) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT genere FROM videogioco_genere WHERE id_videogioco=?");
        ps.setInt(1, game);
        ResultSet rs = ps.executeQuery();
        ArrayList<String> generi = new ArrayList<>();
        while (rs.next())
            generi.add(rs.getString(1));

        return generi;

    }

    public void doSave(int gioco, ArrayList<String> generi) throws SQLException {
        PreparedStatement ps;
        for (String g : generi) {
            ps = con.prepareStatement("INSERT INTO videogioco_genere VALUES (?,?)");

            ps.setInt(1, gioco);
            ps.setString(2, g);

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("Insert error");
        }
    }

}
