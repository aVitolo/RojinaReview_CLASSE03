package model.dao;

import model.beans.Utente;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteDAO {
    Connection con;

    public UtenteDAO() throws SQLException {
        this.con = ConPool.getConnection();
    }

    public Utente doRetriveByEmail(String email) throws SQLException, UnsupportedEncodingException {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Utente WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Utente(
                rs.getInt(6),
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                new IndirizzoDAO().doRetriveByUser(email),
                new TelefonoDAO().doRetriveByUser(email),
                new PagamentoDAO().doRetrieveByUser(email),
                new OrdineDAO().doRetrieveByUser(email),
                new CarrelloDAO().doRetrieveByUser(email)
                );
            }
            return null;
    }
}


