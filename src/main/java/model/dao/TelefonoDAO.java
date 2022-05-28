package model.dao;

import model.beans.Telefono;
import model.beans.Tipologia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TelefonoDAO {
    public ArrayList<Telefono> doRetriveByUser(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT tipologia FROM gioco_tipologia WHERE gioco=?");
            ResultSet rs =  ps.executeQuery();
            ArrayList<Telefono> telefoni = new ArrayList<>();
            while (rs.next())
                telefoni.add(new Telefono(rs.getString(1)));
            return telefoni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
