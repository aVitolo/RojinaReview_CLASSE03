package model.dao;

import model.beans.Commento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentoDAO {

    public ArrayList<Commento> getCommentByIdNotizia(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT testo, dataScrittura, utente FROM commentonotizia WHERE notizia=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
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

    public ArrayList<Commento> getCommentByIdRecensione(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT testo, dataScrittura, utente FROM commentorecensione WHERE recensione=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
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
