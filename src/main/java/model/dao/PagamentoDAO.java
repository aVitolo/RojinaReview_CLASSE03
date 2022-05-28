package model.dao;

import model.beans.Pagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PagamentoDAO {

    public ArrayList<Pagamento> doRetrieveByUser(String user) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT nome, cognome, numeroCarta, dataScadenza FROM pagamento WHERE utente=?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            ArrayList<Pagamento> pagamenti = new ArrayList<>();
            while(rs.next()) {
                Pagamento p = new Pagamento();
                p.setNome(rs.getString(1));
                p.setCognome(rs.getString(2));
                p.setNumeroCarta(rs.getString(3));
                p.setDataScadenza(rs.getDate(4));
                pagamenti.add(p);
            }
            return pagamenti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
