package model.dao;

import model.beans.Utente;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO {
    private Connection con;

    public UtenteDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public UtenteDAO(Connection con) {
        this.con = con;
    }

    public Utente doRetriveByEmail(String email) throws SQLException, UnsupportedEncodingException {
        PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Utente WHERE email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
                return new Utente(
                rs.getInt("eta"),
                rs.getString("email"),
                rs.getString("nickname"),
                rs.getString("pass"),
                rs.getString("nome"),
                rs.getString("cognome"),
                new IndirizzoDAO(con).doRetriveByUser(email),
                new TelefonoDAO(con).doRetriveByUser(email),
                new PagamentoDAO(con).doRetrieveByUser(email),
                new OrdineDAO(con).doRetrieveByUser(email),
                new CarrelloDAO(con).doRetrieveByUser(email)
                );
        }

        return null;
    }

}


