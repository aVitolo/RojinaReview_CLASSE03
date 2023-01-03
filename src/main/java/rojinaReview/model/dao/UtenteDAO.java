package rojinaReview.model.dao;

import rojinaReview.model.beans.Videogiocatore;
import rojinaReview.model.utilities.ConPool;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;

public class UtenteDAO {
    private final Connection con;

    public Connection getCon() {
        return con;
    }

    public UtenteDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public UtenteDAO(Connection con) {
        this.con = con;
    }

    public Videogiocatore doRetriveByEmail(String email) throws SQLException, UnsupportedEncodingException {
        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM Utente WHERE email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Videogiocatore(
                    rs.getInt("età"),
                    rs.getString("email"),
                    rs.getString("nickname"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("pass"),
                    new IndirizzoDAO(con).doRetriveByUser(email),
                    new TelefonoDAO(con).doRetriveByUser(email),
                    new PagamentoDAO(con).doRetrieveByUser(email),
                    new OrdineDAO(con).doRetrieveByUser(email),
                    new CarrelloDAO(con).doRetrieveByUser(email),
                    rs.getString("immagine")
            );
        }

        return null;
    }

    public Videogiocatore doRetriveByNickname(String nickname) throws SQLException, UnsupportedEncodingException {
        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM Utente WHERE nickname=?");
        ps.setString(1, nickname);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String email = rs.getString("email");
            return new Videogiocatore(
                    rs.getInt("età"),
                    rs.getString("email"),
                    rs.getString("nickname"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("pass"),
                    new IndirizzoDAO(con).doRetriveByUser(email),
                    new TelefonoDAO(con).doRetriveByUser(email),
                    new PagamentoDAO(con).doRetrieveByUser(email),
                    new OrdineDAO(con).doRetrieveByUser(email),
                    new CarrelloDAO(con).doRetrieveByUser(email),
                    rs.getString("immagine")
            );
        }

        return null;
    }

    public boolean doInsertUser(Videogiocatore user) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("INSERT INTO Utente VALUES(?,?,?,null,null,null,null)");
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getNickname());
        ps.setString(3, user.getPassword());
        int i = ps.executeUpdate();
        return i == 1;
    }

    public ArrayList<Videogiocatore> doRetriveAll() throws SQLException, UnsupportedEncodingException {
        ArrayList<Videogiocatore> list = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Utente");

        while(rs.next()){
            list.add(new Videogiocatore(rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("email"),
                    rs.getString("pass"),
                    rs.getInt("età"),
                    rs.getString("nickname")));
        }
        return list;
    }

    public boolean doRemoveByEmail(String email) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("DELETE FROM Utente WHERE email=?");
        ps.setString(1,email);
        int i = ps.executeUpdate();
        return i == 1;
    }
}


