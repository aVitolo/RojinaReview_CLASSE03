package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarrelloDAO {

    public Carrello doRetrieveByUser(String user) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT totale FROM carrello WHERE utente=?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            Carrello c = new Carrello();
            c.setTotale(rs.getFloat(1));
            ps = con.prepareStatement("SELECT prodotto FROM prodotto_carrello pc join prodotto p on pc.prodotto = p.id WHERE utente=?");
            ProdottoDAO prodottoDAO = new ProdottoDAO();
            while(rs.next())
                c.getCarrello().add(new ProdottoDAO().doRetriveById(rs.getInt(1)));
            return c;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
