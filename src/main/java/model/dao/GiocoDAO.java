package model.dao;

import model.beans.Gioco;
import model.beans.Piattaforma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GiocoDAO {
    private Connection con;

    public GiocoDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public GiocoDAO(Connection con) {
        this.con = con;
    }

    public Gioco doRetrieveByTitle(String titolo) throws SQLException {
        PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM gioco WHERE titolo=?");
        ps.setString(1, titolo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Gioco(
                    rs.getString(1),
                    rs.getString(3),
                    rs.getInt(5),
                    rs.getInt(4),
                    rs.getDate(2),
                    rs.getBytes(6),
                    new PiattaformaDAO(con).doRetriveByGame(titolo),
                    new TipologiaDAO(con).doRetriveByGame(titolo));
            }

        return null;


    }

    public ArrayList<Gioco> getGiocoByIdNotizia(int id) throws SQLException {
            PreparedStatement ps =
                    con.prepareStatement("SELECT g.* FROM gioco g join gioco_notizia gn on  g.titolo=gn.gioco WHERE notizia=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ArrayList<Gioco> g = new ArrayList<>();
            while (rs.next())
                g.add(new Gioco(
                    rs.getString(1),
                    rs.getString(3),
                    rs.getInt(5),
                    rs.getInt(4),
                    rs.getDate(2),
                    rs.getBytes(6),
                    new PiattaformaDAO(con).doRetriveByGame(rs.getString(1)),
                    new TipologiaDAO(con).doRetriveByGame(rs.getString(1))));
            return g;

    }

}
