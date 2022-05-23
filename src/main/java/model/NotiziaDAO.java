package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotiziaDAO {

    /*
        Considerazioni
        -Aggiungere Corpo e Sottoticolo alla tabella Notizia
        -Aggiungere la tabella Commento al DB
     */

    public Notizia doRetrieveById(int id ) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT g.Nome,g.Cognome, n.id, n.titolo, n.testo, n.dataCaricamento FROM notizia n JOIN giornalista g on g.id = n.giornalista WHERE n.id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Notizia n = new Notizia();
                n.setGiornalista(rs.getString(1)+" "+rs.getString(2));
                n.setId(rs.getInt(3));
                n.setTitolo(rs.getString(4));
                n.setTesto(rs.getString(5));
                n.setDataCaricamento(rs.getDate(6));
                n.setCommenti(new CommentoDAO().getCommentByIdNotizia(id));
                n.setGiochi(new GiocoDAO().getGiocoByIdNotizia(id));
                return n;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Notizia> doRetrieveLast() {
        return null;
    }
}
