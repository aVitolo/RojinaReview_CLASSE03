package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetodoPagementoDAO {

    public MetodoPagemento doRetrieveByUser(String user) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT Data, Numero FROM pagamento WHERE Utente=?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                MetodoPagemento p = new MetodoPagemento();
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
