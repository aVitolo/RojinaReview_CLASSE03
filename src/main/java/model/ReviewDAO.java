package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewDAO {

    public Review doRetrieveByTitle(String titolo) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT g.Nome,g.Cognome, r.Gioco, r.Titolo, r.Voto, r.DataCaricamento FROM recensione r JOIN giornalista g on g.CF = r.Giornalista WHERE r.Titolo=?");
            ps.setString(1, titolo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Review r = new Review();
                r.setGiornalista(rs.getString(1)+" "+rs.getString(2));
                r.setGioco(rs.getString(3));
                r.setTitolo(rs.getString(4));
                r.setVoto(rs.getFloat(5));
                r.setDataCaricamento(rs.getDate(6));
                return r;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
