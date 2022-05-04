package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsDAO {

    public News doRetrieveByTitle(String titolo) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT g.Nome,g.Cognome, n.Titolo, n.DataCaricamento FROM notizia n JOIN giornalista g on g.CF = n.Giornalista WHERE n.Titolo=?");
            ps.setString(1, titolo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                News n = new News();
                n.setGiornalista(rs.getString(1)+" "+rs.getString(2));
                n.setTitolo(rs.getString(3));/* verificare compatibilita tipi */
                n.setDataCaricamento(rs.getDate(4));
                return n;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
