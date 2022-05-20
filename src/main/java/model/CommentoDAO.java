package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentoDAO {

    public ArrayList<Commento> getCommentByArticol(String titolo) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT Testo, Data, User FROM commento WHERE Articolo=?");
            ps.setString(1, titolo);
            ResultSet rs = ps.executeQuery();
            CommentoDAO cDAO = new CommentoDAO();
            ArrayList<Commento> commenti = new ArrayList();
            while (rs.next()) {
                Commento c = new Commento();
                c.setTesto(rs.getString(1));
                c.setData(rs.getDate(2));
                c.setUser(rs.getString(3));
                commenti.add(c);
            }
            return commenti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
