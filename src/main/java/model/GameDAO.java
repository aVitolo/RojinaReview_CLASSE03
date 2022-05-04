package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDAO {

    public Game doRetrieveByTitle(String titolo) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT Titolo, DataDiRilascio, MediaVoto, NumeroVoti FROM gioco WHERE Titolo=?");
            ps.setString(1, titolo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Game g = new Game();
                g.setTitolo(rs.getString(1));
                g.setDataDiRilascio(rs.getDate(2));
                g.setCasaDiSviluppo(rs.getString(3));
                g.setMediaVoto(rs.getFloat(4));
                g.setNumeroVoti(rs.getInt(5));
                return g;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
