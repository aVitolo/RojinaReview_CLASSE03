package model.dao;

import model.beans.Telefono;
import model.utilities.ConPool;

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

}
