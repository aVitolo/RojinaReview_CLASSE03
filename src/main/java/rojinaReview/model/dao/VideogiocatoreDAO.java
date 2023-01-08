package rojinaReview.model.dao;

import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.beans.Videogioco;
import rojinaReview.model.utilities.ConPool;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;

public class VideogiocatoreDAO {
    private final Connection con;

    public Connection getCon() {
        return con;
    }

    public VideogiocatoreDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public VideogiocatoreDAO(Connection con) {
        this.con = con;
    }


    public Videogiocatore doRetriveByEmail(String email) throws SQLException, UnsupportedEncodingException {
        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM videogiocatore WHERE email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Videogiocatore v = new Videogiocatore();
            v.setId(rs.getInt(1));
            v.setEmail(rs.getString(2));
            v.setPassword(rs.getString(3));
            v.setNome(rs.getString(4));
            v.setCognome(rs.getString(5));
            v.setImmagine(rs.getString(6));
            v.setNickname(rs.getString(7));

            return v;
        }

        return null;
    }

    public Videogiocatore doRetriveByNickname(String nickname) throws SQLException, UnsupportedEncodingException {
        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM videogiocatore WHERE nickname=?");
        ps.setString(1, nickname);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Videogiocatore v = new Videogiocatore();

            v.setId(rs.getInt(1));
            v.setEmail(rs.getString(2));
            v.setPassword(rs.getString(3));
            v.setNome(rs.getString(4));
            v.setCognome(rs.getString(5));
            v.setImmagine(rs.getString(6));
            v.setNickname(rs.getString(7));

            return v;
        }

        return null;
    }

    public Videogiocatore doRetriveById(int videogiocatore) throws SQLException, UnsupportedEncodingException {
        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM videogiocatore WHERE id=?");
        ps.setInt(1, videogiocatore);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Videogiocatore v = new Videogiocatore();

            v.setId(rs.getInt(1));
            v.setEmail(rs.getString(2));
            v.setPassword(rs.getString(3));
            v.setNome(rs.getString(4));
            v.setCognome(rs.getString(5));
            v.setImmagine(rs.getString(6));
            v.setNickname(rs.getString(7));
            return v;
        }

        return null;
    }

    public void doInsertUser(Videogiocatore user) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("INSERT INTO videogiocatore VALUES(?,?,null,null,null,?,0)");
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getNickname());
        ResultSet rs;
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            if(e.getErrorCode() == 23000) //integrity constraint violation
            {
                ps = con.prepareStatement("SELECT email FROM videogiocatore WHERE email=?");
                ps.setString(1, user.getEmail());
                rs = ps.executeQuery();
                if(rs.next())
                    throw new SQLException("email");

                ps = con.prepareStatement("SELECT nickname FROM videogiocatore WHERE nickname=?");
                ps.setString(1, user.getNickname());
                rs = ps.executeQuery();
                if(rs.next())
                    throw new SQLException("nickname");
            }
        }
    }

    public ArrayList<Videogiocatore> doRetriveAll() throws SQLException, UnsupportedEncodingException {
        ArrayList<Videogiocatore> list = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM videogiocatore");

        while(rs.next()){
            Videogiocatore v = new Videogiocatore();

            v.setId(rs.getInt(1));
            v.setEmail(rs.getString(2));
            v.setPassword(rs.getString(3));
            v.setNome(rs.getString(4));
            v.setCognome(rs.getString(5));
            v.setImmagine(rs.getString(6));
            v.setNickname(rs.getString(7));


            list.add(v);
        }
        return list;
    }

    public void banUser(int user) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE videogiocatore SET bannato = 1 WHERE id=?");
        ps.setInt(1, user);
        ps.executeUpdate();
    }

    //non serve?
    public boolean doRemoveByEmail(String email) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("DELETE FROM Utente WHERE email=?");
        ps.setString(1,email);
        int i = ps.executeUpdate();
        return i == 1;
    }


}


