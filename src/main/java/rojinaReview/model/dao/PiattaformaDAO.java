package rojinaReview.model.dao;

import rojinaReview.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PiattaformaDAO {
    private final Connection con;

    public PiattaformaDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public PiattaformaDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<String> doRetrieveAll() throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT nome FROM piattaforma");
        ResultSet rs = ps.executeQuery();
        ArrayList<String> piattaforme = new ArrayList<>();
        while (rs.next())
            piattaforme.add(rs.getString(1));

        return piattaforme;
    }

    public ArrayList<String> doRetriveByGame(int game) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT piattaforma FROM videogioco_piattaforma WHERE id_videogioco=?");
        ps.setInt(1, game);
        ResultSet rs = ps.executeQuery();
        ArrayList<String> piattaforme = new ArrayList<>();
        while (rs.next())
            piattaforme.add(rs.getString(1));

        return piattaforme;
    }

    public void doSave(int gioco, ArrayList<String> piattaforme) throws SQLException {
        PreparedStatement ps;
        for (String p : piattaforme) {
            ps = con.prepareStatement("INSERT INTO videogioco_piattaforma VALUES (?,?)");

            ps.setInt(1, gioco);
            ps.setString(2, p);

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("Insert error");
        }
    }
}
