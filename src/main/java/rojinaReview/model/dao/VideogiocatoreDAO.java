package rojinaReview.model.dao;

import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.utilities.ConPool;

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


    public Videogiocatore doRetriveByEmail(String email) throws SQLException{
        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM videogiocatore WHERE email=? and bannato=0");
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
        else
            throw new SQLException("Invalid email");



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

    public int doInsertUser(Videogiocatore user) throws SQLException
    {
        PreparedStatement ps =
                con.prepareStatement("INSERT INTO videogiocatore (email, password, nome, cognome, immagine, nickname, bannato) VALUES (?,?,null,null,null,?,0)");
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getNickname());
        ResultSet rs;
        try {
            ps.executeUpdate();
            ps = con.prepareStatement("SELECT id FROM videogiocatore ORDER BY id DESC LIMIT 1");
            rs = ps.executeQuery();
            if(rs.next())
                return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            if(e.getClass().getSimpleName().equalsIgnoreCase("SQLIntegrityConstraintViolationException")) //integrity constraint violation
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

        return 0;
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
                con.prepareStatement("DELETE FROM videogiocatore WHERE email=?");
        ps.setString(1,email);
        int i = ps.executeUpdate();
        return i == 1;
    }

    public void update(Videogiocatore videogiocatore) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE videogiocatore SET email=?, nickname=?, nome=?, cognome=?, password=? WHERE id=?");
        ps.setString(1, videogiocatore.getEmail());
        ps.setString(2, videogiocatore.getNickname());
        ps.setString(3, videogiocatore.getNome());
        ps.setString(4, videogiocatore.getCognome());
        ps.setString(5, videogiocatore.getPassword());
        ps.setInt(6, videogiocatore.getId());
        ps.executeUpdate();
    }


}


