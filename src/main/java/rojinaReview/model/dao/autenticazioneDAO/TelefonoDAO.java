package rojinaReview.model.dao.autenticazioneDAO;

import rojinaReview.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TelefonoDAO {
    private final Connection con;

    public TelefonoDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public TelefonoDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<String> doRetriveByUser(int user) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT numero FROM Telefono WHERE id_videogiocatore=?");
        ps.setInt(1, user);
        ResultSet rs = ps.executeQuery();
        ArrayList<String> telefoni = new ArrayList<>();
        while (rs.next())
            telefoni.add(rs.getString(1));

        return telefoni;

    }

    public void doSave(int user, String t) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO telefono VALUES (?,?)");
        ps.setString(1, t);
        ps.setInt(2, user);

        if(ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");
    }

}
