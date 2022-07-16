package model.dao;

import jakarta.activation.CommandMap;
import model.beans.Commento;
import model.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class CommentoDAO {
    private Connection con;

    public CommentoDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public CommentoDAO(Connection con) {
        this.con = con;
    }

    /*table: Prodotto-Recensione-Notizia*/
    public ArrayList<Commento> getCommentById(int id, String table) throws SQLException {
        String commentTable = "Commento".concat(table);
        String query = "SELECT testo, dataScrittura, utente, " + table.toLowerCase(Locale.ROOT) + " " + "FROM "+commentTable+" "+"WHERE "+table.toLowerCase(Locale.ROOT)+"=? "+"ORDER BY dataScrittura DESC";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        ArrayList<Commento> commenti = new ArrayList();
        while (rs.next()) {
            commenti.add(
                    new Commento(   rs.getString(1),
                                    rs.getString(3),
                                    rs.getTimestamp(2),
                                    rs.getInt(4),
                                    table));
            }



        return commenti;

    }

    public ArrayList<Commento> getCommentByUser(String email) throws SQLException {
        ArrayList<Commento> commenti = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT testo, dataScrittura, utente, recensione FROM commentorecensione WHERE utente=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            Commento c = new Commento();
            c.setTesto(rs.getString(1));
            c.setData(rs.getTimestamp(2));
            c.setUtente(rs.getString(3));
            c.setId(rs.getInt(4));
            c.setResource("recensione");
            commenti.add(c);
        }

        ps = con.prepareStatement("SELECT testo, dataScrittura, utente, notizia FROM commentonotizia WHERE utente=?");
        ps.setString(1, email);
        rs = ps.executeQuery();

        while (rs.next())
        {
            Commento c = new Commento();
            c.setTesto(rs.getString(1));
            c.setData(rs.getTimestamp(2));
            c.setUtente(rs.getString(3));
            c.setId(rs.getInt(4));
            c.setResource("notizia");
            commenti.add(c);
        }

        ps = con.prepareStatement("SELECT testo, dataScrittura, utente, prodotto FROM commentoprodotto WHERE utente=?");
        ps.setString(1, email);
        rs = ps.executeQuery();

        while (rs.next())
        {
            Commento c = new Commento();
            c.setTesto(rs.getString(1));
            c.setData(rs.getTimestamp(2));
            c.setUtente(rs.getString(3));
            c.setId(rs.getInt(4));
            c.setResource("prodotto");
            commenti.add(c);
        }

        commenti.sort(Comparator.comparing(c -> c.getData()));

        return commenti;

    }

    public void doSave(Commento c) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO commento"+c.getResource()+" VALUES (?, ?, ?, ?)");
        ps.setString(1, c.getUtente());
        ps.setString(2, c.getTesto());
        ps.setTimestamp(3, c.getData());
        ps.setInt(4, c.getId());

        if(ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");
    }

}
