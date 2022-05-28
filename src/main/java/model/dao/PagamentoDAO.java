package model.dao;

import model.beans.Pagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PagamentoDAO {

    public Pagamento doRetrieveByUser(String user) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT numeroCarta, dataScadenza FROM pagamento WHERE Utente=?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Pagamento p = new Pagamento();
                p.setNumeroCarta(rs.getString(1));
                p.setDataScadenza(rs.getDate(2));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
