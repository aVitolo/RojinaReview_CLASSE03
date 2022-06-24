package model.dao;

import model.beans.Commento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

public class CommentoDAO {
    private Connection con;

    public CommentoDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public CommentoDAO(Connection con) {
        this.con = con;
    }

    //table: Prodotto-Recensione-Notizia
    public ArrayList<Commento> getCommentById(int id, String table) throws SQLException {

        PreparedStatement ps =
                    con.prepareStatement("SELECT testo, dataScrittura, utente FROM ? WHERE ?=?");
        ps.setString(1,"commento".concat(table));
        ps.setString(2, table);
        ps.setInt(3, id);
        ResultSet rs = ps.executeQuery();
        ArrayList<Commento> commenti = new ArrayList();
        while (rs.next()) {
            commenti.add(
                    new Commento(   rs.getString(1),
                                    rs.getString(3),
                                    rs.getDate(2)));
            }
        return commenti;
    }

}
