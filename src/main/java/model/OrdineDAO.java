package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdineDAO {

    public ArrayList<Ordine> doRetrieveByUser(String user) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT Stato, Data, Numero FROM ordine WHERE Utente=?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            ArrayList<Ordine> ordini = new ArrayList<>();
            CarrelloDAO carelloDAO = new CarrelloDAO();
            while(rs.next()) {
                Ordine o = new Ordine();
                o.setState(rs.getString(1));
                o.setData(rs.getDate(2));
                o.setId(rs.getString(3));
                o.setProducts(carelloDAO.getProductByOrder(o.getId()));
            }
            return ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
