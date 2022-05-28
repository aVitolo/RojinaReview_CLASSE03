package model.dao;

import model.beans.Commento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentoDAO {

    public ArrayList<Commento> getCommentById(int id, String table) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT testo, dataScrittura, utente FROM ? WHERE notizia=?");
            ps.setString(1,table);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            ArrayList<Commento> commenti = new ArrayList();
            while (rs.next()) {
                commenti.add(
                        new Commento(   rs.getString(1),
                                        rs.getString(3),
                                        rs.getDate(2)));
            }
            return commenti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
