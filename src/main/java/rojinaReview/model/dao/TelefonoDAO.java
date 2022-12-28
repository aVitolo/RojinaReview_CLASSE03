package rojinaReview.model.dao;

import rojinaReview.model.beans.Telefono;
import rojinaReview.model.utilities.ConPool;

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

    public ArrayList<Telefono> doRetriveByUser(String user) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT numero FROM Telefono WHERE utente=?");
        ps.setString(1, user);
        ResultSet rs = ps.executeQuery();
        ArrayList<Telefono> telefoni = new ArrayList<>();
        while (rs.next())
            telefoni.add(new Telefono(rs.getString(1)));

        return telefoni;

    }

    public void doSave(String user, Telefono t) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO telefono VALUES (?,?)");
        ps.setString(1, user);
        ps.setString(2, t.getNumero());

        if(ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");
    }

}
